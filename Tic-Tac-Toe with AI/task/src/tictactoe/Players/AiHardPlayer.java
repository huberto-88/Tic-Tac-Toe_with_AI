package tictactoe.Players;

import tictactoe.Board;
import tictactoe.PlayerType;

import java.util.List;
import java.util.Scanner;

public class AiHardPlayer implements PlayerType {
    private Scanner scanner = new Scanner(System.in);
    private char[][] copyBoard = new char[3][3];
    private Board board;
    private char player;
    private char opponent;

    public AiHardPlayer(Board board) {
        this.board = board;
    }

    @Override
    public String getMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copyBoard[i][j] = board.getBoard()[i+1][j+1];
            }
        }

        this.player = board.whoseMove() ? 'X' : 'O';
        this.opponent = !board.whoseMove() ? 'X' : 'O';
        return findBestMove(copyBoard);
    }

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    private int minimax(char board[][], int depth, boolean isMax) {
        int score = evaluate(board);

        // if maximizer has won the game return his evaluated score
        if (score == 10) {
            return score;
        }

        // if minimizer has won the game return his evaluated score
        if (score == -10) {
            return score;
        }

        // if there are no more moves and no winner then it is a draw
        if (isMovesLeft(board) == false) {
            return 0;
        }

        // if this maximizer's move
        if (isMax) {
            int best = -1000000;

            //traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //check if cell is empty
                    if (board[i][j] == ' ') {
                        //Make the move
                        board[i][j] = player;

                        //call minimax recursively and choose the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        //undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
        // if the minimizer's move
        else {
            int best = 1000000;

            // traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //check if cell is empty
                    if (board[i][j] == ' ') {
                        //make the move
                        board[i][j] = opponent;

                        // call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax));

                        //undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

    }

    // this will return the best possible move for the player
    private String findBestMove(char board[][]) {
        int bestVal = -1000000;
        String coordinates = null;

        // traverse all cells, evaluate minimax function
        // for all empty cells and return the cel with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //check if cell is empty
                if (board[i][j] == ' ') {
                    //make the move
                    board[i][j] = player;

                    // compute evaluation for this move
                    int moveVal = minimax(board, 0, false);

                    // undo the move
                    board[i][j] = ' ';

                    // if the value of the current move is more than
                    // the best value, then update best
                    // coordinates have to be +1 because original board is 1 - 3
                    if (moveVal > bestVal) {
                        coordinates = (i+1) + " " + (j+1);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return coordinates;
    }


    // This function returns true if there are moves
    // remaining on the board. It returns false if
    // there are no moves left to play.
    private boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    // Evaluation function
    private int evaluate(char b[][]) {
        // checking for rows for X or O victory
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player) {
                    return +10;
                } else if (b[row][0] == opponent) {
                    return -10;
                }
            }
        }

        // checking for columns for X or O victory
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player) {
                    return +10;
                } else if (b[0][col] == opponent) {
                    return -10;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }
}
