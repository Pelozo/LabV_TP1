package net.pelozo;

import net.pelozo.model.Human;
import net.pelozo.model.Spartan;
import net.pelozo.model.Viking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Clash {

    private List<Viking> teamA;
    private List<Spartan> teamB;

    private List<Human> winners;


    public Clash(List<Viking> teamA, List<Spartan> teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        winners = new ArrayList<>();
        //sort them by age
        sort();
    }


    public void start(){

        //make 'em fight
        while(teamA.size() > 0 && teamB.size() > 0){

            Human winner = oneVsOne(getAndRemove(teamA), getAndRemove(teamB));

            //it'll return null in case of a tie
            if(winner != null){
                winners.add(winner);
            }
            System.out.println("");

        }
    }

    public static Human oneVsOne(Human h1, Human h2){
        //present them
        System.out.println(h1.getName() + " vs " + h2.getName());
        //make them drink until one of them needs to piss
        while(h1.getStaminaLeft() > 0 && h2.getStaminaLeft() > 0){
            h1.drink();
            h2.drink();
        }

        //check winner
        if(h1.getStaminaLeft() <= 0 && h2.getStaminaLeft() <= 0){
            h1.piss();
            h2.piss();
            System.out.println("It's a tie! they both suck!");
        }else if(h1.getStaminaLeft() <= 0){
            h1.piss();
            System.out.println(h2.getName() + " wins!");
            return h2;
        }else if(h2.getStaminaLeft() <= 0){
            h2.piss();
            System.out.println(h1.getName() + " wins!");
            return h1;
        }
        return null;
    }


    public void presentWinners(){
        System.out.println("And the winners are...");
        for(int i=0;i<winners.size();i++){
            System.out.println(i+1 + ". " + winners.get(i).getName());
        }
    }

    public List<Human> getWinners() {
        return winners;
    }

    private void sort(){
        Collections.sort(teamA);
        Collections.sort(teamB);
    }

    private Human getAndRemove(List<? extends Human> list){
        Human dude = list.get(new Random().nextInt(list.size()));
        list.remove(dude);
        return dude;
    }



}
