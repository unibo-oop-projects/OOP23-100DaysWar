package it.unibo.the100dayswar.model.savedata.api;

import java.util.List;
import java.io.Serializable;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.Tower;

/**
 * Interface that contains all the data of a player.
 */
public interface PlayerData extends Serializable {
    /**
     * {@inheritDoc}
     */
    @Override
    int hashCode();

    /**
     * {@inheritDoc}
     */
    @Override
    boolean equals(Object obj);

    /**
     * Gets the player that has this data.
     * 
     * @return the player that has this data
     */
    Player getPlayer();

    /**
     * Gets a list of the towers of the player.
     * 
     * @return a list of the towers of the player
     */
    List<Tower> getTowers();
}
