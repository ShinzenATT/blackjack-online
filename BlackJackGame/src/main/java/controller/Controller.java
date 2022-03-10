package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import model.GameTracker;
import model.SoundEffectModel;
import model.json_data.ConnectionSetup;
import model.json_data.ErrorRes;
import model.json_data.GameModel;
import model.json_data.RecievingCmd;
import model.objects.Card;
import model.objects.Hand;
import view.GameWindow;
import com.google.gson.Gson;

/**
 * The controller class controls the communication
 * between model and view classes.
 * @author Alen and Joel
 * @version 2022-03-07
 */
public class Controller {

    private final GameWindow bjview;
    private final GameTracker bjmodel;
    private boolean musicOn = true;
    private Hand dealerHand = null;
    private String serverUrl = "localhost";
    private int serverPort = 8080;
    private Socket conn;
    private BufferedReader input;
    private PrintWriter output;
    private final Gson gson = new Gson();
    private CompletableFuture<Void> serverListener;

    /**
     * Creates a controller that handles communication
     * between given model and view.
     * 
     * @param bjmodel The model object to link with the controller.
     * @param bjview The view object to link with the controller.
     * @throws IOException Errors that might occur within the communication.
     */
    public Controller(GameTracker bjmodel, GameWindow bjview) throws IOException {

        this.bjmodel = bjmodel;
        this.bjview = bjview;

        conn = new Socket(serverUrl, serverPort);
        input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        output = new PrintWriter(conn.getOutputStream(), true);

        serverListener = CompletableFuture.runAsync(() -> {
            try {
                listenForUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Setup menu background music
        SoundEffectModel bgplayer = new SoundEffectModel();
        bgplayer.playBackgroundSound("background.wav");
        musicOn = true;

        bjview.addJoinRoomBtnListener(e -> {
            String s = gson.toJson(new ConnectionSetup(bjview.getPlayerFieldText(), bjview.getRoomCode()));
            output.println(s);
            System.out.println(s);
            bjview.switchToGame();
        });

        bjview.addStartButtonListener(e -> output.println(gson.toJson(new RecievingCmd("start", 0))));

        bjview.addBetFieldListener(e -> {
            int bet = 0;
            try {
                bet = Integer.parseInt(bjview.getBetText());
            }catch (NumberFormatException err){
                return;
            }

            output.println(gson.toJson(new RecievingCmd("bet", bet)));
            SoundEffectModel.playSound("bet.wav");
        });

        //Controls for hit button
        bjview.addHitButtonListener(e -> {
            output.println(gson.toJson(new RecievingCmd("hit", 0)));
            SoundEffectModel.playSound("dealCard.wav");
        });

        // Controls for stand button
        bjview.addStandButtonListener(e -> {
                output.println(gson.toJson(new RecievingCmd("stand", 0)));
                SoundEffectModel.playSound("stand.wav");
        });

        // Controls for double down button
        bjview.addDoubleDownButtonListener(e -> {
            output.println(gson.toJson(new RecievingCmd("double down", 0)));
            SoundEffectModel.playSound("dealCard.wav");
        });

        // Controls for split button
        bjview.addSplitButtonListener(e -> {
            output.println(gson.toJson(new RecievingCmd("split", 0)));
            SoundEffectModel.playSound("dealCard.wav");
        });

        // Controls for music managing button in main menu
        bjview.addMusicButtonListener(e -> {
            if(musicOn){
                bgplayer.stopBackgroundSound();
                musicOn = false;
                bjview.toggleMusicOnButton(false);
            } else {
                bgplayer.playBackgroundSound("background.wav");
                musicOn = true;
                bjview.toggleMusicOnButton(true);
            }
            
        });

        // Controls for join room button in main menu
        bjview.addJoinRoomButtonListener(e -> bjview.switchToJoinRoom());

        // Controls for close game button in main menu
        bjview.addCloseButtonListener(e -> System.exit(0));

        // Controls for back button in join room panel
        bjview.addJoinBackButtonListener(e -> bjview.switchToMenu());

        // Controls rules button in menu
        bjview.addRulesButtonListener(e -> bjview.switchToRules());

        // Controls for back button in join room panel
        bjview.addRulesBackButtonListener(e -> bjview.switchToMenu());

        // Controls for create room button in main room
        bjview.addCreateRoomMenuBtnListener(e -> bjview.switchToCreateRoom());

        // Controls for create room button in create room
        bjview.addCreateRoomBtnListener(e -> {
            output.println(gson.toJson(new ConnectionSetup(bjview.getPlayer2ndFieldText(), null)));
            bjview.switchToGame();
        });

        // Controls for back button in create room panel
        bjview.addCreateRoomBackBtnListener(e -> bjview.switchToMenu());

        // Controls for back button in game room
        bjview.addRoomBackButtonListener(e -> {
            if(bjview.confirmExit()){
                try {
                    serverListener.cancel(true);
                    conn.close();
                    conn = new Socket(serverUrl, serverPort);
                    input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    output = new PrintWriter(conn.getOutputStream(), true);

                    serverListener = CompletableFuture.runAsync(() -> {
                        try {
                            listenForUpdate();
                        } catch (IOException err) {
                            err.printStackTrace();
                        }
                    });

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
                bjview.switchToMenu();
            } 
            
        });
    }
    
    /**
     * Gets the name of all the cards in a hand
     * 
     * @param h The hand to get all the name of cards from
     * @return ArrayList of all the names of cards in a hand
     */
    private ArrayList<String> getHandImageStrings(Hand h){
        ArrayList<String> imageList = new ArrayList<>();
        for(Card c : h.getHand()){
            imageList.add(c.toString());
        }
        return imageList;
    }

    /**
     * Constatly listen for sporadic updates from the server
     * and updates data and the view
     * 
     * @throws IOException Error message that might occur in the communication
     */
    private void listenForUpdate() throws IOException {
        while (!conn.isClosed()){
            String res = ReadFile();
            System.out.println(res);
            if(res.contains("errorType")){
                System.out.println(gson.fromJson(res, ErrorRes.class).toString());
                bjview.updateErrorMessage(gson.fromJson(res, ErrorRes.class).getError());
            } else {
                bjview.updateErrorMessage("");
                bjmodel.updateData(gson.fromJson(res, GameModel.class));
                System.out.println(bjmodel);

                bjview.setRoomCode(bjmodel.getRoomCode());

                dealerHand = bjmodel.getDealerHand();
                //bjview.getDrawnCardLabel().setText(bjmodel.getCurrentTurn().toString());
                
                bjview.setPlayerHandPoints(bjmodel.getCurrentTurn().getPoints());
                bjview.setPlayerName(bjmodel.getCurrentTurn().getPlayer().getUsername());
                bjview.setPlayerBet(bjmodel.getCurrentTurn().getBet());
                bjview.setPlayerChips(bjmodel.getCurrentTurn().getPlayer().getChips());

                bjview.setupUserCard(getHandImageStrings(bjmodel.getCurrentTurn()));
                bjview.setupDealerCard(getHandImageStrings(dealerHand), bjmodel.hasNext()); // true, hide first card

                if(!bjmodel.hasNext()){
                    bjview.setDealerHandPoints(dealerHand.getPoints()); //sätts i slutet ist
                } else {
                    bjview.setDealerHandPoints(-1);
                }

                List<String> players = new ArrayList<>();
                List<List<String>> handStrings = new ArrayList<>();

                for (Hand h: bjmodel.getTurnOrder()){
                    players.add(h.getPlayer().getUsername());
                    handStrings.add(getHandImageStrings(h));
                }

                bjview.setupTurnOrderGrid(players, handStrings, bjmodel.getTurnIndex());
            }
        }
    }

    /**
     * Parses the connections input stream. 
     * Returns value when the entire file has been read.
     * 
     * @return Parsed text from file.
     * @throws IOException Error message that might occur if file was read wrong
     */
    private String ReadFile() throws IOException {
        StringBuilder str = new StringBuilder();
        String line;
        int nests = 0;
        do {
            line = input.readLine();

            if(line == null){
                break;
            }

            str.append(line).append('\n');
            if(line.contains("{")){
                nests++;
            } if(line.contains("}")){
                nests--;
            }
        }while (nests > 0);

        return str.toString();
    }
}
