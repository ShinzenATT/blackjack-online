package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.objects.Card.Rank;

/**
 * Class that tries to read custom images for all the playing cards,
 * linking images to set names in a hashmap making it easier to use them later.
 * @author Joel
 * @version 2022-03-07
 */
public class ImageDisplayModel {
    static private Map<String, Image> IMGS = new HashMap<String, Image>();

    /**
     * Creates a ImageDisplayModel object where all custom images for
     * the playing cards are read and linked to a specific given name in a hashmap.
     */
    public ImageDisplayModel() {
        HashMap<String, Image> imgs = new HashMap<String, Image>();
        String png = ".png";

        for (String suit = "diamonds", name; ;) {
            for (Rank rank : Rank.values()) {
                name = (rank + "_of_" + suit).toLowerCase();

                try {
                    System.out.println(getClass().getResource(name + png).getFile());
                    imgs.put(
                        name,
                        ImageIO.read((getClass().getResourceAsStream(name + png)))
                    );
                } catch (IOException e) {
                    System.out.println(
                        "Exception <" + e.toString() + "> for " + name
                    );
                    System.out.println("Working Directory = " + System.getProperty("user.dir"));
                }
            }
    
            if (suit.equals("diamonds")) {
                suit = "clubs";
            } else if (suit.equals("clubs")) {
                suit = "hearts";
            } else if (suit.equals("hearts")) {
                suit = "spades";
            } else {
                break;
            }
        }
        try {
            imgs.put("back", ImageIO.read(getClass().getResourceAsStream("back.png")));
        } catch (IOException e) {
            System.out.println(
                "Can't find image of back card."
            );
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
        }
        IMGS = Collections.unmodifiableMap(imgs);
    }

    /**
     * Given the name linked with an read image, return the image from the hashmap.
     *
     * @param name The specificly given name that associate with a read image.
     * @return The image that was linked with given name.
     */
    public Image getImageFromName(String name){
        return IMGS.get(name.toLowerCase());
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
    public Image getScaledImageInstanceFromName(String name, int labelWidth, int labelHeight){
        return getImageFromName(name).getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
    }
}