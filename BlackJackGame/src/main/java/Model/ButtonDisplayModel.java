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

/**
 * Class that tries to read custom images, linking images to
 * set names in a hashmap making it easier to use them later.
 */
public class ButtonDisplayModel {
    static private Map<String, Image> BIMGS = new HashMap<String, Image>();

    /**
     * Creates a ButtonDisplayModel object where all custom images are
     * read and linked to a specific given name in a hashmap. 
     */    
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
            imgs.put("emptyButton", ImageIO.read(new File("BlackJackGame/src/main/resources/emptyButton.png")));
            imgs.put("emptyButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/emptyButtonRollover.png")));
            imgs.put("startButton", ImageIO.read(new File("BlackJackGame/src/main/resources/startButton.png")));
            imgs.put("startButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/startButtonRollover.png")));
            imgs.put("splitButton", ImageIO.read(new File("BlackJackGame/src/main/resources/splitButton.png")));
            imgs.put("splitButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/splitButtonRollover.png")));
            imgs.put("splitUnavailableButtonRollover", ImageIO.read(new File("BlackJackGame/src/main/resources/splitUnavailableButton.png")));
            imgs.put("betEmpty", ImageIO.read(new File("BlackJackGame/src/main/resources/betEmpty.png")));
        } catch (IOException e) {
            System.out.println(
                "Can't find all the images for the buttons."
            );
        }
        BIMGS = Collections.unmodifiableMap(imgs);
    }

    /** 
     * Given the name linked with an read image, return the image from the hashmap.
     * 
     * @param name The specificly given name that associate with a read image.
     * @return The image that was linked with given name.
     */
    public Image getButtonImageFromName(String name){
        return BIMGS.get(name);
    }

    /** 
     * 
     * Given the name linked with an read image, scale it according
     * to given width and height before returning the image.
     * 
     * @param name The specificly given name that associate with a read image.
     * @param labelWidth The wanted width of the image.
     * @param labelHeight The wanted height of the image.
     * @return The scaled image that was linked with given name.
     */
    public Image getScaledButtonImageInstanceFromName(String name, int labelWidth, int labelHeight){
        return getButtonImageFromName(name).getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
    }
}
