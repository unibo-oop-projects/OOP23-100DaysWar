package it.unibo.the100dayswar.model.unit.impl;

/**
 * Enum which rapresent the different types of 
 * towers and their cost.
 */
public class TowerType {
    private final TypeName typeName;
    private final int price;
    private static final int BASIC_PRICE = 3;
    private static final int ADVANCED_PRICE = 5;

    /**
     * Constructs a TowerType with the specified typeName and price.
     * 
     * @param typeName
     */
    public TowerType(final TypeName typeName) {
        if (typeName == null) {
            throw new IllegalArgumentException("The type of the tower can't be null");
        } else if (typeName != TypeName.BASIC || typeName != TypeName.ADVANCED) {
            throw new IllegalArgumentException("The type of the tower can't be null");
        }

        this.typeName = typeName;

        if (typeName == TypeName.BASIC) {
            this.price = BASIC_PRICE;
        } else {
            this.price = ADVANCED_PRICE;
        }
    }

    /**
     * Gets the type name of the tower.
     * 
     * @return the type name of the tower 
     */
    public TypeName getTypeName() {
        return typeName;
    }

    /**
     * Gets the price of the level. 
     * 
     * @return price the price of a tower
     */
    public int getPrice() {
        return price;
    }
}

