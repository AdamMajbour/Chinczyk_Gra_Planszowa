package sample;

import java.util.Random;

public class Dice
{
    private Random generator;

    public Dice()
    {
        generator = new Random();
    }

    public int getValue()
    {
        return generator.nextInt(6)+1;
    }
}