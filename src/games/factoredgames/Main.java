package games.factoredgames;

import games.factoredgames.TicTacToe;
import games.factoredgames.Nim;
import java.util.Scanner;

/**
 * Classe principale pour exécuter un jeu interactif.
 * Le joueur peut choisir entre deux jeux : Tic-Tac-Toe et Nim.
 */
public class Main {

    /**
     * Point d'entrée du programme.
     * Demande aux joueurs de choisir un jeu, initialise la partie, puis lance la boucle principale du jeu sélectionné.
     * 
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un jeu :");
        System.out.println("1. Tic-Tac-Toe");
        System.out.println("2. Nim");
        int choix = scanner.nextInt();
        scanner.nextLine(); 

        String joueur1, joueur2;

        if (choix == 1) {
            /**
             * Partie pour le jeu Tic-Tac-Toe.
             * Demande les noms des joueurs, initialise la grille, puis exécute la partie.
             */
            System.out.print("Entrer le nom du joueur1 : ");
            joueur1 = scanner.nextLine();
            System.out.print("Entrer le nom du joueur2 : ");
            joueur2 = scanner.nextLine();
            
            TicTacToe jeu = new TicTacToe(joueur1, joueur2);

            while (!jeu.isOver()) {
                System.out.println(jeu.situationToString());
                System.out.println("C'est au tour de " + jeu.getCurrentPlayer() + "\nEntrez le numéro de ligne et le numéro de colonne");
                System.out.print("Numéro de ligne: ");
                int rangee = scanner.nextInt();
                System.out.print("Numéro de colonne: ");
                int colonne = scanner.nextInt();

                if (jeu.isValid(rangee * 3 + colonne)) {
                    jeu.execute(rangee * 3 + colonne);
                } else {
                    System.out.println("Le coup que vous avez joué est invalide. Merci de réessayer.");
                }
            }

            if (jeu.getWinner() != null) {
                System.out.println("Plateau final :\n" + jeu.situationToString());
                System.out.println("Le gagnant est " + jeu.getWinner() + " 🎉🎉");
            } else {
                System.out.println("Match nul !");
            }

        } else if (choix == 2) {
            /**
             * Partie pour le jeu Nim.
             * Demande les noms des joueurs, initialise la partie avec 20 allumettes et un retrait maximal de 5 allumettes par tour.
             */
            System.out.print("Entrer le nom du joueur1 : ");
            joueur1 = scanner.nextLine();
            System.out.print("Entrer le nom du joueur2 : ");
            joueur2 = scanner.nextLine();

            Nim nim = new Nim(20, 5, joueur1, joueur2);

            while (nim.getWinner() == null) {
                System.out.println("Le nombre actuel d'allumettes est : " + nim.getCurrentNbMatches() + " " + "╿".repeat(nim.getCurrentNbMatches()));
                System.out.println("Vous pouvez retirer entre 1 et " + nim.getMaxMatches() + " allumettes maximun.");
                System.out.println("Joueur actuel : " + nim.getCurrentPlayer());
                System.out.print(nim.getCurrentPlayer() + ", combien d'allumettes voulez-vous retirer ? ");
                int reponse = scanner.nextInt();

                if (nim.isValid(reponse)) {
                    nim.doExecute(reponse);
                } else {
                    System.out.println("Nombre invalide ! Veuillez entrer un nombre valide.");
                }
            }

            System.out.println("Félicitations " + nim.getWinner() + ", tu as  gagner 🎉🎉");
        } else {
            System.out.println("Choix invalide. Veuillez redémarrer le programme et choisir 1 ou 2.");
        }

        scanner.close();
    }
}