package it.unibo.the100dayswar.model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.loaddata.api.GameLoader;
import it.unibo.the100dayswar.model.loaddata.impl.GameLoaderImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.GameMapImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.GameSaver;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.statistic.api.GameStatistics;
import it.unibo.the100dayswar.model.statistic.impl.GameStatisticImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
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
    private static final int MAX_USERNAME_LENGTH = 15;
    private static final int BOT_PLAYER = 0;
    private static final int HUMAN_PLAYER = 1;
    private static final Logger LOGGER = Logger.getLogger(ModelImpl.class.getName());

    private final GameTurnManager turnManager;
    private final MapManager mapManager;
    private final List<Player> players;
    private final UnitFactory factory = new UnitFactoryImpl();
    private final GameStatistics gameStatistics;

    /** 
     * Constructor of the model from scratch.
     * 
     * @param namePlayer the username of the player
     */
    public ModelImpl(final String namePlayer) {
        this.mapManager = new MapManagerImpl(new GameMapBuilderImpl(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE));
        ActionType.add(mapManager);

        this.players = List.of(new SimpleBot(mapManager), new HumanPlayerImpl(namePlayer, mapManager.getPlayerSpawn()));
        this.turnManager = new GameTurnManagerImpl(players);
        this.gameStatistics = new GameStatisticImpl(players, mapManager);
        gameStatistics.updateAllStatistics();
    }

    /**
     * Constructor of the model from a pre-saved match.
     * 
     * @param path the path of the file containing the saves
     * @throws IllegalStateException if the data aren't laoded correctly
     */
    public ModelImpl(final Optional<String> path) {
        final GameLoader loader = path.isPresent() ? new GameLoaderImpl(path.get()) : new GameLoaderImpl();
        final Optional<GameData> data = loader.loadGame();

        if (data.isEmpty()) {
            throw new IllegalStateException("Data weren't laoded correctly");
            /*
             * TODO in questo caso possiamo lanciare una schermata di errore
             */
        }

        this.mapManager = new MapManagerImpl(data.get().getMapManager());
        ActionType.add(mapManager);

        this.players = List.of(new SimpleBot(data.get().getBotData()), new HumanPlayerImpl(getHumanPlayer()));

        this.turnManager = data.get().getGameTurnManager();
        this.gameStatistics = new GameStatisticImpl(players, mapManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyTower(final TowerType type, final Cell position) {
        final Tower tower = factory.createTower(players.get(HUMAN_PLAYER), type, position);
        players.get(HUMAN_PLAYER).buyUnit(tower);
        updateAfterCreation(tower, List.of(mapManager));
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
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(final String username) {
        if (username.length() > MAX_USERNAME_LENGTH) {
            throw new IllegalArgumentException("The username is too long");
        }
        if (players.size() == 1) {
            players.add(new HumanPlayerImpl(
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
    @Override
    public Player getHumanPlayer() {
        if (players.size() > HUMAN_PLAYER && players.get(HUMAN_PLAYER) instanceof HumanPlayer) {
            return players.get(HUMAN_PLAYER);
        } else {
            throw new IllegalStateException("The human player has not been added yet");
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Player getBotPlayer() {
        if (!players.isEmpty() && players.get(BOT_PLAYER) instanceof BotPlayer) {
            return players.get(BOT_PLAYER);
        } else {
            throw new IllegalStateException("No bot player is present");
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer() {
        return turnManager.getCurrentPlayer();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        //  mapManager.getBotSpawn() - mapMnaager.getHumanSpawn()
        /* return mapManager.getPlayersCells()
            .values().stream()
            .anyMatch(c -> c.stream().anyMatch(c -> c.equals() && c.equals())); */
        // Placeholder
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
        try {
            final GameData data = new GameDataImpl(
                (HumanPlayer) getHumanPlayer(), 
                (BotPlayer) getBotPlayer(), 
                mapManager, turnManager
            );
            final GameSaver gameSaver = new GameSaverImpl(data, path);

            gameSaver.saveGame();
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }

        // TODO isOver? o un metodo per chiudere il gioco?
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Unit unit) {
        unit.upgrade();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void skipTurn() {
        turnManager.switchTurn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        turnManager.stopTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reasumeGame() {
        turnManager.startTimer();
    }

    /**
     * TODO
     * Computes the ideal cell starting from the given cell
     * and the specified direction.
     * 
     * @param currentCell the current cell of the soldier
     * @param direction the direction the soldier has to follow
     * @return the ideal cell
     * 
     * @implNote This method computes the ideal cell, which may be invalid.
     *           The returned cell must be validated to ensure it is within
     *           the boundaries of the map.
     */
    /* 
    private Cell idealCell(final Cell currentCell, final Direction direction) {
        final Cell idCell = new CellImpl(currentCell);
        final Position idPos = idCell.getPosition();
        final int x = idPos.getX();
        final int y = idPos.getY();

        if (direction == Direction.UP) {
            idPos.setY(y - 1);
        } else if (direction == Direction.DOWN) {
            idPos.setY(y + 1);
        } else if (direction == Direction.RIGHT) {
            idPos.setX(x + 1);
        } else if (direction == Direction.LEFT) {
            idPos.setX(x - 1);
        }

        return idCell;
    }
        */

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMapWidth() {
        return mapManager.getMapDimension().getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMapHeight() {
        return mapManager.getMapDimension().getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getMap() {
        return new GameMapImpl(
            (int) mapManager.getMapDimension().getWidth(), 
            (int) mapManager.getMapDimension().getHeight(),
            MapManager.createMapFromStream((int) getMapWidth(),
            (int) getMapHeight(), mapManager.getMapAsAStream())
            );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Cell> getMapStream() {
        return mapManager.getMapAsAStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveSoldier(final Pair<Unit, Cell> source) {
        if (source.getFirst() instanceof Soldier) {
            try {
                mapManager.update(source);
            } catch (IllegalStateException e) {
                return false;
            }
            return true;
        }
        LOGGER.log(Level.WARNING, "The unit is not a soldier");
        throw new IllegalArgumentException("The unit is not a soldier");
    }

    /**
     * Method that updates the observer after the creation of a unit.
     * 
     * @param unit the unit created
     * @param observers the observer to update
     */
    private void updateAfterCreation(final Unit unit, final List<Observer<Pair<Unit, Cell>>> observers) {
        observers.forEach(o -> o.update(new Pair<>(unit, unit.getPosition())));
    }
}
