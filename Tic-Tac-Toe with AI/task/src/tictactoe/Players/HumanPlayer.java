package tictactoe.Players;

import tictactoe.PlayerType;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements PlayerType {
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> availableMoves;

    public HumanPlayer(List<String> availableMoves) {
        this.availableMoves = availableMoves;
    }

    @Override
    public String getMove() {
            System.out.println("Enter the coordinates:");
            String coordinates = scanner.nextLine();

            availableMoves.remove(coordinates);
        return coordinates;
    }
}
