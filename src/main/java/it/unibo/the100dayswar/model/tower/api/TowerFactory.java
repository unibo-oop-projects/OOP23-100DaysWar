package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.impl.AbstractTower;

/**
 * Interface that rapresent a TowerFactory.
 */
public interface TowerFactory {
    /**
     * Method that call the constructor which is corrispondent to towerType.
     * 
     * @param owner the owner of the tower
     * @param towerType the type of the tower
     * @param position the position of the tower
     * 
     * @return a new tower
     */
    public AbstractTower buildTower(final Player owner, final TowerType towerType, final Cell position);
}
