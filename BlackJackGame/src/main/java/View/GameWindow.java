package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Model.ButtonDisplayModel;
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
    private final JButton doubleDownButton;
    private final JButton startButton;
    private final JTextField betField;
    private final JLabel drawnLabel;
    private final JLabel playerName;
    private final JLabel chipText;
    private final JLabel handPoints;
    private final JLabel dealerNameLabel;
    private final JLabel playerNameLabel;
    private final JLabel playerHandValue;
    private final JLabel dealerHandValue;

    private final JPanel dealerCardsPanel;

    private final JPanel playerCardsPanel;
    private final JPanel dealerNamePanel;
    private final JPanel playerNamePanel;

    private final JLabel playerCardImageLabel;
    
	private ArrayList<JLabel> dealerLabels;
	private ArrayList<JLabel> userLabels;

    private final JPanel menuPanel;
    private final JTextField joinRoom;
    private final JTextField playerField;
    private final JButton createRoom;

    private final JPanel root;
    
    ImageDisplayModel allImages = new ImageDisplayModel();
    ButtonDisplayModel allButtonImages = new ButtonDisplayModel();

    public GameWindow() {


        mainFrame = new JFrame();
        mainPanel = new JPanel();
        playButton = new JButton();
        hitButton = new JButton();
        stayButton = new JButton();
        doubleDownButton = new JButton();
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

        dealerNamePanel = new JPanel();
        dealerNamePanel.setSize(new java.awt.Dimension(700,100));
        dealerNamePanel.setBackground(new java.awt.Color(24, 40, 24));

        playerNamePanel = new JPanel();
        playerNamePanel.setSize(new java.awt.Dimension(700,100));
        playerNamePanel.setBackground(new java.awt.Color(24, 40, 24));
        



        //JFrame setup
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        //JPanel setup
        mainPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainPanel.setSize(new java.awt.Dimension(700, 500));
        mainPanel.setLayout(new BorderLayout());

        //Hit button setup
        hitButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("hitButton")));
        hitButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("hitButtonRollover")));
        hitButton.setBounds(200, 300, 76, 49);
        hitButton.setOpaque(false);
        hitButton.setContentAreaFilled(false);
        hitButton.setBorderPainted(false);
        mainPanel.add(hitButton);

        //Stand button setup
        stayButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("standButton")));
        stayButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("standButtonRollover")));
        stayButton.setBounds(300, 300, 106, 49);
        stayButton.setOpaque(false);
        stayButton.setContentAreaFilled(false);
        stayButton.setBorderPainted(false);
        mainPanel.add(stayButton);

        //DoubleDown button setup
        doubleDownButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("doubleDownButton")));
        doubleDownButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("doubleDownButtonRollover")));
        doubleDownButton.setBounds(425, 300, 178, 49);
        doubleDownButton.setOpaque(false);
        doubleDownButton.setContentAreaFilled(false);
        doubleDownButton.setBorderPainted(false);
        mainPanel.add(doubleDownButton);

        startButton = new JButton("Start game");
        startButton.setBounds(610, 300, 150, 49);
        mainPanel.add(startButton);

        betField = new JTextField("bet chips here");
        betField.setBounds(770, 300, 100, 49);
        mainPanel.add(betField);

        drawnLabel = new JLabel("Hello");
        mainPanel.add(drawnLabel);
        drawnLabel.setBounds(50, 200, 800, 50);

        playerName.setBounds(50, 250, 800, 50);
        mainPanel.add(playerName);

        handPoints.setBounds(50, 140, 800, 50);
        mainPanel.add(handPoints);

        chipText.setBounds(50, 300, 800, 50);
        mainPanel.add(chipText);

        dealerNameLabel = new JLabel("Dealers Hand", SwingConstants.CENTER);
        dealerNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18));
        dealerNameLabel.setForeground(Color.WHITE);

        playerNameLabel = new JLabel("Players Hand", SwingConstants.CENTER);
        playerNameLabel.setFont(new java.awt.Font("Times New Roman", 1, 18));
        playerNameLabel.setForeground(Color.WHITE);

        playerHandValue = new JLabel("Hand Value: ");
        playerHandValue.setFont(new java.awt.Font("Times New Roman", 1, 18));
        playerHandValue.setForeground(Color.WHITE);

        dealerHandValue = new JLabel("Hand Value: ?");
        dealerHandValue.setFont(new java.awt.Font("Times New Roman", 1, 18));
        dealerHandValue.setForeground(Color.WHITE);

        dealerNamePanel.add(dealerNameLabel);
        dealerNamePanel.add(dealerHandValue);

        playerNamePanel.add(playerNameLabel);
        playerNamePanel.add(playerHandValue);

        dealerCardsPanel.add(dealerNamePanel);
        playerCardsPanel.add(playerNamePanel);
        mainPanel.add(dealerCardsPanel, BorderLayout.NORTH);
        mainPanel.add(playerCardsPanel, BorderLayout.SOUTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 0, 20));
        menuPanel.setBackground(new java.awt.Color(24, 139, 24));
        menuPanel.setBorder(new EmptyBorder(300, 100, 300, 100));

        playerField = new JTextField("player name here");
        menuPanel.add(playerField);

        joinRoom = new JTextField("Enter room code to join room");
        menuPanel.add(joinRoom);

        createRoom = new JButton("Create room");
        menuPanel.add(createRoom);

        root = new JPanel(new CardLayout());
        root.add(menuPanel, "menu");
        root.add(mainPanel, "game");
        mainFrame.getContentPane().add(root);
        mainFrame.setVisible(true);
    }

    public JLabel getDrawnCardLabel() {
        return drawnLabel;
    }

    public void setPlayerName(String name){ playerName.setText(name); }

    //public void setHandPoints(int points){ handPoints.setText(Integer.toString(points)); }

    public void setDealerHandPoints(int points){ dealerHandValue.setText("Hand Value: " + Integer.toString(points)); }

    public void setPlayerHandPoints(int points){ playerHandValue.setText("Hand Value: " + Integer.toString(points)); }

    public void setPlayerChips(int chips) { chipText.setText(Integer.toString(chips)); }

    public void setupUserCard(ArrayList<String> cardNames) {
        playerCardsPanel.removeAll();
        playerCardsPanel.add(playerNamePanel);
        userLabels.removeAll(userLabels);

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
    public void setupDealerCard(ArrayList<String> cardNames, Boolean faceDownFirst) {
        System.out.println("dealer labels:" + dealerLabels);
        dealerCardsPanel.removeAll();
        dealerCardsPanel.add(dealerNamePanel);
        dealerLabels.removeAll(dealerLabels);

        for(int i = 0; i < cardNames.size(); i++){
            JLabel label = new JLabel();
            if(faceDownFirst == true && i == 0){
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

    public void clearDealerLabels(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.remove(label);
        }
        dealerLabels.clear();
    }

    public void updateDealerLabelsPanel(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.add(label);
        }
    }

    public void switchPanel(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "game");
    }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }

    public void addPlayerFieldListener(ActionListener al){ playerField.addActionListener(al); }

    public void addRoomFieldListener(ActionListener al){ joinRoom.addActionListener(al); }

    public void addCreateRoomListener(ActionListener al){ createRoom.addActionListener(al); }

    public void addStartButtonListener(ActionListener al){ startButton.addActionListener(al); }

    public void addBetFieldListener(ActionListener al){ betField.addActionListener(al); }

    public String getBetText(){ return betField.getText(); }

    public String getPlayerFieldText(){ return playerField.getText(); }

    public String getRoomCode(){ return joinRoom.getText(); }
}

