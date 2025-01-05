package it.unibo.the100dayswar.controller.mapcontroller.impl;

import it.unibo.the100dayswar.view.map.CellView;
import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.unit.api.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the MapController interface.
 */
public class MapControllerImpl implements MapController {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapWidth() {
        return (int)The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getWidth();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapHeight() {
        return (int)The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CellView> getCellsView() {
        final List<CellView> cellDataList = new ArrayList<>();
        The100DaysWar.CONTROLLER.getGameInstance().getMap().getAllCells().forEach(cell -> {
            String imagePath = "";

            if (cell.isSpawn()) {
                imagePath = "/map/spawn.png";
            }

            if (cell.getUnit().isPresent()) {
                final Unit unit = cell.getUnit().get();
                if (unit instanceof Soldier) {
                    imagePath = "/units/soldier/soldier" + unit.getLevel() + ".png";
                } else if (unit instanceof Tower) {
                    imagePath = "/units/tower" + unit.getLevel() + ".png";
                }
            } else if (!cell.isBuildable()) {
                imagePath = "/map/obstacle.png";
            }
            cellDataList.add(new CellView(cell.getPosition().getX(), cell.getPosition().getY(), imagePath));
        });
        return cellDataList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMap getMap() {
        return The100DaysWar.CONTROLLER.getGameInstance().getMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCellClick(int cellX, int cellY) {
        The100DaysWar.CONTROLLER.getGameInstance().getMap().getAllCells()
            .filter(cell -> cell.getPosition().getX() == cellX && cell.getPosition().getY() == cellY)
            .findFirst()
            .ifPresent(cell -> {
                final Optional<Unit> unit = cell.getUnit();

                System.out.println("Cell clicked at (" + cellX + ", " + cellY + ")");
                System.out.println("Unit present: " + (unit.isPresent() ? unit.get().toString() : "None"));
            });
    }   
}
