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
 * @author Joel and Alen
 * @version 2022-03-07
 */
public class GameWindow extends JFrame {

    /**
     * The game panel containing ui for playing blackjack
     */
    private final JPanel mainPanel;
    /**
     * A button for performing the blackjack 'hit' action
     */
    private final JButton hitButton;
    /**
     * A button for performing the blackjack 'stand' action
     */
    private final JButton stayButton;
    /**
     * A button for performing the blackjack 'double down' action
     */
    private final JButton doubleDownButton;
    /**
     * A button for informing the server to start a blackjack game
     */
    private final JButton startButton;
    /**
     * A text field for placing a bet to the current hand
     */
    private final JTextField betField;
    /**
     * Displays the name of the player that currently has their turn
     */
    private final JLabel playerName;
    /**
     * Displays the balance of the current player in play
     */
    private final JLabel playerBet;
    /**
     * The amount of chips that was bet to the current hand
     */
    private final JLabel chipText;
    /**
     * The point total of the current hand
     */
    private final JLabel playerHandValue;
    /**
     * The point total of the dealer's hand
     */
    private final JLabel dealerHandValue;
    /**
     * Exit button to main menu from the join room menu
     */
    private final JButton joinBackButton;
    /**
     * Exit button to main menu from the rules screen
     */
    private final JButton rulesBackButton;
    /**
     * Exit button to main menu from the create room menu
     */
    private final JButton roomBackButton;
    /**
     * The panel containing the dealer's hand
     */
    private final JPanel dealerCardsPanel;
    /**
     * The panel containing the current player's hand
     */
    private final JPanel playerCardsPanel;
    /**
     * The panel containing the dealer information
     */
    private final JPanel dealerNamePanel;
    /**
     * The panel containing the current player information
     */
    private final JPanel playerNamePanel;
    /**
     * The exit button in the main menu
     */
    private final JButton closeButton;
    /**
     * The rules button in the main menu
     */
    private final JButton rulesButton;
    /**
     * Create server button in the main menu
     */
    private final JButton createServerButton;
    /**
     * The background music toggle in the main menu
     */
    private final JButton musicButton;
    /**
     * the join room button the main menu
     */
    private final JTextField joinRoom;
    /**
     * the player name text field in the join room menu
     */
    private final JTextField playerField;
    /**
     * the conformation button in the join room menu
     */
    private final JButton joinRoomBtn;
    /**
     * The join room button in the main menu
     */
    private final JButton joinRoomButton;
    /**
     * The main panel that contains all the menu that are to be switched between
     * @see CardLayout
     */
    private final JPanel root;
    /**
     * The split button in the game panel
     */
    private final JButton splitButton;
    /**
     * The error message in the game panel
     */
    private final JLabel errorMessageLabel;
    /**
     * The room code text in the game panel
     */
    private final JLabel roomCode;
    /**
     * the conformation button in the create room menu
     */
    private final JButton createRoomBtn;
    /**
     * the player name text feild in the create room menu
     */
    private final JTextField playerField2;
    /**
     * the exit to main menu button in the create room menu
     */
    private final JButton createRoomBackBtn;
    /**
     * The panel that contains all the players hands in the game panel
     */
    private final JPanel cardGrid;
    /**
     * The dealer card image objects
     */
	private final ArrayList<JLabel> dealerLabels;
    /**
     * the current player card image objects
     */
	private final ArrayList<JLabel> userLabels;
    /**
     * an instance of {@link ImageDisplayModel}
     */
    private final ImageDisplayModel allImages = new ImageDisplayModel();
    /**
     * an instance of {@link ButtonDisplayModel}
     */
    private final ButtonDisplayModel allButtonImages = new ButtonDisplayModel();

    /**
     * An enum for commanding the visibility of the dealer's cards
     */
    public enum CardVisibility { HIDDEN, FIRST_HIDDEN, VISIBLE}


