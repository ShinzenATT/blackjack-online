package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class that tries to read custom images, linking images to
 * set names in a hashmap making it easier to use them later.
 * @author Joel
 * @version 2022-03-07
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
            imgs.put("hitButton", ImageIO.read(getClass().getResourceAsStream("hitButton.png")));
            imgs.put("hitButtonRollover", ImageIO.read(getClass().getResourceAsStream("hitButtonRollover.png")));
            imgs.put("doubleDownButton", ImageIO.read(getClass().getResourceAsStream("doubleDownButton.png")));
            imgs.put("doubleDownButtonRollover", ImageIO.read(getClass().getResourceAsStream("doubleDownButtonRollover.png")));
            imgs.put("standButton", ImageIO.read(getClass().getResourceAsStream("standButton.png")));
            imgs.put("standButtonRollover", ImageIO.read(getClass().getResourceAsStream("standButtonRollover.png")));
            imgs.put("splitButton", ImageIO.read(getClass().getResourceAsStream("splitButton.png")));
            imgs.put("musicOnButton", ImageIO.read(getClass().getResourceAsStream("musicOn.png")));
            imgs.put("musicOffButton", ImageIO.read(getClass().getResourceAsStream("musicOff.png")));
            imgs.put("joinServerButton", ImageIO.read(getClass().getResourceAsStream("joinServer.png")));
            imgs.put("joinServerButtonRollover", ImageIO.read(getClass().getResourceAsStream("joinServerRollover.png")));
            imgs.put("createServerButton", ImageIO.read(getClass().getResourceAsStream("createServer.png")));
            imgs.put("createServerButtonRollover", ImageIO.read(getClass().getResourceAsStream("createServerRollover.png")));
            imgs.put("rulesButton", ImageIO.read(getClass().getResourceAsStream("rules.png")));
            imgs.put("rulesButtonRollover", ImageIO.read(getClass().getResourceAsStream("rulesRollover.png")));
            imgs.put("exitButton", ImageIO.read(getClass().getResourceAsStream("exit.png")));
            imgs.put("exitButtonRollover", ImageIO.read(getClass().getResourceAsStream("exitRollover.png")));
            imgs.put("singlePlayerButton", ImageIO.read(getClass().getResourceAsStream("singlePlayer.png")));
            imgs.put("singlePlayerButtonRollover", ImageIO.read(getClass().getResourceAsStream("singlePlayerRollover.png")));
            imgs.put("blackJackLogo", ImageIO.read(getClass().getResourceAsStream("blackJackLogo.png")));
            imgs.put("backButton", ImageIO.read(getClass().getResourceAsStream("backButton.png")));
            imgs.put("backButtonRollover", ImageIO.read(getClass().getResourceAsStream("backButtonRollover.png")));
            imgs.put("emptyButton", ImageIO.read(getClass().getResourceAsStream("emptyButton.png")));
            imgs.put("emptyButtonRollover", ImageIO.read(getClass().getResourceAsStream("emptyButtonRollover.png")));
            imgs.put("startButton", ImageIO.read(getClass().getResourceAsStream("startButton.png")));
            imgs.put("startButtonRollover", ImageIO.read(getClass().getResourceAsStream("startButtonRollover.png")));
            imgs.put("splitButton", ImageIO.read(getClass().getResourceAsStream("splitButton.png")));
            imgs.put("splitButtonRollover", ImageIO.read(getClass().getResourceAsStream("splitButtonRollover.png")));
            imgs.put("splitUnavailableButtonRollover", ImageIO.read(getClass().getResourceAsStream("splitUnavailableButton.png")));
            imgs.put("betEmpty", ImageIO.read(getClass().getResourceAsStream("betEmpty.png")));
        } catch (IOException e) {
            System.out.println(
                "Can't find all the images for the buttons."
            );
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
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
