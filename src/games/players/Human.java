package games.players;

import games.genericgames.Game;
import java.util.List;
import java.util.Scanner;

public class Human implements Player {
    private String name;
    private Scanner scanner;

    public Human(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    @Override
    public int chooseMove(Game game) {
        List<Integer> validMoves = game.validMoves();
        int move;

        System.out.println("Coups valides : " + validMoves);

        while (true) {
            System.out.print("Choisissez un coup : ");
            move = scanner.nextInt();
            if (validMoves.contains(move)) {
                break;
            } else {
                System.out.println("Coup invalide. Essayez encore.");
            }
        }
        return move;
    }

    public String toString() {
        return name;
    }
}
