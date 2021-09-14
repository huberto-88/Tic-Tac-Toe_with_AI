package tictactoe.Players;

import tictactoe.Board;
import tictactoe.PlayerType;

import java.util.List;
import java.util.Random;

public class AiMediumPlayer implements PlayerType {
    private final List<String> availableMoves;
    private final Board board;
    private char player;
    private char opponent;

    public AiMediumPlayer(List<String> availableMoves, Board board) {
        this.availableMoves = availableMoves;
        this.board = board;
        if (board.whoseMove()) {
            this.player = 'X';
            this.opponent = 'O';
        } else {
            this.player = 'O';
            this.opponent = 'X';
        }
    }

    @Override
    public String getMove() {
        System.out.println("Making move level \"medium\"");

        for (String coordinates : availableMoves) {
            if (findPossibleViningCoordinates(player, coordinates)) {
                availableMoves.remove(coordinates);
                return coordinates;
            } else if (findPossibleViningCoordinates(opponent, coordinates)) {
                availableMoves.remove(coordinates);
                return coordinates;
            }
        }
        String coordinates = availableMoves.get(new Random()
                .nextInt(availableMoves.size()));
        availableMoves.remove(coordinates);
        return coordinates;
    }

    private boolean findPossibleViningCoordinates(char player, String coordinatesToCheck) {
        int x = Integer.parseInt(coordinatesToCheck.split("\\s+")[0]);
        int y = Integer.parseInt(coordinatesToCheck.split("\\s+")[1]);
        int allCharsTheSame = player * 3;
        int horizontalResult = 0;
        int verticalResult = 0;
        int diagonalResult1 = 0;
        int diagonalResult2 = 0;

        for (int i = 1; i < board.getBoardSize() + 1; i++) {
            for (int j = 1; j < board.getBoardSize() + 1; j++) {
                char onBoard = board.getBoard()[i][j];
                if (i == x && j == y) {
                    onBoard = player;
                }
                horizontalResult += onBoard;
                verticalResult += onBoard;
                if (i == j) {
                    diagonalResult1 += onBoard;
                }
                if (i == 1 && j == 3 || i == 2 && j == 2 || i == 3 && j == 1) {
                    diagonalResult2 += onBoard;
                }
            }
            if (horizontalResult == allCharsTheSame || verticalResult == allCharsTheSame
                    || diagonalResult1 == allCharsTheSame || diagonalResult2 == allCharsTheSame) {
                return true;
            } else {
                horizontalResult = 0;
                verticalResult = 0;
            }
        }
        return false;
    }

}
