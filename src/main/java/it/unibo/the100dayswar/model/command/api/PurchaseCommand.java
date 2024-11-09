package it.unibo.the100dayswar.model.command.api;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An extension of the command pattern that represents the purchase of a buyable object.
 */
public interface PurchaseCommand extends GenericPlayerCommand<Unit> {
    /** 
     * Purchases the unit of the player.
     * 
     * @param player the player that purchases the unit.
     * @param unit the unit that is purchased.
     */
    @Override
    void execute(Player player, Unit unit);
}
