package controller;
import model.*;
public class TwoPlayerController extends Controller{
    
	@Override
    public void play(Position position) {
        System.out.println("Position X: "+position.getX()+" Y: "+position.getY());
        board.setMarker(position.getX(),position.getY(),xTurn? MarkerType.X :MarkerType.O );
        // check win or draw 
        if(checkWin()){
            // winng opration 
        }
        if(checkDraw()){
            // draw
        }
        toggleTurn();
    }
}
