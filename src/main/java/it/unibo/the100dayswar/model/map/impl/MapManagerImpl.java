package it.unibo.the100dayswar.model.map.impl;

import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Unit;


/**
 * the implementation of the MapManager.
 */
public class MapManagerImpl implements MapManager {
    private static final long serialVersionUID = 1L;

    private final GameMapBuilder builder;
    private final GameMap map;

    /**
     * the builder of the map.
     * @param builder
     */
    public MapManagerImpl(final GameMapBuilder builder) {
        this.builder = builder;
        map = createMap();
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
     * create a new soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void createSoldier(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        final BuildableCell currentCell = (BuildableCellImpl) soldier.getPosition();
        if (((BuildableCell) source.getSecond()).isSpawn()) {
        currentCell.setOccupation(Optional.of(soldier));
        }
    }

    /**
     * create a new tower.
     * @param source is the pair of the tower and the cell.
     */
    private void createTower(final Pair<Unit, Cell> source) {
        final Tower tower = (Tower) source.getFirst();
        final BuildableCellImpl targetCell = (BuildableCellImpl) source.getSecond();
        final BuildableCell currentCell = (BuildableCellImpl) tower.getPosition();
        if (currentCell.equals(targetCell)) {
            currentCell.setOccupation(Optional.of(tower));
        } 
    }

    /**
     * move the soldier.
     * @param source is the pair of the soldier and the cell.
     */
    private void soldierMovement(final Pair<Unit, Cell> source) {
        final Soldier soldier = (Soldier) source.getFirst();
        final BuildableCellImpl targetCell = (BuildableCellImpl) source.getSecond();
        final BuildableCell currentCell = (BuildableCellImpl) soldier.getPosition();
        if (currentCell.isAdiacent(targetCell) && targetCell.isFree()) {
            soldier.move(targetCell);
            currentCell.setOccupation(Optional.empty());
            targetCell.setOccupation(Optional.of(soldier));
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
        return source.getFirst() instanceof Soldier && source.getFirst().getPosition().equals(source.getSecond());
    }

    /**
     * check if the unit is a tower.
     * @param source is the pair of the unit and the cell.
     * @return true if the unit is a tower.
     */
    private boolean isTower(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Tower;
    }

    /**
     * check if the soldier wants to move.
     * @param source is the pair of the soldier and the cell.
     * @return true if the soldier wants to move.
     */
    private boolean isSoldierWantsToMove(final Pair<Unit, Cell> source) {
        return source.getFirst() instanceof Soldier && !source.getFirst().getPosition().equals(source.getSecond());
    }
}
