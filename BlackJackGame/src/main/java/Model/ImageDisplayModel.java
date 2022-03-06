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

public class ImageDisplayModel {
    static private Map<String, Image> IMGS = new HashMap<String, Image>();

    public ImageDisplayModel() {
        HashMap<String, Image> imgs = new HashMap<String, Image>();

        String dir = "BlackJackGame/src/main/resources/";
        String png = ".png";
    

        for (String suit = "diamonds", name; ;) {
            for (Rank rank : Rank.values()) {
                name = (rank + "_of_" + suit).toLowerCase();

                try {
                    imgs.put(
                        name,
                        ImageIO.read(new File(dir + name + png))
                    );
                } catch (IOException e) {
                    System.out.println(
                        "Exception <" + e.toString() + "> for " + name
                    );
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
            imgs.put("back", ImageIO.read(new File("BlackJackGame/src/main/resources/back.png")));
        } catch (IOException e) {
            System.out.println(
                "Can't find image of back card."
            );
        }
        IMGS = Collections.unmodifiableMap(imgs);
    }

    public Image getImageFromName(String name){
        return IMGS.get(name.toLowerCase());
    }

    public Image getScaledImageInstanceFromName(String name, int labelWidth, int labelHeight){
        return getImageFromName(name).getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
    }
}