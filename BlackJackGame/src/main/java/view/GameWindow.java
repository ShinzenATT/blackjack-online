package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.ButtonDisplayModel;
import model.ImageDisplayModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The main window of the game.
 */
public class GameWindow extends JFrame {

    private JFrame mainFrame;
    private final JPanel mainPanel;
    private final JButton hitButton;
    private final JButton stayButton;
    private final JButton doubleDownButton;
    private final JButton startButton;
    private final JTextField betField;
    private final JLabel holder;
    private final JLabel playerName;
    private final JLabel playerBet;
    private final JLabel chipText;
    private final JLabel handPoints;
    private final JLabel dealerNameLabel;
    private final JLabel playerNameLabel;
    private final JLabel playerHandValue;
    private final JLabel dealerHandValue;
    private final JLabel blackJackLogo;
    private final JButton joinBackButton;
    private final JButton rulesBackButton;
    private final JButton roomBackButton;
    private final JPanel dealerCardsPanel;
    private final JPanel playerCardsPanel;
    private final JPanel dealerNamePanel;
    private final JPanel playerNamePanel;
    private final JPanel rulesPanel;
    private final JTextArea rulesText;
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
    private final JButton joinRoomBtn;
    private final JButton joinRoomButton;
    private final JPanel backPanel;
    private final JPanel rulesBackPanel;
    private final JPanel roomBackPanel;
    private final JPanel rulesInfoPanel;
    private final JPanel root;
    private final JPanel roomTopPanel;
    private final JLabel playerFieldBackground;
    private final JLabel roomFieldBackground;
    private final JButton splitButton;
    private final JLabel betFieldBackground;
    private final JLabel errorMessageLabel;
    private final JLabel roomCode;
    private final JPanel createRoomPanel;
    private final JButton createRoomBtn;
    private final JLabel playerFieldBackground2;
    private final JTextField playerField2;
    private final JPanel createRoomBackPanel;
    private final JButton createRoomBackBtn;
    private final JPanel cardGrid;
	private ArrayList<JLabel> dealerLabels;
	private ArrayList<JLabel> userLabels;
    ImageDisplayModel allImages = new ImageDisplayModel();
    ButtonDisplayModel allButtonImages = new ButtonDisplayModel();


    /**
     * Creates the main window for the game.
     */
    public GameWindow() {


        mainFrame = new JFrame();
        mainPanel = new JPanel();
        hitButton = new JButton();
        stayButton = new JButton();
        doubleDownButton = new JButton();
        playerName = new JLabel();
        fill = new JLabel();
        playerBet = new JLabel();
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
        roomBackButton = new JButton();
        roomBackPanel = new JPanel();
        roomTopPanel = new JPanel();
        playerFieldBackground = new JLabel();
        roomFieldBackground = new JLabel();
        betFieldBackground = new JLabel();
        errorMessageLabel = new JLabel();
        roomCode = new JLabel();
        holder = new JLabel();
        dealerLabels = new ArrayList<>();
        userLabels = new ArrayList<>();

        //The panel that holds player info and cards
        dealerCardsPanel = new JPanel();
        dealerCardsPanel.setSize(new java.awt.Dimension(700,200));
        dealerCardsPanel.setBackground(new java.awt.Color(24, 90, 24));

        //The panel that holds back button top left corner and dealer info and cards panel
        roomTopPanel.setSize(new java.awt.Dimension(700,200));
        roomTopPanel.setBackground(new java.awt.Color(24, 90, 24));
        roomTopPanel.setLayout(new BorderLayout());

        //The panel that holds dealer info and cards
        playerCardsPanel = new JPanel();
        playerCardsPanel.setSize(new java.awt.Dimension(700,200));
        playerCardsPanel.setBackground(new java.awt.Color(24, 90, 24));

        //The panel that holds the dealers name and total hand value
        dealerNamePanel = new JPanel();
        dealerNamePanel.setBackground(new java.awt.Color(24, 90, 24));
        dealerNamePanel.setLayout(new BorderLayout());

        //The panel that holds the players name and total hand value
        playerNamePanel = new JPanel();
        playerNamePanel.setBackground(new java.awt.Color(24, 90, 24));
        playerNamePanel.setLayout(new BorderLayout());
        
        //Main frame setup
        JFrame mainFrame = new JFrame("BlackJack");
        mainFrame.setSize(1200, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        //Main panel setup
        mainPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        mainPanel.setSize(new java.awt.Dimension(700, 500));
        mainPanel.setLayout(new BorderLayout());

        //Menu panel setup
        menuPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        menuPanel.setSize(new java.awt.Dimension(700, 500));
        menuPanel.setBackground(new java.awt.Color(24, 139, 24));
        menuPanel.setLayout(new BorderLayout());

        //Panel for buttons in menu
        menuButtonsPanel.setLayout(new BoxLayout(menuButtonsPanel, BoxLayout.Y_AXIS));
        menuButtonsPanel.setBackground(new java.awt.Color(24, 139, 24));

        //Panel that holds information about the current game
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.Y_AXIS));
        interactionPanel.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.add(interactionPanel, BorderLayout.WEST);

