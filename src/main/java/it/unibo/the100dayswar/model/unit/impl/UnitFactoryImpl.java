package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.impl.Pair;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.AdvancedTower;
import it.unibo.the100dayswar.model.tower.api.BasicTower;
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;
import it.unibo.the100dayswar.model.tower.impl.BasicTowerImpl;
import it.unibo.the100dayswar.model.unit.api.Unit;
import it.unibo.the100dayswar.model.unit.api.UnitFactory;

/**
 * Class that implements the UnitFactory interface.
 */
public class UnitFactoryImpl implements UnitFactory {

    /** 
     * {@inheritDoc}
     */
    @Override
    public Soldier createSoldier(Player player, Observer<Pair<Unit, Cell>> observer) {
        return new SoldierImpl(player);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public BasicTower createBasicTower(Player player, Cell position, Observer<Pair<Unit, Cell>> observer) {
        return new BasicTowerImpl(player, position);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public AdvancedTower createAdvancedTower(Player player, Cell position, Observer<Pair<Unit, Cell>> observer) {
        return new AdvancedTowerImpl(player, position);
    }
}
