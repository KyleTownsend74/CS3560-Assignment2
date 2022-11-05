package user;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.AdminControl;

public abstract class UserComponent {
    
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static final Color SELECTED = new Color(160, 180, 255);

    private String id;
    private JLabel label;

    public UserComponent(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    protected JLabel getLabel(String labelText) {
        if(label == null) {
            label = new JLabel();
            label.setText(labelText);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    UserComponent curUserSelected = AdminControl.curUserSelected;
                    
                    if(curUserSelected != null) {
                        JLabel curSelectedLabel = curUserSelected.getLabel(null);
                        curSelectedLabel.setOpaque(false);
                        curSelectedLabel.setBackground(TRANSPARENT);
                    }

                    label.setOpaque(true);
                    label.setBackground(SELECTED);
                    AdminControl.curUserSelected = UserComponent.this;
                }
            });
        }

        return label;
    }

    public abstract void display(JPanel displayPanel);

}
