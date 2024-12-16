package it.unibo.the100dayswar.model.tower.api;

/**
 * Interface that rapresent an advanced tower.
 */
public interface AdvancedTower extends Tower {
    /**
     * Methods that get the damage of the advanced tower.
     * 
     * @return the damage of the advanced tower
     */
    @Override
    int getDamage();
}
