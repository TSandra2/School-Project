package games.players;

import games.genericgames.*;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu !");
        
        // Sélection des joueurs
        Player player1 = choosePlayer(scanner, "Joueur 1");

        Player player2 = choosePlayer(scanner, "Joueur 2");

        // Choisir le jeu (par exemple, jeu de Nim ou Morpion)
        System.out.println("Choisissez un jeu :");
        System.out.println("1 - Jeu de Nim (13 allumettes, 1-3 par coup)");
        System.out.println("2 - Morpion");

        int gameChoice = scanner.nextInt();
        Game game;

        // Initialiser le jeu choisi
        if (gameChoice == 1) {
            game = new Nim(13, 3, player1, player2);  // Jeu de Nim
        } else {
            game = new TicTacToe(player1, player2);  // Jeu de Morpion
        }

        // Gestion du jeu
        while (!game.isOver()) {
            System.out.println(game.situationToString());

            List<Integer> validMoves = game.validMoves();
            System.out.println("Coups possibles : " + validMoves);

            // Le joueur actuel choisit un coup
            int coup = game.getCurrentPlayer().chooseMove(game);
            game.execute(coup);
        }

        // Fin de la partie
        System.out.println("Partie terminée !");
        if (game.getWinner() != null) {
            System.out.println("Le gagnant est : " + game.getWinner().getName());
        } else {
            System.out.println("Match nul !");
        }

        // Fermeture du scanner
        scanner.close();
    }

    private static Player choosePlayer(Scanner scanner, String playerName) {
        System.out.println(playerName + " :");
        System.out.println("1 - Humain");
        System.out.println("2 - Aléatoire");
        System.out.println("3 - Negamax");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Entrez le nom du joueur humain : ");
                String humanName = scanner.next();
                return new Human(humanName, scanner);
            case 2:
                return new RandomPlayer("Joueur Aléatoire", new Random());
            case 3:
                return new NegamaxPlayer();
            default:
                System.out.println("Choix invalide, veuillez recommencer.");
                return choosePlayer(scanner, playerName);  // Relancer si choix invalide
        }
    }
}