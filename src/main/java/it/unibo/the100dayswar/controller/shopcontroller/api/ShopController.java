package it.unibo.the100dayswar.controller.shopcontroller.api;

import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * Interface that represents the controller of the shop.
 */
public interface ShopController {
    
    /**
     * Method that allows the player to buy a soldier.
     */
    void buySoldier();

    /**
     * Method that allows the player to buy a tower.
     */
    void buyBasicTower();

    /**
     * Method that allows the player to buy a tower.
     */
    void buyAdvancedTower();

    /**
     * Method that allows the player to upgrade a unit.
     * 
     * @param unit the unit to upgrade
     */
    void upgradeUnit(Unit unit);
}
