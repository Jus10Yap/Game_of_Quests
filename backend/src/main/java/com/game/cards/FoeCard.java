package com.game.cards;

public class FoeCard extends Card {
    private int value;

    public FoeCard(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return "Foe - " + getName() + " with value " + value;
    }
}
