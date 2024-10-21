package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.model.player.api.BankAccount;

/**
 * The implementation of a bank account for managing a player's resources.
 */
public class BankAccountImpl implements BankAccount {

    private static final int INITIAL_BALANCE = 1000;
    private int balance;
    /** 
     * Constructor for a bank account.
     */
    public BankAccountImpl() {
        this.balance = INITIAL_BALANCE;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getBalance() {
        return this.balance;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void earn(final int amount) {
        setBalance(this.balance + amount);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void purchase(final int amount) {
        if (!canAfford(amount)) {
            throw new IllegalStateException();
        }
        setBalance(this.balance - amount);
    }
    /** 
     * Change the balance of the bank account.
     * 
     * @param newBalance the new balance to set
     */
    private void setBalance(final int newBalance) {
        this.balance = newBalance;
    }
    /**
     * Checks if the current bank account can spend an amount of resources.
     * 
     * @param amount the amount of resources to check
     * @return true if the player can afford the amount, false otherwise
     */
    private boolean canAfford(final int amount) {
        return this.getBalance() >= amount;
    }
}
