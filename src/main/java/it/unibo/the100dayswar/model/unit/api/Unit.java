package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;

/** 
 * An interface for a generic unit of the game.
 */
public interface Unit extends Buyable, Combatant {
  long serialVersionUID = 1L;
  /**
  * This method returns the owner of the unit.
  * 
  * @return the owner of the unit
  */
  Player getOwner();

  /**
  * 
  * @return the current cell of the unit.
  */
  Cell getCell();
}
