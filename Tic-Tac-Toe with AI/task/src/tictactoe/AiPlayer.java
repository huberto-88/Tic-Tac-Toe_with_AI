package tictactoe;

import java.util.List;
import java.util.Random;

public class AiPlayer implements PlayerType {
    private final List<String> availableMoves;

    public AiPlayer(List<String> availableMoves) {
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
