package controller;

import model.MarkerType;
import model.Position;

public class SinglePlayerController extends Controller {

    private MarkerType checkerX = MarkerType.X;
    private MarkerType checkerO = MarkerType.O;
    private Position attack;
    private Position defense;
    private boolean attackFlag = false;
    private boolean defenseFlag = false;

    private void computerMove() {
        if (board.getMarker(1, 1).equals(MarkerType.EMPTY))
            board.setMarker(0, 0, MarkerType.O);
        else if ((checkRowAttack() || checkColumnAttack() || checkDigonalAttack())&&attackFlag) {
            board.setMarker(attack.getX(), attack.getY(), MarkerType.O);
        } else if ((checkRowDefinse() || checkColumnDefinse() || checkDigonalDefinse())&&defenseFlag) {
            board.setMarker(defense.getX(), defense.getY(), MarkerType.O);
        } else {
            randomAttack();
        }
        attackFlag = false;
        defenseFlag = false;
        if (checkWin()) {
            // winng opration
            gameEnded = true;
        }
        if (checkDraw()) {
            gameEnded = true;
            // draw
        }
    }

    @Override
    protected void play(Position position) {
        board.setMarker(position.getX(), position.getY(), xTurn ? MarkerType.X : MarkerType.O);
        // check win or draw
        if (checkWin()) {
            // winng opration
            gameEnded = true;
        }
        if (checkDraw()) {
            gameEnded = true;
            // draw
        }
        // computer's turn
        computerMove();
        toggleTurn();
        
    }

    private boolean checkRowDefinse() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 1).getMarkerType() == checkerX
                    && board.getMarker(i, 2).equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(2);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(i, 1).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 0).equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(0);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 1).equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(1);
                defenseFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnDefinse() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(0, i).getMarkerType() == checkerX &&
                    board.getMarker(1, i).getMarkerType() == checkerX
                    && board.getMarker(2, i).equals(MarkerType.EMPTY)) {
                defense.setX(2);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(0, i).getMarkerType() == checkerX &&
                    board.getMarker(2, i).getMarkerType() == checkerX
                    && board.getMarker(1, i).equals(MarkerType.EMPTY)) {
                defense.setX(1);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(1, i).getMarkerType() == checkerX &&
                    board.getMarker(2, i).getMarkerType() == checkerX
                    && board.getMarker(0, i).equals(MarkerType.EMPTY)) {
                defense.setX(0);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkDigonalDefinse() {
        if (board.getMarker(1, 1).getMarkerType() == checkerX) {
            if (board.getMarker(2, 2).getMarkerType() == checkerX) {
                defense.setX(0);
                defense.setY(0);
                defenseFlag = true;
                return true;
            } else if (board.getMarker(0, 0).getMarkerType() == checkerX) {
                defense.setX(2);
                defense.setY(2);
                defenseFlag = true;
                return true;
            } else if (board.getMarker(2, 0).getMarkerType() == checkerX) {
                defense.setX(0);
                defense.setY(2);
                defenseFlag = true;
                return true;
            } else if (board.getMarker(0, 2).getMarkerType() == checkerX) {
                defense.setX(2);
                defense.setY(0);
                defenseFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkRowAttack() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(i, 0).getMarkerType() == checkerO &&
                    board.getMarker(i, 1).getMarkerType() == checkerO
                    && board.getMarker(i, 2).equals(MarkerType.EMPTY)) {
                attack.setX(i);
                attack.setY(2);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(i, 1).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 0).equals(MarkerType.EMPTY)) {
                attack.setX(i);
                attack.setY(0);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 1).equals(MarkerType.EMPTY)) {
                attack.setX(i);
                attack.setY(1);
                attackFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnAttack() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(0, i).getMarkerType() == checkerO &&
                    board.getMarker(1, i).getMarkerType() == checkerO
                    && board.getMarker(2, i).equals(MarkerType.EMPTY)) {
                attack.setX(2);
                attack.setY(i);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(0, i).getMarkerType() == checkerO &&
                    board.getMarker(2, i).getMarkerType() == checkerO
                    && board.getMarker(1, i).equals(MarkerType.EMPTY)) {
                attack.setX(1);
                attack.setY(i);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(1, i).getMarkerType() == checkerO &&
                    board.getMarker(2, i).getMarkerType() == checkerO
                    && board.getMarker(0, i).equals(MarkerType.EMPTY)) {
                attack.setX(0);
                attack.setY(i);
                attackFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkDigonalAttack() {
        if (board.getMarker(1, 1).getMarkerType() == checkerO) {
            if (board.getMarker(2, 2).getMarkerType() == checkerO) {
                attack.setX(0);
                attack.setY(0);
                attackFlag = true;
                return true;
            } else if (board.getMarker(0, 0).getMarkerType() == checkerO) {
                attack.setX(2);
                attack.setY(2);
                attackFlag = true;
                return true;
            } else if (board.getMarker(2, 0).getMarkerType() == checkerO) {
                attack.setX(0);
                attack.setY(2);
                attackFlag = true;
                return true;
            } else if (board.getMarker(0, 2).getMarkerType() == checkerO) {
                attack.setX(2);
                attack.setY(0);
                attackFlag = true;
                return true;
            }
        }
        return false;
    }

    private void randomAttack() {
        if (board.getMarker(0,0).equals(MarkerType.EMPTY) )
            board.setMarker(0, 0, MarkerType.O);
        else if (board.getMarker(2,2).equals(MarkerType.EMPTY) )
            board.setMarker(2, 2, MarkerType.O);
        else if (board.getMarker(2,0).equals(MarkerType.EMPTY) )
            board.setMarker(2, 0, MarkerType.O);  
        else if (board.getMarker(0,2).equals(MarkerType.EMPTY) )

            board.setMarker(1, 2, MarkerType.O);
        else if (board.getMarker(1,2).equals(MarkerType.EMPTY) )
            board.setMarker(0, 1, MarkerType.O);
        else if (board.getMarker(0,1).equals(MarkerType.EMPTY) )
    
        for (int i = 0; i < 3; i++) {
            board.setMarker(1, i, MarkerType.O);
            }
        }
    }
