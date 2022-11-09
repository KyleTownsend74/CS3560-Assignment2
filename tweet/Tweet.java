package tweet;

import java.util.UUID;

public class Tweet {
    
    private String id;
    private String name;
    private String message;

    public Tweet(String name, String message) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.message = message;
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
