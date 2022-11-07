package user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import observable.Observer;
import tweet.PostedTweets;
import tweet.Tweet;

public class User extends UserComponent implements Observer {
    
    private static List<User> allUsers = new ArrayList<>();

    private List<String> followers;
    private List<String> followings;

    // Each User has an object holding the Tweets that they themselves have posted.
    // This object is a Subject for other Users (Observers) to subscribe to
    private PostedTweets tweetsPosted;
    // private List<Tweet> feed;

    public User(String id) {
        super(id);
        allUsers.add(this);
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
    }

    @Override
    public void update() {
        System.out.println(getId() + " has been updated");
    }

}
