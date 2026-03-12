package games.nim;

public class Nim {
    private int initailSize; 
    private int maxMatches;
    private String player1;
    private String player2;
    private int numberOfCurrentMatches; 
    private String currentPlayer;
   

    public Nim(int initailSize , int maxMatches , String player1 , String player2){
        this.initailSize = initailSize;
        this.maxMatches = maxMatches;
        this.player1 = player1;
        this.player2 = player2;
        this.numberOfCurrentMatches= initailSize;
        this.currentPlayer=player1;
    }

    public int getInitialNbMatches(){
        return this.initailSize;
    }
    public int getCurrentNbMatches(){
        return this.numberOfCurrentMatches;
    }
    public String getCurrentPlayer(){
        return this.currentPlayer;
    }

    public String situationToString(){
        return "Il reste " + numberOfCurrentMatches + " allumettes. Vous pouvez retirer jusqu'à " + maxMatches + " allumettes maximum.";
    }

    public void removeMatches(int nbMatches){
        numberOfCurrentMatches = numberOfCurrentMatches - nbMatches; 
         if(currentPlayer == player1){
            currentPlayer = player2;
         }else{
            currentPlayer= player1;
         } 

    }

    public boolean isValid(int nbMatches){

          return  nbMatches > 0 && maxMatches >= nbMatches && nbMatches <= numberOfCurrentMatches ;   
    }

    public boolean isOver(){
        return numberOfCurrentMatches == 0; 
    }

    public String getWinner(){
        if(!isOver()){
            return null;
        } 
        return currentPlayer;
    }



}

