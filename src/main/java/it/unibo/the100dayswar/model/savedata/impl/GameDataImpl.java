package it.unibo.the100dayswar.model.savedata.impl;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.PlayerData;

/**
 * Class that saves all the data that need to be serialized.
 */
public class GameDataImpl implements GameData {
    private static final long serialVersionUID = 1L;

    private final PlayerData playerData1;
    private final PlayerData playerData2;
    private final GameMap gameMap;

    /**
     * Constructor of GameDataImpl, initializes the object
     * with the given params.
     * 
     * @param playerData1 the player to save
     * @param playerData2 the towers of the player to save
     * @param gameMap the map of the current game
     */
    public GameDataImpl(final PlayerData playerData1, final PlayerData playerData2, final GameMap gameMap) {
        if (playerData1.equals(playerData2)) {
            throw new IllegalArgumentException("playerData1 and playerData2 must be different");
        }

        this.playerData1 = playerData1;
        this.playerData2 = playerData2;
        this.gameMap = gameMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerData getPlayerData1() {
        return this.playerData1;
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public PlayerData getPlayerData2() {
        return this.playerData2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getGameMap() {
        return this.gameMap;
    }
}
