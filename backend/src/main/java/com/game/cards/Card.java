package com.game.cards;

public abstract class Card {
    // Type of Card
    private String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();
}