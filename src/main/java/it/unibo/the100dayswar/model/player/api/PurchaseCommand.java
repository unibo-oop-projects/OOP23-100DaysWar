package it.unibo.the100dayswar.model.player.api;

import it.unibo.the100dayswar.model.unit.api.Buyable;

/**
 * An extension of the command pattern that represents the 
 * purchase of a buyable objectby a player.
 * 
 * @param <T> the type of the object to be bought
 */
public interface PurchaseCommand<T extends Buyable> {
    /** 
     * Purchases the object.
     * 
     * @param player the player that buys the object
     */
    void execute(Player player);
}
