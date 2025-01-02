package it.unibo.the100dayswar.model;

import java.util.stream.Stream;

import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.statistic.api.GameStatistics;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.unit.api.Unit;

/** 
 * The interface of the model of the game.
 */
public interface Model {
  /**
   * Buy a tower of the specified type.
   * 
   * @param type the type of the tower
   * @param position the position of the tower
   */
  void buyTower(TowerType type, Cell position);

  /**
   * Buy a soldier.
   */
  void buySoldier();

  /**
   * Add a player to the game.
   * 
   * @param username the name of the player
   */
  void addPlayer(String username);

  /**
   * Move a soldier following the specified direction.
   * 
   * @param source the pair of the soldier and the cell
   * 
   * @return true if the soldier was moved correctly false otherwise
   */
  boolean moveSoldier(Pair<Unit, Cell> source);

  /**
   * Save the current game.
   * 
   * @param path the path of the saved file
   * 
   * @return true if the game is saved correctly
   */
  boolean saveGame(String path);

  /**
   * Upgrade the unit.
   * 
   * @param unit the unit to upgrade
   */
  void upgradeUnit(Unit unit);

  /**
   * Gets the width of the map.
   * 
   * @return the width of the map
   */
  double getMapWidth();

  /**
   * Gets the height of the map.
   * 
   * @return the height of the map
   */
  double getMapHeight();

  /**
   * Gets the map as a 2D array of cells.
   * 
   * @return the map
   */
  Cell[][] getMap();

  /**
   * Get the map as a stream of cells.
   * 
   * @return the map as a stream of cells
   */
  Stream<Cell> getMapStream();

  /**
   * Gets the GameStatistics.
   * 
   * @return the GameStatistics
   */
  GameStatistics getGameStatistics();

  /**
   * Skip the current turn.
   */
  void skipTurn();

  /**
   * Pause the game.
   */
  void pauseGame();

  /**
   * Reasume the game.
   */
  void reasumeGame();
}
