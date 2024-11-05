package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;

/** 
 * An interface for a generic unit of the game.
 */
public interface Unit extends Buyable, Combatant {
  /**
   * This method returns the owner of the unit.
   * 
   * @return the owner of the unit
   */
  Player getOwner();
  /**
   * This method return the cell of the map where the unit is located.
   * 
   * @return the cell where the unit is located
   */
  Cell getCell();
}
