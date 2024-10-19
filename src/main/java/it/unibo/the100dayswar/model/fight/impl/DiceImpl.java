package it.unibo.the100dayswar.model.fight.impl;

import java.util.Random;
import it.unibo.the100dayswar.model.fight.api.Dice;

/**
 * Implementation of the {@link Dice} interface that simulates a six-sided die.
 * 
 * This class uses a {@link Random} number generator to produce random integers between 1 and 6,
 * emulating the roll of a traditional die.
 */
public class DiceImpl implements Dice {
    /**
     * Random number generator used to simulate the die roll.
     */
    private final Random random = new Random();

    /**
     * Number of faces on the die.
     */
    private static final int FACES = 6;

    /**
     * Rolls the die and returns an integer value between 1 and {@value #FACES}.
     *
     * @return the result of the die roll, an integer between 1 and 6 inclusive
     */
    @Override
    public int roll() {
       return random.nextInt(FACES) + 1;
    }
}
