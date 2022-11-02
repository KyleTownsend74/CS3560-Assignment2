package user;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class User extends UserComponent {
    
    private List<String> followers;
    private List<String> followings;
    // private List<Tweet> feed;

    public User(String id) {
        super(id);
    }

    public void display(JPanel displayPanel) {
        // Dislay ID for current User
        JLabel label = new JLabel();
        label.setText("User - " + getId());
        displayPanel.add(label);
    }

}
