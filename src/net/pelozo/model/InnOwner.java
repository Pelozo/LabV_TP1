package net.pelozo.model;

import net.pelozo.interfaces.Drink;
import net.pelozo.interfaces.Piss;

public class InnOwner extends Human implements Drink,Piss{



    public InnOwner(String name, Integer age, Integer weight, Piss piss, Drink drink) {
        super(name, age, weight, piss, drink);

    }

    @Override
    public Integer drink() {
        return drink();
    }

    @Override
    public void piss() {
        piss();

    }
}
