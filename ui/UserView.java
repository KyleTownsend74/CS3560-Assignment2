package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tweet.Tweet;
import user.User;
import visitor.AnalysisVisitor;
import visitor.NodeVisitor;

public class UserView extends JFrame {
    
    private User user;
    private JPanel feedPanel;
    private JTextField textFollow;
    private JTextField textTweet;
    private JButton btnFollow;
    private JButton btnTweet;

    // Action listeners for each button in the user view
    private ActionListener actFollow = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            User userToFollow = User.getUserById(textFollow.getText());
            
            if(userToFollow != null) {
                user.follow(userToFollow);
            }
            else {
                System.out.println("Invalid User ID");
            }
        }
    };
    private ActionListener actTweet = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Tweet tweet = new Tweet(user.getId(), textTweet.getText());
            user.post(tweet);
            NodeVisitor analysisVisitor = new AnalysisVisitor();
            tweet.accept(analysisVisitor);
        }
    };

    // Window listener for when this view is closed
    private WindowListener windowClose = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            user.unbindFeedView();
        }
    };

    public UserView(User user) {
        this.user = user;

        Color backgroundColor = new Color(220, 240, 255);

        // Set up frame
        this.setTitle("User View - " + user.getId());
        this.setSize(500, 500);
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(backgroundColor);
        this.addWindowListener(windowClose);

        // Set up follow control panel
        JPanel followControlPanel = new JPanel();
        followControlPanel.setBackground(backgroundColor);
        followControlPanel.setLayout(new GridLayout(1, 2));
        textFollow = new JTextField(16);
        followControlPanel.add(textFollow);
        btnFollow = new JButton("Follow User");
        btnFollow.addActionListener(actFollow);
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
        btnTweet.addActionListener(actTweet);
        tweetPanel.add(btnTweet);
        this.add(tweetPanel);

        // Set up feed panel
        feedPanel = new JPanel();
        feedPanel.setBackground(backgroundColor);
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.PAGE_AXIS));
        drawFeed(user.getOrderedFeedMessages());
        this.add(feedPanel);
        user.bindFeedView(this);

        this.setVisible(true);

    }

    public void drawFeed(Collection<Tweet> feed) {
        feedPanel.removeAll();
        JLabel feedLabel = new JLabel("News Feed");
        feedPanel.add(feedLabel);
        for(Tweet curTweet : feed) {
            feedPanel.add(new JLabel(curTweet.getName() + " : " + curTweet.getMessage()));
        }
        feedPanel.revalidate();
        feedPanel.repaint();
    }

}
