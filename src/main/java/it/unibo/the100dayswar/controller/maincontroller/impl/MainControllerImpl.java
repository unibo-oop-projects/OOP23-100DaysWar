package it.unibo.the100dayswar.controller.maincontroller.impl;

import java.util.Optional;

import it.unibo.the100dayswar.controller.maincontroller.api.MainController;
import it.unibo.the100dayswar.controller.shopcontroller.api.ShopController;
import it.unibo.the100dayswar.controller.shopcontroller.impl.ShopControllerImpl;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.controller.statisticscontoller.impl.StatisticControllerImpl;
import it.unibo.the100dayswar.model.Model;
import it.unibo.the100dayswar.model.ModelImpl;
import it.unibo.the100dayswar.view.startmenu.StartMenuView;


/**
 * The implementation of the main controller of the game.
 */
public class MainControllerImpl implements MainController {

    private final StatisticController statisticController;
    private final ShopController shopController;
    private final Model model;


    /**
     * Constructor of the main controller.
     */
    public MainControllerImpl() {
        this.statisticController = new StatisticControllerImpl();
        this.shopController = new ShopControllerImpl();
        this.model = null;  // TODO da inizializzare
    }


    /** 
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        new StartMenuView();
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
    public ShopController getShopController() {
        return this.shopController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveGame(final String path) {
       return model.saveGame(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean loadOldGame(final String path) {
        try {
            this.model = new ModelImpl(Optional.ofNullable(path));
        } catch (IllegalStateException e) {
            return false;
        }

        return true;
    }
}
