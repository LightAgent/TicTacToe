package model;

public class XOBoard {
    private Marker[][] board;

    public XOBoard() {
        this.board = new Marker[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                this.board[i][j] = new Marker();
    }

    public Marker[][] getBoard() {
        return board;
    }

    public Marker getMarker(int x, int y) {
        return board[x][y];
    }

    public void setMarker(int x, int y, Marker marker) {
        board[x][y] = marker;
    }
}