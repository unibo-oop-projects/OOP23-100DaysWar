package it.unibo.the100dayswar.controller.gamecontroller.impl;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.controller.gamecontroller.api.GameController;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * The implementation of the game controller of the game.
 */
public class GameControllerImpl implements GameController {
    /** 
     * {@inheritDoc}
     */
    @Override
    public void attack() {
        final var selectedCell = The100DaysWar.CONTROLLER.getMapController().getSelectedCell();
        if (selectedCell.getSecond().getUnit().isPresent()) {
            final var unit = selectedCell.getSecond().getUnit().get();
            if (unit instanceof Soldier) {
                final var soldier = (Soldier) unit;
                final var adjacentCells = The100DaysWar.CONTROLLER.getMapController().getAdjacentCells(selectedCell.getSecond());
                for (final var cell : adjacentCells) {
                    if (cell.getUnit().isPresent() && cell.getUnit().get() instanceof Combatant) {
                        final var defender = cell.getUnit().get();
                        soldier.performAttack(defender);
                    }
                }
            }
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public void skip() {
        The100DaysWar.CONTROLLER.getGameInstance().skipTurn();
        The100DaysWar.CONTROLLER.checkGameOver();
    }
}
