package user;

import javax.swing.JPanel;

public abstract class UserComponent {
    
    private String id;

    public UserComponent(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void display(JPanel displayPanel);

}
