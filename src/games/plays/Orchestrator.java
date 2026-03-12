package games.plays;

import games.genericgames.Game;
import games.players.Player;

public class Orchestrator {
    private Game game;

    public Orchestrator(Game game) {
        this.game = game;
    }

    public void play() {
        while (!game.isOver()) {
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("C'est à " + currentPlayer + " de jouer.");
            System.out.println(game.situationToString());
            int move = currentPlayer.chooseMove(game); 
            game.execute(move); 
            System.out.println(game.moveToString(move)); 
        System.out.println("La partie est terminée. Le gagnant est : " + game.getWinner());
        }
    }
}
