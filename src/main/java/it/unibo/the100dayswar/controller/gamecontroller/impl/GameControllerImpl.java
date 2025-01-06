package it.unibo.the100dayswar.controller.gamecontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.gamecontroller.api.GameController;

/**
 * The implementation of the game controller of the game.
 */
public class GameControllerImpl implements GameController {

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void skip() {
        The100DaysWar.CONTROLLER.getGameInstance().skipTurn();
    }
}
