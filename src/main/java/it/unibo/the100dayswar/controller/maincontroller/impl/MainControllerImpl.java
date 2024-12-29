package it.unibo.the100dayswar.controller.maincontroller.impl;

import it.unibo.the100dayswar.controller.maincontroller.api.MainController;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.controller.statisticscontoller.impl.StatisticControllerImpl;
import it.unibo.the100dayswar.model.Model;


/**
 * The implementation of the main controller of the game.
 */
public class MainControllerImpl implements MainController {

    private final StatisticController statisticController;
    private final Model model;


    /**
     * Constructor of the main controller.
     */
    public MainControllerImpl() {
        this.statisticController = new StatisticControllerImpl();
        this.model = null;
    }


    /** 
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startGame'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Model getGameInstance() {
        return this.model;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public StatisticController getStatisticController() {
        return this.statisticController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
       return model.saveGame(path);
    }
}
