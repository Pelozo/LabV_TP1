package net.pelozo.model;

import net.pelozo.interfaces.Drink;
import net.pelozo.interfaces.Piss;

import java.util.Comparator;

public abstract class Human implements Comparable<Human>{

    protected String name;
    protected Integer age;
    protected Integer weight;
    protected Integer bladderCapacity;
    protected Piss piss;
    protected Drink drink;

    protected Integer drinkConsumed;


    public Human(String name, Integer age, Integer weight, Piss piss, Drink drink) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.bladderCapacity = (int)((age * 0.4) + (weight * 0.2));//idk man, i just want to use age and weight for something.
        this.piss = piss;
        this.drink = drink;
        this.drinkConsumed = 0;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Integer getStaminaLeft(){
        return bladderCapacity - drinkConsumed;
    }

    public Integer getDrinkConsumed() {
        return drinkConsumed;
    }

    public void addDrink(Integer drink) {
        this.drinkConsumed += drink;
    }

    @Override
    public int compareTo(Human o) {
        int c = ((Human)o).getAge();
        return this.getAge() - c;
    }


}
