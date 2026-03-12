package games.genericgames;

import games.players.Player;
import games.genericgames.Game;
import games.players.Player;
import java.util.List;
import java.util.ArrayList;

public class TicTacToe extends AbstractGame implements Game {
    String plateau[][];

    /**
     * Constructeur de la classe TicTacToe.
     * @param joueur1 Nom du premier joueur.
     * @param joueur2 Nom du deuxième joueur.
     */
    public TicTacToe(Player joueur1, Player joueur2) {
        super(joueur1 , joueur2);
        this.plateau = new String[3][3];
    }


    /**
     * Exécute un coup sur la grille.
     * @param rangee Indice de la rangée.
     * @param colonne Indice de la colonne.
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
     * @param rangee Indice de la rangée. 
     * @param colonne Indice de la colonne.
     * @return true si le coup est valide, false sinon.
     */
    @Override
    public boolean isValid(int coup) {
        if (coup/3 < 0 || coup/3 >= 3 || coup%3 < 0 || coup%3 >= 3) {
            return false;
        }
        if (plateau[coup/3][coup%3] != null) {
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
    public boolean wins(Player joueur, int row, int column, int deltaRow, int deltaColumn) {
        String symbol;
        if (joueur == getJoueur1()) {
            symbol = "x";
        } else {
            symbol = "o";
        }
        return plateau[row][column] == symbol &&
               plateau[row + deltaRow][column + deltaColumn] == symbol &&
               plateau[row + 2 * deltaRow][column + 2 * deltaColumn] == symbol;
    }

    
    @Override
    public Player getWinner() {
        Player[] liste = {getJoueur1(), getJoueur2()};
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
    @Override
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


    @Override
    public List<Integer> validMoves() {
        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (isValid(i)) {
                moves.add(i);
            }
        }
        return moves;
    }

    @Override
    public String moveToString(int coup) {
        int rangee = coup / 3;
        int colonne = coup % 3;
        return "Position : (" + rangee + ", " + colonne + ")";
    }

    /**
     * Retourne une représentation textuelle de l'état actuel de la grille.
     * @return Une chaîne de caractères représentant la grille.
     */
    @Override
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

    @Override
    public Game copy() {
        TicTacToe copie = new TicTacToe(getJoueur1(), getJoueur2());

        // Copier le plateau
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copie.plateau[i][j] = this.plateau[i][j];
            }
        }

        // Copier le joueur courant
        copie.setCurrentPlayer(getCurrentPlayer());
        return copie;
    }

}