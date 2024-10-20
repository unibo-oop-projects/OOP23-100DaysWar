package it.unibo.the100dayswar.model.unit.api;

/**
 * An interface for a buyable and upgradable object.
 */
public interface Buyable {
    /**
     * This method returns the cost to buy the object.
     * 
     * @return the cost to buy
     */
    int costToBuy();
    /**
     * This method returns the cost to upgrade of the object.
     * 
     * @return the cost to upgrade
     */
    int costToUpgrade();
    /**
     * This method returns the level of the object.
     * 
     * @return the level
     */
    int level();
    /**
     * Upgrade the object if it is possible.
     * 
     * @throws IllegalStateException if it is not possible upgrade the object
     */
    void upgrade();
    /**
     * Downgrade the object if it is possible otherwise it will destroy it.
     */
    void downgrade();
}
