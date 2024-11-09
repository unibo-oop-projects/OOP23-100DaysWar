package it.unibo.the100dayswar.model.playeraction.impl;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.playeraction.api.MovementCommand;
import it.unibo.the100dayswar.model.unit.api.Movable;
import it.unibo.the100dayswar.model.unit.api.Soldier;

/**
 * An implementations of the command pattern that represents the movement of a 
 * movable unit.
 */
public class MovementUnitCommand implements MovementCommand {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player player, final Pair<Movable, Cell> pair) {
        if (player.getSoldiers().contains((Soldier) pair.getFirst())) {
            pair.getFirst().movementRequest(pair.getSecond());
        } else {
            throw new IllegalArgumentException("The unit is not owned by the player.");
        }
    }
}
