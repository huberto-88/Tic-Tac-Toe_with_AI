package tictactoe;

public class Board {
    private final char[][] board;
    private final int boardSize;

    public Board() {
        this.board = new char[5][5];
        createEmptyBoard();
        this.boardSize = board.length - 2;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void displayBoard() {
        for (char[] c : board) {
            for (char cell : c) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private void createEmptyBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (i == 0 || i == board.length - 1) {
                    board[i][j] = '-';
                } else if (j == 0 || j == board.length - 1) {
                    board[i][j] = '|';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public void getMove(char player, int x, int y) {
        board[x][y] = player;
    }

    public boolean whoseMove() {
        int o = 0;
        int x = 0;
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {
                if (board[i][j] == 'X') {
                    x++;
                }
                if (board[i][j] == 'O') {
                    o++;
                }
            }
        }
        return o >= x;
    }
}
