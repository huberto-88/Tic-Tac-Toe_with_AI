package tictactoe;

import tictactoe.Players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    private Board board;
    private final Scanner scanner = new Scanner(System.in);
    private Validator validator;
    private boolean XplayerWins;
    private boolean OplayerWins;
    private boolean isDraw;
    private Player player1;
    private Player player2;

    private boolean switchPlayer;

    public Game() {}

    private void createNewGame() {
        this.board = new Board();
        this.validator = new Validator(this.board, this);
        List<String> availableMoves = new ArrayList<>(Arrays.asList("1 1", "1 2", "1 3",
                "2 1", "2 2", "2 3",
                "3 1", "3 2", "3 3")
        );
        this.player1 = new Player(board, availableMoves);
        this.player2 = new Player(board, availableMoves);
        this.XplayerWins = false;
        this.OplayerWins = false;
        this.isDraw = false;
    }

    public void setXplayerWins(boolean xplayerWins) {
        XplayerWins = xplayerWins;
    }
    public void setOplayerWins(boolean oplayerWins) {
        OplayerWins = oplayerWins;
    }
    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public void runGame() {
        while (true) {
            String[] typeOfPlayers;
            do {
                System.out.println("Input command:");
                String command = scanner.nextLine();
                typeOfPlayers = command.split("\\s+");
                if (command.contains("exit")) {
                    System.exit(0);
                } else if (typeOfPlayers.length != 3 || !typeOfPlayers[0].equals("start")) {
                    System.out.println("Bad parameters!");
                }
            } while (typeOfPlayers.length != 3);

            createNewGame();

            this.player1.setPlayerType(typeOfPlayers[1]);
            this.player2.setPlayerType(typeOfPlayers[2]);

            this.switchPlayer = board.whoseMove();
            board.displayBoard();
            startGame();
        }
    }

    private void startGame() {
        boolean isResult = false;

        while (!isResult) {
            String move;
            do {
                move = switchPlayer ? player1.getMove() : player2.getMove();
            } while (!validator.validateCoordinates(board.getBoardSize(), move));

            int[] coordinates = Arrays.stream(move.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            board.getMove(switchPlayer ? 'X' : 'O', coordinates[0], coordinates[1]);
            board.displayBoard();
            isResult = validator.checkIsWinner(switchPlayer ? 'X' : 'O');
            switchPlayer = !switchPlayer;
            if (isDraw) {
                break;
            }
        }
        if (OplayerWins) {
            System.out.println("O wins!");
        } else if (XplayerWins) {
            System.out.println("X wins!");
        } else if (isDraw) {
            System.out.println("Draw!");
        }
    }
}
