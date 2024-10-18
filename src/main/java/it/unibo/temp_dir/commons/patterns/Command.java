package it.unibo.the100dayswar.commons.patterns;

/**
 * Interface that represents a generic command.
 * Implementing this interface allows to create a concrete command
 * for different classes.
 */
public interface Command {
    /**
     * Method to execute the command.
     */
    void execute();
}
