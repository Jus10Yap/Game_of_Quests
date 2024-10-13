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
            case "Queen's Favor" -> {
                System.out.println("\n[Game] " + currentPlayer.getName() + " draws 2 adventure cards!");

                // Draw 2 adventure cards
                for (int i = 0; i < 2; i++) {
                    Card drawnCard = adventureDeck.drawCard();
                    currentPlayer.addCardToHand(drawnCard);
                }

                // Display player's hand after drawing
                currentPlayer.displayHand();

                // Check if the player needs to trim their hand
                if (currentPlayer.getHand().size() > 12) {
                    System.out.println("[Game] " + currentPlayer.getName()
                            + " has more than 12 cards, starting hand trimming process!");
                    trimHand(currentPlayer, scanner);
                }
            }
            case "Prosperity" -> {
                System.out.println("\n[Game] All players will draw 2 adventure cards!");

                int index = this.currentPlayerIndex;
                for (int i = 0; i < NUM_PLAYERS; i++) {
                    Player player = players.get((index + i) % NUM_PLAYERS);

                    System.out.println("[Game] " + player.getName() + " draws 2 adventure cards!");
                    for (int j = 0; j < 2; j++) {
                        Card drawnCard = adventureDeck.drawCard();
                        player.addCardToHand(drawnCard);
                    }

                    // Check if the player needs to trim their hand
                    if (player.getHand().size() > 12) {
                        System.out.println("[Game] " + player.getName()
                                + " has more than 12 cards, starting hand trimming process.");
                        trimHand(player, scanner);
                    }
                }
            }
        }
    }

    // Trim player's hand to 12 cards
    public void trimHand(Player player, Scanner scanner) {
        int handSize = player.getHand().size();

        if (handSize <= 12) {
            System.out.println("[Game] " + player.getName() + "'s hand is already at or below the limit.");
            return;
        }

        int cardsToDiscard = handSize - 12; // Number of cards to discard
        System.out.println("\n[Game] " + player.getName() + ", you need to discard " + cardsToDiscard
                + " cards to trim your hand to 12.");

        // Loop to prompt the player to discard cards
        for (int i = 0; i < cardsToDiscard; i++) {
            // Display player's hand each time before asking for a discard
            player.displayHand();
            System.out.println("\n[Game] Select the position (0-" + (player.getHand().size() - 1)
                    + ") of the card you want to discard:");

            int position = -1;
            while (position < 0 || position >= player.getHand().size()) {
                try {
                    position = Integer.parseInt(scanner.nextLine()); // Use nextLine to avoid input issues

                    if (position < 0 || position >= player.getHand().size()) {
                        System.out.println("[WARNING] Invalid position, please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[WARNING] Please enter a valid number.");
                }
            }
            System.out.println("[" + player.getName() + "] " + position);

            // Remove the card at the selected position and add it to the discard pile
            Card removedCard = player.getHand().remove(position);
            adventureDeck.discardCard(removedCard); // Add the card to the discard pile
            System.out.println("[Game] Removed and discarded: " + removedCard.getName());

            // Provide a message if more cards need to be discarded
            if (i < cardsToDiscard - 1) {
                System.out.println("[Game] You need to discard " + (cardsToDiscard - i - 1) + " more cards.");
            }
        }

        System.out.println("[Game] " + player.getName() + "'s hand has been trimmed to 12 cards!");
        player.displayHand(); // Display the trimmed hand
    }

    public void moveToNextPlayer(Scanner scanner) {
        // Ask player to leave hot seat by hitting return key
        System.out.println("[Game] Your turn is complete, please pass the device to the next player.");
        System.out.println("[Game] Press 'Enter' to continue to the next player's turn.");
        scanner.nextLine();
        System.out.println("[" + players.get(currentPlayerIndex).getName() + "] <Enter>");
        // Flush display
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Call next player
        System.out.println(
                "\n[Game] The next player is " + players.get(getNextPlayerIndex()).getName() + "! Summon thee.");
    }

    public boolean isValidSponsor(Player player, int numberOfStages) {
        int foeCardCount = 0;
        int weaponCardCount = 0;

        // Loop through the player's hand to count Foe and Weapon cards
        for (Card card : player.getHand()) {
            if (card instanceof FoeCard) {
                foeCardCount++; // Increment foe card count
            } else if (card instanceof WeaponCard) {
                weaponCardCount++;
            }
        }

        // Check if the player has enough Foe or Weapon cards
        if (foeCardCount < numberOfStages) {
            return false;
        }

        if (weaponCardCount < numberOfStages) {
            return false;
        }
        return true; // Valid sponsor
    }

    public String promptForInput(Scanner scanner, Player player, String message) {
        System.out.println(message);
        String input = scanner.nextLine().trim();
        System.out.println("[" + player.getName() + "] " + input);
        return input;
    }

    public int promptForSponsorship(QuestCard questCard, Scanner scanner) {
        int index = getCurrentPlayerIndex();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            Player player = players.get((index + i) % NUM_PLAYERS);
            if (isValidSponsor(player, questCard.getStages())) {
                String message = "[Game] " + player.getName() + ", do you want to sponsor the current quest? (y/n)";
                String response = promptForInput(scanner, player, message).toLowerCase();

                if (response.equals("y")) {
                    System.out.println("[Game] " + player.getName() + " is sponsoring the quest!");
                    return (index + i) % NUM_PLAYERS; // Return the index of the player who said yes
                } else if (response.equals("n")) {
                    System.out.println("[Game] " + player.getName() + " has chosen not to sponsor the quest.");
                } else {
                    System.out.println("[Game] Invalid input. Please enter 'y' or 'n'.");
                    i--;
                }
            }
        }
        System.out.println("[Game] No player has chosen to sponsor the quest."); // All players said no
        return -1;
    }

    public boolean isValidCardPosition(String input, int index) {
        Player sponsor = players.get(index);
        try {
            int pos = Integer.parseInt(input);
            if (pos >= 0 && pos < sponsor.getHand().size()) {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Game] Invalid Card Position.");
        }
        return false;
    }

    public boolean isValidCardForStage(Card card, List<Card> stageCards) {
        if (card instanceof FoeCard) {
            // Only one foe card is allowed
            for (Card c : stageCards) {
                if (c instanceof FoeCard) {
                    return false;
                }
            }
        } else if (card instanceof WeaponCard) {
            // No repeated weapon cards
            for (Card c : stageCards) {
                if (c.getName().equals(card.getName())) {
                    return false;
                }
            }
        } else {
            return false; // Card is neither a FoeCard or WeaponCard
        }
        return true;
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
        Scanner scanner = new Scanner(System.in);
        moveToNextPlayer(scanner);
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

    public int getNumPlayers() {
        return NUM_PLAYERS;
    }

    public int getNextPlayerIndex() {
        return (currentPlayerIndex + 1) % NUM_PLAYERS;
    }

    // Setters
    public void setCurrentPlayerIndex(int index) {
        currentPlayerIndex = index;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}