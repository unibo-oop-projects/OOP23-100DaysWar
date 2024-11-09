package it.unibo.the100dayswar.model.command.impl;

import it.unibo.the100dayswar.model.command.api.PurchaseCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An implementations of the command pattern that represents the purchase of a buyable unit.
 */
public class PurchaseUnitCommand implements PurchaseCommand{
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player, final Unit unit) {
        player.spendResources(unit.costToBuy());
        player.addUnit(unit);
    }
}
