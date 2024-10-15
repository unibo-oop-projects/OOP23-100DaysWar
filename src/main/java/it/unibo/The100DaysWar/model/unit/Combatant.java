package it.unibo.the100dayswar.model.unit;

/** 
 * An interface for the objects that can fight with other ones.
 */
public interface Combatant {
    /**
     * Getter for the current health of the object.
     * 
     * @return the health of the object.
     */
    int currentHealth();
    /** 
     * Make the object take damage.
     * 
     * @param damage the amount of damage to take.
     */
    void takeDamage(int damage);
    /** 
     * Perform the attack of the object.
     */
    void performAttack();
}
