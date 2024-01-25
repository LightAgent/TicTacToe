package controller;
import model.*;
import model.XOBoard.Memento;

public abstract class Controller {

    protected boolean xTurn = true;
    protected boolean gameEnded;
    protected XOBoard board = new XOBoard();
    protected int remainingSquares = 9;
    protected Caretaker caretaker;

    abstract public GameState play(Position position);

    public Controller(Caretaker caretaker){
        this.caretaker = caretaker;
    }
    public void undoLastMove(){
        Memento lastMove = caretaker.undo();
        board = lastMove.getXoBoard();
        // System.out.println("UNDOING =>>>>>>>>>>>>.");
        // board.printBoard();
        System.out.println(xTurn);
        toggleTurn();
    }
    public void saveMove(){
        // board.printBoard();
        Memento memento = new Memento((XOBoard) board.clone());
        caretaker.saveMove(memento);
    }
    protected boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((board.getMarker(i, 0).getMarkerType() == board.getMarker(i, 1).getMarkerType() && board.getMarker(i, 1).getMarkerType() == board.getMarker(i, 2).getMarkerType())&& board.getMarker(i, 0).getMarkerType()!= MarkerType.EMPTY ||
                    (board.getMarker(0, i).getMarkerType() == board.getMarker(1, i).getMarkerType() && board.getMarker(0, i).getMarkerType() == board.getMarker(2, i).getMarkerType())&& board.getMarker(0, i).getMarkerType()!= MarkerType.EMPTY)
                return true;
        }
        if ((board.getMarker(0, 0).getMarkerType() == board.getMarker(1, 1).getMarkerType() && board.getMarker(1, 1).getMarkerType() == board.getMarker(2, 2).getMarkerType())&& board.getMarker(2, 2).getMarkerType()!= MarkerType.EMPTY ||
                (board.getMarker(0, 2).getMarkerType() == board.getMarker(1, 1).getMarkerType() && board.getMarker(0, 2).getMarkerType() == board.getMarker(2, 0).getMarkerType())&& board.getMarker(2, 0).getMarkerType()!= MarkerType.EMPTY)
            return true;
        return false;
    }

    protected boolean checkDraw() {
        return remainingSquares == 0;
    }

    public void toggleTurn() {
        xTurn = ! xTurn;
    }
    
    public MarkerType getCurrentTurn(){
        return xTurn ? MarkerType.X : MarkerType.O;
    }
    public XOBoard getBoard(){
        return board;
    }
    public Marker getMarker(Position position){
        return board.getMarker(position.getX(), position.getY());
    }
    public GameState getGameState(){
        if (checkWin()) {
            return  GameState.WON;
        }
        else if (checkDraw()) {
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }
    
}
