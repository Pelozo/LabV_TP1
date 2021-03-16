package net.pelozo.model;

import net.pelozo.interfaces.Drink;
import net.pelozo.interfaces.Piss;

import java.util.concurrent.ThreadLocalRandom;

public class Viking extends Human implements Drink, Piss {


    final Integer PROFESSIONAL_DRINKER = 97; //dunno what to do with this

    public Viking(String name, Integer age, Integer weight, Piss piss, Drink drink) {
        super(name, age, weight, piss, drink);
    }

    @Override
    public Integer drink() {

        Integer toAdd = drink.drink();//(drink.drink() / 100) * PROFESSIONAL_DRINKER;

        //PROFESSIONAL_DRINKER: there's a % of not adding it to drinkConsumed
        //TBH this should be in DrinkBikingImpl but then I wouldn't know what to do with PROFESSIONAL_DRINKER
        if(ThreadLocalRandom.current().nextInt(1, 100 + 1) < PROFESSIONAL_DRINKER){
            addDrink(toAdd);
        }

        return toAdd;
    }

    @Override
    public void piss() {
        piss.piss();
        drinkConsumed = 0; //I guess?...
    }

    @Override
    public String toString() {
        return "Viking{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", bladderCapacity=" + bladderCapacity +
                '}';
    }
}
