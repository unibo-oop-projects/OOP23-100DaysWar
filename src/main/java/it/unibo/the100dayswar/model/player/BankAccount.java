package it.unibo.the100dayswar.model.player;

/**
 * Interface to define the behavior of a bank account for managing a player's resources.
 */
public interface BankAccount {
    /**
     * Retrieves the current balance of the bank account.
     *
     * @return the current balance of resources
     */
    int getBalance();
    /**
     * Adds resources to the player's bank account.
     *
     * @param amount the amount of resources to add
     */
    void addResources(int amount);
    /**
     * Spends a specific amount of resources from the player's bank account.
     *
     * @param amount the amount of resources to spend
     * @throws IllegalStateException if the player does not have enough resources
     */
    void buy(int amount);
    /**
     * Checks if the bank account has enough resources for a specified amount.
     *
     * @param amount the amount of resources needed
     * @return true if the player has enough resources, false otherwise
     */
    boolean canAfford(int amount);
}
