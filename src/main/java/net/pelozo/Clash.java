package net.pelozo;

import net.pelozo.impl.DrinkVikingImpl;
import net.pelozo.impl.PissSpartanImpl;
import net.pelozo.model.Human;
import net.pelozo.model.InnOwner;
import net.pelozo.model.Spartan;
import net.pelozo.model.Viking;
import net.pelozo.model.repositories.WinnerRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Clash {

    private List<Viking> teamA;
    private List<Spartan> teamB;

    private List<Human> winners;
    private Human finalWinner;

    private final String FILE_WINNERS = "winners.txt";


    public Clash(List<Viking> teamA, List<Spartan> teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        winners = new ArrayList();
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

    public void ownerFight(){
        //edge case no winners
        if(winners.size() > 0){

            System.out.println("And now, one lucky winner will be picked to face the inn owner...\n");

            //it says the winner gets to go against the inn owner but there are several winners so idk, lets pick a random one?
            Human luckyWinner = winners.get(new Random().nextInt(winners.size()));
            //let that dude pee
            System.out.println("Grats " + luckyWinner.getName() + ", you'll be facing Mr Inn Owner. But first, go pee...");
            luckyWinner.piss();
            System.out.println("And now, the last clash...");

            //not a fan of instantiation this here.
            InnOwner owner = new InnOwner("Mr Inn Owner", 50, 100, new PissSpartanImpl(), new DrinkVikingImpl());

            finalWinner = oneVsOne(owner,luckyWinner);
            if(finalWinner != null) winners.add(finalWinner);
        }else{
            System.out.println("No winners to fight Inn Owner. Did you run start()?");
        }
    }


    //technically this is persisting in a database.
    public void saveWinners() throws SQLException, ClassNotFoundException {

        if(winners.isEmpty()) return; //should give feedback

        WinnerRepository winnerRepo = WinnerRepository.getInstance();


        for(Human winner : winners) {
            winnerRepo.addWinner(winner);
        }
    }



    private Human oneVsOne(Human h1, Human h2){
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
