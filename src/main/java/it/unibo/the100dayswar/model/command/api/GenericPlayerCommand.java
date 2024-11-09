package it.unibo.the100dayswar.model.command.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * An extension of the command pattern that represents a generic action
 * performed by a player on a buyable object.
 * 
 * @param <T> the type of the object to be bought.
 */
public interface GenericPlayerCommand<T> {
    /** 
     * Applies the command in relation to the player and the buyable object.
     * 
     * @param player the player that performs the command
     * @param object the object on which the command is performed
     */
    void execute(Player player, T object);
}
