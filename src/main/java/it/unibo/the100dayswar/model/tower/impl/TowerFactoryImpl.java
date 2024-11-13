package it.unibo.the100dayswar.model.tower.impl;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.TowerFactory;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Class that implements a factory pattern for all differents kind of towers.
 */
public class TowerFactoryImpl implements TowerFactory {
    private static final Logger LOGGER = Logger.getLogger(TowerFactoryImpl.class.getName());
    /**
     * Contains the tower type associated with its constructor.
     */
    private static final Map<TowerType, Optional<BiFunction<Player, Cell, AbstractTower>>> TOWER_CREATORS = Map.of(
        TowerType.BASIC, Optional.of(BasicTowerImpl::new),
        TowerType.ADVANCED, Optional.of(AdvancedTowerImpl::new)
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractTower buildTower(final Player owner, final TowerType towerType, final Cell position) {
        final Optional<BiFunction<Player, Cell, AbstractTower>> towerConstructor = TOWER_CREATORS.get(towerType);

        if (towerConstructor.isEmpty()) {
            LOGGER.log(Level.SEVERE, "Unknow Tower Type " + towerType, new IllegalArgumentException());
            throw new IllegalArgumentException("Unknown Tower Type: " + towerType);
        }

        return towerConstructor.get().apply(owner, position);
    } 
}
