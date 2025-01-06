package it.unibo.the100dayswar.controller.mapcontroller.impl;

import it.unibo.the100dayswar.view.map.CellView;
import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.map.api.GameMap;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.tower.api.AdvancedTower;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.unit.api.Unit;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the MapController interface.
 */
public class MapControllerImpl implements MapController {

    private Pair<Unit, Cell> selectedCell;
    /**
     * Constructor for the MapControllerImpl.
     */
    public MapControllerImpl() {
        this.selectedCell = new Pair<>(null, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapWidth() {
        return (int) The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMapHeight() {
        return (int) The100DaysWar.CONTROLLER.getGameInstance().getMap().getSize().getHeight();
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
                if (unit instanceof Soldier && unit.getOwner() instanceof HumanPlayer ) {
                    imagePath = "/units/soldier/soldier" + unit.getLevel() + ".png";
                } else if (unit instanceof Soldier && unit instanceof BotPlayer ){
                    imagePath = "/units/soldier/enemy" + unit.getLevel() + ".png";
                } else if (unit instanceof BasicTower && unit.getOwner() instanceof HumanPlayer) {
                    imagePath = "/units/tower/human_basic" + unit.getLevel() + ".png";
                } else if (unit instanceof AdvancedTower && unit.getOwner() instanceof HumanPlayer) {
                    imagePath = "/units/tower/human_advanced" + unit.getLevel() + ".png";    
                } else if (unit instanceof BasicTower && unit.getOwner() instanceof BotPlayer) {
                    imagePath = "/units/tower/enemy_basic" + unit.getLevel() + ".png";
                } else if (unit instanceof AdvancedTower && unit.getOwner() instanceof BotPlayer) {
                    imagePath = "/units/tower/enemy_advanced" + unit.getLevel() + ".png";
                }
            } else if (!cell.isBuildable()) {
                imagePath = "/map/obstacle.png";
            }
            cellDataList.add(new CellView(cell.getPosition(), imagePath));
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
    public void onCellClick(final int cellX, final int cellY) {
         final GameMap map = The100DaysWar.CONTROLLER.getGameInstance().getMap();
         final Cell clickedCell = map.getCell(new PositionImpl(cellX, cellY));
         selectedCell = new Pair<>(clickedCell.getUnit().orElse(null), clickedCell);
         System.out.println("Selected cell:\t" + selectedCell.getSecond().getPosition().toString());
         System.out.println("Occupied:\t" + !selectedCell.getSecond().isFree());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Unit, Cell> getSelectedCell() {
        return this.selectedCell;
    }
}
