import cards.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Variables
    private Deck adventureDeck;
    private Deck eventDeck;
    private List<Player> players;
    private final int NUM_PLAYERS = 4;

    public Main() {
        adventureDeck = new Deck(100);
        eventDeck = new Deck(17);
        setupDecks();
        setupPlayers();
    }
    // Functions
    public void setupDecks() {
        // Add Foe cards
        int[] foeValues = { 5, 10, 15, 20, 25, 30, 35, 40, 50, 70 };
        int[] foeCounts = { 8, 7, 8, 7, 7, 4, 4, 2, 2, 1 };
        for (int i = 0; i < foeValues.length; i++) {
            adventureDeck.addMultipleCards(new FoeCard("F" + foeValues[i], foeValues[i]), foeCounts[i]);
        }
        // Add Weapon cards
        adventureDeck.addMultipleCards(new WeaponCard("D5", 5), 6); // 6 D5 cards
        adventureDeck.addMultipleCards(new WeaponCard("H10", 10), 12); // 12 H10 cards
        adventureDeck.addMultipleCards(new WeaponCard("S10", 10), 16); // 16 S10 cards
        adventureDeck.addMultipleCards(new WeaponCard("B15", 15), 8); // 8 B15 cards
        adventureDeck.addMultipleCards(new WeaponCard("L20", 20), 6); // 6 L20 cards
        adventureDeck.addMultipleCards(new WeaponCard("E30", 30), 2); // 2 E30 cards

        // Add Quest cards
        eventDeck.addMultipleCards(new QuestCard("Q2", 2), 3); // 3 Q2 cards
        eventDeck.addMultipleCards(new QuestCard("Q3", 3), 4); // 4 Q3 cards
        eventDeck.addMultipleCards(new QuestCard("Q4", 4), 3); // 3 Q4 cards
        eventDeck.addMultipleCards(new QuestCard("Q5", 5), 2); // 2 Q5 cards

        // Add Event cards
        eventDeck.addCard(new EventCard("Plague")); // 1 Plague card
        eventDeck.addMultipleCards(new EventCard("Queen's Favor"), 2); // 2 Queen's Favor cards
        eventDeck.addMultipleCards(new EventCard("Prosperity"), 2); // 2 Prosperity cards

        // Shuffle the decks

        System.out.println("[Game] Adventure and Event Deck has been setup!");
    }

    public void setupPlayers() {
        players = new ArrayList<>();

        // Add 4 players
        for (int i = 0; i < 4; i++) {
            Player player = new Player("P" + (i + 1));
            players.add(player);
        }
        System.out.println("[Game] Four players are ready to play!");
    }

    public void distributeCardsToPlayers() {
        int cardsPerPlayer = 12;

        // Draw 12 cards for each player
        for (Player player : players) {
            List<Card> playerHand = new ArrayList<>();
            for (int i = 0; i < cardsPerPlayer; i++) {
                Card drawnCard = adventureDeck.drawCard();
                playerHand.add(drawnCard);
            }
            player.setHand(playerHand);
        }
        System.out.println("[Game] Each player has drawn 12 cards!");
    }

    // Getters
    public Deck getAdventureDeck() {
        return adventureDeck;
    }

    public Deck getEventDeck() {
        return eventDeck;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}