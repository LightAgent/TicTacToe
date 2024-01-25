package controller;

import model.GameState;
import model.MarkerType;
import model.Position;

public class SinglePlayerController extends Controller {

    private MarkerType checkerX = MarkerType.X;
    private MarkerType checkerO = MarkerType.O;
    private Position attack = new Position(-1, -1);;
    private Position defense = new Position(-1, -1);;
    private boolean attackFlag = false;
    private boolean defenseFlag = false;

    private void computerMove() {
        if (board.getMarker(1, 1).getMarkerType().equals(MarkerType.EMPTY)){
            board.setMarker(1, 1, MarkerType.O);
            System.out.println("Center");
        }
        else if ((checkRowAttack() || checkColumnAttack() || checkDigonalAttack())&&attackFlag) {
            board.setMarker(attack.getX(), attack.getY(), MarkerType.O);
            System.out.println("Attack");
        } else if ((checkRowDefense() || checkColumnDefense() || checkDigonalDefense())&&defenseFlag) {
            board.setMarker(defense.getX(), defense.getY(), MarkerType.O);
            System.out.println("Defense");
        } else {
            randomAttack();
            System.out.println("Random ATTACK");
        }
        attackFlag = false;
        defenseFlag = false;
        
    }

    @Override
    public GameState play(Position position) {
        board.setMarker(position.getX(), position.getY(),MarkerType.X);
        remainingSquares--;
        // check game state
        if(getGameState()!=GameState.IN_PROGRESS) return getGameState();
        
        // computer's turn
        computerMove();
        remainingSquares--;
        if(getGameState()!=GameState.IN_PROGRESS) return getGameState();

        toggleTurn();

        return getGameState();
    }

    private boolean checkRowDefense() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 1).getMarkerType() == checkerX
                    && board.getMarker(i, 2).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(2);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(i, 1).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 0).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(0);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 1).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(i);
                defense.setY(1);
                defenseFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnDefense() {
        for (int i = 0; i < 3; i++) {
            if (board.getMarker(0, i).getMarkerType() == checkerX &&
                    board.getMarker(1, i).getMarkerType() == checkerX
                    && board.getMarker(2, i).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(2);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(0, i).getMarkerType() == checkerX &&
                    board.getMarker(2, i).getMarkerType() == checkerX
                    && board.getMarker(1, i).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(1);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
            if (board.getMarker(1, i).getMarkerType() == checkerX &&
                    board.getMarker(2, i).getMarkerType() == checkerX
                    && board.getMarker(0, i).getMarkerType().equals(MarkerType.EMPTY)) {
                defense.setX(0);
                defense.setY(i);
                defenseFlag = true;
                return true;
            }
        }
        return false;
    }

    private boolean checkDigonalDefense() {
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
                    && board.getMarker(i, 2).getMarkerType().equals(MarkerType.EMPTY)) {
                attack.setX(i);
                attack.setY(2);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(i, 1).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 0).getMarkerType().equals(MarkerType.EMPTY)) {
                attack.setX(i);
                attack.setY(0);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(i, 0).getMarkerType() == checkerX &&
                    board.getMarker(i, 2).getMarkerType() == checkerX
                    && board.getMarker(i, 1).getMarkerType().equals(MarkerType.EMPTY)) {
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
                    && board.getMarker(2, i).getMarkerType().equals(MarkerType.EMPTY)) {
                attack.setX(2);
                attack.setY(i);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(0, i).getMarkerType() == checkerO &&
                    board.getMarker(2, i).getMarkerType() == checkerO
                    && board.getMarker(1, i).getMarkerType().equals(MarkerType.EMPTY)) {
                attack.setX(1);
                attack.setY(i);
                attackFlag = true;
                return true;
            }
            if (board.getMarker(1, i).getMarkerType() == checkerO &&
                    board.getMarker(2, i).getMarkerType() == checkerO
                    && board.getMarker(0, i).getMarkerType().equals(MarkerType.EMPTY)) {
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
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board.getBoard()[i][j].getMarkerType()==MarkerType.EMPTY){
                    board.setMarker(i, j, MarkerType.O);
                    return;
                }
            }
        }
    
    }
}
