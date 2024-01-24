package controller;
import model.MarkerType;

public class SinglePlayerController extends Controller {
    public void gameFlow() {
        if (board.getMarker(1, 1).equals(MarkerType.EMPTY))
            caseOne();
        else
            caseTwo();
    }

    public void caseOne() {
        while (!gameEnded && validSqu-- != 0) {

        }
    }

    public void caseTwo() {
        while (!gameEnded && validSqu-- != 0) {

        }
    }

}
