package it.unibo.the100dayswar.model.cell.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.model.cell.api.BonusCell;

/**
 * Implementation of the BonusCell interface.
 */
public class BonusCellDecorator extends BuildableCellImpl implements BonusCell {
    private static final long serialVersionUID = 1L;

    private final Set<Observer<ResourceGenerator>> observers;
    private static final int BONUS = 100;
    private boolean bonusActive;
    /**
     * Constructor of a cell that give a bonus to the player by a Buildable Cell given.
     * 
     * @param decoratedCell is the cell that will be decorated.
     */
    public BonusCellDecorator(final BuildableCellImpl decoratedCell) {
        super(decoratedCell);
        this.observers = new HashSet<>();
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
    public void attach(final Observer<ResourceGenerator> observer) {
        observers.add(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void detach(final Observer<ResourceGenerator> observer) {
        observers.remove(observer);
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void notify(final Observer<ResourceGenerator> observer) {
        if (isBonusActive()) {
            observers.stream().filter(observer::equals).forEach(p -> p.update(this));
            setBonusActive(false);
        }
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isBonusActive() {
        return this.bonusActive && !this.getUnit().isEmpty();
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public void setBonusActive(final boolean bonusActive) {
        this.bonusActive = bonusActive;
    }
}
