package it.unibo.the100dayswar.model;

import java.util.List;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Unit;
import it.unibo.the100dayswar.model.unit.api.UnitFactory;
import it.unibo.the100dayswar.model.unit.impl.UnitFactoryImpl;
import it.unibo.the100dayswar.model.turn.api.GameTurnManager;
import it.unibo.the100dayswar.model.turn.impl.GameTurnManagerImpl;

/**
 * Class that implements the Model interface.
 */
public class ModelImpl implements Model {
    
    private static final int DEFAULT_MAP_SIZE = 10;
    private static final int DEFAULT_OBSTACLES = 10;
    private static final int DEFAULT_BONUS_CELLS = 15;

    private final GameTurnManager turnManager;
    private final MapManager mapManager;
    private final List<Player> players;
    private final UnitFactory factory = new UnitFactoryImpl();

    /** 
     * Constructor of the ModelImpl.
     */
    private ModelImpl() {
        mapManager = new MapManagerImpl(createMapBuilder());
        ActionType.add(mapManager);
        players = List.of(new SimpleBot(mapManager));
        turnManager = new GameTurnManagerImpl(players);
    }

    /**
     * Create an istance of the game map builder.
     * 
     * @return the game map builder
     */
    private GameMapBuilder createMapBuilder() {
        return new GameMapBuilderImpl(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE)
            .initializeBuildableCells()
            .addSpawnCells()
            .addBonusCell(DEFAULT_BONUS_CELLS)
            .addObstacles(DEFAULT_OBSTACLES);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyBasicTower(Cell position) {
        BasicTower basicTower = factory.createBasicTower(getHumanPlayer(), position);
        players.get(1).buyUnit(basicTower);
        updateAfterCreation(basicTower, List.of(mapManager));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyAdvancedTower(final Cell position) {
        Tower advancedTower = factory.createAdvancedTower(getHumanPlayer(), position);
        players.get(1).buyUnit(advancedTower);
        updateAfterCreation(advancedTower, List.of(mapManager));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buySoldier() {
        final Soldier soldier = factory.createSoldier(getHumanPlayer());
        players.get(1).buyUnit(soldier);
        updateAfterCreation(soldier, List.of(mapManager));
    }

    /**
     * Method that updates the observer after the creation of a unit.
     * 
     * @param unit the unit created
     * @param observer the observer to update
     */
    private void updateAfterCreation(Unit unit, List<Observer<Pair<Unit, Cell>>> observers) {
        observers.forEach(o -> o.update(new Pair<>(unit, unit.getPosition())));
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(String username) {
        if (username.length() > 15) {
            throw new IllegalArgumentException("The username is too long");
        }
        if(players.size() == 1) {
            players.add(new PlayerImpl(
                username,
                mapManager.getPlayerSpawn()
                ));
        } else {
            throw new IllegalStateException("Error: the bot player is not added yet or maximum number of players reached");
        }
    }

    /** 
     * {@inheritDoc}
     */
    public Player getHumanPlayer() {
        if (players.size() > 1) {
            return players.get(1);
        } else {
            throw new IllegalStateException("The human player has not been added yet");
        }
    }

    /** 
     * {@inheritDoc}
     */
    public Player getBotPlayer() {
        if (!players.isEmpty() && players.get(0) instanceof BotPlayer) {
            return players.get(1);
        } else {
            throw new IllegalStateException("No bot player is present");
        }
    }

    /** 
     * {@inheritDoc}
     */
    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    /** 
     * {@inheritDoc}
     */
    public boolean isOver() {
        //  mapManager.getBotSpawn() - mapMnaager.getHumanSpawn()
        /* return mapManager.getPlayersCells()
            .values().stream()
            .anyMatch(c -> c.stream().anyMatch(c -> c.equals() && c.equals())); */
        // Placeholder
        return true;
    }
}
