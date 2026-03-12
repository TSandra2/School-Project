package games.factoredgames;

import games.factoredgames.AbstractGame;

/**
 * Classe représentant le jeu de Tic-Tac-Toe.
 * Deux joueurs s'affrontent pour aligner trois symboles identiques sur une grille de 3x3.
 */
public class TicTacToe extends AbstractGame {
    private String[][] plateau;

    /**
     * Constructeur de la classe TicTacToe.
     * Initialise la grille et les joueurs.
     *
     * @param joueur1 Nom du premier joueur.
     * @param joueur2 Nom du deuxième joueur.
     */
    public TicTacToe(String joueur1, String joueur2) {
        super(joueur1, joueur2);
        this.plateau = new String[3][3];
    }

    /**
     * Exécute un coup sur la grille.
     * Place le symbole du joueur actuel dans la case spécifiée par le coup.
     *
     * @param coup Indice de la case (0-8) où le joueur veut jouer.
     */
    @Override
    public void doExecute(int coup) {
        int rangee = coup / 3;
        int colonne = coup % 3;

        if (getCurrentPlayer().equals(getJoueur1())) {
            plateau[rangee][colonne] = "x";
        } else {
            plateau[rangee][colonne] = "o";
        }
    }

    /**
     * Vérifie si un coup est valide.
     * Un coup est valide s'il est dans les limites de la grille et si la case est vide.
     *
     * @param coup Indice de la case (0-8) à vérifier.
     * @return {@code true} si le coup est valide, {@code false} sinon.
     */
    public boolean isValid(int coup) {
        int rangee = coup / 3;
        int colonne = coup % 3;
        return rangee >= 0 && rangee < 3 && colonne >= 0 && colonne < 3 && plateau[rangee][colonne] == null;
    }

    /**
     * Vérifie si un joueur a aligné trois symboles dans une direction donnée.
     *
     * @param joueur Le nom du joueur à vérifier.
     * @param row L'indice de la ligne de départ.
     * @param column L'indice de la colonne de départ.
     * @param deltaRow Incrément pour les lignes.
     * @param deltaColumn Incrément pour les colonnes.
     * @return {@code true} si le joueur a aligné trois symboles, {@code false} sinon.
     */
    public boolean wins(String joueur, int row, int column, int deltaRow, int deltaColumn) {
        String symbol = joueur.equals(getJoueur1()) ? "x" : "o";
        return plateau[row][column] == symbol &&
               plateau[row + deltaRow][column + deltaColumn] == symbol &&
               plateau[row + 2 * deltaRow][column + 2 * deltaColumn] == symbol;
    }

    /**
     * Retourne le nom du joueur gagnant, s'il y en a un.
     *
     * @return Le nom du joueur gagnant ou {@code null} si aucun joueur n'a encore gagné.
     */
    public String getWinner() {
        String[] joueurs = {getJoueur1(), getJoueur2()};
        for (String joueur : joueurs) {
            for (int i = 0; i < 3; i++) {
                if (wins(joueur, i, 0, 0, 1) || wins(joueur, 0, i, 1, 0)) return joueur; // Ligne ou colonne.
            }
            if (wins(joueur, 0, 0, 1, 1) || wins(joueur, 0, 2, 1, -1)) return joueur; // Diagonales.
        }
        return null;
    }

    /**
     * Vérifie si la partie est terminée.
     * La partie est terminée si un joueur a gagné ou si toutes les cases sont remplies.
     *
     * @return {@code true} si la partie est terminée, {@code false} sinon.
     */
    public boolean isOver() {
        if (getWinner() != null) return true;
        for (String[] ligne : plateau) {
            for (String caseCourante : ligne) {
                if (caseCourante == null) return false;
            }
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle de la grille.
     * Les cases vides sont représentées par des points.
     *
     * @return Une chaîne de caractères représentant l'état actuel de la grille.
     */
    public String situationToString() {
        StringBuilder chaine = new StringBuilder("  0 1 2" + System.lineSeparator());
        for (int i = 0; i < 3; i++) {
            chaine.append(i).append(" ");
            for (int j = 0; j < 3; j++) {
                chaine.append(plateau[i][j] == null ? ". " : plateau[i][j] + " ");
            }
            chaine.append(System.lineSeparator());
        }
        return chaine.toString();
    }
}