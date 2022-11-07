package user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import observable.Observer;
import tweet.PostedTweets;
import tweet.Tweet;
import ui.UserView;

public class User extends UserComponent implements Observer {
    
    private static List<User> allUsers = new ArrayList<>();

    private List<User> followers;
    private List<User> followings;

    // Each User has an object holding the Tweets that they themselves have posted.
    // This object is a Subject for other Users (Observers) to subscribe to
    private PostedTweets tweetsPosted;
    // private List<Tweet> feed;

    public User(String id) {
        super(id);
        allUsers.add(this);
        followings = new ArrayList<>();
        tweetsPosted = new PostedTweets();
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

    // Bind the user view to observe all of the tweets from the users
    // this user is following
    public void bindUserView(UserView userView) {
        for(User followedUser : followings) {
            followedUser.tweetsPosted.attachObserver(userView);
        }
    }

    public void unbindUserView(UserView userView) {
        for(User followedUser : followings) {
            followedUser.tweetsPosted.detachObserver(userView);
        }
    }

    public void rebindUserView(UserView userView) {
        unbindUserView(userView);
        bindUserView(userView);
    }

    @Override
    public void update() {
        System.out.println(getId() + " has been updated");
    }

}
