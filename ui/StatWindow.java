package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StatWindow extends JFrame {
    
    public StatWindow(String message) {
        Color backgroundColor = new Color(220, 240, 255);

        // Set up frame
        this.setTitle("Twitter Stat");
        this.setSize(250, 100);
        this.getContentPane().setBackground(backgroundColor);
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalTextPosition(JLabel.CENTER);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(messageLabel);
        this.setVisible(true);
    }

}
