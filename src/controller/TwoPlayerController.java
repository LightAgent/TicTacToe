package controller;
import model.*;

public class TwoPlayerController extends Controller{
    
    public TwoPlayerController(Caretaker caretaker){
        super(caretaker);
    }

	@Override
    public GameState play(Position position) {
        System.out.println("Position X: "+position.getX()+" Y: "+position.getY());
        saveMove();        
        board.setMarker(position.getX(),position.getY(),xTurn? MarkerType.X :MarkerType.O );
        remainingSquares--;
        // check win or draw 
        if(checkWin()){
            System.out.println("Player WON!");
            return GameState.WON;
        }
        if(checkDraw()){
            System.out.println("Draw!");
            return GameState.DRAW;
        }
        toggleTurn();
        return GameState.IN_PROGRESS;
    }
}
