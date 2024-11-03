package it.unibo.the100dayswar.model.tower.api;

import it.unibo.the100dayswar.model.map.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Unit;

public interface Tower extends Unit {
    int getDamege();
    Cell getCell();
}
