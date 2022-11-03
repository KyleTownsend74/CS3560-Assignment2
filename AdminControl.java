import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        this.setLayout(new GridLayout(1, 2));

        // Set up tree view panel
        treePanel = new JPanel();
        Color treePanelColor = new Color(204, 230, 255);
        treePanel.setBackground(treePanelColor);
        JLabel treeLabel = new JLabel();
        treeLabel.setText("Tree View");
        treePanel.add(treeLabel);
        this.add(treePanel);
        drawTree();

        // Set up control view panel (panel for all controls on right side of UI)
        JPanel controlPanel = new JPanel();
        Color controlPanelColor = new Color(220, 240, 255);
        controlPanel.setBackground(controlPanelColor);
        controlPanel.setLayout(new GridLayout(0, 1, 0, 4));

        // Set up add view panel
        JPanel addPanel = new JPanel();
        addPanel.setBackground(controlPanelColor);
        addPanel.setLayout(new GridLayout(2, 2, 4, 4));
        JTextField userTextField = new JTextField(16);
        addPanel.add(userTextField);
        JButton btnAddUser = new JButton("Add User");
        addPanel.add(btnAddUser);
        JTextField groupTextField = new JTextField(16);
        addPanel.add(groupTextField);
        JButton btnAddGroup = new JButton("Add Group");
        addPanel.add(btnAddGroup);
        controlPanel.add(addPanel);

        // Set up UV (User View) open view panel
        JPanel uvOpenPanel = new JPanel();
        uvOpenPanel.setBackground(controlPanelColor);
        uvOpenPanel.setLayout(new GridLayout(2, 1));
        JButton btnOpenUserView = new JButton("Open User View");
        uvOpenPanel.add(btnOpenUserView);
        controlPanel.add(uvOpenPanel);

        // Set up stats view panel
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(controlPanelColor);
        statsPanel.setLayout(new GridLayout(2, 2, 4, 4));
        JButton btnShowUserTotal = new JButton("Show User Total");
        statsPanel.add(btnShowUserTotal);
        JButton btnShowGroupTotal = new JButton("Show Group Total");
        statsPanel.add(btnShowGroupTotal);
        JButton btnShowMessagesTotal = new JButton("Show Messages Total");
        statsPanel.add(btnShowMessagesTotal);
        JButton btnShowPosPercent = new JButton("Show Positive Percentage");
        statsPanel.add(btnShowPosPercent);
        controlPanel.add(statsPanel);

        this.add(controlPanel);
        this.setVisible(true);
    }

    public void drawTree() {
        root.display(treePanel);
    }

}
