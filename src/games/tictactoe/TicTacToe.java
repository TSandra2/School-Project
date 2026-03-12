package games.tictactoe;

public class TicTacToe {
    String joueur1;
    String joueur2;
    String joueurCourant;
    String plateau[][];

    /**
     * Constructeur de la classe TicTacToe.
     * @param joueur1 Nom du premier joueur.
     * @param joueur2 Nom du deuxième joueur.
     */
    public TicTacToe(String joueur1, String joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurCourant = joueur1;
        this.plateau = new String[3][3];
    }

    /**
     * Accesseur pour obtenir le joueur courant.
     * @return Le nom du joueur courant.
     */
    public String getCurrentPlayer() {
        return joueurCourant;
    }

    /**
     * Exécute un coup sur la grille.
     * @param rangee Indice de la rangée.
     * @param colonne Indice de la colonne.
     */
    public void execute(int rangee, int colonne) {
        if (joueurCourant == joueur1) {
            plateau[rangee][colonne] = "x";
            joueurCourant = joueur2;
        } else {
            plateau[rangee][colonne] = "o";
            joueurCourant = joueur1;
        }
    }

    /**
     * Vérifie si un coup est valide.
     * @param rangee Indice de la rangée.
     * @param colonne Indice de la colonne.
     * @return true si le coup est valide, false sinon.
     */
    public boolean isValid(int rangee, int colonne) {
        if (rangee < 0 || rangee >= 3 || colonne < 0 || colonne >= 3) {
            return false;
        }
        if (plateau[rangee][colonne] != null) {
            return false;
        }
        return true;
    }

    /**
     * Vérifie si un joueur a aligné trois symboles.
     * @param joueur Le nom du joueur.
     * @param row L'indice de la ligne.
     * @param column L'indice de la colonne.
     * @param deltaRow Incrément pour les lignes.
     * @param deltaColumn Incrément pour les colonnes.
     * @return true si les trois symboles sont alignés, false sinon.
     */
    public boolean wins(String joueur, int row, int column, int deltaRow, int deltaColumn) {
        String symbol;
        if (joueur == joueur1) {
            symbol = "x";
        } else {
            symbol = "o";
        }
        return plateau[row][column] == symbol &&
               plateau[row + deltaRow][column + deltaColumn] == symbol &&
               plateau[row + 2 * deltaRow][column + 2 * deltaColumn] == symbol;
    }

    /**
     * Retourne le nom du joueur gagnant, s'il y en a un.
     * @return Le nom du joueur gagnant ou null s'il n'y a pas de gagnant.
     */
    public String getWinner() {
        String[] liste = {joueur1, joueur2};
        for (int i = 0; i < liste.length; i++) {
            for (int j = 0; j < 3; j++) {

                if (wins(liste[i], j, 0, 0, 1)) return liste[i];
                if (wins(liste[i], 0, j, 1, 0)) return liste[i];
            }

            if (wins(liste[i], 0, 0, 1, 1)) return liste[i];
            if (wins(liste[i], 0, 2, 1, -1)) return liste[i];
        }
        return null;
    }

    /**
     * Détermine si la partie est terminée.
     * @return true si la partie est terminée, false sinon.
     */
    public boolean isOver() {
        if (getWinner() != null) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (plateau[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle de l'état actuel de la grille.
     * @return Une chaîne de caractères représentant la grille.
     */
    public String situationToString() {
        String chaine = "  0 1 2" + System.lineSeparator();
        for (int i = 0; i < 3; i++) {
            chaine += i + " ";
            for (int j = 0; j < 3; j++) {
                if (plateau[i][j] == null) {
                    chaine += ". ";
                } else {
                    chaine += plateau[i][j] + " ";
                }
            }
            chaine += System.lineSeparator();
        }
        return chaine;
    }
}