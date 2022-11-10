package tweet;

import java.util.UUID;

import visitor.Node;
import visitor.NodeVisitor;

public class Tweet implements Node {
    
    private static int nextCreated = 1;
    private static int tweetTotal = 0;
    private static int numPositive = 0;
    private static double positivePercentage = 0;

    private int orderCreated;
    private String id;
    private String name;
    private String message;

    public Tweet(String name, String message) {
        orderCreated = nextCreated++;
        id = UUID.randomUUID().toString();
        this.name = name;
        this.message = message;
    }

    public static int getTweetTotal() {
        return tweetTotal;
    }

    public static double getPositivePercentage() {
        return positivePercentage;
    }

    public static void updatePositivePercent(boolean isPositive) {
        if(isPositive) {
            numPositive++;
        }
        
        positivePercentage = numPositive / tweetTotal;
    }

    public void incrementTweetTotal() {
        tweetTotal++;
    }

    public int getOrderCreated() {
        return orderCreated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
        visitor.visit(message);
    }

}
