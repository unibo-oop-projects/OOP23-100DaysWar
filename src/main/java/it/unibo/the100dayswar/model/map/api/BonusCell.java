package it.unibo.the100dayswar.model.map.api;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Interface that represents a cell that can give a bonus to a player.
 */
public interface BonusCell extends BuildableCell {
    /**
     * 
     * @return true if the bonus can be given, false otherwise.
     */
    boolean isBonusActive();
    /**
     * Method that gives the bonus to the player.
     * 
     * @param player is the player that will receive the bonus.
     */
    void activateBonus(Player player);
}
