package net.pelozo.impl;

import net.pelozo.interfaces.Drink;

import java.util.concurrent.ThreadLocalRandom;

public class DrinkVikingImpl implements Drink {
    @Override
    public Integer drink() {
        return (int) ((Math.random()*5) +1);
    }
}
