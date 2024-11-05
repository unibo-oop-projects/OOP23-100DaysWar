package it.unibo.the100dayswar.model.map.impl;

import java.util.Optional;

import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.map.api.GameMapBuilder;
import it.unibo.the100dayswar.model.map.api.MapManager;
import it.unibo.the100dayswar.model.unit.api.Soldier;


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
     * map.getsize for fixing intermediate error by the unusing of map object.
     */
    @Override
    public void handleUnitMovement(final Soldier soldier, final Cell targetPosition) {
        final BuildableCellImpl targetCell = (BuildableCellImpl) targetPosition;
        final BuildableCell currentCell = (BuildableCellImpl) soldier.getPosition();
        if (currentCell.isAdiacent(targetCell) && targetCell.isFree()) {
            soldier.move(targetPosition);
            currentCell.setOccupation(Optional.empty());
            targetCell.setOccupation(Optional.of(soldier));
            if (targetCell instanceof BonusCell) {
                ((BonusCell) targetCell).notify(soldier.getOwner());
            }
            map.getSize();
        }
    }
}