    /**
     * Creates the main window for the game and all it's panels.
     */
    public GameWindow() {


        mainPanel = new JPanel();
        hitButton = new JButton();
        stayButton = new JButton();
        doubleDownButton = new JButton();
        playerName = new JLabel();
        playerBet = new JLabel();
        chipText = new JLabel();
        closeButton = new JButton();
        joinRoomButton = new JButton();
        createServerButton = new JButton();
        rulesButton = new JButton();
        musicButton = new JButton();
        JPanel menuButtonsPanel = new JPanel();
        JPanel menuMusicPanel = new JPanel();
        JPanel joinServerPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        JLabel blackJackLogo = new JLabel();
        joinBackButton = new JButton();
        JPanel backPanel = new JPanel();
        JPanel joinServerMenuPanel = new JPanel();
        JPanel rulesPanel = new JPanel();
        JTextArea rulesText1 = new JTextArea();
        JPanel rulesBackPanel = new JPanel();
        rulesBackButton = new JButton();
        JPanel rulesInfoPanel = new JPanel();
        roomBackButton = new JButton();
        JPanel roomBackPanel = new JPanel();
        JPanel roomTopPanel = new JPanel();
        JLabel playerFieldBackground = new JLabel();
        JLabel roomFieldBackground = new JLabel();
        JLabel betFieldBackground = new JLabel();
        errorMessageLabel = new JLabel();
        roomCode = new JLabel();
        JLabel holder = new JLabel();
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

        JLabel dealerNameLabel = new JLabel("Dealers Hand", SwingConstants.CENTER);
        dealerNameLabel.setFont(new java.awt.Font("", 1, 18));
        dealerNameLabel.setForeground(Color.WHITE);

        JLabel playerNameLabel = new JLabel("Players Hand", SwingConstants.CENTER);
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
        JPanel createRoomPanel = new JPanel();
        createRoomPanel.setLayout(new BoxLayout(createRoomPanel, BoxLayout.Y_AXIS));
        createRoomPanel.setBorder(new EmptyBorder(0, 0, 250, 0));
        createRoomPanel.setBackground(new java.awt.Color(24, 139, 24));

        //Panel that holds back button in the create room panel
        JPanel createRoomBackPanel = new JPanel();
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
        JLabel playerFieldBackground2 = new JLabel();
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

    /**
     * Set the current turn player name shown in-game
     * @param name the name of the player
     */
    public void setPlayerName(String name){ playerName.setText("Current turn: " + name); }

    /**
     * show the amount the the current hand has bet in-game
     * @param bet the amount of the bet
     */
    public void setPlayerBet(int bet){ playerBet.setText("Current bet: " + bet); }

    /**
     * sets the current player's chip balance that is shown in-game
     * @param chips the player's chip balance
     */
    public void setPlayerChips(int chips) { chipText.setText("Current chips: " + chips); }

    /**
     * sets to display the curren room's join code associated with the game
     * @param code the room code string
     */
    public void setRoomCode(String code) { roomCode.setText("Room code: " + code); }

    /**
     * Displays the dealers displayed total hand point, it is hidden when the set value is 0 or below
     *  @param points the dealer's hand point value, if it's 0 or below the text will show '?'
     */
    public void setDealerHandPoints(int points){
        if(points > 0) {
            dealerHandValue.setText("Hand Value: " + Integer.toString(points));
        } else {
            dealerHandValue.setText("Hand Value: ?");
        }
    }

    /**
     * Updates the players hand total points that is displayed next to players hand
     * @param points the point total for the current hand
     */
    public void setPlayerHandPoints(int points){
        if(points > 0) {
            playerHandValue.setText("Hand Value: " + Integer.toString(points));
        } else{
            playerHandValue.setText("Hand Value: ?");
        }
    }

    /**
     *  Setups all the images to the cards in the players hand to be displayed
     * @param cardNames A list of string where each string represents a card and the list represents the hand
     * @param hidden The card will be shown as hidden (card backs) if true
      */
    public void setupUserCard(ArrayList<String> cardNames, boolean hidden) {
        playerCardsPanel.removeAll();
        playerCardsPanel.add(playerNamePanel);
        userLabels.removeAll(userLabels);

        for (String cardName : cardNames) {
            JLabel label = new JLabel();
            String c = cardName;

            if (hidden) {
                c = "back";
            }

            label.setIcon(new ImageIcon(allImages.getScaledImageInstanceFromName(c, 100, 145)));
            userLabels.add(label);
        }

        updateUserLabelsPanel();
        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();
    }

    /**
     * Add card to users hand that is displayed
     * @param cardName A string that represents a card that is to be added to the player's hand
     */
    public void addUserCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        userLabels.add(label);
        updateUserLabelsPanel();
    }

    /**
     * Updates the label that holds all the player cards to be displayed
     */
    public void updateUserLabelsPanel(){
        for (JLabel label: userLabels){
            playerCardsPanel.add(label);
        }
    }

