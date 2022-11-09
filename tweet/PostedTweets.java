package tweet;

import java.util.ArrayList;
import java.util.List;

import observable.Subject;

// The Subject for User Observers to subscribe to
public class PostedTweets extends Subject {
    
    List<Tweet> tweets;

    public PostedTweets() {
        tweets = new ArrayList<>();
    }

    public void post(Tweet tweet) {
        tweets.add(tweet);
        notifyObservers();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

}
