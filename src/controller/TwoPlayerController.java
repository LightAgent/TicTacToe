package controller;

public class TwoPlayerController extends Controller{
    
	@Override
    protected void play(Position position) {
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
