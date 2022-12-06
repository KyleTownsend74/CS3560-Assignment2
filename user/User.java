package user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import observable.Observer;
import tweet.PostedTweets;
import tweet.Tweet;
import ui.UserView;
import visitor.NodeVisitor;

public class User extends UserComponent implements Observer {
    
    private static List<User> allUsers = new ArrayList<>();
    private static int userTotal = 0;

    private UserView curUserView;
    private long lastUpdateTime;
    private List<User> followers;
    private List<User> followings;

    // Each User has an object holding the Tweets that they themselves have posted.
    // This object is a Subject for other Users (Observers) to subscribe to
    private PostedTweets tweetsPosted;
    // Feed is a map from Tweet ID to the Tweet itself
    // Need ID easily accessible to keep track of which Tweets are in feed
    private Map<String, Tweet> feed;

    public User(String id) {
        super(id);
        lastUpdateTime = getCreationTime();
        allUsers.add(this);
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        tweetsPosted = new PostedTweets();
        feed = new HashMap<>();
        follow(this); // User follows itself to see own tweets
    }

    public static User getUserById(String id) {
        User foundUser = null;

        for(User user : allUsers) {
            if(foundUser == null && user.getId().equals(id)) {
                foundUser = user;
            }
        }

        return foundUser;
    }

    public static User getLastUpdatedUser() {
        User lastUpdatedUser = null;
        long curLastUpdateTime = 0;

        for(User user : allUsers) {
            long curUserUpdateTime = user.getLastUpdateTime();

            if(curUserUpdateTime > curLastUpdateTime) {
                curLastUpdateTime = curUserUpdateTime;
                lastUpdatedUser = user;
            }
        }
        
        return lastUpdatedUser;
    }

    public static int getUserTotal() {
        return userTotal;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public void incrementUserTotal() {
        userTotal++;
    }

    public Collection<Tweet> getOrderedFeedMessages() {
        List<Tweet> orderedFeed = new ArrayList<>(feed.values());
        orderedFeed.sort(Comparator.comparing(Tweet::getOrderCreated));
        return orderedFeed;
    }

    public void display(JPanel displayPanel) {
        // Dislay ID for current User
        displayPanel.add(getLabel("\uD83D\uDC64 " + getId()));
    }

    public void post(Tweet tweet) {
        tweetsPosted.post(tweet);
    }

    public void follow(User user) {
        user.tweetsPosted.attachObserver(this);
        followings.add(user);
        user.followers.add(this);

        if(curUserView != null) {
            curUserView.drawFollowing(followings);
        }
    }

    // Bind the instance of the UserView to the User
    public void bindUserView(UserView feedView) {
        curUserView = feedView;
    }

    public void unbindUserView() {
        curUserView = null;
    }

    @Override
    public void update() {
        for(User followedUser : followings) {
            List<Tweet> tweetsByUser = followedUser.tweetsPosted.getTweets();

            for(Tweet tweet : tweetsByUser) {
                String curTweetId = tweet.getId();

                if(!feed.containsKey(curTweetId)) {
                    feed.put(curTweetId, tweet);
                }
            }
        }

        lastUpdateTime = System.currentTimeMillis();
        curUserView.drawInfo();
        curUserView.drawFeed(getOrderedFeedMessages());
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
