package tictactoe;

public class Validator {
    private final Board board;
    private final Game game;

    public Validator(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    public boolean validateCoordinates(int boardSize, String coordinates) {
        try {
            Integer.valueOf(coordinates.split("\\s+")[0]);
            Integer.parseInt(coordinates.split("\\s+")[1]);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Give exactly two coordinates");
            return false;
        }

        int x = Integer.parseInt(coordinates.split("\\s+")[0]);
        int y = Integer.parseInt(coordinates.split("\\s+")[1]);

        if (x > boardSize || x < 1 || y > boardSize || y < 1) {
            System.out.printf("Coordinates should be from 1 to %d!\n", boardSize);
            return false;
        }

        if (board.getBoard()[x][y] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    public boolean checkIsWinner(char player) {
        int allCharsTheSame = player * 3;
        int draw1 = 'O' * 5 + 'X' * 4;
        int draw2 = 'O' * 4 + 'X' * 5;
        int horizontalResult = 0;
        int verticalResult = 0;
        int diagonalResult1 = 0;
        int diagonalResult2 = 0;
        int drawResult = 0;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                horizontalResult += board.getBoard()[i][j];
                verticalResult += board.getBoard()[j][i];
                drawResult += board.getBoard()[i][j];
                if (i == j) {
                    diagonalResult1 += board.getBoard()[i][j];
                }
                if (i == 1 && j == 3 || i == 2 && j == 2 || i == 3 && j == 1) {
                    diagonalResult2 += board.getBoard()[i][j];
                }
            }

            if (horizontalResult == allCharsTheSame || verticalResult == allCharsTheSame
                    || diagonalResult1 == allCharsTheSame || diagonalResult2 == allCharsTheSame) {
                if (player == 'X') {
                    game.setXplayerWins(true);
                } else if (player == 'O') {
                    game.setOplayerWins(true);
                }
                return true;
            } else if (drawResult == draw1 || drawResult == draw2) {
                game.setDraw(true);
                return true;
            } else {
                 horizontalResult = 0;
                 verticalResult = 0;
            }
        }
        return false;
    }
}
