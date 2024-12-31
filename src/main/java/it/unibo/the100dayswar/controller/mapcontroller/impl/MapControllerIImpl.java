package it.unibo.the100dayswar.controller.mapcontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.mapcontroller.api.MapController;
import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;

/**
 * The implementation of the map controller.
 */
public class MapControllerIImpl implements MapController {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMapHeight() {
        return (int) The100DaysWar.CONTROLLER.getGameInstance().getMapHeight();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMapWidth() {
        return  (int) The100DaysWar.CONTROLLER.getGameInstance().getMapWidth();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getBonusCells() {
        return  (int) The100DaysWar.CONTROLLER.getGameInstance().getMapStream().filter( cell -> cell instanceof BonusCell).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getObstacles() {
        return  (int) The100DaysWar.CONTROLLER.getGameInstance().getMapStream().filter( cell -> !cell.isBuildable()).count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell[][] getMap() {
        return The100DaysWar.CONTROLLER.getGameInstance().getMap();
    }

}
