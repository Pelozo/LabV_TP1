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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
        saveToFile(clash.getWinners());


        InnOwner owner = new InnOwner("Eredin Bréacc Glas", 50, 150, new PissSpartanImpl(), new DrinkVikingImpl());

        //TODO



    }

    //shouldn't be here, 2 lazy 2 move.
    public static Integer getRand(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    //technically this is persisting in a database.
    public static void saveToFile(List<Human> winners){

        String tempFileName = "winners.txt";
        File outputf = new File(tempFileName);

        try {
            PrintWriter outputWriter = new PrintWriter(outputf);
            for(int i = 0; i < winners.size(); i++) {
                outputWriter.print(i+1 + ". " + winners.get(i).getName() + ": " + winners.get(i).getDrinkConsumed() + " - " + winners.get(i).getStaminaLeft());
                outputWriter.print("\n");
            }
            outputWriter.println();
            outputWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
