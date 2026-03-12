package games.genericgames;

import games.players.Player;
import games.genericgames.Game;
import java.util.List;
import java.util.ArrayList;
import games.genericgames.AbstractGame;


public class Nim extends AbstractGame {
    private int initialSize;
    private int maxMatches;
    private int numberOfCurrentMatches;

    public Nim(int initialSize, int maxMatches, Player joueur1, Player joueur2) {
        super(joueur1, joueur2);
        this.initialSize = initialSize;
        this.maxMatches = maxMatches;
        this.numberOfCurrentMatches = initialSize;
    }

    public int getInitialNbMatches() {
        return this.initialSize;
    }

    public int getCurrentNbMatches() {
        return this.numberOfCurrentMatches;
    }

    @Override
    public String moveToString(int allumettes) {
        return  getCurrentPlayer() + " a retirer "  + allumettes + "allumettes";
    }

    @Override
    public String situationToString() {
        return "Il reste " + numberOfCurrentMatches + " allumettes";
    }

    
    @Override
    public void doExecute(int nbMatches) {
        if (isValid(nbMatches)) {
            numberOfCurrentMatches -= nbMatches; 
        }
    }


    @Override
    public boolean isValid(int nbMatches) {
        return nbMatches > 0 && nbMatches <= maxMatches && nbMatches <= numberOfCurrentMatches;
    }

    @Override
    public boolean isOver() {
        return numberOfCurrentMatches == 0;
    }

    @Override
    public Player getWinner() {
        if (!isOver()) {
            return null;
        }
        return getCurrentPlayer();
    }

    @Override
    public List<Integer> validMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 1; i <= maxMatches && i <= numberOfCurrentMatches; i++) {
            moves.add(i);
        }
        return moves;
    }


    @Override
    public Game copy() {
        Nim copie = new Nim(this.initialSize, this.maxMatches, getJoueur1(), getJoueur2());
        copie.numberOfCurrentMatches = this.numberOfCurrentMatches;
        copie.setCurrentPlayer(getCurrentPlayer());
        return copie;
    }
    
}

