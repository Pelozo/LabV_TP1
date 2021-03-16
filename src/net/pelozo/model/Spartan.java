package net.pelozo.model;

import net.pelozo.interfaces.Drink;
import net.pelozo.interfaces.Piss;

public class Spartan extends Human implements Piss, Drink {


    final Integer EXTRA_TOLERANCE = 5;

    public Spartan(String name, Integer age, Integer weight, Piss piss, Drink drink) {
        super(name, age, weight, piss, drink);
        this.bladderCapacity += EXTRA_TOLERANCE;
    }

    @Override
    public Integer drink() {
        Integer toAdd = drink.drink();
        addDrink(toAdd);
        return toAdd;
    }

    @Override
    public void piss() {
        piss.piss();
        drinkConsumed = 0; //I guess?...
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", bladderCapacity=" + bladderCapacity +
                '}';
    }
}
