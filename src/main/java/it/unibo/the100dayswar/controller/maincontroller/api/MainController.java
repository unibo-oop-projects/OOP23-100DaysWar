package it.unibo.the100dayswar.controller.maincontroller.api;

import it.unibo.the100dayswar.controller.statisticContoller.api.StatisticController;
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
     * Returns the game instance.
     * @return the game instance
     */
    Model getGameInstance();
}