    /**
     * Setups all the images to the cards in the dealers hand to be displayed
     * @param cardNames A list of string where each string represents a card and the list represents the dealer's hand
     * @param visibility a boolean that when true the first card in the dealer's will be hidden when rendered
     */
    public void setupDealerCard(ArrayList<String> cardNames, CardVisibility visibility) {
        dealerCardsPanel.removeAll();
        dealerCardsPanel.add(dealerNamePanel);
        dealerLabels.removeAll(dealerLabels);

        for(int i = 0; i < cardNames.size(); i++){
            JLabel label = new JLabel();
            if(visibility == CardVisibility.HIDDEN || (visibility == CardVisibility.FIRST_HIDDEN && i == 0)){
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

    /**
     * Add card to dealers hand that is displayed
     * @param cardName a string representing a card that is to be added
     */
    public void addDealerCard(String cardName) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(allImages.getScaledImageInstanceFromName(cardName, 100, 145));
        label.setIcon(imageIcon);
        dealerLabels.add(label);
        updateDealerLabelsPanel();
    }

    /**
     * Clears the dealer's hand
     */
    public void clearDealerLabels(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.remove(label);
        }
        dealerLabels.clear();
    }

    /**
     * Adds dealers cards to the panel that displays them
     */
    public void updateDealerLabelsPanel(){
        for (JLabel label: dealerLabels){
            dealerCardsPanel.add(label);
        }
    }

    /**
     * Renders every players' hand(s) in the card grid when in-game
     * @param players A list of the player names that should be synchronized with the handStrings argument
     * @param handStrings A List of a String list where the initial list is each hand and the sublist is
     *                    all the card names in that hand
     * @param handBets A list of ints that represent the ets of each hand, the list must be synchronized with players
     * @param currentTurn An index that show the current turn relative to the list
     * @throws IllegalArgumentException when the players, handString and handBet lists don't have the same size
     */
    public void setupTurnOrderGrid(List<String> players, List<List<String>> handStrings, List<Integer> handBets, int currentTurn){
        if(players.size() != handStrings.size() || players.size() != handBets.size()){
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
                String cs = c;

                if(handBets.get(i) <= 0){
                    cs = "back";
                }

                ImageIcon ic = new ImageIcon(allImages.getScaledImageInstanceFromName(cs, 40, 58));
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

    /**
     * Switches the panel to the blackjack layout
     */
    public void switchToGame(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "game");
    }

    /**
     * switches the menu to the join room menu
     */
    public void switchToJoinRoom(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "joinroom");
    }

    /**
     * switches the menu to the main menu
     */
    public void switchToMenu(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "menu");
    }

