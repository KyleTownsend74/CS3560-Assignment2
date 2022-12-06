package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import tweet.Tweet;
import user.User;
import visitor.AnalysisVisitor;
import visitor.NodeVisitor;

public class UserView extends JFrame {
    
    private User user;
    private JPanel infoPanel;
    private JPanel followDisplayPanel;
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
                textFollow.setText("");
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
            textTweet.setText("");
            user.post(tweet);
            NodeVisitor analysisVisitor = new AnalysisVisitor();
            tweet.accept(analysisVisitor);
        }
    };

    // Document listener for when text fields are empty or populated
    private DocumentListener textChange = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            textChanged();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            textChanged();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            textChanged();
        }
        
    };

    // Window listener for when this view is closed
    private WindowListener windowClose = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            user.unbindUserView();
        }
    };

    public UserView(User user) {
        this.user = user;
        user.bindUserView(this);

        ImageIcon icon = new ImageIcon("assets/twitter-logo.jpg");
        Color backgroundColor = new Color(220, 240, 255);

        // Set up frame
        this.setTitle("User View - " + user.getId());
        this.setIconImage(icon.getImage());
        this.setSize(500, 500);
        this.setLayout(new GridLayout(0, 1));
        this.setBackground(backgroundColor);
        this.addWindowListener(windowClose);

        // Set up info panel
        infoPanel = new JPanel();
        infoPanel.setBackground(backgroundColor);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        drawInfo();
        this.add(infoPanel);

        // Set up follow control panel
        JPanel followControlPanel = new JPanel();
        followControlPanel.setBackground(backgroundColor);
        followControlPanel.setLayout(new GridLayout(1, 2));
        textFollow = new JTextField(16);
        textFollow.getDocument().addDocumentListener(textChange);
        followControlPanel.add(textFollow);
        btnFollow = new JButton("Follow User");
        btnFollow.addActionListener(actFollow);
        btnFollow.setEnabled(false);
        followControlPanel.add(btnFollow);
        this.add(followControlPanel);

        // Set up follow display panel
        followDisplayPanel = new JPanel();
        followDisplayPanel.setBackground(backgroundColor);
        followDisplayPanel.setLayout(new BoxLayout(followDisplayPanel, BoxLayout.PAGE_AXIS));
        drawFollowing(user.getFollowings());
        this.add(followDisplayPanel);

        // Set up tweet panel
        JPanel tweetPanel = new JPanel();
        tweetPanel.setBackground(backgroundColor);
        tweetPanel.setLayout(new GridLayout(1, 2));
        textTweet = new JTextField(16);
        textTweet.getDocument().addDocumentListener(textChange);
        tweetPanel.add(textTweet);
        btnTweet = new JButton("Post Tweet");
        btnTweet.addActionListener(actTweet);
        btnTweet.setEnabled(false);
        tweetPanel.add(btnTweet);
        this.add(tweetPanel);

        // Set up feed panel
        feedPanel = new JPanel();
        feedPanel.setBackground(backgroundColor);
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.PAGE_AXIS));
        drawFeed(user.getOrderedFeedMessages());
        this.add(feedPanel);

        this.setVisible(true);
    }

    private void textChanged() {
        if(textFollow.getText().isEmpty()) {
            btnFollow.setEnabled(false);
        }
        else {
            btnFollow.setEnabled(true);
        }

        if(textTweet.getText().isEmpty()) {
            btnTweet.setEnabled(false);
        }
        else {
            btnTweet.setEnabled(true);
        }
    }

    public void drawInfo() {
        infoPanel.removeAll();
        JLabel creationTimeLabel = new JLabel("User created at: " + user.getCreationTime());
        JLabel updateTimeLabel = new JLabel("User last updated at: " + user.getLastUpdateTime());
        infoPanel.add(creationTimeLabel);
        infoPanel.add(updateTimeLabel);
        infoPanel.revalidate();
        infoPanel.repaint();
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

    public void drawFollowing(List<User> followings) {
        followDisplayPanel.removeAll();
        JLabel followLabel = new JLabel("Current Following");
        followDisplayPanel.add(followLabel);
        for(User following : followings) {
            String followingId = following.getId();

            if(!followingId.equals(user.getId())) {
                followDisplayPanel.add(new JLabel(" - " + following.getId()));
            }
        }
        followDisplayPanel.revalidate();
        followDisplayPanel.repaint();
    }

}
