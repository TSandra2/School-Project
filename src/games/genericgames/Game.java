package games.genericgames;

import games.players.Player;
import java.util.List;

/**
 * Interface représentant un jeu générique.
 * Fournit les méthodes nécessaires pour définir les règles, exécuter les coups et gérer l'état du jeu.
 */
public interface Game {

    /**
     * Retourne le joueur qui joue actuellement.
     *
     * @return Le joueur courant.
     */
    Player getCurrentPlayer();

    /**
     * Vérifie si un coup est valide.
     *
     * @param coup Le coup à vérifier.
     * @return {@code true} si le coup est valide, {@code false} sinon.
     */
    boolean isValid(int coup);

    /**
     * Exécute un coup.
     *
     * @param coup Le coup à exécuter.
     */
    void execute(int coup);

    /**
     * Vérifie si le jeu est terminé.
     *
     * @return {@code true} si le jeu est terminé, {@code false} sinon.
     */
    boolean isOver();

    /**
     * Retourne le joueur gagnant, s'il y en a un.
     *
     * @return Le joueur gagnant ou {@code null} si aucun joueur n'a encore gagné.
     */
    Player getWinner();

    /**
     * Retourne une liste des coups valides disponibles.
     *
     * @return Une liste contenant les coups valides.
     */
    List<Integer> validMoves();

    /**
     * Convertit un coup en une représentation textuelle.
     *
     * @param coup Le coup à convertir.
     * @return Une chaîne de caractères représentant le coup.
     */
    String moveToString(int coup);

    /**
     * Retourne une représentation textuelle de l'état actuel du jeu.
     *
     * @return Une chaîne de caractères décrivant la situation actuelle du jeu.
     */
    String situationToString();

    /**
     * Crée une copie de l'état actuel du jeu.
     * Permet d'expérimenter des coups sans altérer l'état réel du jeu.
     *
     * @return Une copie de l'objet Game.
     */
    Game copy();
}