package user;

import java.util.List;

import javax.swing.JPanel;

import observable.Observer;
import tweet.PostedTweets;

public class User extends UserComponent implements Observer {
    
    private List<String> followers;
    private List<String> followings;

    // Each User has an object holding the Tweets that they themselves have posted.
    // This object is a Subject for other Users (Observers) to subscribe to
    private PostedTweets tweetsPosted;
    // private List<Tweet> feed;

    public User(String id) {
        super(id);
        tweetsPosted = new PostedTweets();
    }

    public void display(JPanel displayPanel) {
        // Dislay ID for current User
        displayPanel.add(getLabel("User - " + getId()));
    }

    @Override
    public void update() {
        System.out.println(getId() + " has been updated");
    }

}
