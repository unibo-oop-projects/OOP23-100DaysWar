package it.unibo.the100dayswar.controller.shopcontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.controller.shopcontroller.api.ShopController;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Class that implements the ShopController interface.
 */
public class ShopControllerImpl implements ShopController {
    /** 
     * {@inheritDoc}
     */
    @Override
    public void buySoldier() {
        The100DaysWar.CONTROLLER.getGameInstance().buySoldier();
        The100DaysWar.CONTROLLER.getGameController().skip();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyBasicTower() {
        final Pair<Unit, Cell> selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        The100DaysWar.CONTROLLER.getGameInstance().buyTower(TowerType.BASIC, selectedCell.getSecond());
        The100DaysWar.CONTROLLER.getGameController().skip();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyAdvancedTower() {
        final Pair<Unit, Cell> selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        The100DaysWar.CONTROLLER.getGameInstance().buyTower(TowerType.ADVANCED, selectedCell.getSecond());
        The100DaysWar.CONTROLLER.getGameController().skip();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit() {
        final Pair<Unit, Cell> selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        final Unit unit = selectedCell.getFirst();
        if (unit == null) {
            throw new IllegalStateException("No unit selected");
        }
        The100DaysWar.CONTROLLER.getGameInstance().upgradeUnit(unit);
        The100DaysWar.CONTROLLER.getGameController().skip();
    }
}
