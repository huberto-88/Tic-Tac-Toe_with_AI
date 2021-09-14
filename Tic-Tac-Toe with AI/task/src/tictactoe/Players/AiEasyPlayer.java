package tictactoe.Players;

import tictactoe.PlayerType;

import java.util.List;
import java.util.Random;

public class AiEasyPlayer implements PlayerType {

    private final List<String> availableMoves;

    public AiEasyPlayer(List<String> availableMoves) {
        this.availableMoves = availableMoves;
    }

    @Override
    public String getMove() {
        System.out.println("Making move level \"easy\"");
        String move = availableMoves.get(new Random()
                .nextInt(availableMoves.size()));
        availableMoves.remove(move);
        return move;
    }
}
