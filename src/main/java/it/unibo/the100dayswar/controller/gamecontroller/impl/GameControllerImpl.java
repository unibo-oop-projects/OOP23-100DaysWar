package it.unibo.the100dayswar.controller.gamecontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.gamecontroller.api.GameController;

public class GameControllerImpl implements GameController {

    @Override
    public void attack() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void pause() {
        The100DaysWar.CONTROLLER.getGameInstance().pauseGame();
    }

    @Override
    public void resume() {
        The100DaysWar.CONTROLLER.getGameInstance().reasumeGame();
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }
    
}
