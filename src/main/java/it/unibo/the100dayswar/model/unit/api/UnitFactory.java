package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.AdvancedTower;
import it.unibo.the100dayswar.model.tower.api.BasicTower;

/**
 * Interface that rapresent a factory that manages the creation
 * of the types of Unit in the game.
 */
public interface UnitFactory {
    
    /**
     * Creates a soldier.
     * 
     * @param player the player that own the soldier
     * @param observer the observer that will be notified when the soldier is created
     * @return the soldier created
     */
    Soldier createSoldier(Player player, Observer<Pair<Unit, Cell>> observer);

    /**
     * Creates a basic tower.
     * 
     * @param player the player that own the tower
     * @param position the position of the tower
     * @param observer the observer that will be notified when the tower is created
     * @return
     */
    BasicTower createBasicTower(Player player, Cell position, Observer<Pair<Unit, Cell>> observer);

    /**
     * Creates an advanced tower.
     * 
     * @param player the player that own the tower
     * @param position the position of the tower
     * @return the advanced tower created
     */
    AdvancedTower createAdvancedTower(Player player, Cell position, Observer<Pair<Unit, Cell>> observer);
}
