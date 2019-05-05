package fxapp;

import java.util.Random;

public class Die extends Random{
    private int sides;

    Die(int sides) {
        this.sides = sides;
    }

    int roll() {
        return nextInt(sides) + 1;
    }

    @Override
    public String toString() {
        return "d" + sides;
    }
}
