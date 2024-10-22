package it.unibo.the100dayswar.model.player.impl;

import it.unibo.the100dayswar.commons.patterns.Command;
import it.unibo.the100dayswar.model.player.api.MutablePlayer;
import it.unibo.the100dayswar.model.unit.api.Buyable;

/**
 * An implementations of the command pattern that represents the purchase of a buyable unit.
 */
public class PurchaseUnitCommand implements Command {
    private final MutablePlayer player;
    private final Buyable unit;
    /**
     * Constructor for the BuyCommand.
     * 
     * @param player the player that buys the unit
     * @param unit the unit to be bought
     */
    public PurchaseUnitCommand(MutablePlayer player, Buyable unit) {
        this.player = player;
        this.unit = unit;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        player.buyUnit(unit);
    }
}
