package it.unibo.the100dayswar.model.tower.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.TowerFactory;
import it.unibo.the100dayswar.model.tower.api.TowerType;

public class TowerFactoryImpl implements TowerFactory {
    public static AbstractTower buildTower (final Player owner, final TowerType towerType, final Cell position){
        return TowerFactoryImpl.buildTower(owner, towerType, position);
    }
}
