package model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class FlyweightFactory {
    private static final Map <MarkerType,ImageIcon> imageIcon= new HashMap<>();
    public static ImageIcon createImage(MarkerType markerType){
        if(imageIcon.get(markerType) == null){
            imageIcon.put(markerType,new ImageIcon("images\\"+markerType.toString()+".png"));
        }
        Image img = imageIcon.get(markerType).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        return scaledIcon;
    }
}
