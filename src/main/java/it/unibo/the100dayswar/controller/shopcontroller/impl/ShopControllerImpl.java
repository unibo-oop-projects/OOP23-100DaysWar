package it.unibo.the100dayswar.controller.shopcontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.shopcontroller.api.ShopController;
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
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyBasicTower() {
        The100DaysWar.CONTROLLER.getGameInstance().buyTower(TowerType.BASIC, null);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void buyAdvancedTower() {
        The100DaysWar.CONTROLLER.getGameInstance().buyTower(TowerType.ADVANCED, null);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void upgradeUnit(final Unit unit) {
        The100DaysWar.CONTROLLER.getGameInstance().upgradeUnit(unit);
    }
}
