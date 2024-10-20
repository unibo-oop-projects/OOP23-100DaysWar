package it.unibo.the100dayswar.model.unit.impl;

public enum TowerType {
    BASIC(3),
    ADVANCED(5),
    // Add other tower types with their respective prices

    private final int price;

    TowerType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

