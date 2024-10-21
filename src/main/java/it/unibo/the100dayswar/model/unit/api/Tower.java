package it.unibo.the100dayswar.model.unit.api;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;

/**
 * Abstract class representing a Tower in the game.
 */
public abstract class Tower {
    /**
     * The position of the tower on the game map.
     */
    private final Position position;

    /**
     * The current level of the tower.
     */
    private int level;

    /**
     * The price of the tower.
     */
    private final int price;

    /**
     * The type of the tower.
     */
    private final TowerType towerType;

    /**
     * The price that represents an error.
     */
    public static final int PRICE_ERROR = 0;

    /**
     * Constructs a Tower with the specified position, level, and tower type.
     *
     * @param position  the position of the tower
     * @param level     the level of the tower
     * @param towerType the type of the tower
     */
    public Tower(final Position position, final int level, final TowerType towerType) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        if (towerType == null) {
            throw new IllegalArgumentException("TowerType cannot be null");
        }

        this.position = new PositionImpl(position);
        this.level = level;
        this.towerType = towerType;
        this.price = towerType.getPrice();

        if (this.price == PRICE_ERROR) {
            throw new IllegalStateException("Invalid price for TowerType " + towerType);
        }
    }

    /**
     * Performs the attack action of the tower.
     */
    public abstract void attack();

    /**
     * Performs the defence of the tower.
     */
    public abstract void defend();

    /**
     * Gets a copy of the position of the tower.
     *
     * @return a copy of the position of the tower
     */
    public Position getPosition() {
        return new PositionImpl(position);
    }

    /**
     * Gets the current level of the tower.
     *
     * @return the level of the tower
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the type of the tower.
     *
     * @return the tower type
     */
    public TowerType getTowerType() {
        return towerType;
    }

    /**
     * Increases the level of the tower by one.
     */
    public void upLevel() {
        level++;
    }

    /**
     * Gets the price of the tower.
     *
     * @return the price of the tower
     */
    public int getPrice() {
        return price;
    }
}
