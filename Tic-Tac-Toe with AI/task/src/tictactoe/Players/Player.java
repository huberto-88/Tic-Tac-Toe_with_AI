package tictactoe.Players;

import tictactoe.Board;
import tictactoe.PlayerType;

import java.util.List;

public class Player {

    private PlayerType playerType;
    private final List<String> availableMoves;
    private final Board board;

    public Player(Board board, List<String> availableMoves) {
        this.availableMoves = availableMoves;
        this.board = board;
    }

    public void setPlayerType(String typeOfPlayer) {
        switch (typeOfPlayer) {
            case "easy":
                this.playerType = new AiEasyPlayer(availableMoves);
                break;
            case "medium":
                this.playerType = new AiMediumPlayer(availableMoves, board);
                break;
            case "hard":
                this.playerType = new AiHardPlayer(board);
                break;
            default:
                this.playerType = new HumanPlayer(availableMoves);
                break;
        }
    }

    public String getMove() {
        return playerType.getMove();
    }
}
