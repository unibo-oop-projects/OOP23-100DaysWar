package it.unibo.the100dayswar.model.savedata.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;
import it.unibo.the100dayswar.model.tower.api.Tower;

/**
 * Implementation of of PlayerData interface, contains all the
 * needed data of a player to save the game.
 */
public class PlayerDataImpl implements PlayerData {
    private static final Logger LOGGER = Logger.getLogger(PlayerDataImpl.class.getName());
    private static final long serialVersionUID = 1L;
    private static final String CLONE_NOT_SUPPORTED = "Clone not supported";

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
            LOGGER.log(Level.SEVERE, "Every tower in tower must belong to player", new IllegalArgumentException());
            throw new IllegalArgumentException("Every tower in tower must belong to player");
        }

        try {
            this.player = player.copy();
        } catch (CloneNotSupportedException e) {
            LOGGER.log(Level.SEVERE, CLONE_NOT_SUPPORTED, new IllegalArgumentException());
            throw new IllegalStateException(CLONE_NOT_SUPPORTED, e);
        }

        this.towers = List.copyOf(towers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((towers == null) ? 0 : towers.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        final PlayerDataImpl other = (PlayerDataImpl) obj;
        if (player == null) {
            if (other.player != null) {
                return false;
            }
        } else if (!player.equals(other.player)) {
            return false;
        }

        if (towers == null) {
            if (other.towers != null) {
                return false;
            }
        } else if (!towers.equals(other.towers)) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        try {
            return player.copy();
        } catch (CloneNotSupportedException e) {
            LOGGER.log(Level.SEVERE, CLONE_NOT_SUPPORTED, new IllegalArgumentException());
            throw new IllegalStateException(CLONE_NOT_SUPPORTED, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tower> getTowers() {
        return List.copyOf(towers);
    }
}
