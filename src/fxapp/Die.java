package fxapp;

import java.util.Random;

public class Die {
    private int sides;
    private Random rng = new Random();


    Die(int sides) {
        this.sides = sides;
    }

    int roll() {
        return rng.nextInt(sides) + 1;
    }

    @Override
    public String toString() {
        return "d" + sides;
    }
}
