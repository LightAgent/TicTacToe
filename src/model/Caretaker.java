package model;

import java.util.Stack;

import model.XOBoard.Memento;

public class Caretaker {
    private Stack<Memento> undo;

    public Caretaker() {
        undo = new Stack<>();
    }

    public void saveMove(Memento board) {
        undo.push(board);
    }

    public Memento undo() {
        if (!undo.isEmpty()) {
            Memento board = undo.pop();
            return board;
        } else {
            return null;
        }
    }
    
}
