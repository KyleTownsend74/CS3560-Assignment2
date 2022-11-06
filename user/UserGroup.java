package user;

import java.util.ArrayList;
import java.util.List;

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
        displayPanel.add(getLabel("UserGroup - " + getId()));
        spacing += 2;

        // Display all UserComponents in this group
        for(UserComponent userComponent : userComponents) {
            userComponent.display(displayPanel);
        }

        spacing -= 2;
    }

}
