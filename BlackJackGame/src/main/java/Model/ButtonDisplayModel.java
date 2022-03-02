package Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import Model.objects.*;
import Model.objects.Card.Rank;

public class ButtonDisplayModel {
    static private Map<String, Image> BIMGS = new HashMap<String, Image>();

    public ButtonDisplayModel() {
        HashMap<String, Image> imgs = new HashMap<String, Image>();

        try {
            imgs.put("hitButton", ImageIO.read(new File("BlackJackGame/src/main/resources/hitButton.png")));
            imgs.put("hitButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/hitButtonRollover.png")));
            imgs.put("doubleDownButton", ImageIO.read(new File("BlackJackGame/src/main/resources/doubleDownButton.png")));
            imgs.put("doubleDownButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/doubleDownButtonRollover.png")));
            imgs.put("standButton", ImageIO.read(new File("BlackJackGame/src/main/resources/standButton.png")));
            imgs.put("standButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/standButtonRollover.png")));
            imgs.put("splitButton", ImageIO.read(new File("BlackJackGame/src/main/resources/splitButton.png")));
            imgs.put("musicOnButton", ImageIO.read(new File("BlackJackGame/src/main/resources/musicOn.png")));
            imgs.put("musicOffButton", ImageIO.read(new File("BlackJackGame/src/main/resources/musicOff.png")));
            imgs.put("joinServerButton", ImageIO.read(new File("BlackJackGame/src/main/resources/joinServer.png")));
            imgs.put("joinServerButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/joinServerRollover.png")));
            imgs.put("createServerButton", ImageIO.read(new File("BlackJackGame/src/main/resources/createServer.png")));
            imgs.put("createServerButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/createServerRollover.png")));
            imgs.put("rulesButton", ImageIO.read(new File("BlackJackGame/src/main/resources/rules.png")));
            imgs.put("rulesButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/rulesRollover.png")));
            imgs.put("exitButton", ImageIO.read(new File("BlackJackGame/src/main/resources/exit.png")));
            imgs.put("exitButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/exitRollover.png")));
            imgs.put("singlePlayerButton", ImageIO.read(new File("BlackJackGame/src/main/resources/singlePlayer.png")));
            imgs.put("singlePlayerButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/singlePlayerRollover.png")));
            imgs.put("blackJackLogo", ImageIO.read(new File("BlackJackGame/src/main/resources/blackJackLogo.png")));
            imgs.put("backButton", ImageIO.read(new File("BlackJackGame/src/main/resources/backButton.png")));
            imgs.put("backButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/backButtonRollover.png")));
        } catch (IOException e) {
            System.out.println(
                "Can't find all the images for the buttons."
            );
        }
        BIMGS = Collections.unmodifiableMap(imgs);
    }

    public Image getButtonImageFromName(String name){
        return BIMGS.get(name);
    }

    public Image getScaledButtonImageInstanceFromName(String name, int labelWidth, int labelHeight){
        return getButtonImageFromName(name).getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
    }
}
