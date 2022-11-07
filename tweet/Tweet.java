package tweet;

public class Tweet {
    
    private String name;
    private String message;

    public Tweet(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
