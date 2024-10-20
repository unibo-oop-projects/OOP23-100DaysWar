package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.commons.utilities.api.Position;

/**
 * Abstract class representing a Tower in the game.
 */
public abstract class Tower {
    private final Position position;
    private int level;
    private final int price;
    private final TowerType towerType;

    public static final int PRICE_ERROR = 0;

    /**
     * Constructs a Tower with the specified position, level, and tower type.
     *
     * @param position  the position of the tower
     * @param level     the level of the tower
     * @param towerType the type of the tower
     */
    public Tower(Position position, int level, TowerType towerType) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        if (towerType == null) {
            throw new IllegalArgumentException("TowerType cannot be null");
        }

        this.position = position;
        this.level = level;
        this.towerType = towerType;
        this.price = computePrice();

        if (this.price == PRICE_ERROR) {
            throw new IllegalStateException("Invalid price for TowerType " + towerType);
        }
    }

    /**
     * Computes the price of the tower based on its type.
     *
     * @return the price of the tower
     */
    private int computePrice() {
        return towerType.getPrice();
    }

    /**
     * Gets the position of the tower.
     *
     * @return the position of the tower
     */
    public Position getPosition() {
        return position;
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
