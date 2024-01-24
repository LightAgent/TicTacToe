package controller;
import model.*;

public abstract class Controller {

    protected boolean xTurn = true;
    protected boolean gameEnded;
    protected XOBoard board = new XOBoard();
    protected int validSqu = 9;

    protected boolean checkWin() {

        for (int i = 0; i < 3; i++) {
            if (board.getMarker(i, 0) == board.getMarker(i, 1) && board.getMarker(i, 1) == board.getMarker(i, 2) ||
                    board.getMarker(0, i) == board.getMarker(1, i) && board.getMarker(0, i) == board.getMarker(2, i))
                return true;
        }
        if (board.getMarker(0, 0) == board.getMarker(1, 1) && board.getMarker(1, 1) == board.getMarker(2, 2) ||
                board.getMarker(0, 2) == board.getMarker(1, 1) && board.getMarker(0, 2) == board.getMarker(2, 0))
            return true;
        return false;
    }

    protected boolean checkDraw() {
        return validSqu == 0;
    }

    protected void toggleTurn() {
        xTurn = ! xTurn;
    }
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
