package it.unibo.the100dayswar.model.unit;

/**
 * An interface for a buyable and upgradable object.
 */
public interface Buyable {
    /**
     * This method returns the cost to buy the object.
     * 
     * @return the cost to buy the specific object.
     */
    int costToBuy();
    /**
     * This method returns the cost to upgrade the object.
     * 
     * @return the cost to upgrade the specific object.
     */
    int costToUpgrade();
    /**
     * This method returns the level of the object.
     * 
     * @return the level of the specific object.
     */
    int level();
    /**
     * This method upgrades the object if it is possible.
     * 
     * @return true if the object has been upgraded, false otherwise.
     */
    boolean upgrade();
    /**
     * This method downgrades the object if it is possible otherwise it will destroy it.
     */
    void downgrade();
}
