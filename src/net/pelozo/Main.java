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
        clash.ownerFight();

        try {
            clash.saveWinners();
            System.out.println("Winners saved to file");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't save winners.");
        }



    }

    //shouldn't be here, 2 lazy 2 move.
    public static Integer getRand(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
