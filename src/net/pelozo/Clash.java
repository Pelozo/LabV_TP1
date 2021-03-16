package net.pelozo;

import net.pelozo.model.Human;
import net.pelozo.model.Spartan;
import net.pelozo.model.Viking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Clash {

    private List<Viking> vikings;
    private List<Spartan> spartans;

    private List<Human> winners;


    public Clash(List<Viking> vikings, List<Spartan> spartans) {
        this.vikings = vikings;
        this.spartans = spartans;
        winners = new ArrayList<>();
        //sort them by age
        sort();
    }


    public void start(){

        //make 'em fight
        while(vikings.size() > 0 && spartans.size() > 0){
            //get one of each
            Viking viking = (Viking)getAndRemove(vikings);
            Spartan spartan = (Spartan)getAndRemove(spartans);

            //present them
            System.out.println(viking.getName() + " vs " + spartan.getName());

            //make them drink until one of them needs to piss
            while(viking.getStaminaLeft() > 0 && spartan.getStaminaLeft() > 0){
                viking.drink();
                spartan.drink();
            }
            //check winner
            if(viking.getStaminaLeft() <= 0 && spartan.getStaminaLeft() <= 0){
                viking.piss();
                spartan.piss();
                System.out.println("It's a tie! they both suck!");
            }else if(viking.getStaminaLeft() <= 0){
                viking.piss();
                winners.add(spartan);
                System.out.println(spartan.getName() + " wins!");
            }else if(spartan.getStaminaLeft() <= 0){
                spartan.piss();
                winners.add(viking);
                System.out.println(viking.getName() + " wins!");
            }
            System.out.println("");

        }
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
        Collections.sort(vikings);
        Collections.sort(spartans);
    }

    private Human getAndRemove(List<? extends Human> list){
        Human dude = list.get(new Random().nextInt(list.size()));
        list.remove(dude);
        return dude;
    }



}
