package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import Model.GameTracker;
import Model.SoundEffectModel;
import Model.json_data.ConnectionSetup;
import Model.json_data.ErrorRes;
import Model.json_data.GameModel;
import Model.json_data.RecievingCmd;
import Model.objects.Card;
import Model.objects.Dealer;
import Model.objects.Hand;
import View.GameWindow;
import com.google.gson.Gson;

public class Controller {

    private final GameWindow bjview;
    private final GameTracker bjmodel;
    //private Hand currentTurn = null;
    private boolean musicOn = true;
    private Hand dealerHand = null;
    private String serverUrl = "localhost";
    private int serverPort = 8080;
    Socket conn;
    BufferedReader input;
    PrintWriter output;
    Gson gson = new Gson();

    public Controller(GameTracker bjmodel, GameWindow bjview) throws IOException {
        this.bjmodel = bjmodel;
        this.bjview = bjview;

        conn = new Socket(serverUrl, serverPort);
        input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        output = new PrintWriter(conn.getOutputStream(), true);

        CompletableFuture.runAsync(() -> {
            try {
                listenForUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // setup menu background music
        SoundEffectModel bgplayer = new SoundEffectModel();
        bgplayer.playBackgroundSound("background.wav");
        musicOn = true;

        //dealerHand = bjmodel.getDealerHand();

        //currentTurn = bjmodel.next();

        bjview.addRoomFieldListener(e -> {
            String s = gson.toJson(new ConnectionSetup(bjview.getPlayerFieldText(), bjview.getRoomCode()));
            output.println(s);
            System.out.println(s);
            bjview.switchPanel();
        });

        bjview.addCreateRoomListener(e -> {
            String s = gson.toJson(new ConnectionSetup(bjview.getPlayerFieldText(), null));
            output.println(s);
            System.out.println(s);
            bjview.switchPanel();
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
        });

        //Controls for hit button
        bjview.addHitButtonListener(e -> {
            output.println(gson.toJson(new RecievingCmd("hit", 0)));
            //bjview.getDrawnCardLabel().setText(bjmodel.getCurrentTurn().toString());
            //bjview.setPlayerHandPoints(bjmodel.getCurrentTurn().getPoints());
            SoundEffectModel.playSound("dealCard.wav");
        });

    // Controls for stand button
    bjview.addStandButtonListener(e -> {
            output.println(gson.toJson(new RecievingCmd("stand", 0)));
            //currentTurn = bjmodel.next();
            /*bjview.getDrawnCardLabel().setText(bjmodel.getCurrentTurn().toString());
            bjview.setPlayerName(bjmodel.getCurrentTurn().getPlayer().getUsername());
            bjview.setPlayerHandPoints(bjmodel.getCurrentTurn().getPoints());
            bjview.setPlayerChips(bjmodel.getCurrentTurn().getBet());*/
            /*if(!bjmodel.hasNext()){
                /*while(currentTurn.getPoints() <= 16){
                    currentTurn.addCard(bjmodel.nextCard());
                }
                bjview.clearDealerLabels();
                bjview.setupDealerCard(getHandImageStrings(dealerHand), false);
                bjview.setDealerHandPoints(bjmodel.getCurrentTurn().getPoints());
            }*/
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

    // Controls for close game button in main menu
    bjview.addCloseButtonListener(e -> {
        System.exit(0);
    });
    }
    
    private ArrayList<String> getHandImageStrings(Hand h){
        ArrayList<String> imageList = new ArrayList<>();
        for(Card c : h.getHand()){
            imageList.add(c.toString());
        }
        return imageList;
    }

    private void listenForUpdate() throws IOException {
        while (!conn.isClosed()){
            String res = ReadFile();
            System.out.println(res);
            if(res.contains("errorType")){
                System.out.println(gson.fromJson(res, ErrorRes.class).toString());
            } else {
                bjmodel.updateData(gson.fromJson(res, GameModel.class));
                System.out.println(bjmodel);

                dealerHand = bjmodel.getDealerHand();
                bjview.getDrawnCardLabel().setText(bjmodel.getCurrentTurn().toString());
                bjview.setPlayerName(bjmodel.getCurrentTurn().getPlayer().getUsername());
                bjview.setPlayerHandPoints(bjmodel.getCurrentTurn().getPoints());
                bjview.setDealerHandPoints(dealerHand.getPoints()); //sätts i slutet ist
                bjview.setPlayerChips(bjmodel.getCurrentTurn().getBet());

                bjview.setupUserCard(getHandImageStrings(bjmodel.getCurrentTurn()));

                bjview.setupDealerCard(getHandImageStrings(dealerHand), bjmodel.hasNext()); // true, hide first card
            }
        }
    }

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
