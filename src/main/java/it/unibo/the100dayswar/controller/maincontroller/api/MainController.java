package it.unibo.the100dayswar.controller.maincontroller.api;

import it.unibo.the100dayswar.controller.shopcontroller.api.ShopController;
import it.unibo.the100dayswar.controller.statisticscontoller.api.StatisticController;
import it.unibo.the100dayswar.model.Model;

/** 
 * MainController is a provider that allow to get access to the model
 * and to the other controllers of the game.
 */
public interface MainController {
    /** 
     * Starts the game.
     */
    void startGame();

    /** 
     * Returns the statistic controller.
     * @return the statistic controller
     */
    StatisticController getStatisticController();

    /** 
     * Returns the shop controller.
     * 
     * @return the shop controller
     */
    ShopController getShopController();

    /** 
     * Returns the game instance.
     * @return the game instance
     */
    Model getGameInstance();

    /**
     * Save the current game.
     * 
     * @param path the path of the saving_file
     * @return true if the game was saved correctly
     *         false otherwise
     * 
     * @implNote if the path is null the game will be 
     * saved in the default path.
     */
    boolean saveGame(String path);

    /**
     * Load a previous game.
     * 
     * @param path the path of the saving_file
     * 
     * @return true if the game is loaded correctly
     *         false otherwise
     * 
     * @implNote if the path is null the game will be 
     * loaded from the default path.
     */
    boolean loadOldGame(String path);
}
