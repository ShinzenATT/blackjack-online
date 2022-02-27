package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    private final JPanel mainPanel;
    private final JButton playButton;
    private final JButton hitButton;
    private final JButton stayButton;
    private final JLabel drawnLabel;
    private final JLabel playerName;
    private final JLabel chipText;
    private final JLabel handPoints;

    public GameWindow() {


        mainPanel = new JPanel();
        playButton = new JButton();
        hitButton = new JButton();
        stayButton = new JButton();
        playerName = new JLabel();
        chipText = new JLabel();
        handPoints = new JLabel();

        //JFrame setup
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setSize(800, 800);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().add(mainPanel);

        //JPanel setup
        mainPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainPanel.setSize(new java.awt.Dimension(700, 500));
        mainPanel.setLayout(null);

        //Hit button setup
        hitButton.setText("Hit");
        mainPanel.add(hitButton);
        hitButton.setBounds(200, 300, 100, 100);

        stayButton.setText("Stand");
        mainPanel.add(stayButton);
        stayButton.setBounds(300, 300, 100, 100);

        drawnLabel = new JLabel("Hello");
        mainPanel.add(drawnLabel);
        drawnLabel.setBounds(50, 200, 800, 50);

        playerName.setBounds(50, 250, 800, 50);
        mainPanel.add(playerName);

        handPoints.setBounds(50, 140, 800, 50);
        mainPanel.add(handPoints);

        chipText.setBounds(50, 300, 800, 50);
        mainPanel.add(chipText);
    }

    public JLabel getDrawnCardLabel() {
        return drawnLabel;
    }

    public void setPlayerName(String name){ playerName.setText(name); }

    public void setHandPoints(int points){ handPoints.setText(Integer.toString(points)); }

    public void setPlayerChips(int chips) { chipText.setText(Integer.toString(chips)); }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }
}

