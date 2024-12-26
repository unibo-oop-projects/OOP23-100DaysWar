package it.unibo.the100dayswar.model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.Direction;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.impl.ActionType;
import it.unibo.the100dayswar.model.bot.impl.SimpleBot;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.loaddata.api.GameLoader;
import it.unibo.the100dayswar.model.loaddata.impl.GameLoaderImpl;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.map.impl.GameMapBuilderImpl;
import it.unibo.the100dayswar.model.map.impl.MapManagerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.savedata.api.GameData;
import it.unibo.the100dayswar.model.savedata.api.GameSaver;
import it.unibo.the100dayswar.model.savedata.impl.GameDataImpl;
import it.unibo.the100dayswar.model.savedata.impl.GameSaverImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
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
    private static final int DEFAULT_OBSTACLES = 10;
    private static final int DEFAULT_BONUS_CELLS = 15;
    private static final int MAX_USERNAME_LENGTH = 15;
    private static final int HUMAN_PLAYER = 1;

    private final GameTurnManager turnManager;
    private final MapManager mapManager;
    private final List<Player> players;
    private final UnitFactory factory = new UnitFactoryImpl();

    /** 
     * Constructor of the model from scratch.
     * 
     * @param namePlayer the username of the player
     */
    public ModelImpl(final String namePlayer) {
        this.mapManager = new MapManagerImpl(createMapBuilder());
        ActionType.add(mapManager);

        this.players = List.of(new SimpleBot(mapManager));
        this.players.add(new PlayerImpl(namePlayer, mapManager.getPlayerSpawn()));

        this.turnManager = new GameTurnManagerImpl(players);
    }

    /**
     * Constructor of the model from a pre-saved match.
     * 
     * @param path the path of the file containing the saves
     * @throws IllegalStateException if the data aren't laoded correctly
     */
    public ModelImpl(final Optional<String> path) {
        final GameLoader loader = new GameLoaderImpl(path.get());
        final Optional<GameData> data = loader.loadGame();

        if (data.isEmpty()) {
            throw new IllegalStateException("Data weren't laoded correctly");
            /*
             * TODO in questo caso possiamo lanciare una schermata di errore
             */
        }

        this.mapManager = new MapManagerImpl(data.get().getMapManager());
        ActionType.add(mapManager);

        this.players = List.of(new PlayerImpl(data.get().getPlayerData1()), new PlayerImpl(data.get().getPlayerData1()));

        this.turnManager = data.get().getGameTurnManager();
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
     * Method that updates the observer after the creation of a unit.
     * 
     * @param unit the unit created
     * @param observers the observer to update
     */
    private void updateAfterCreation(final Unit unit, final List<Observer<Pair<Unit, Cell>>> observers) {
        observers.forEach(o -> o.update(new Pair<>(unit, unit.getPosition())));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveSoldier(final Soldier soldier, final Direction direction) {
        final Cell currentPosition = soldier.getPosition();
        final int totalRows = (int) mapManager.getMapDimension().getHeight();
        final int totalCols = (int) mapManager.getMapDimension().getWidth();

        final Cell idCell = idealCell(currentPosition, direction);
        final int newX = idCell.getPosition().getX();
        final int newY = idCell.getPosition().getY();

        /*
         * Check that the new cell is valid.
         */
        if (newX >= 0 && newX < totalCols && newY >= 0 && newY < totalRows) {
            soldier.move(idCell);
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
        try {
            final GameData data = new GameDataImpl(getCurrentPlayer(), getBotPlayer(), mapManager, turnManager);
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
     * Create a map builder to instanciate the map.
     * 
     * @return the builder of the map
     */
    private GameMapBuilder createMapBuilder() {
        return new GameMapBuilderImpl(DEFAULT_MAP_SIZE, DEFAULT_MAP_SIZE)
            .initializeBuildableCells()
            .addSpawnCells()
            .addBonusCell(DEFAULT_BONUS_CELLS)
            .addObstacles(DEFAULT_OBSTACLES);
    }

    /**
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
}
