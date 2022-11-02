import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.UserGroup;

public class AdminControl extends JFrame {

    private UserGroup root;
    private JPanel treePanel;

    public AdminControl () {
        root = new UserGroup("Root");

        // Set up frame
        this.setTitle("Twitter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(null);
        
        // Create tree view label
        JLabel treeLabel = new JLabel();
        treeLabel.setText("Tree View");

        // Set up tree view panel
        treePanel = new JPanel();
        treePanel.setBackground(new Color(204, 230, 255));
        treePanel.setBounds(0, 0, 250, 500);
        treePanel.add(treeLabel);
        this.add(treePanel);
        drawTree();
    }

    public void drawTree() {
        root.display(treePanel);
    }

}
