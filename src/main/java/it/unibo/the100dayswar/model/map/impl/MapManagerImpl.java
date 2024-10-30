package it.unibo.the100dayswar.model.map.impl;

import java.util.Optional;

import it.unibo.the100dayswar.model.map.api.BonusCell;
import it.unibo.the100dayswar.model.map.api.BuildableCell;
import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.unit.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * the implementation of the MapManager.
 */
public class MapManagerImpl implements MapManager {
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
     */
    @Override
    public void handleUnitMovement(final Soldier soldier, final Cell targetPosition) {
        final BuildableCellImpl targetCell = (BuildableCellImpl) targetPosition;
        if (targetCell instanceof BonusCell) {
            ((BonusCell) targetCell).notify(soldier.getOwner());
        }
        soldier.move(targetPosition);
        update(targetCell);
    }

    /**
     *{@inheritDoc} 
     * map.getsize() for fixing error.
     */
    @Override
    public void update(final Cell cell) {
            if (cell instanceof BuildableCellImpl && ((BuildableCell) cell).isFree()) {
                 final Unit unit = ((BuildableCell) cell).getUnit().get();
                ((BuildableCell) cell).setOccupation(Optional.of(unit));
            }
        map.getSize(); 
    }
}
