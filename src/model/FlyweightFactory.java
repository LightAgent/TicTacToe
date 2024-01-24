package model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class FlyweightFactory {
    private static final Map <MarkerType,ImageIcon> imageIcon= new HashMap<>();
    public static ImageIcon createImage(MarkerType markerType){
        if(imageIcon.get(markerType) == null){
            imageIcon.put(markerType,new ImageIcon("images\\"+markerType.toString()+".png"));
        }
        return imageIcon.get(markerType);
    }
}
