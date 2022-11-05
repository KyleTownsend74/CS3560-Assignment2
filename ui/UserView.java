package ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserView extends JFrame {
    
    private JTextField textFollow;
    private JTextField textTweet;
    private JButton btnFollow;
    private JButton btnTweet;

    public UserView() {
        Color backgroundColor = new Color(220, 240, 255);

        // Set up frame
        this.setTitle("User View");
        this.setSize(500, 500);
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(backgroundColor);

        // Set up follow control panel
        JPanel followControlPanel = new JPanel();
        followControlPanel.setBackground(backgroundColor);
        followControlPanel.setLayout(new GridLayout(1, 2));
        textFollow = new JTextField(16);
        followControlPanel.add(textFollow);
        btnFollow = new JButton("Follow User");
        followControlPanel.add(btnFollow);
        this.add(followControlPanel);

        // Set up follow display panel
        JPanel followDisplayPanel = new JPanel();
        followDisplayPanel.setBackground(backgroundColor);
        followDisplayPanel.setLayout(new BoxLayout(followDisplayPanel, BoxLayout.PAGE_AXIS));
        JLabel followLabel = new JLabel("Current Following");
        followDisplayPanel.add(followLabel);
        this.add(followDisplayPanel);

        // Set up tweet panel
        JPanel tweetPanel = new JPanel();
        tweetPanel.setBackground(backgroundColor);
        tweetPanel.setLayout(new GridLayout(1, 2));
        textTweet = new JTextField(16);
        tweetPanel.add(textTweet);
        btnTweet = new JButton("Post Tweet");
        tweetPanel.add(btnTweet);
        this.add(tweetPanel);

        // Set up feed panel
        JPanel feedPanel = new JPanel();
        feedPanel.setBackground(backgroundColor);
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.PAGE_AXIS));
        JLabel feedLabel = new JLabel("News Feed");
        feedPanel.add(feedLabel);
        this.add(feedPanel);

        this.setVisible(true);

    }

}
