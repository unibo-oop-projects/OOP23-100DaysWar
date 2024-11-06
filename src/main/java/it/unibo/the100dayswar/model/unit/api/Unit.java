package it.unibo.the100dayswar.model.unit.api;

import java.io.Serializable;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;

/** 
 * An interface for a generic unit of the game.
 */
public interface Unit extends Buyable, Combatant, Serializable {
  /**
   * This method returns the owner of the unit.
   * 
   * @return the owner of the unit
   */
  Player getOwner();
  /**
  * This method return the cell of the map where the unit is located.
  *
  * @return the current cell of the unit
  */
  Cell getPosition();
}
