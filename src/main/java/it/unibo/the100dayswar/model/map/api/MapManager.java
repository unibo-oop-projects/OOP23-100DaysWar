package it.unibo.the100dayswar.model.map.api;


import java.util.Map;
import java.util.Set;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * interface that model the concept of Mapmanager for the managment of the iteration between the soldiers and the bonus cells.
 */
public interface MapManager extends Observer<Pair<Unit, Cell>> {

    /**
     * method that update the map with the new position of the soldier.
     * @param pair the pair of the soldier and the new position.
     */
    @Override
    void update(Pair<Unit, Cell> pair);

    /**
     * @return the cells in possession of the players.
     */
    Map<Player, Set<Cell>> getPlayersCells();
}
