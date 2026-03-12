package games.tictactoe;

import java.util.Scanner;

/**
 * Classe principale pour exécuter une partie de morpion.
 */
class Main {
    public static void main(String[] args) {

        // Initialisation du Scanner pour les entrées des joueurs
        Scanner scanner = new Scanner(System.in);

        /**
         * Demande le nom du joueur 1.
         */
        System.out.println("Entrer le nom du joueur1 : ");
        String joueur1 = scanner.nextLine();

        /**
         * Demande le nom du joueur 2.
         */
        System.out.println("Entrer le nom du joueur2 : ");
        String joueur2 = scanner.nextLine();

        // Création de l'objet TicTacToe pour gérer la partie
        TicTacToe jeu = new TicTacToe(joueur1, joueur2);
 
        /**
         * Boucle principale du jeu, elle continue jusqu'à la fin de la partie.
         */
        while (!jeu.isOver()) {

            /**
             * Affiche la situation actuelle du plateau de jeu.
             */
            System.out.println(jeu.situationToString());

            /**
             * Indique le joueur actuel et demande de saisir les coordonnées de son coup.
             */
            System.out.println("C'est au tour de " + jeu.getCurrentPlayer() + "\nEntrez le nombre de ligne et le nombre de colonne\n");
            System.out.println("Numero de ligne: ");
            int rangee = scanner.nextInt(); 
            System.out.println("Numero de colonne: ");
            int colonne = scanner.nextInt();

            /**
             * Vérifie si le coup est valide, l'exécute ou affiche un message d'erreur.
             */
            if (jeu.isValid(rangee, colonne)) {
                jeu.execute(rangee, colonne);
            } else {
                System.out.println("Le coup que vous avez joué est invalide. Merci de réessayer.");
            }
        }

        /**
         * Fin de la partie : annonce du résultat (gagnant ou match nul).
         */
        if (jeu.getWinner() != null) {
            System.out.println("Plateau final :\n" + jeu.situationToString());
            System.out.println("Le gagnant est " + jeu.getWinner()); 

        } else {
            System.out.println("Match nul !");
        }
    }
}