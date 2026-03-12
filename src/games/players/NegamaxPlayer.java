package games.players;

import games.genericgames.*;
import java.util.List;

public class NegamaxPlayer implements Player {

    public NegamaxPlayer() {
    }

    public int evaluate(Game jeu){
        if(jeu.isOver()){
            if(jeu.getWinner() == jeu.getCurrentPlayer()){
                return 1;
            }
            if(jeu.getWinner() != null && jeu.getWinner() != jeu.getCurrentPlayer()){
                return -1;
            }
            return 0;
        }

        Integer points = null;

        List<Integer> coupValides = jeu.validMoves();
        for(int i = 0; i < coupValides.size(); i++){
            Game copieDeJeu = jeu.copy();
            copieDeJeu.execute(coupValides.get(i));
            int valeur = -evaluate(copieDeJeu);

            if(points == null || valeur > points){
                points = valeur;
            }
        }

        return points;
    }


    @Override
    public int chooseMove(Game jeu){
        Integer meilleurCoup = null;
        Integer meilleurScore = null;

        List<Integer> coupValides = jeu.validMoves();
        for(int i = 0; i < coupValides.size(); i++){
            Game copie = jeu.copy();
            copie.execute(coupValides.get(i));
            int valeur = -evaluate(copie);

            if(meilleurScore == null || valeur > meilleurScore){
                meilleurScore = valeur;
                meilleurCoup = coupValides.get(i);
            }
        }

        return meilleurCoup;
    }

    @Override
    public String toString() {
        return "NegamaxPlayer n° " + this.hashCode();
    }
}
