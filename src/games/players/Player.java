package games.players;

import games.players.Player;
import games.genericgames.Game;

public interface Player{

    int chooseMove(Game game);

    default String getName() {
        return this.toString();
    }
}
