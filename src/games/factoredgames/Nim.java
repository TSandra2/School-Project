package games.factoredgames;

import games.factoredgames.AbstractGame;

/**
 * Représente le jeu des allumettes (Nim). 
 * Deux joueurs s'affrontent pour ne pas être celui qui retire la dernière allumette.
 */
public class Nim extends AbstractGame {
    private int initialSize; // Nombre initial d'allumettes.
    private int maxMatches; // Nombre maximum d'allumettes qu'un joueur peut retirer par tour.
    private int numberOfCurrentMatches; // Nombre actuel d'allumettes restantes.

    /**
     * Constructeur pour initialiser le jeu Nim avec les paramètres spécifiés.
     * 
     * @param initialSize Nombre initial d'allumettes.
     * @param maxMatches Nombre maximum d'allumettes pouvant être retirées par tour.
     * @param joueur1 Nom du premier joueur.
     * @param joueur2 Nom du deuxième joueur.
     */
    public Nim(int initialSize, int maxMatches, String joueur1, String joueur2) {
        super(joueur1, joueur2);
        this.initialSize = initialSize;
        this.maxMatches = maxMatches;
        this.numberOfCurrentMatches = initialSize;
    }

    /**
     * Obtient le nombre initial d'allumettes.
     * 
     * @return Le nombre initial d'allumettes.
     */
    public int getInitialNbMatches() {
        return this.initialSize;
    }

    /**
     * Obtient le nombre actuel d'allumettes restantes.
     * 
     * @return Le nombre actuel d'allumettes restantes.
     */
    public int getCurrentNbMatches() {
        return this.numberOfCurrentMatches;
    }

    /**
     * Obtient le nombre maximum d'allumettes qu'un joueur peut retirer.
     * 
     * @return Le nombre maximum d'allumettes par tour.
     */
    public int getMaxMatches() {
        return this.maxMatches;
    }

    /**
     * Représente la situation actuelle du jeu sous forme de texte.
     * 
     * @return Une chaîne décrivant la situation actuelle.
     */
    public String situationToString() {
        return "Il reste " + numberOfCurrentMatches + " allumettes. Vous pouvez retirer jusqu'à " + maxMatches + " allumettes maximum.";
    }

    /**
     * Effectue un tour en retirant un nombre donné d'allumettes si le coup est valide.
     * 
     * @param nbMatches Le nombre d'allumettes à retirer.
     */
    @Override
    public void doExecute(int nbMatches) {
        if (isValid(nbMatches)) {
            numberOfCurrentMatches -= nbMatches; 
        }
    }

    /**
     * Vérifie si le coup proposé est valide.
     * 
     * @param nbMatches Le nombre d'allumettes proposé pour être retiré.
     * @return {@code true} si le coup est valide, sinon {@code false}.
     */
    public boolean isValid(int nbMatches) {
        return nbMatches > 0 && nbMatches <= maxMatches && nbMatches <= numberOfCurrentMatches;
    }

    /**
     * Vérifie si le jeu est terminé.
     * 
     * @return {@code true} si toutes les allumettes ont été retirées, sinon {@code false}.
     */
    public boolean isOver() {
        return numberOfCurrentMatches == 0;
    }

    /**
     * Retourne le gagnant du jeu.
     * 
     * @return Le nom du joueur ayant gagné, ou {@code null} si le jeu n'est pas terminé.
     */
    public String getWinner() {
        if (!isOver()) {
            return null;
        }
        return getCurrentPlayer();
    }
}