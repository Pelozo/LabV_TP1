package net.pelozo;

import net.pelozo.impl.DrinkSpartanImpl;
import net.pelozo.impl.DrinkVikingImpl;
import net.pelozo.impl.PissSpartanImpl;
import net.pelozo.impl.PissVikingImpl;
import net.pelozo.model.Human;
import net.pelozo.model.InnOwner;
import net.pelozo.model.Spartan;
import net.pelozo.model.Viking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

        List<Viking> vikings = new ArrayList<>();
        List<Spartan> spartans = new ArrayList<>();

        //generate teams
        for(int i=1;i<11;i++){
            vikings.add(
                    new Viking(
                            "Viking N° " + i,
                            getRand(5,60),
                            getRand(30,150),
                            new PissVikingImpl(),
                            new DrinkVikingImpl()
                            )
            );
            spartans.add(
                    new Spartan(
                            "Spartan N° " + i,
                            getRand(5,60),
                            getRand(30,150),
                            new PissSpartanImpl(),
                            new DrinkSpartanImpl()
                    )
            );
        }


        Clash clash = new Clash(vikings, spartans);
        clash.start();
        clash.presentWinners();

        //TODO? All of this should be in Clash class...

        //edge case no winners
        if(clash.getWinners().size() > 0){

            System.out.println("And now, one lucky winner will be picked to face the inn owner...\n");

            //it says the winner gets to go against the inn owner but there are several winners so idk, lets pick a random one?
            Human luckyWinner = clash.getWinners().get(new Random().nextInt(clash.getWinners().size()));
            //let that dude pee
            System.out.println("Grats " + luckyWinner.getName() + ", you'll be facing Mr Inn Owner. But first, go pee...");
            luckyWinner.piss();
            System.out.println("And now, the last clash...");

            InnOwner owner = new InnOwner("Mr Inn Owner", 50, 100, new PissSpartanImpl(), new DrinkVikingImpl());

            Human finalWinner = Clash.oneVsOne(owner,luckyWinner);

            //save to "database"
            try {
                saveResults(clash.getWinners(), finalWinner);
                System.out.println("Winners saved to file");
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't save winners.");
            }

        }





    }

    //shouldn't be here, 2 lazy 2 move.
    public static Integer getRand(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    //technically this is persisting in a database.
    public static void saveResults(List<Human> winners, Human finalWinner) throws FileNotFoundException {

        String tempFileName = "winners.txt";
        File outputf = new File(tempFileName);


        PrintWriter outputWriter = new PrintWriter(outputf);

        for(int i = 0; i < winners.size(); i++) {
            outputWriter.print(i+1 + ". " + winners.get(i).getName() + ": " + winners.get(i).getDrinkConsumed() + " - " + winners.get(i).getStaminaLeft());
            outputWriter.print("\n");
        }
        if(finalWinner != null) {
            outputWriter.print(winners.size() + 1 + ". " + finalWinner.getName() + ": " + finalWinner.getDrinkConsumed() + " - " + finalWinner.getStaminaLeft());
        }

        outputWriter.println();
        outputWriter.close();

    }
}
