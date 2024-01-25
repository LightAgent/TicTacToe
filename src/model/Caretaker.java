package model;

import java.util.Stack;

import model.XOBoard.Memento;

public class Caretaker {
    private Stack<Memento> undo;
    private Stack<Memento> redo;

    public Caretaker() {
        undo = new Stack<>();
        redo = new Stack<>();
    }

    public void saveMove(Memento board) {
        undo.push(board);
        redo.clear();
    }

    public Memento undo() {
        if (!undo.isEmpty()) {
            Memento board = undo.pop();
            redo.push(board);
            return board;
        } else {
            return null;
        }
    }
    public Memento redo(){
        if(!redo.isEmpty()){
            Memento board = redo.pop();
            undo.push(board);
            return board;
        }
        return null;
    }
}
