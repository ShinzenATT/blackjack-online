package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import Model.ImageDisplayModel;

import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private JFrame mainFrame;
    private final JPanel mainPanel;
    private final JButton playButton;
    private final JButton hitButton;
    private final JButton stayButton;
    private final JLabel drawnLabel;
    private final JLabel playerName;
    private final JLabel chipText;
    private final JLabel handPoints;

    private final JPanel dealerCardsPanel;

    private final JPanel playerCardsPanel;

    private final JLabel playerCardImageLabel;
    
	private ArrayList<JLabel> dealerLabels;
	private ArrayList<JLabel> userLabels;
    
    ImageDisplayModel allImages = new ImageDisplayModel();

    public GameWindow() {


        mainFrame = new JFrame();
        mainPanel = new JPanel();
        playButton = new JButton();
        hitButton = new JButton();
        stayButton = new JButton();
        playerName = new JLabel();
        chipText = new JLabel();
        handPoints = new JLabel();

        playerCardImageLabel = new JLabel();
        dealerLabels = new ArrayList<>();
        userLabels = new ArrayList<>();

        dealerCardsPanel = new JPanel();
        dealerCardsPanel.setSize(new java.awt.Dimension(700,200));
        dealerCardsPanel.setBackground(new java.awt.Color(24, 40, 24));

        playerCardsPanel = new JPanel();
        playerCardsPanel.setSize(new java.awt.Dimension(700,200));
        playerCardsPanel.setBackground(new java.awt.Color(24, 40, 24));

        //JFrame setup
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().add(mainPanel);

        //JPanel setup
        mainPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainPanel.setSize(new java.awt.Dimension(700, 500));
        mainPanel.setLayout(new BorderLayout());

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

        //ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName("queen_of_spades", 100, 145));
        //playerCardImageLabel.setIcon(imageIcon);
        // playing card original 500x726
        //playerCardsPanel.add(playerCardImageLabel);
        //addUserCard("queen_of_spades");
        //addUserCard("king_of_hearts");

        mainPanel.add(dealerCardsPanel, BorderLayout.NORTH);
        mainPanel.add(playerCardsPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);

    }

    public JLabel getDrawnCardLabel() {
        return drawnLabel;
    }

    public void setPlayerName(String name){ playerName.setText(name); }

    public void setHandPoints(int points){ handPoints.setText(Integer.toString(points)); }

    public void setPlayerChips(int chips) { chipText.setText(Integer.toString(chips)); }

    public void setupUserCard(ArrayList<String> cardNames) {
        for(int i = 0; i < cardNames.size(); i++){
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(allImages.getScaledImageInstanceFromName(cardNames.get(i), 100, 145)));
            userLabels.add(label);
        }

        updateUserLabelsPanel();
        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();
    }

    public void addUserCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        userLabels.add(label);
        updateUserLabelsPanel();
    }

    public void updateUserLabelsPanel(){
        for (JLabel label: userLabels){
            playerCardsPanel.add(label);
        }
    }

    // kan nog kombineras med setupUserCard, det ända som skiljer är dealerLabels..
    public void setupDealerCard(ArrayList<String> cardNames) {
        for(int i = 0; i < cardNames.size(); i++){
            JLabel label = new JLabel();
            if(i == 0){
                label.setIcon(new ImageIcon(allImages.getScaledImageInstanceFromName("back", 100, 145)));
            } else{
                label.setIcon(new ImageIcon(allImages.getScaledImageInstanceFromName(cardNames.get(i), 100, 145)));
            }
            dealerLabels.add(label);
        }

        updateDealerLabelsPanel();
        dealerCardsPanel.revalidate();
        dealerCardsPanel.repaint();
    }

    public void addDealerCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        dealerLabels.add(label);
        updateDealerLabelsPanel();
    }

    public void updateDealerLabelsPanel(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.add(label);
        }
    }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }
}

