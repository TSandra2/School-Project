package games.genericgames;

import games.genericgames.TicTacToe;
import games.players.Player;
import games.players.Human;
import games.players.RandomPlayer;
import games.plays.Orchestrator;

import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

       
        Random rand = new Random(123);
        Scanner scanner = new Scanner(System.in);
        // verify if the value chosen is between 1 and 2
        int gameChoice = 0;
        while (gameChoice != 1 && gameChoice != 2) {
            System.out.println("Choisissez votre jeu :");
            System.out.println("1. Tic-Tac-Toe");
            System.out.println("2. Nim");
            System.out.print("Votre choix (1 ou 2) : ");
            gameChoice = scanner.nextInt();
            if (gameChoice != 1 && gameChoice != 2) {
                System.out.println("Il faut choisir entre les valeur demander . Veuiller reessayer");
            }
        }
        
      

    
        
        // Choose player types for both players
        int player1Type = 0;
        while (player1Type != 1 && player1Type != 2) {
            System.out.println("\nQuel type de joueur pour le premier joueur ?");
            System.out.println("1 - Humain");
            System.out.println("2 - Joueur aléatoire");
            System.out.print("Votre choix (1 ou 2) : ");
            player1Type = scanner.nextInt();
            if (player1Type != 1 && player1Type != 2) {
                System.out.println("Il faut choisir entre les valeur demander . Veuiller reessayer");
            }
        }




        int player2Type = 0;
        while (player2Type != 1 && player2Type != 2) {
            System.out.println("\nQuel type de joueur pour le deuxième joueur ?");
            System.out.println("1 - Humain");
            System.out.println("2 - Joueur aléatoire");
            System.out.print("Votre choix (1 ou 2) : ");
            player2Type = scanner.nextInt();
            if (player2Type != 1 && player2Type != 2) {
                System.out.println("Il faut choisir entre les valeur demander . Veuiller reessayer");
            }
        }


        // Ask user for the names of both players
        System.out.print("\nEntrez le nom du Joueur 1 : ");
        String player1Name = scanner.next();

        System.out.print("\nEntrez le nom du Joueur 2 : ");
        String player2Name = scanner.next();



        
        // creation of plauer based on their input
        Player player1 = null;
        Player player2 = null;

        if (player1Type == 1) {
            player1 = new Human(player1Name, scanner);  // Create Human player
        } else if (player1Type == 2) {
            player1 = new RandomPlayer(player1Name, rand);  // Create Random player
        }

        if (player2Type == 1) {
            player2 = new Human(player2Name, scanner);  // Create Human player
        } else if (player2Type == 2) {
            player2 = new RandomPlayer(player2Name, rand);  // Create Random player
        }

        // Create the game based on user's choice
        Game game = null;
        if (gameChoice == 1) {
            game = new TicTacToe(player1, player2);  // Create TicTacToe game
        } else if (gameChoice == 2) {
            // Ask for Nim game specific settings
            System.out.print("Enter the number of matches for Nim: ");
            int matches = scanner.nextInt();
            System.out.print("Enter the maximum number of matches a player can take: ");
            int maxMatches = scanner.nextInt();
            game = new Nim(matches, maxMatches, player1, player2);  // Create Nim game
        } else {
            System.out.println("Invalid game choice.");
            return;
        }

        // Create an Orchestrator to manage the game and start playing
        Orchestrator orchestrator = new Orchestrator(game);
        orchestrator.play();

        // Close the scanner after use
        scanner.close();
    }
}

