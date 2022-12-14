package user;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import visitor.NodeVisitor;

public class UserGroup extends UserComponent {
    
    private static int groupTotal = 1; // Initialized to 1 to account for root group

    private List<UserComponent> userComponents;

    public UserGroup(String id) {
        super(id);
        
        userComponents = new ArrayList<>();
    }

    public static int getGroupTotal() {
        return groupTotal;
    }

    public void add(UserComponent userComponent) {
        userComponents.add(userComponent);
    }

    public void display(JPanel displayPanel) {
        // Dislay ID for current UserGroup
        displayPanel.add(getLabel("\uD83D\uDCC1 " + getId()));
        spacing += 2;

        // Display all UserComponents in this group
        for(UserComponent userComponent : userComponents) {
            userComponent.display(displayPanel);
        }

        spacing -= 2;
    }

    public void incrementGroupTotal() {
        groupTotal++;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

}
