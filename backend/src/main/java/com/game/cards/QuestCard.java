package com.game.cards;

public class QuestCard extends Card {
    private int stages;

    public QuestCard(String name, int stages) {
        super(name);
        this.stages = stages;
    }

    public int getStages() {
        return stages;
    }

    @Override
    public String getDescription() {
        return "Quest - " + getName() + " with " + stages + " stages";
    }
}
