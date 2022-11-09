package tweet;

import java.util.UUID;

public class Tweet {
    
    private static int nextCreated = 1;

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

}