    /**
     * switch the menu to the rules panel
     */
    public void switchToRules(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "rules");
    }

    /**
     * switch the menu to the create room menu
     */
    public void switchToCreateRoom(){
        CardLayout cl = (CardLayout) root.getLayout();
        cl.show(root, "createroom");
    }

    /**
     * Adds an on click {@link ActionListener} to the hit button in the game panel
     * @param hl the event handler
     */
    public void addHitButtonListener(ActionListener hl) {
        hitButton.addActionListener(hl);
    }

    /**
     * Toggles the background music icon in the main menu to reflect the status of the music
     * @param on if true then the music icon is green otherwise it's red
     */
    public void toggleMusicOnButton(boolean on){
        if(on){
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOnButton")));
        } else{
            musicButton.setIcon(new ImageIcon(allButtonImages.getButtonImageFromName("musicOffButton")));
        }
    }

    /**
     * Update error message label in the game panel
     * @param message the text to be displayed as an error message
     */
    public void updateErrorMessage(String message){
        errorMessageLabel.setText(message);
    }

    // Actionlisteners setup for buttons used in the application

    /**
     * Add an on click {@link ActionListener} to the stand button in game panel
     * @param al the event handler
     */
    public void addStandButtonListener(ActionListener al){ stayButton.addActionListener(al); }

    /**
     * Add an on enter press {@link ActionListener } to the player name field in join room menu
     * @param al the event handler
     */
    public void addPlayerFieldListener(ActionListener al){ playerField.addActionListener(al); }

    /**
     * Add an on enter press {@link ActionListener} to the room code field in the join room menu
     * @param al the event handler
     */
    public void addRoomFieldListener(ActionListener al){ joinRoom.addActionListener(al); }

    /**
     * Add an on click {@link ActionListener} to the confirmation button in the join room menu
     * @param al the event handler
     */
    public void addJoinRoomBtnListener(ActionListener al){ joinRoomBtn.addActionListener(al); }

    /**
     * Add an on click {@link ActionListener} to the start game button in the game panel
     * @param al the event handler
     */
    public void addStartButtonListener(ActionListener al){ startButton.addActionListener(al); }

    /**
     * Add an on enter press {@link ActionListener} to the bet field in the game panel
     * @param al the event handler
     */
    public void addBetFieldListener(ActionListener al){ betField.addActionListener(al); }

    /**
     * Add an on click {@link ActionListener} to the background music button in the main menu
     * @param ml the event handler
     */
    public void addMusicButtonListener(ActionListener ml){ musicButton.addActionListener(ml); }

    /**
     * Add an on click {@link ActionListener} to the exit button in the main menu
     * @param cl the event handler
     */
    public void addCloseButtonListener(ActionListener cl){ closeButton.addActionListener(cl); }

    /**
     * Add an on click {@link ActionListener} to join room button in the main menu
     * @param cl the event handler
     */
    public void addJoinRoomButtonListener(ActionListener cl){ joinRoomButton.addActionListener(cl); }

    /**
     * Add an on click {@link ActionListener} to the back button in the join room menu
     * @param bl the event handler
     */
    public void addJoinBackButtonListener(ActionListener bl){ joinBackButton.addActionListener(bl); }

    /**
     * Add an on click {@link ActionListener} to the rules button in the main menu
     * @param bl the event handler
     */
    public void addRulesButtonListener(ActionListener bl){ rulesButton.addActionListener(bl); }

    /**
     * Add an on click {@link ActionListener} to the exit button in the rules panel
     * @param bl the event handler
     */
    public void addRulesBackButtonListener(ActionListener bl){ rulesBackButton.addActionListener(bl); }

    /**
     * Add an on click {@link ActionListener} to the double down button in the game panel
     * @param dl the event handler
     */
    public void addDoubleDownButtonListener(ActionListener dl){ doubleDownButton.addActionListener(dl); }

    /**
     * Add an on click {@link ActionListener} to the exit button in the in-game panel
     * @param rl the event handler
     */
    public void addRoomBackButtonListener(ActionListener rl){ roomBackButton.addActionListener(rl); }

    /**
     * Add an on click {@link ActionListener} to the split button in the game panel
     * @param rl the event handler
     */
    public void addSplitButtonListener(ActionListener rl){ splitButton.addActionListener(rl); }

    /**
     * Add an on click {@link ActionListener} to the create room button in the main menu
     * @param al the event handler
     */
    public void addCreateRoomMenuBtnListener(ActionListener al){ createServerButton.addActionListener(al); }

    /**
     * Add an on click {@link ActionListener} to the confirmation button in the create room menu
     * @param al the event handler
     */
    public void addCreateRoomBtnListener(ActionListener al) { createRoomBtn.addActionListener(al); }

    /**
     * Add an on click {@link ActionListener} to the exit button in the create room menu
     * @param al the event handler
     */
    public void addCreateRoomBackBtnListener(ActionListener al) { createRoomBackBtn.addActionListener(al); }

    //Getters for player input field values

    /**
     * Get the inputted bet text value
     * @return the inputted bet value String
     */
    public String getBetText(){ return betField.getText(); }

    /**
     * get the player name text from the player text field in join room menu
     * @return the player name string
     */
    public String getPlayerFieldText(){ return playerField.getText(); }

    /**
     * get the player name input form the player text field in create room menu
     * @return the player name string
     */
    public String getPlayer2ndFieldText() { return playerField2.getText(); }

    /**
     * get the inputted room code from the room code text field in the join room menu
     * @return the room code string
     */
    public String getRoomCode(){ return joinRoom.getText(); }

    /**
     * Confirm dialog when the players leaves a game room that is currently running
     * @return true when the user clicks the yes option in the confirm dialog
     */
    public boolean confirmExit(){
        int a = JOptionPane.showConfirmDialog(mainPanel, "Do you really want to exit the current game?",
                "Quit the game?", JOptionPane.YES_NO_OPTION);
        return a == JOptionPane.YES_OPTION;
    }
}

