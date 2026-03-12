package games.factoredgames;

/**
 * Classe abstraite représentant un jeu générique à deux joueurs.
 * Elle fournit un cadre pour gérer les joueurs et alterner entre eux
 * après chaque action dans le jeu.
 */
public abstract class AbstractGame {

    // Attributs protégés pour les noms des deux joueurs
    protected String joueur1; // Nom du premier joueur
    protected String joueur2; // Nom du second joueur

    // Attribut privé pour le joueur actuellement actif
    private String joueurCourant;

    /**
     * Constructeur de la classe AbstractGame.
     * Initialise les noms des joueurs et définit le joueur courant au premier joueur.
     * 
     * @param joueur1 le nom du premier joueur.
     * @param joueur2 le nom du second joueur.
     */
    public AbstractGame(String joueur1, String joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = joueur1; // Le joueur 1 commence par défaut.
    }

    /**
     * Récupère le nom du premier joueur.
     * 
     * @return le nom du joueur 1.
     */
    public String getJoueur1() {
        return joueur1;
    }

    /**
     * Récupère le nom du second joueur.
     * 
     * @return le nom du joueur 2.
     */
    public String getJoueur2() {
        return joueur2;
    }

    /**
     * Récupère le nom du joueur actuellement en train de jouer.
     * 
     * @return le nom du joueur courant.
     */
    public String getCurrentPlayer() {
        return joueurCourant;
    }

    /**
     * Définit le joueur actuellement en train de jouer.
     * 
     * @param joueur le nom du joueur qui devient le joueur courant.
     */
    public void setCurrentPlayer(String joueur) {
        this.joueurCourant = joueur;
    }

    /**
     * Change le joueur courant en alternant entre joueur1 et joueur2.
     */
    public void switchPlayer() {
        if (getCurrentPlayer().equals(getJoueur1())) { 
            setCurrentPlayer(getJoueur2());
        } else {
            setCurrentPlayer(getJoueur1());
        }
    }

    /**
     * Méthode abstraite à implémenter par les sous-classes.
     * Représente l'exécution d'un coup spécifique dans le jeu.
     * 
     * @param coup une représentation abstraite d'un coup (par exemple un entier).
     */
    public abstract void doExecute(int coup);

    /**
     * Méthode publique pour exécuter un coup.
     * Appelle la méthode abstraite `doExecute` et alterne ensuite le joueur courant.
     * 
     * @param coup une représentation abstraite d'un coup (par exemple un entier).
     */
    public void execute(int coup) {
        doExecute(coup); // Applique le coup spécifique au jeu.
        switchPlayer();  // Change le joueur courant après le coup.
    }
}