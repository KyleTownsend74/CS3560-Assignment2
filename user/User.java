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

    private UserView curFeedView;
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
        allUsers.add(this);
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

    public static int getUserTotal() {
        return userTotal;
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
        displayPanel.add(getLabel("User - " + getId()));
    }

    public void post(Tweet tweet) {
        tweetsPosted.post(tweet);
    }

    public void follow(User user) {
        user.tweetsPosted.attachObserver(this);
        followings.add(user);
    }

    // Bind the instance of the UserView to the User
    public void bindFeedView(UserView feedView) {
        curFeedView = feedView;
    }

    public void unbindFeedView() {
        curFeedView = null;
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

        curFeedView.drawFeed(getOrderedFeedMessages());
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
