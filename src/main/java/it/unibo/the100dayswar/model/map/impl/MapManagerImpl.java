package it.unibo.the100dayswar.model.map.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Unit;


/**
 * the implementation of the MapManager.
 */
public class MapManagerImpl implements MapManager {
    private static final long serialVersionUID = 1L;

    private final GameMapBuilder builder;
    private final GameMap map;
    private static final Logger LOGGER = Logger.getLogger(MapManagerImpl.class.getName());
    private final Map<Player, Set<Cell>> playersCells;

    /**
     * the builder of the map.
     * @param builder
     */
    public MapManagerImpl(final GameMapBuilder builder) {
        this.builder = builder;
        map = createMap();
        playersCells = new HashMap<>();
    }

    /**
     * the builder of the map with all objects.
     * @return the map
    */
    private GameMap createMap() {
        return builder
        .addBonusCell(4)
        .addObstacles(3)
        .addSpawnCells()
        .build();
    }

    /**
     *{@inheritDoc}
     * map.getsize for fixing intermediate error by the unusing of map object.
     */
    @Override
    public void update(final Pair<Unit, Cell> source) {
            if (isSoldierWantsToMove(source)) {
                soldierMovement(source);
            }
            if (isNewSoldier(source)) {
                createSoldier(source);
            }
            if (isTower(source)) {
                createTower(source);
            }
            map.getSize();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Map<Player, Set<Cell>> getPlayersCells() {
        return new HashMap<>(playersCells); 
    }

    /**
     * add the cell to the player.
     * @param player is the player.
     * @param targetCell is the cell.
     */
    private void  addCell(final Player player, final Cell targetCell) {
        final Set<Cell> cells = playersCells.computeIfAbsent(player, p -> new HashSet<>());
        cells.add(targetCell); 
    }

    /**
     * remove the cell from the player.
     * @param player is the player.
     * @param targetCell is the cell.
     */
    private void removeCell(final Player player, final Cell targetCell) {
        final Set<Cell> cells = playersCells.get(player);
        if (cells != null && cells.contains(targetCell)) {
            cells.remove(targetCell);
        }
    }

    /**
     * create a new soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void createSoldier(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        final BuildableCell currentCell = (BuildableCellImpl) soldier.getPosition();
        final BuildableCell targetCell = (BuildableCellImpl) source.getSecond();
        if (!((BuildableCell) source.getSecond()).isSpawn()) {
            LOGGER.log(Level.WARNING, "Target cell is not a spawn cell for soldier creation: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not a spawn cell for soldier creation.");
        }
        currentCell.setOccupation(Optional.of(soldier));
        addCell(soldier.getOwner(), targetCell);
    }

    /**
     * create a new tower.
     * @param source is the pair of the tower and the cell.
     */
    private void createTower(final Pair<Unit, Cell> source) {
        final Tower tower = (Tower) source.getFirst();
        final BuildableCellImpl targetCell = (BuildableCellImpl) source.getSecond();
        final BuildableCell currentCell = (BuildableCellImpl) tower.getPosition();
        if (!targetCell.isBuildable()) {
            LOGGER.log(Level.WARNING, "Target cell is not buildable for tower placement: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not buildable for tower placement.");
        }
            currentCell.setOccupation(Optional.of(tower));
    }

    /**
     * move the soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void soldierMovement(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        final BuildableCellImpl targetCell = (BuildableCellImpl) source.getSecond();
        final BuildableCell currentCell = (BuildableCellImpl) soldier.getPosition();
        if (!targetCell.isFree()) {
            LOGGER.log(Level.WARNING, "Target cell is not free for soldier movement: {0}", targetCell.getPosition());
            throw new IllegalStateException("Target cell is not free for soldier movement.");
        }
        if (currentCell.isAdiacent(targetCell) && targetCell.isFree()) {
            soldier.move(targetCell);
            currentCell.setOccupation(Optional.empty());
            targetCell.setOccupation(Optional.of(soldier));
            playersCells.forEach((p, s) -> {
                if (p.equals(soldier.getOwner())) {
                    addCell(p, targetCell);
                } else if (s.contains(targetCell)) {
                    removeCell(p, targetCell);
                }
            });
            if (!playersCells.containsKey(soldier.getOwner()) || !playersCells.get(soldier.getOwner()).contains(currentCell)) {
                addCell(soldier.getOwner(), currentCell);
            }
            if (targetCell instanceof BonusCell) {
                ((BonusCell) targetCell).notify(soldier.getOwner());
            }
        }
    }

    /**
     * check if the soldier is new.
     * @param source is the pair of the soldier and the cell.
     * @return true if the soldier is new.
     */
    private boolean isNewSoldier(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Soldier
        && source.getSecond().equals(((Soldier) source.getFirst()).getPosition())
        && ((Soldier) source.getFirst()).getPosition().isSpawn();
    }

    /**
     * check if the unit is a tower.
     * @param source is the pair of the unit and the cell.
     * @return true if the unit is a tower.
     */
    private boolean isTower(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Tower && source.getFirst().getPosition().equals(source.getSecond());
    }

    /**
     * check if the soldier wants to move.
     * @param source is the pair of the soldier and the cell.
     * @return true if the soldier wants to move.
     */
    private boolean isSoldierWantsToMove(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Soldier
        && !source.getFirst().getPosition().equals(source.getSecond());
    }
}
