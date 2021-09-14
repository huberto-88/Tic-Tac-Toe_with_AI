package tictactoe;

import tictactoe.Players.AiHardPlayer;
import tictactoe.Players.AiMediumPlayer;

import java.util.List;

public class Player {

    private PlayerType playerType;
    private List<String> availableMoves;
    private Board board;

    public Player(List<String> availableMoves, Board board) {
        this.availableMoves = availableMoves;
        this.board = board;
    }

    public void setPlayerType(String typeOfPlayer) {
        if (typeOfPlayer.equals("easy")) {
            this.playerType = new AiPlayer(availableMoves);
        } else if (typeOfPlayer.equals("medium")) {
            this.playerType = new AiMediumPlayer(availableMoves, board);
        } else if (typeOfPlayer.equals("hard")) {
            this.playerType = new AiHardPlayer(board);
        } else {
            this.playerType = new HumanPlayer(availableMoves);
        }
    }

    public String getMove(List<String> availableMoves) {
        return playerType.getMove();
    }
}
