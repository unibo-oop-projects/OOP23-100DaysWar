package it.unibo.the100dayswar.model.savedata.impl;

import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;

/**
 * Class that saves all the data that need to be serialized.
 */
public class GameDataImpl implements GameData {
    private static final long serialVersionUID = 1L;

    private final Player playerData1;
    private final Player playerData2;
    private final GameMap gameMap;  // TODO cambiare a MapManager
    private final GameTurnManager gameTurnManager;

    /**
     * Constructor of GameDataImpl, initializes the object
     * with the given params.
     * 
     * @param playerData1 the player to save
     * @param playerData2 the player to save
     * @param gameMap the map of the current game
     * @param gameTurnManager the game turn manager of the current game
     */
    public GameDataImpl(
            final Player playerData1, 
            final Player playerData2, 
            final GameMap gameMap, 
            final GameTurnManager gameTurnManager) {

        if (playerData1.equals(playerData2)) {
            throw new IllegalArgumentException("playerData1 and playerData2 must be different");
        }

        this.playerData1 = new PlayerImpl(playerData1);
        this.playerData2 = new PlayerImpl(playerData2);
        this.gameMap = new GameMapImpl(gameMap);
        this.gameTurnManager = gameTurnManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerData1() {
        return new PlayerImpl(playerData1);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayerData2() {
        return new PlayerImpl(playerData2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getGameMap() {
        return new GameMapImpl(this.gameMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameTurnManager getGameTurnManager() {
        return gameTurnManager;
    }
}
