package it.unibo.the100dayswar.model.map.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.commons.utilities.impl.GameEvent;
import it.unibo.the100dayswar.model.map.api.BonusCell;
import it.unibo.the100dayswar.model.player.api.Player;

/**
 * Implementation of the BonusCell interface.
 */
public class BonusCellDecorator extends BuildableCellImpl implements BonusCell, ResourceGenerator {
    private final Set<Observer<Player>> players;
    private static final int BONUS = 100;
    private boolean bonusActive;
    /**
     * Constructor of a cell that give a bonus to the player by a Buildable Cell given.
     * 
     * @param decoratedCell is the cell that will be decorated.
     */
    public BonusCellDecorator(final BuildableCellImpl decoratedCell) {
        super(decoratedCell);
        this.players = new HashSet<>();
        this.bonusActive = true;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public int getAmount() {
        return BONUS;
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void attach(final Observer<Player> observer) {
        players.add(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<Player> observer) {
        players.remove(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void notify(final Player player) {
        player.update(GameEvent.INCOME, player);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isBonusActive() {
        return this.bonusActive && !this.getUnit().isEmpty();
    }

    public void activateBonus(Player player) {
        notify(player);
    }
}
