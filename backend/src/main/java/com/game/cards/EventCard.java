package com.game.cards;

public class EventCard extends Card {

    public EventCard(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "Event - " + getName();
    }
}
