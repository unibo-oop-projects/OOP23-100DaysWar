package it.unibo.the100dayswar.model.player;

/**
 * The implementation of a bank account for managing a player's resources.
 */
public class BankAccountImpl implements BankAccount {

    private int balance;
    /** 
     * Constructor for a bank account.
     */
    public BankAccountImpl() {
        this.balance = 0;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getBalance() {
        return this.balance;
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
     * {@inheritDoc}
     */
    @Override
    public void addResources(final int amount) {
        setBalance(this.balance + amount);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void buy(final int amount) {
        if (!spendResources(amount)) {
            throw new IllegalStateException();
        }
    }
    /**
     * A method to spend some resources.
     * 
     * @param amount the amount to spend
     * @return true if the player can afford the amount, false otherwise
     */
    private boolean spendResources(final int amount) {
        if (canAfford(amount)) {
            setBalance(this.balance - amount);
            return true;
        }
        return false;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean canAfford(final int amount) {
        return this.getBalance() >= amount;
    }
}
