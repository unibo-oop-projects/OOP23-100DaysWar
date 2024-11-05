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
    private final List <Tower> towers;

    /**
     * Constructor of PlayerDataImpl, initializes the object
     * with the given params.
     * 
     * @param player the player to save
     * @param towers the towers of the player to save
     */
    public PlayerDataImpl(Player player, List<Tower> towers) {
        if(!towers.stream().allMatch(tower -> tower.getOwner().equals(player) )) {
            throw new IllegalArgumentException("Every tower in tower must belong to player");
        }

        this.player = player;
        this.towers = towers;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Tower> getTowers() {
        return towers;
    }
}
