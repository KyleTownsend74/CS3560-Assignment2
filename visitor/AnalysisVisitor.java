package visitor;

import tweet.Tweet;
import user.User;
import user.UserGroup;

public class AnalysisVisitor implements NodeVisitor {

    @Override
    public void visit(User user) {
        user.incrementUserTotal();
    }

    @Override
    public void visit(UserGroup userGroup) {
        userGroup.incrementGroupTotal();
    }

    @Override
    public void visit(Tweet tweet) {
        tweet.incrementTweetTotal();
    }

    @Override
    public void visit(String message) {
        message = message.toLowerCase(); // Only need to search for lowercase words

        if(message.contains("good")
            || message.contains("great")
            || message.contains("excellent")
            || message.contains("fantastic")
            || message.contains("awesome")
            || message.contains("yay")
            || message.contains("hooray")
            || message.contains("joy"))
        {
            Tweet.updatePositivePercent(true);
        }
        else {
            Tweet.updatePositivePercent(false);
        }
    }

}
