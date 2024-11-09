package it.unibo.the100dayswar.model.playeraction.impl;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.playeraction.api.PurchaseCommand;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An implementations of the command pattern that represents the purchase 
 * of a buyable unit.
 */
public class PurchaseUnitCommand implements PurchaseCommand {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player, final Unit unit) {
        player.spendResources(unit.costToBuy());
        player.addUnit(unit);
        unit.notifyObservers(unit.getPosition());
        if (player.getUnits().contains(unit)) {
            unit.notifyObservers(unit.getPosition());
        } else {
            throw new IllegalStateException("The unit is not be added correctly to the player.");
        }
    }
}
