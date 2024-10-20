package it.unibo.the100dayswar.model.unit.impl;

/**
 * Enum which rapresent the different types of 
 * towers and their cost.
 */
public class TowerType {
    private final TypeName typeName;
    private final int price;
    
    /**
     * Constructs a TowerType with the specified typeName and price.
     * 
     * @param typeName
     */
    public TowerType(final TypeName typeName) {
        if (typeName == null){
            throw new IllegalArgumentException("The type of the tower can't be null");
        }else if (typeName != TypeName.BASIC || typeName != TypeName.ADVANCED) {
            throw new IllegalArgumentException("The type of the tower can't be null");
        }

        this.typeName = typeName;

        if (typeName == TypeName.BASIC) {
            this.price = 3;
        }else {
            this.price = 5;
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

