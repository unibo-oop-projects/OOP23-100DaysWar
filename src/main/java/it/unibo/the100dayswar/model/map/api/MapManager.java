package it.unibo.the100dayswar.model.map.api;


import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Soldier;

/**
 * interface that model the concept of Mapmanager for the managment of the iteration between the soldiers and the bonus cells.
 */
public interface MapManager extends Observer<Cell> {
    /**
     * manage the update of the soldier's owner player's BankAccount.
     * @param soldier is the soldier that is on the Bonus cell.
     * @param targetPosition is the position of the bonus cell.
     */
    void handleUnitMovement(Soldier soldier, Cell targetPosition);

    /**
     * This metodo update the status of a single cell.
     * @param cell is the cell that will be updated. 
     */
    @Override
    void update(Cell cell);
}
