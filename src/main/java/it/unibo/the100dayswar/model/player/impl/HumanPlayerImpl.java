package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;

/**
 * An implementation of the HumanPlayer interface with the specific
 * actions that a player can perform during the game.
 */
public class HumanPlayerImpl extends AbstractPlayer implements HumanPlayer {
    private static final long serialVersionUID = 1L;
    /**
     * Constructor for the human player from the given parameters.
     * 
     * @param username the username of the player
     * @param spawnPoint the spawn point of the player
      */
    public HumanPlayerImpl(final String username, final BuildableCell spawnPoint) {
        super(username, spawnPoint);
    }
}
