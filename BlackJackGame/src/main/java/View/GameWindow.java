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
    private final JLabel blackJackLogo;
    private final JButton joinBackButton;
    private final JButton rulesBackButton;

    private final JPanel dealerCardsPanel;

    private final JPanel playerCardsPanel;
    private final JPanel dealerNamePanel;
    private final JPanel playerNamePanel;
    private final JPanel rulesPanel;

    private final JTextArea rulesText;
    private final JLabel playerCardImageLabel;
    
	private ArrayList<JLabel> dealerLabels;
	private ArrayList<JLabel> userLabels;

    //private final JButton gameOnButton;
    private final JButton closeButton;
    private final JButton rulesButton;
    private final JButton createServerButton;
    private final JButton musicButton;
    private final JPanel menuButtonsPanel;
    private final JPanel menuMusicPanel;
    private final JPanel joinServerPanel;
    private final JPanel joinServerMenuPanel;

    private final JPanel menuPanel;
    private final JTextField joinRoom;
    private final JTextField playerField;
    private final JButton createRoom;
    private final JButton joinRoomButton;
    private final JPanel backPanel;
    private final JPanel rulesBackPanel;
    private final JPanel rulesInfoPanel;

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
        closeButton = new JButton();
        joinRoomButton = new JButton();
        createServerButton = new JButton();
        rulesButton = new JButton();
        musicButton = new JButton();
        menuButtonsPanel = new JPanel();
        menuMusicPanel = new JPanel();
        joinServerPanel = new JPanel();
        menuPanel = new JPanel();
        blackJackLogo = new JLabel();
        joinBackButton = new JButton();
        backPanel = new JPanel();
        joinServerMenuPanel = new JPanel();
        rulesPanel = new JPanel();
        rulesText = new JTextArea();
        rulesBackPanel = new JPanel();
        rulesBackButton = new JButton();
        rulesInfoPanel = new JPanel();

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
        
        //main frame setup
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        //main panel setup
        mainPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainPanel.setSize(new java.awt.Dimension(700, 500));
        mainPanel.setLayout(new BorderLayout());

        //menu panel setup
        menuPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        menuPanel.setSize(new java.awt.Dimension(700, 500));
        menuPanel.setBackground(new java.awt.Color(24, 139, 24));
        menuPanel.setLayout(new BorderLayout());

        // panel for buttons in menu
        menuButtonsPanel.setLayout(new BoxLayout(menuButtonsPanel, BoxLayout.Y_AXIS));
        menuButtonsPanel.setBackground(new java.awt.Color(24, 139, 24));

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

        // Close button setup
        closeButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("exitButton")));
        closeButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("exitButtonRollover")));
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuMusicPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        menuMusicPanel.setBackground(new java.awt.Color(24, 139, 24));

        rulesInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rulesInfoPanel.setBackground(new java.awt.Color(24, 139, 24));

        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new java.awt.Color(24, 139, 24));

        rulesBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rulesBackPanel.setBackground(new java.awt.Color(24, 139, 24));

        joinServerMenuPanel.setLayout(new BoxLayout(joinServerMenuPanel, BoxLayout.Y_AXIS));
        joinServerMenuPanel.setSize(new java.awt.Dimension(200, 50));
        joinServerMenuPanel.setBackground(new java.awt.Color(24, 139, 24));

        rulesPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        rulesPanel.setSize(new java.awt.Dimension(700, 500));
        rulesPanel.setBackground(new java.awt.Color(24, 139, 24));
        rulesPanel.setLayout(new BorderLayout());

        musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOnButton")));
        //musicButton.setBounds(10, 400, 66, 66);
        //musicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        musicButton.setOpaque(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setBorderPainted(false);
        musicButton.setFocusPainted(false);
        menuMusicPanel.add(musicButton);

        joinBackButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        joinBackButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        //joinBackButton.setBounds(10, 400, 66, 66);
        //musicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinBackButton.setOpaque(false);
        joinBackButton.setContentAreaFilled(false);
        joinBackButton.setBorderPainted(false);
        joinBackButton.setFocusPainted(false);
        backPanel.add(joinBackButton);
        //backPanelRules.add(joinBackButton);

        rulesBackButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        rulesBackButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        rulesBackButton.setOpaque(false);
        rulesBackButton.setContentAreaFilled(false);
        rulesBackButton.setBorderPainted(false);
        rulesBackButton.setFocusPainted(false);
        rulesBackPanel.add(rulesBackButton);

        joinServerPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        joinServerPanel.setSize(new java.awt.Dimension(700, 500));
        joinServerPanel.setBackground(new java.awt.Color(24, 139, 24));
        joinServerPanel.setLayout(new BorderLayout());

        playerField = new JTextField("player name here");
        //playerField.setMaximumSize( joinServerMenuPanel.getPreferredSize() );
        playerField.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinRoom = new JTextField("Enter room code to join room");
        //joinRoom.setMaximumSize( joinServerMenuPanel.getPreferredSize() );
        joinRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        joinRoomButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("joinServerButton")));
        joinRoomButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("joinServerButtonRollover")));
        joinRoomButton.setOpaque(false);
        joinRoomButton.setContentAreaFilled(false);
        joinRoomButton.setBorderPainted(false);
        joinRoomButton.setFocusPainted(false);
        joinRoomButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create server button setup
        createServerButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("createServerButton")));
        createServerButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("createServerButtonRollover")));
        createServerButton.setOpaque(false);
        createServerButton.setContentAreaFilled(false);
        createServerButton.setBorderPainted(false);
        createServerButton.setFocusPainted(false);
        createServerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Rules button setup
        rulesButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("rulesButton")));
        rulesButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("rulesButtonRollover")));
        rulesButton.setOpaque(false);
        rulesButton.setContentAreaFilled(false);
        rulesButton.setBorderPainted(false);
        rulesButton.setFocusPainted(false);
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        blackJackLogo.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("blackJackLogo")));
        blackJackLogo.setOpaque(false);
        blackJackLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuButtonsPanel.add(blackJackLogo);
        menuButtonsPanel.add(joinRoomButton);
        menuButtonsPanel.add(createServerButton);
        menuButtonsPanel.add(rulesButton);
        menuButtonsPanel.add(closeButton);

        createRoom = new JButton("Create room");
        createRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea rulesText = new JTextArea(
            "Basic BlackJack rules: \n" +
            "\u2022 The goal of blackjack is to beat the dealer's hand without going over 21. \n" +
            "\u2022 Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand. \n" +
            "\u2022 Each player starts with two cards, one of the dealer's cards is hidden until the end. \n" +
            "\u2022 To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn. \n" +
            "\u2022 If you go over 21 you bust, and the dealer wins regardless of the dealer's hand. \n" +
            "\u2022 If you are dealt 21 from the start (Ace & 10), you got a blackjack. \n" +
            "\u2022 Blackjack usually means you win 1.5 the amount of your bet. Depends on the casino. \n" +
            "\u2022 Dealer will hit until his/her cards total 17 or higher. \n" +
            "\u2022 Doubling is like a hit, only the bet is doubled and you only get one more card. \n" +
            "\u2022 Split can be done when you have two of the same card, the pair is split into two hands. \n" +
            "\u2022 Splitting also doubles the bet, because each new hand is worth the original bet. \n" +
            "\u2022 You can only double/split on the first move, or first move of a hand created by a split. \n" +
            "\u2022 You cannot play on two aces after they are split. \n" +
            "\u2022 You can double on a hand resulting from a split, tripling or quadrupling you bet. \n", 
                5, 
                46);
        rulesText.setFont(new Font("Serif", Font.PLAIN, 18));
        rulesText.setForeground(Color.WHITE);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setOpaque(false);
        rulesText.setEditable(false);

        joinServerMenuPanel.add(joinRoom);
        joinServerMenuPanel.add(playerField);
        joinServerMenuPanel.add(createRoom);
        joinServerPanel.add(joinServerMenuPanel, BorderLayout.CENTER);

        rulesInfoPanel.add(rulesText);
        rulesPanel.add(rulesInfoPanel, BorderLayout.CENTER);
        rulesPanel.add(rulesBackPanel, BorderLayout.NORTH);
        joinServerPanel.add(backPanel, BorderLayout.NORTH);
        menuPanel.add(menuMusicPanel, BorderLayout.SOUTH);
        menuPanel.add(menuButtonsPanel, BorderLayout.CENTER);

        root = new JPanel(new CardLayout());
        root.add(menuPanel, "menu");
        root.add(mainPanel, "game");
        root.add(joinServerPanel, "joinroom");
        root.add(rulesPanel, "rules");
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

    public void switchToJoinRoom(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "joinroom");
    }

    public void switchToMenu(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "menu");
    }

    public void switchToRules(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "rules");
    }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    public void toggleMusicOnButton(boolean on){
        if(on){
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOnButton")));
        } else{
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOffButton")));
        }
    }

    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }

    public void addPlayerFieldListener(ActionListener al){ playerField.addActionListener(al); }

    public void addRoomFieldListener(ActionListener al){ joinRoom.addActionListener(al); }

    public void addCreateRoomListener(ActionListener al){ createRoom.addActionListener(al); }

    public void addStartButtonListener(ActionListener al){ startButton.addActionListener(al); }

    public void addBetFieldListener(ActionListener al){ betField.addActionListener(al); }

    public void addMusicButtonListener(ActionListener ml){ musicButton.addActionListener(ml); }

    public void addCloseButtonListener(ActionListener cl){ closeButton.addActionListener(cl); }

    public void addJoinRoomButtonListener(ActionListener cl){ joinRoomButton.addActionListener(cl); }

    public void addJoinBackButtonListener(ActionListener bl){ joinBackButton.addActionListener(bl); }

    public void addRulesButtonListener(ActionListener bl){ rulesButton.addActionListener(bl); }

    public void addRulesBackButtonListener(ActionListener bl){ rulesBackButton.addActionListener(bl); }


    public String getBetText(){ return betField.getText(); }

    public String getPlayerFieldText(){ return playerField.getText(); }

    public String getRoomCode(){ return joinRoom.getText(); }
}

