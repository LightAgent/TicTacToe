package model;

public class XOBoard implements Cloneable {
    private Marker[][] board;

    public XOBoard() {
        this.board = new Marker[3][3];
        initializeBoard();
    }
    public XOBoard(Marker[][] board) {
        this.board = board;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                this.board[i][j] = new Marker();
    }
    public void printBoard() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++)
                System.out.print(this.board[i][j].getMarkerType()+"   ");
            }
            System.out.println("");
        }

    public Marker[][] getBoard() {
        return board;
    }

    public Marker getMarker(int x, int y) {
        return board[x][y];
    }

    public void setMarker(int x, int y, MarkerType markerType) {
        board[x][y].setMarkerType(markerType);
    }
    public static class Memento {
        private XOBoard xoBoard;

        public Memento(XOBoard board) {
            this.xoBoard = board;
        }

        public XOBoard getXoBoard() {
            return xoBoard;
        }
    
    }
    @Override
    public Object clone(){
        Marker[][] newBoard = new Marker[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                newBoard[i][j] = new Marker(board[i][j].getMarkerType());
        return new XOBoard(newBoard);
    }
}
