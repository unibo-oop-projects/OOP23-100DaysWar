package it.unibo.the100dayswar.model.command.api;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Buyable;

/**
 * An extension of the command pattern that represents the upgrade of a buyable object.
 */
public interface UpgradeCommand extends GenericPlayerCommand<Buyable> {
    /** 
     * Upgrades the unit of the player.
     * 
     * @param player the player that upgrades the unit.
     * @param unit the unit that is upgraded.
     */
    @Override
    void execute(Player player, Buyable unit);
}
