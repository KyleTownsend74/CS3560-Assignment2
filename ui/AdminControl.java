package ui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tweet.Tweet;
import user.User;
import user.UserComponent;
import user.UserGroup;
import visitor.AnalysisVisitor;
import visitor.NodeVisitor;

public class AdminControl extends JFrame {

    private static AdminControl instance;

    public static UserComponent curUserSelected;

    private UserGroup root;
    private JPanel treePanel;
    private JTextField textUser;
    private JTextField textGroup;
    private JButton btnAddUser;
    private JButton btnAddGroup;
    private JButton btnOpenUserView;
    private JButton btnValidateIds;
    private JButton btnLastUpdatedUser;
    private JButton btnShowUserTotal;
    private JButton btnShowGroupTotal;
    private JButton btnShowMessagesTotal;
    private JButton btnShowPosPercent;

    // Action listeners for each button on the admin control
    private ActionListener actAddUser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addToGroup(new User(textUser.getText()));
            textUser.setText("");
            drawTree();
        }
    };
    private ActionListener actAddGroup = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addToGroup(new UserGroup(textGroup.getText()));
            textGroup.setText("");
            drawTree();
        }
    };
    private ActionListener actOpenUserView = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(curUserSelected instanceof User) {
                new UserView((User) curUserSelected);
            }
        }
    };
    private ActionListener actValidateIds = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message;
            boolean isValid = UserComponent.validateIds();

            if(isValid) {
                message = "All IDs are valid!";
            }
            else {
                message = "Not all IDs are valid";
            }

            JOptionPane.showMessageDialog(AdminControl.this, message);
        }
    };
    private ActionListener actLastUpdatedUser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message;
            User lastUpdatedUser = User.getLastUpdatedUser();

            if(lastUpdatedUser != null) {
                message = "Last Updated User: " + User.getLastUpdatedUser().getId();
            }
            else {
                message = "No Users Exist";
            }

            JOptionPane.showMessageDialog(AdminControl.this, message);
        }
    };
    private ActionListener actShowUserTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StatWindow("User Total: " + User.getUserTotal());
        }
    };
    private ActionListener actShowGroupTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StatWindow("Group Total: " + UserGroup.getGroupTotal());
        }
    };
    private ActionListener actShowMessagesTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StatWindow("Messages Total: " + Tweet.getTweetTotal());
        }
    };
    private ActionListener actShowPosPercent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StatWindow("Positive Percentage: " + Tweet.getPositivePercentage() + "%");
        }
    };

    private AdminControl () {
        root = new UserGroup("Root");

        ImageIcon icon = new ImageIcon("assets/twitter-logo.jpg");

        // Set up frame
        this.setTitle("Twitter");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new GridLayout(1, 2));

        // Set up tree view panel
        treePanel = new JPanel();
        treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
        Color treePanelColor = new Color(204, 230, 255);
        treePanel.setBackground(treePanelColor);
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
        textUser = new JTextField(16);
        addPanel.add(textUser);
        btnAddUser = new JButton("Add User");
        btnAddUser.addActionListener(actAddUser);
        addPanel.add(btnAddUser);
        textGroup = new JTextField(16);
        addPanel.add(textGroup);
        btnAddGroup = new JButton("Add Group");
        btnAddGroup.addActionListener(actAddGroup);
        addPanel.add(btnAddGroup);
        controlPanel.add(addPanel);

        // Set up UV (User View) open view panel
        JPanel uvOpenPanel = new JPanel();
        uvOpenPanel.setBackground(controlPanelColor);
        uvOpenPanel.setLayout(new GridLayout(3, 1));
        btnOpenUserView = new JButton("Open User View");
        btnOpenUserView.addActionListener(actOpenUserView);
        uvOpenPanel.add(btnOpenUserView);
        btnValidateIds = new JButton("Validate IDs");
        btnValidateIds.addActionListener(actValidateIds);
        uvOpenPanel.add(btnValidateIds);
        btnLastUpdatedUser = new JButton("Show Last Updated User");
        btnLastUpdatedUser.addActionListener(actLastUpdatedUser);
        uvOpenPanel.add(btnLastUpdatedUser);
        controlPanel.add(uvOpenPanel);
        disableUserView();

        // Set up stats view panel
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(controlPanelColor);
        statsPanel.setLayout(new GridLayout(2, 2, 4, 4));
        btnShowUserTotal = new JButton("Show User Total");
        btnShowUserTotal.addActionListener(actShowUserTotal);
        statsPanel.add(btnShowUserTotal);
        btnShowGroupTotal = new JButton("Show Group Total");
        btnShowGroupTotal.addActionListener(actShowGroupTotal);
        statsPanel.add(btnShowGroupTotal);
        btnShowMessagesTotal = new JButton("Show Messages Total");
        btnShowMessagesTotal.addActionListener(actShowMessagesTotal);
        statsPanel.add(btnShowMessagesTotal);
        btnShowPosPercent = new JButton("Show Positive Percentage");
        btnShowPosPercent.addActionListener(actShowPosPercent);
        statsPanel.add(btnShowPosPercent);
        controlPanel.add(statsPanel);

        this.add(controlPanel);
        this.setVisible(true);
    }

    public static AdminControl getInstance() {
        if(instance == null) {
            instance = new AdminControl();
        }

        return instance;
    }

    public void enableUserView() {
        btnOpenUserView.setEnabled(true);
    }

    public void disableUserView() {
        btnOpenUserView.setEnabled(false);
    }

    // Add a UserComponent to a UserGroup
    private void addToGroup(UserComponent componentToAdd) {
        // If a UserGroup is selected, add the UserComponent to the currently
        // selected group. Otherwise, add it to the root group
        if(curUserSelected instanceof UserGroup) {
            ((UserGroup) curUserSelected).add(componentToAdd);
        }
        else {
            root.add(componentToAdd);
        }

        NodeVisitor analysisVisitor = new AnalysisVisitor();
        componentToAdd.accept(analysisVisitor);
    }

    public void drawTree() {
        treePanel.removeAll();
        JLabel treeLabel = new JLabel("Tree View");
        treePanel.add(treeLabel);
        root.display(treePanel);
        treePanel.revalidate();
        treePanel.repaint();
    }

}
