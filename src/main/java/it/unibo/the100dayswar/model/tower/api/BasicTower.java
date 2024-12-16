package it.unibo.the100dayswar.model.tower.api;

/**
 * Interface that rapresent a basic tower.
 */
public interface BasicTower extends Tower {
    /**
     * Method to get the damage of the basic tower.
     * 
     * @return the damage of the basic tower
     */
    @Override
    int getDamage();
}
