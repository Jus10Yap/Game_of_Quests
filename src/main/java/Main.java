import cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Variables
    private Deck adventureDeck;
    private Deck eventDeck;
    private List<Player> players;
    private final int NUM_PLAYERS = 4;
    private int currentPlayerIndex;
    private boolean ongoing;

    public Main() {
        adventureDeck = new Deck(100);
        eventDeck = new Deck(17);
        setupDecks();
        setupPlayers();
        currentPlayerIndex = -1;
        ongoing = true;
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

    public void changeCurrentPlayer() {
        if (currentPlayerIndex == -1) { // First round
            distributeCardsToPlayers();
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % NUM_PLAYERS;
        }
    }

    public void checkForWinners() {
        List<Player> winners = new ArrayList<>();

        // Check if any player has won
        for (Player player : players) {
            if (player.hasWon()) {
                winners.add(player); // Add all players with 7 or more shields to the winners list
            }
        }

        if (!winners.isEmpty()) {
            System.out.println("\n\n[Game] Congratulations! Here are our Winner(s): ");
            for (Player winner : winners) {
                System.out.println(
                        "[Game] " + winner.getName() + " has won the game with " + winner.getShields() + " shields!!");
            }
            System.out.println("\n[Game] The game has ended! GG!");
            ongoing = false; // Stop the game
        }
    }

    // Handle the event card and pass the Scanner object
    public void handleEventCard(EventCard card, Scanner scanner) {
        String eventType = card.getName();
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("\n[Game] " + currentPlayer.getName() + " has drawn the " + eventType + " card!");

        switch (eventType) {
            case "Plague" -> {
                System.out.println("\n[Game] " + currentPlayer.getName() + " loses 2 shields!");

                // Player loses 2 shields, ensuring shields don't go below 0
                int shieldsLost = Math.min(2, currentPlayer.getShields());
                currentPlayer.addShields(-shieldsLost); // Deduct 2 shields or whatever is available

                System.out.println(
                        "[Game] " + currentPlayer.getName() + " now has " + currentPlayer.getShields() + " shields.");
            }
        }
    }

    // Trim player's hand to 12 cards
    public void trimHand(Player player, Scanner scanner) {
        
    }

    public void playRound() {
        // Player takes their turn
        changeCurrentPlayer();

        // Indicate whose turn it is
        System.out.println("\n[Game] Hello " + players.get(currentPlayerIndex).getName() + "! Here is your current hand:");

        // Display current player hand in sorted order
        players.get(currentPlayerIndex).displayHand();

        // The game ‘draws’ (i.e., displays) the next event card
        Card drawnCard = eventDeck.drawCard();

        // Handle Event or Quest Card
        if (drawnCard instanceof EventCard) {
            Scanner scanner = new Scanner(System.in);
            handleEventCard((EventCard) drawnCard, scanner);
        } else if (drawnCard instanceof QuestCard) {

        } else {
            System.out.println("\n[WARNING] Incorrect card found in event deck!!!\n");
        }

        // Put drawn card on discard pile
        eventDeck.discardCard(drawnCard);

        // Check for winners at the end of the round
        checkForWinners();

        // End round and move to the next player
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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    // Setters
    public void setCurrentPlayerIndex(int index) {
        currentPlayerIndex = index;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}