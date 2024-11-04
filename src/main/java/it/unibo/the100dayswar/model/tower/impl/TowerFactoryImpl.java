package it.unibo.the100dayswar.model.tower.impl;

import java.util.Map;
import java.util.function.BiFunction;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.TowerFactory;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Class that implements a factory pattern for all differents kind of towers.
 */
public class TowerFactoryImpl implements TowerFactory {

    /**
     * Contains the tower type associated with its constructor.
     */
    private static final Map<TowerType, BiFunction<Player, Cell, AbstractTower>> TOWER_CREATORS = Map.of(
        TowerType.BASIC, BasicTowerImpl::new,
        TowerType.ADVANCED, AdvancedTowerImpl::new
    );

    /**
     * {@inheritDoc}}
     */
    @Override
    public AbstractTower buildTower(Player owner, TowerType towerType, Cell position) {
        BiFunction<Player, Cell, AbstractTower> towerConstructor = TOWER_CREATORS.get(towerType);

        if (towerConstructor == null) {
            throw new IllegalArgumentException("Unknown Tower Type: " + towerType);
        }

        return towerConstructor.apply(owner, position);
    } 
}
