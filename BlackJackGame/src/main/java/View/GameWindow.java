package main.java.View;

import javax.swing.*;

import main.java.Model.ModelTest;

import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    private final ModelTest bjModel;

    private final JPanel mainPanel;
    private final JButton playButton;
    private final JButton hitButton;
    private final JButton stayButton;
    private final JLabel drawnLabel;

    public GameWindow(ModelTest bjmodel) {
        this.bjModel = bjmodel;


        mainPanel = new JPanel();
        playButton = new JButton();
        hitButton = new JButton();
        stayButton = new JButton();

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

        drawnLabel = new JLabel("Hello");
        mainPanel.add(drawnLabel);
        drawnLabel.setBounds(50, 200, 200, 50);
    }

    public JLabel getDrawnCardLabel() {
        return drawnLabel;
    }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }
}

