package games.nim;
import java.util.Scanner;


public class Main{
    public static void main(String args[]){

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



        Nim nim = new Nim(20, 5, "Klaus", "Colin");

        while(nim.getWinner()== null){ // continues untill there's a winner
            System.out.println("Le nombre actuelle des allumettes actuelle sont : " + nim.getCurrentNbMatches() + " "  + "╿".repeat(nim.getCurrentNbMatches()));
            System.out.println("Jouer Actuelle : " +  nim.getCurrentPlayer());
            
            System.out.print( nim.getCurrentPlayer() + "  combien d'allumettes voulez-vous retirer ? " + "\n");
            int reponse = scanner.nextInt();// reads the players input

            if(nim.isValid(reponse)){
                nim.removeMatches(reponse);
            }else{
                System.out.println("Nombre invalid!! Pouvez-vous entrer un nombre valid?");

            }
     
        }


        System.out.println("Felicitations !!!!!!!!!  " + nim.getWinner() + " tu est le gagnant du jeuu 🎉🎉");



        scanner.close();
    }
}
