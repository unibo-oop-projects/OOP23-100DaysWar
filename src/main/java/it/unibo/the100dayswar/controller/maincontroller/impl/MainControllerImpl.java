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
    /*
     * TODO il model non deve essere final perchè può essere 
     * inizializzato in due modi diversi.
     */
    private Model model;

    /**
     * Constructor of the main controller.
     */
    public MainControllerImpl() {
        this.statisticController = new StatisticControllerImpl();
        this.shopController = new ShopControllerImpl();
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
    public void startNewGame(final String username) {
        this.model = new ModelImpl(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean loadOldGame(String path) {
        try {
            this.model = new ModelImpl(Optional.ofNullable(path));
        } catch (IllegalStateException e) {
           return false;
        }

        return true;
    }

    /*
     * TODO controllo del tempo limite 
     *  
     * 
      public boolean loadOldGame(String path) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Callable<Boolean> task = () -> {
                try {
                    this.model = new ModelImpl(Optional.ofNullable(path));
                    return true;
                } catch (IllegalStateException e) {
                    return false;
                }
            };

            Future<Boolean> future = executor.submit(task);
            try {
                // Limita il tempo massimo a 2 secondi
                return future.get(2, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.err.println("Load old game timed out!");
                return false;
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error during load old game: " + e.getMessage());
                return false;
            } finally {
                executor.shutdownNow();
            }
        }
     *   
     *
     */
}
