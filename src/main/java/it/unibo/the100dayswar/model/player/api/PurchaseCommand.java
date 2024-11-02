package it.unibo.the100dayswar.model.player.api;

import it.unibo.the100dayswar.commons.patterns.Command;
import it.unibo.the100dayswar.model.unit.api.Buyable;

/**
 * An extension of the command pattern that represents the purchase of a buyable object.
 * 
 * @param <T> the type of the object to be bought.
 */
public interface PurchaseCommand<T extends Buyable> extends Command{
    /** 
     * Purchases the object.
     */
    @Override
    void execute();
}
