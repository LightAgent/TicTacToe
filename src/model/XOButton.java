package model;

import javax.swing.JButton;

public class XOButton extends JButton {
    private Position position;

    public XOButton(int x,int y){
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
}
