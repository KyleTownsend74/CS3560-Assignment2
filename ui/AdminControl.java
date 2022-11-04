package ui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.User;
import user.UserGroup;

public class AdminControl extends JFrame {

    public static JLabel curUserSelected;

    private UserGroup root;
    private JPanel treePanel;
    private JTextField textUser;
    private JTextField textGroup;
    private JButton btnAddUser;
    private JButton btnAddGroup;
    private JButton btnOpenUserView;
    private JButton btnShowUserTotal;
    private JButton btnShowGroupTotal;
    private JButton btnShowMessagesTotal;
    private JButton btnShowPosPercent;

    // Action listeners for each button on the admin control
    private ActionListener actAddUser = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            root.add(new User(textUser.getText()));
            drawTree();
        }
    };
    private ActionListener actAddGroup = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            root.add(new UserGroup(textGroup.getText()));
            drawTree();
        }
    };
    private ActionListener actOpenUserView = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Open User View");
        }
    };
    private ActionListener actShowUserTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show User Total");
        }
    };
    private ActionListener actShowGroupTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show Group Total");
        }
    };
    private ActionListener actShowMessagesTotal = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show Messages Total");
        }
    };
    private ActionListener actShowPosPercent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Show Positive Percentage");
        }
    };

    public AdminControl () {
        root = new UserGroup("Root");

        // Set up frame
        this.setTitle("Twitter");
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
        uvOpenPanel.setLayout(new GridLayout(2, 1));
        btnOpenUserView = new JButton("Open User View");
        btnOpenUserView.addActionListener(actOpenUserView);
        uvOpenPanel.add(btnOpenUserView);
        controlPanel.add(uvOpenPanel);

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

    public void drawTree() {
        treePanel.removeAll();
        JLabel treeLabel = new JLabel("Tree View");
        treePanel.add(treeLabel);
        root.display(treePanel);
        treePanel.revalidate();
        treePanel.repaint();
    }

}
