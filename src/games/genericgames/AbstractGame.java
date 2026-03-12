package games.genericgames;

import games.players.Player;
import games.genericgames.Game;

/**
 * Classe abstraite représentant la base pour tous les jeux.
 * Gère les joueurs, le joueur courant et les opérations communes.
 */
public abstract class AbstractGame implements Game {
    private Player joueur1;
    private Player joueur2;
    private Player joueurCourant;

    /**
     * Constructeur de la classe AbstractGame.
     * Initialise les deux joueurs et définit le joueur courant comme le premier joueur.
     *
     * @param joueur1 Le premier joueur.
     * @param joueur2 Le deuxième joueur.
     */
    public AbstractGame(Player joueur1, Player joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = joueur1;
    }

    /**
     * Retourne le premier joueur.
     *
     * @return Le premier joueur.
     */
    public Player getJoueur1() {
        return joueur1;
    }

    /**
     * Retourne le deuxième joueur.
     *
     * @return Le deuxième joueur.
     */
    public Player getJoueur2() {
        return joueur2;
    }

    /**
     * Retourne le joueur qui joue actuellement.
     *
     * @return Le joueur courant.
     */
    @Override
    public Player getCurrentPlayer() {
        return joueurCourant;
    }

    /**
     * Définit le joueur courant.
     *
     * @param joueur Le joueur à définir comme joueur courant.
     */
    public void setCurrentPlayer(Player joueur) {
        this.joueurCourant = joueur;
    }

    /**
     * Change le joueur courant pour le joueur suivant.
     * Si le joueur courant est le premier joueur, le second devient le joueur courant, et vice versa.
     */
    public void switchPlayer() {
        if (getCurrentPlayer() == getJoueur1()) {
            setCurrentPlayer(getJoueur2());
        } else {
            setCurrentPlayer(getJoueur1());
        }
    }

    /**
     * Méthode abstraite pour exécuter un coup spécifique.
     * Cette méthode doit être implémentée par les classes filles.
     *
     * @param coup Le coup à exécuter (défini par le jeu).
     */
    public abstract void doExecute(int coup);

    /**
     * Exécute un coup, appelle la méthode spécifique au jeu, puis change le joueur courant.
     *
     * @param coup Le coup à exécuter (défini par le jeu).
     */
    @Override
    public void execute(int coup) {
        doExecute(coup);
        switchPlayer();
    }
}