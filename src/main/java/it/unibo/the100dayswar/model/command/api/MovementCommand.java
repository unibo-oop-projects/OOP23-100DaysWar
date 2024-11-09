package it.unibo.the100dayswar.model.command.api;

import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.unit.api.Movable;

/**
 * An extension of the command pattern that represents the movement 
 * of a movable object.
 */
public interface MovementCommand extends GenericPlayerCommand<Movable> {
    /** 
     * Moves the unit of the player.
     * 
     * @param player the player that moves the unit.
     * @param unit the unit that is moved.
     */
    @Override
    void execute(Player player, Movable unit);
}
