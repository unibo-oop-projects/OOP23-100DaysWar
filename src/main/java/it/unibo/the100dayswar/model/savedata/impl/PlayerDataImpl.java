package it.unibo.the100dayswar.model.savedata.impl;

import java.util.List;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;
import it.unibo.the100dayswar.model.tower.api.Tower;

/**
 * Implementation of of PlayerData interface, contains all the
 * needed data of a player to save the game.
 */
public class PlayerDataImpl implements PlayerData {
    private final Player player;
    private final List<Tower> towers;

    /**
     * Constructor of PlayerDataImpl, initializes the object
     * with the given params.
     * 
     * @param player the player to save
     * @param towers the towers of the player to save
     */
    public PlayerDataImpl(final Player player, final List<Tower> towers) {
        if (!towers.stream().allMatch(tower -> tower.getOwner().equals(player))) {
            throw new IllegalArgumentException("Every tower in tower must belong to player");
        }

        try {
            this.player = player.copy();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }

        this.towers = List.copyOf(towers);
    }

    /**
     * Gets the player.
     * 
     * @return the player
     */
    public Player getPlayer() {
        try {
            return player.copy();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
    }

    /**
     * Gets the list of towers of the player.
     * 
     * @return the list of towers of the player.
     */
    public List<Tower> getTowers() {
        return List.copyOf(towers);
    }
}
