package it.unibo.the100dayswar.model.command.impl;

import it.unibo.the100dayswar.model.command.api.MovementCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Movable;

/**
 * An implementations of the command pattern that represents the movement of a movable unit.
 */
public class MovementUnitCommand implements MovementCommand {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Player player, Movable unit) {
        unit.movementRequest(null);
    }
}
