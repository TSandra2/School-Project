package games.players;

import games.genericgames.Game;
import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {
    private String name;
    private Random random;

    public RandomPlayer(String name, Random random) {
        this.name = name;
        this.random = random;
    }

    @Override
    public int chooseMove(Game game) {
        List<Integer> validMoves = game.validMoves();
        if (validMoves.isEmpty()) {
            throw new IllegalStateException("Aucun coup valide disponible.");
        }
        int randomIndex = random.nextInt(validMoves.size());
        return validMoves.get(randomIndex);
    }

    @Override
    public String toString() {
        return "Joueur aléatoire n° " + this.hashCode();
    }
}

