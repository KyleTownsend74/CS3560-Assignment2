package user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserGroup extends UserComponent {
    
    private List<UserComponent> userComponents;

    public UserGroup(String id) {
        super(id);
        
        userComponents = new ArrayList<>();
    }

    public void add(UserComponent userComponent) {
        userComponents.add(userComponent);
    }

    public void display(JPanel displayPanel) {
        // Dislay ID for current UserGroup
        JLabel label = new JLabel();
        label.setText("UserGroup - " + getId());
        displayPanel.add(label);

        // Display all UserComponents in this group
        for(UserComponent userComponent : userComponents) {
            userComponent.display(displayPanel);
        }
    }

}