        //Hit button setup
        hitButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("hitButton")));
        hitButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("hitButtonRollover")));
        hitButton.setOpaque(false);
        hitButton.setContentAreaFilled(false);
        hitButton.setBorderPainted(false);
        hitButton.setFocusPainted(false);
        interactionPanel.add(hitButton);

        //Stand button setup
        stayButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("standButton")));
        stayButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("standButtonRollover")));
        stayButton.setOpaque(false);
        stayButton.setContentAreaFilled(false);
        stayButton.setBorderPainted(false);
        stayButton.setFocusPainted(false);
        interactionPanel.add(stayButton);

        //DoubleDown button setup
        doubleDownButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("doubleDownButton")));
        doubleDownButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("doubleDownButtonRollover")));
        doubleDownButton.setOpaque(false);
        doubleDownButton.setContentAreaFilled(false);
        doubleDownButton.setBorderPainted(false);
        doubleDownButton.setFocusPainted(false);
        interactionPanel.add(doubleDownButton);

        //Start button setup
        splitButton = new JButton();
        splitButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("splitButton")));
        splitButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("splitButtonRollover")));
        splitButton.setOpaque(false);
        splitButton.setContentAreaFilled(false);
        splitButton.setBorderPainted(false);
        splitButton.setFocusPainted(false);
        interactionPanel.add(splitButton);

        //Bet field setup
        betField = new JTextField("Write bet amount");
        betField.setOpaque(false);
        betField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        betField.setFont(new Font("", Font.BOLD, 15));
        betField.setForeground(Color.WHITE);
        betField.setHorizontalAlignment(JTextField.CENTER);
        betFieldBackground.setLayout(new BorderLayout());
        betFieldBackground.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("betEmpty")));
        betFieldBackground.add(betField);
        betFieldBackground.setBorder(new EmptyBorder(0, 15, 0, 0));
        interactionPanel.add(betFieldBackground);

        //Start button setup
        startButton = new JButton();
        startButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("startButton")));
        startButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("startButtonRollover")));
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        interactionPanel.add(startButton);

        //The roomcode of the current game
        roomCode.setFont(new Font("", Font.BOLD, 12));
        roomCode.setForeground(Color.WHITE);
        roomCode.setBorder(new EmptyBorder(0, 30, 0, 0));
        interactionPanel.add(roomCode);

        //The name of the player
        playerName.setFont(new Font("", Font.BOLD, 12));
        playerName.setForeground(Color.WHITE);
        playerName.setBorder(new EmptyBorder(0, 30, 0, 0));
        interactionPanel.add(playerName);

        //The current total chips of the player
        chipText.setFont(new Font("", Font.BOLD, 12));
        chipText.setForeground(Color.WHITE);
        chipText.setBorder(new EmptyBorder(0, 30, 0, 0));
        interactionPanel.add(chipText);

        //The current bet of the player
        playerBet.setFont(new Font("", Font.BOLD, 12));
        playerBet.setForeground(Color.WHITE);
        playerBet.setBorder(new EmptyBorder(0, 30, 0, 0));
        interactionPanel.add(playerBet);

        //Error message for game screen
        errorMessageLabel.setText("");
        errorMessageLabel.setFont(new Font("", Font.BOLD, 12));
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setBorder(new EmptyBorder(0, 30, 0, 0));
        interactionPanel.add(errorMessageLabel);

        holder.setBounds(30, 545, 200, 50);
        holder.setFont(new Font("This is a holder", Font.BOLD, 12));
        holder.setForeground(new java.awt.Color(24, 139, 24));
        interactionPanel.add(holder);

        dealerNameLabel = new JLabel("Dealers Hand", SwingConstants.CENTER);
        dealerNameLabel.setFont(new java.awt.Font("", 1, 18));
        dealerNameLabel.setForeground(Color.WHITE);

        playerNameLabel = new JLabel("Players Hand", SwingConstants.CENTER);
        playerNameLabel.setFont(new java.awt.Font("", 1, 18));
        playerNameLabel.setForeground(Color.WHITE);

        playerHandValue = new JLabel("Hand Value: ");
        playerHandValue.setFont(new java.awt.Font("", 1, 18));
        playerHandValue.setForeground(Color.WHITE);

        dealerHandValue = new JLabel("Hand Value: ?");
        dealerHandValue.setFont(new java.awt.Font("", 1, 18));
        dealerHandValue.setForeground(Color.WHITE);

        dealerNamePanel.add(dealerNameLabel, BorderLayout.NORTH);
        dealerNamePanel.add(dealerHandValue, BorderLayout.SOUTH);

        playerNamePanel.add(playerNameLabel, BorderLayout.NORTH);
        playerNamePanel.add(playerHandValue, BorderLayout.SOUTH);

        dealerCardsPanel.add(dealerNamePanel);
        playerCardsPanel.add(playerNamePanel);

        cardGrid = new JPanel(new GridLayout());
        cardGrid.setBackground(new java.awt.Color(24, 139, 24));
        mainPanel.add(cardGrid, BorderLayout.CENTER);

        // Close button setup
        closeButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("exitButton")));
        closeButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("exitButtonRollover")));
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel that holds music button in menu bottom right
        menuMusicPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        menuMusicPanel.setBackground(new java.awt.Color(24, 139, 24));

        // Panel that holds info textfield in rules panel
        rulesInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        rulesInfoPanel.setBackground(new java.awt.Color(24, 139, 24));

        // Panel that holds back button in join room top left corner
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(new java.awt.Color(24, 139, 24));

        // Panel that holds back button in rule room top left corner
        rulesBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rulesBackPanel.setBackground(new java.awt.Color(24, 139, 24));

        // Panel that holds the return button from a room, top left corner
        roomBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        roomBackPanel.setBackground(new java.awt.Color(24, 90, 24));

        // Panel that holds buttons and input fields in tje join room
        joinServerMenuPanel.setLayout(new BoxLayout(joinServerMenuPanel, BoxLayout.Y_AXIS));
        joinServerMenuPanel.setBackground(new java.awt.Color(24, 139, 24));

        // Panel that holds information text about the rules of the games
        rulesPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        rulesPanel.setSize(new java.awt.Dimension(700, 500));
        rulesPanel.setBackground(new java.awt.Color(24, 139, 24));
        rulesPanel.setLayout(new BorderLayout());

        // Music button setup righ bottom corner
        musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOnButton")));
        musicButton.setOpaque(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setBorderPainted(false);
        musicButton.setFocusPainted(false);
        menuMusicPanel.add(musicButton);

        //Back button in the join room setup
        joinBackButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        joinBackButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        joinBackButton.setOpaque(false);
        joinBackButton.setContentAreaFilled(false);
        joinBackButton.setBorderPainted(false);
        joinBackButton.setFocusPainted(false);
        backPanel.add(joinBackButton);

        // Return button in rules panels top left corner
        rulesBackButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        rulesBackButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        rulesBackButton.setOpaque(false);
        rulesBackButton.setContentAreaFilled(false);
        rulesBackButton.setBorderPainted(false);
        rulesBackButton.setFocusPainted(false);
        rulesBackPanel.add(rulesBackButton);

        // Return button in active game panels top left corner
        roomBackButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        roomBackButton.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        roomBackButton.setOpaque(false);
        roomBackButton.setContentAreaFilled(false);
        roomBackButton.setBorderPainted(false);
        roomBackButton.setFocusPainted(false);
        roomBackButton.setBounds(10, 10, 75, 75);
        roomBackPanel.add(roomBackButton);

        // The panel for join room
        joinServerPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        joinServerPanel.setSize(new java.awt.Dimension(700, 500));
        joinServerPanel.setBackground(new java.awt.Color(24, 139, 24));
        joinServerPanel.setLayout(new BorderLayout());

        // Textfield area for player to enter name
        // is added to a jlabel that acts as nicer background
        playerField = new JTextField("player name here");
        playerField.setOpaque(false);
        playerField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        playerField.setFont(new Font("Arial", Font.BOLD, 25));
        playerField.setForeground(Color.WHITE);
        playerField.setHorizontalAlignment(JTextField.CENTER);
        playerFieldBackground.setLayout(new BorderLayout());
        playerFieldBackground.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("emptyButton")));
        playerFieldBackground.add(playerField);
        playerFieldBackground.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Textfield area for player to enter room code
        // is added to a jlabel that acts as nicer background
        joinRoom = new JTextField("room code here");
        joinRoom.setOpaque(false);
        joinRoom.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinRoom.setFont(new Font("Arial", Font.BOLD, 25));
        joinRoom.setForeground(Color.WHITE);
        joinRoom.setHorizontalAlignment(JTextField.CENTER);
        roomFieldBackground.setLayout(new BorderLayout());
        roomFieldBackground.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("emptyButton")));
        roomFieldBackground.add(joinRoom);
        roomFieldBackground.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Join room button setup
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
        
        // Logo on the main panel
        blackJackLogo.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("blackJackLogo")));
        blackJackLogo.setOpaque(false);
        blackJackLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel that holds the buttons in the main panel
        menuButtonsPanel.add(blackJackLogo);
        menuButtonsPanel.add(joinRoomButton);
        menuButtonsPanel.add(createServerButton);
        menuButtonsPanel.add(rulesButton);
        menuButtonsPanel.add(closeButton);

        // Join room button setup
        joinRoomBtn = new JButton();
        joinRoomBtn.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("joinServerButton")));
        joinRoomBtn.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("joinServerButtonRollover")));
        joinRoomBtn.setOpaque(false);
        joinRoomBtn.setContentAreaFilled(false);
        joinRoomBtn.setBorderPainted(false);
        joinRoomBtn.setFocusPainted(false);
        joinRoomBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Blackjack rules infotext
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

        //Panel that holds button and input field in the join room panel
        joinServerMenuPanel.add(Box.createRigidArea(new Dimension(0, 150))); // adds spacing before first item
        joinServerMenuPanel.add(roomFieldBackground);
        joinServerMenuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        joinServerMenuPanel.add(playerFieldBackground);
        joinServerMenuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        joinServerMenuPanel.add(joinRoomBtn);
        joinServerPanel.add(joinServerMenuPanel, BorderLayout.CENTER);

        //Panel that holds dealer cards and panel with back button
        roomTopPanel.add(dealerCardsPanel, BorderLayout.CENTER);
        roomTopPanel.add(roomBackPanel, BorderLayout.WEST);

        rulesInfoPanel.add(rulesText);
        rulesPanel.add(rulesInfoPanel, BorderLayout.CENTER);
        rulesPanel.add(rulesBackPanel, BorderLayout.NORTH);
        joinServerPanel.add(backPanel, BorderLayout.NORTH);
        menuPanel.add(menuMusicPanel, BorderLayout.SOUTH);
        menuPanel.add(menuButtonsPanel, BorderLayout.CENTER);
        mainPanel.add(roomTopPanel, BorderLayout.NORTH);
        mainPanel.add(playerCardsPanel, BorderLayout.SOUTH);

        //Panel for creating room
        createRoomPanel = new JPanel();
        createRoomPanel.setLayout(new BoxLayout(createRoomPanel, BoxLayout.Y_AXIS));
        createRoomPanel.setBorder(new EmptyBorder(0, 0, 250, 0));
        createRoomPanel.setBackground(new java.awt.Color(24, 139, 24));

        //Panel that holds back button in the create room panel
        createRoomBackPanel = new JPanel();
        createRoomBackPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        createRoomBackPanel.setBackground(new java.awt.Color(24, 139, 24));
        createRoomPanel.add(createRoomBackPanel);

        //Back button in create room panel setup
        createRoomBackBtn = new JButton();
        createRoomBackBtn.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButton")));
        createRoomBackBtn.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("backButtonRollover")));
        createRoomBackBtn.setOpaque(false);
        createRoomBackBtn.setContentAreaFilled(false);
        createRoomBackBtn.setBorderPainted(false);
        createRoomBackBtn.setFocusPainted(false);
        createRoomBackPanel.add(createRoomBackBtn);

        // Textfield area for player to enter name
        // is added to a jlabel that acts as nicer background
        playerField2 = new JTextField("player name here");
        playerField2.setOpaque(false);
        playerField2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        playerField2.setFont(new Font("Arial", Font.BOLD, 25));
        playerField2.setForeground(Color.WHITE);
        playerField2.setHorizontalAlignment(JTextField.CENTER);
        playerFieldBackground2 = new JLabel();
        playerFieldBackground2.setLayout(new BorderLayout());
        playerFieldBackground2.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("emptyButton")));
        playerFieldBackground2.add(playerField2);
        playerFieldBackground2.setAlignmentX(Component.CENTER_ALIGNMENT);
        createRoomPanel.add(playerFieldBackground2);

        // Create room button setup
        createRoomBtn = new JButton();
        createRoomBtn.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("createServerButton")));
        createRoomBtn.setRolloverIcon(new ImageIcon(allButtonImages.getButtonImageFromName("createServerButtonRollover")));
        createRoomBtn.setOpaque(false);
        createRoomBtn.setContentAreaFilled(false);
        createRoomBtn.setBorderPainted(false);
        createRoomBtn.setFocusPainted(false);
        createRoomBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createRoomPanel.add(createRoomBtn);

        //Add room panels to the root panel with 
        //cardlayout to switch between panels easy
        root = new JPanel(new CardLayout());
        root.add(menuPanel, "menu");
        root.add(mainPanel, "game");
        root.add(joinServerPanel, "joinroom");
        root.add(rulesPanel, "rules");
        root.add(createRoomPanel, "createroom");
        mainFrame.getContentPane().add(root);
        mainFrame.setVisible(true);
    }

    // Update values for the room data labels
    public void setPlayerName(String name){ playerName.setText("Current turn: " + name); }
    public void setPlayerBet(int bet){ playerBet.setText("Current bet: " + bet); }
    public void setPlayerChips(int chips) { chipText.setText("Current chips: " + chips); }
    public void setRoomCode(String code) { roomCode.setText("Room code: " + code); }

    // Dealers displayed total hand points is hidden while
    // its first card is face down
    public void setDealerHandPoints(int points){
        if(points > 0) {
            dealerHandValue.setText("Hand Value: " + Integer.toString(points));
        } else {
            dealerHandValue.setText("Hand Value: ?");
        }
    }

    // Updates the players hand total points that is displayed next to players hand
    public void setPlayerHandPoints(int points){ playerHandValue.setText("Hand Value: " + Integer.toString(points)); }

    // Setups all the images to the cards in the players hand to be displayed
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

    //Add card to users hand that is dislpayed
    public void addUserCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        userLabels.add(label);
        updateUserLabelsPanel();
    }

    // Updates the label that holds all the player cards to be displayed
    public void updateUserLabelsPanel(){
        for (JLabel label: userLabels){
            playerCardsPanel.add(label);
        }
    }

    // Setups all the images to the cards in the dealers hand to be displayed
    public void setupDealerCard(ArrayList<String> cardNames, Boolean faceDownFirst) {
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

    //Add card to dealers hand that is displayed
    public void addDealerCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        dealerLabels.add(label);
        updateDealerLabelsPanel();
    }

    //Clears dealers hand
    public void clearDealerLabels(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.remove(label);
        }
        dealerLabels.clear();
    }

    //Adds dealers cards to the panel that displays them
    public void updateDealerLabelsPanel(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.add(label);
        }
    }

    //Turnorder grid setup, displays all players hands
    public void setupTurnOrderGrid(List<String> players, List<List<String>> handStrings, int currentTurn){
        if(players.size() != handStrings.size()){
            throw new IllegalArgumentException();
        }
        cardGrid.removeAll();

        int mod = 0;
        if(players.contains("Dealer")){
            mod = 1;
        }

        for(int i = 0; i < players.size() - mod; i++){
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new java.awt.Color(24, 139, 24));

            if(i == currentTurn){
                panel.setBorder(new LineBorder(Color.YELLOW));
            }

            JPanel cpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cpanel.setBackground(new java.awt.Color(24, 139, 24));
            panel.add(cpanel, BorderLayout.CENTER);

            for (String c: handStrings.get(i)){
                JLabel l = new JLabel();
                ImageIcon ic = new ImageIcon(allImages.getScaledImageInstanceFromName(c, 40, 58));
                l.setIcon(ic);
                cpanel.add(l);
                System.out.println(players.get(i) + ' ' + c);
            }

            JLabel pname = new JLabel(players.get(i));
            pname.setForeground(Color.WHITE);
            pname.setFont(new Font("", Font.BOLD, 10));
            panel.add(pname, BorderLayout.NORTH);

            cardGrid.add(panel);
        }
        cardGrid.repaint();
        cardGrid.revalidate();
    }

    //Switches between different panels by using cardlayout on root
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

    public void switchToCreateRoom(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "createroom");
    }

    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    // Changes image on the music button bottom right corner
    // depending if the background music is on or off
    public void toggleMusicOnButton(boolean on){
        if(on){
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOnButton")));
        } else{
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOffButton")));
        }
    }

    // Update error message label
    public void updateErrorMessage(String message){
        errorMessageLabel.setText(message);
    }

    // Actionlisteners setup for buttons used in the application
    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }

    public void addPlayerFieldListener(ActionListener al){ playerField.addActionListener(al); }

    public void addRoomFieldListener(ActionListener al){ joinRoom.addActionListener(al); }

    public void addJoinRoomBtnListener(ActionListener al){ joinRoomBtn.addActionListener(al); }

    public void addStartButtonListener(ActionListener al){ startButton.addActionListener(al); }

    public void addBetFieldListener(ActionListener al){ betField.addActionListener(al); }

    public void addMusicButtonListener(ActionListener ml){ musicButton.addActionListener(ml); }

    public void addCloseButtonListener(ActionListener cl){ closeButton.addActionListener(cl); }

    public void addJoinRoomButtonListener(ActionListener cl){ joinRoomButton.addActionListener(cl); }

    public void addJoinBackButtonListener(ActionListener bl){ joinBackButton.addActionListener(bl); }

    public void addRulesButtonListener(ActionListener bl){ rulesButton.addActionListener(bl); }

    public void addRulesBackButtonListener(ActionListener bl){ rulesBackButton.addActionListener(bl); }

    public void addDoubleDownButtonListener(ActionListener dl){ doubleDownButton.addActionListener(dl); }

    public void addRoomBackButtonListener(ActionListener rl){ roomBackButton.addActionListener(rl); }

    public void addSplitButtonListener(ActionListener rl){ splitButton.addActionListener(rl); }

    public void addCreateRoomMenuBtnListener(ActionListener al){ createServerButton.addActionListener(al); }

    public void addCreateRoomBtnListener(ActionListener al) { createRoomBtn.addActionListener(al); }

    public void addCreateRoomBackBtnListener(ActionListener al) { createRoomBackBtn.addActionListener(al); }

    //Getters for player input field values
    public String getBetText(){ return betField.getText(); }

    public String getPlayerFieldText(){ return playerField.getText(); }

    public String getPlayer2ndFieldText() { return playerField2.getText(); }

    public String getRoomCode(){ return joinRoom.getText(); }

    //Confirm dialog when the players leaves a game room that is currently running
    public boolean confirmExit(){
        int a = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit the current game?",
                "Quit the game?", JOptionPane.YES_NO_OPTION);
        return a == JOptionPane.YES_OPTION;
    }
}
