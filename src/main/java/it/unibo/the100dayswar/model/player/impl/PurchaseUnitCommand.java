package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.api.PurchaseCommand;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An implementations of the command pattern that represents the purchase of a buyable unit.
 */
public class PurchaseUnitCommand implements PurchaseCommand<Unit> {
    private final Unit unit;
    /**
     * Constructor for the class by the given unit to buy.
     * 
     * @param unit the unit to be bought
     */
    public PurchaseUnitCommand(final Unit unit) {
        this.unit = unit;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player) {
        player.spendResources(unit.costToBuy());
        player.addUnit(unit);
    }
}
