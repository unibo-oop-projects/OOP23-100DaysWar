package it.unibo.the100dayswar.model.unit.api;

/**
 * Enum which represents the different types of towers and their cost.
 */
public enum TowerType {

    /**
     * Basic tower type, which has a price of 3.
     */
    BASIC(3),

    /**
     * Advanced tower type, which has a price of 5.
     */
    ADVANCED(5);    // TODO add others type

    /**
     * The price associated with the tower type.
     */
    private final int price;

    /**
     * Constructs a TowerType with the specified price.
     *
     * @param price the price of the tower type
     */
    TowerType(final int price) {
        this.price = price;
    }

    /**
     * Gets the price of the tower type.
     *
     * @return the price of the tower type
     */
    public int getPrice() {
        return price;
    }
}
