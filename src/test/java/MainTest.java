import cards.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    // Variables
    private Deck adventureDeck;
    private Deck eventDeck;
    private Main main;
    private List<Player> players;

    @BeforeEach
    @DisplayName("Initialize Game and Deck")
    public void setUp() {
        // Initialize the main game
        main = new Main();
        adventureDeck = main.getAdventureDeck();
        eventDeck = main.getEventDeck();
        players = main.getPlayers();
    }

    @AfterEach
    @DisplayName("Re-initialize Game and Deck")
    public void tearDown() {
        // Reset game state after each test
        main = new Main();
        adventureDeck = main.getAdventureDeck();
        eventDeck = main.getEventDeck();
        players = main.getPlayers();
    }

    // R-Tests
    @Test
    @DisplayName("RESP_01_test_01: Test Deck(s) Initialization")
    public void RESP_01_test_01() {
        // Test if decks are initialized correctly
        assertNotNull(adventureDeck, "Adventure deck should be initialized.");
        assertNotNull(eventDeck, "Event deck should be initialized.");
    }

    @Test
    @DisplayName("RESP_01_test_02: Test the total number of cards in both decks")
    public void RESP_01_test_02() {
        // Test the total number of cards in both decks
        int expectedTotal = 100 + 17; // 100 cards in adventureDeck and 17 in eventDeck
        assertEquals(expectedTotal, adventureDeck.getSize() + eventDeck.getSize(),
                "Total cards in both decks should match the expected total.");
    }

    @Test
    @DisplayName("RESP_01_test_03: Test the size of the adventure deck")
    public void RESP_01_test_03() {
        // Test the size of the adventure deck
        assertEquals(100, adventureDeck.getSize(), "Adventure deck should contain 100 cards");
    }

    @Test
    @DisplayName("RESP_01_test_04: Test the values of Foe cards")
    public void RESP_01_test_04() {
        // Test values of Foe cards
        assertEquals(5, adventureDeck.getFoeCardValue("F5"), "F5 card should have a value of 5");
        assertEquals(10, adventureDeck.getFoeCardValue("F10"), "F10 card should have a value of 10");
        assertEquals(15, adventureDeck.getFoeCardValue("F15"), "F15 card should have a value of 15");
        assertEquals(20, adventureDeck.getFoeCardValue("F20"), "F20 card should have a value of 20");
        assertEquals(25, adventureDeck.getFoeCardValue("F25"), "F25 card should have a value of 25");
        assertEquals(30, adventureDeck.getFoeCardValue("F30"), "F30 card should have a value of 30");
        assertEquals(35, adventureDeck.getFoeCardValue("F35"), "F35 card should have a value of 35");
        assertEquals(40, adventureDeck.getFoeCardValue("F40"), "F40 card should have a value of 40");
        assertEquals(50, adventureDeck.getFoeCardValue("F50"), "F50 card should have a value of 50");
        assertEquals(70, adventureDeck.getFoeCardValue("F70"), "F70 card should have a value of 70");
    }

    @Test
    @DisplayName("RESP_01_test_05: Test the distribution of Foe cards")
    public void RESP_01_test_05() {
        // Test the distribution of Foe cards
        assertEquals(8, adventureDeck.getSize("F5"), "Should have 8 F5 cards");
        assertEquals(7, adventureDeck.getSize("F10"), "Should have 7 F10 cards");
        assertEquals(8, adventureDeck.getSize("F15"), "Should have 8 F15 cards");
        assertEquals(7, adventureDeck.getSize("F20"), "Should have 7 F20 cards");
        assertEquals(7, adventureDeck.getSize("F25"), "Should have 7 F25 cards");
        assertEquals(4, adventureDeck.getSize("F30"), "Should have 4 F30 cards");
        assertEquals(4, adventureDeck.getSize("F35"), "Should have 4 F35 cards");
        assertEquals(2, adventureDeck.getSize("F40"), "Should have 2 F40 cards");
        assertEquals(2, adventureDeck.getSize("F50"), "Should have 2 F50 cards");
        assertEquals(1, adventureDeck.getSize("F70"), "Should have 1 F70 card");
    }

    @Test
    @DisplayName("RESP_01_test_06: Test the values of Weapon cards")
    public void RESP_01_test_06() {
        // Test values of Weapon cards
        assertEquals(5, adventureDeck.getWeaponCardValue("D5"), "D5 card should have a value of 5");
        assertEquals(10, adventureDeck.getWeaponCardValue("H10"), "H10 card should have a value of 10");
        assertEquals(10, adventureDeck.getWeaponCardValue("S10"), "S10 card should have a value of 10");
        assertEquals(15, adventureDeck.getWeaponCardValue("B15"), "B15 card should have a value of 15");
        assertEquals(20, adventureDeck.getWeaponCardValue("L20"), "L20 card should have a value of 20");
        assertEquals(30, adventureDeck.getWeaponCardValue("E30"), "E30 card should have a value of 30");
    }

    @Test
    @DisplayName("RESP_01_test_07: Test the distribution of Weapon cards")
    public void RESP_01_test_07() {
        // Test the distribution of Weapon cards
        assertEquals(6, adventureDeck.getSize("D5"), "Should have 6 D5 cards");
        assertEquals(12, adventureDeck.getSize("H10"), "Should have 12 H10 cards");
        assertEquals(16, adventureDeck.getSize("S10"), "Should have 16 S10 cards");
        assertEquals(8, adventureDeck.getSize("B15"), "Should have 8 B15 cards");
        assertEquals(6, adventureDeck.getSize("L20"), "Should have 6 L20 cards");
        assertEquals(2, adventureDeck.getSize("E30"), "Should have 2 E30 cards");
    }

    @Test
    @DisplayName("RESP_01_test_08: Test the size of the event deck")
    public void RESP_01_test_08() {
        // Test the size of the event deck
        assertEquals(17, eventDeck.getSize(), "Event deck should contain 17 cards");
    }

    @Test
    @DisplayName("RESP_01_test_09: Test the stage size of Quest cards")
    public void RESP_01_test_09() {
        // Test stages of Quest cards
        assertEquals(2, eventDeck.getQuestCardStage("Q2"), "Q2 card should have 2 stages");
        assertEquals(3, eventDeck.getQuestCardStage("Q3"), "Q3 card should have 3 stages");
        assertEquals(4, eventDeck.getQuestCardStage("Q4"), "Q4 card should have 4 stages");
        assertEquals(5, eventDeck.getQuestCardStage("Q5"), "Q5 card should have 5 stages");
    }

    @Test
    @DisplayName("RESP_01_test_10: Test the distribution of Quest cards")
    public void RESP_01_test_10() {
        // Test the distribution of Quest cards
        assertEquals(3, eventDeck.getSize("Q2"), "Should have 3 Q2 cards");
        assertEquals(4, eventDeck.getSize("Q3"), "Should have 4 Q3 cards");
        assertEquals(3, eventDeck.getSize("Q4"), "Should have 3 Q4 cards");
        assertEquals(2, eventDeck.getSize("Q5"), "Should have 2 Q5 cards");
    }

    @Test
    @DisplayName("RESP_01_test_11: Test the distribution of Event cards")
    public void RESP_01_test_11() {
        // Test the distribution of Event cards
        assertEquals(1, eventDeck.getSize("Plague"), "Should have 1 Plague card"); // 1 Plague
        assertEquals(2, eventDeck.getSize("Queen's Favor"), "Should have 2 Queen's Favor cards"); // 2 Queen's Favor
        assertEquals(2, eventDeck.getSize("Prosperity"), "Should have 2 Prosperity cards"); // 2 Prosperity
    }

    @Test
    @DisplayName("RESP_02_test_01: Test adventure deck is shuffled")
    public void RESP_02_test_01() {
        List<Card> originalDeck = new ArrayList<>(adventureDeck.getCards());
        adventureDeck.shuffle();
        List<Card> shuffledDeck = adventureDeck.getCards();
        // Check if all cards are present in the deck
        assertEquals(originalDeck.size(), shuffledDeck.size());
        // Check if the deck is shuffled (the order is different)
        assertNotEquals(originalDeck, shuffledDeck);
    }

    @Test
    @DisplayName("RESP_02_test_02: Test event deck is shuffled")
    public void RESP_02_test_02() {
        List<Card> originalDeck = new ArrayList<>(eventDeck.getCards());
        eventDeck.shuffle();
        List<Card> shuffledDeck = eventDeck.getCards();
        // Check if all cards are present in the deck
        assertEquals(originalDeck.size(), shuffledDeck.size());
        // Check if the deck is shuffled (the order is different)
        assertNotEquals(originalDeck, shuffledDeck);
    }

    @Test
    @DisplayName("RESP_02_test_03: Test drawing from discard pile when deck is empty")
    public void RESP_02_test_03() {
        // Set up the main deck
        main.getAdventureDeck().getCards().clear();
        Card h10 = new WeaponCard("H10", 10);
        Card s10 = new WeaponCard("S10", 10);

        // Discard the cards
        main.getAdventureDeck().discardCard(h10);
        main.getAdventureDeck().discardCard(s10);

        Card drawnCard = main.getAdventureDeck().drawCard();

        int expectedMainDeckSize = 1;
        int expectedDiscardPileSize = 0; // Discard pile should be empty after reshuffling

        assertNotNull(drawnCard, "A card should have been drawn from the reshuffled discard pile.");
        assertEquals(expectedDiscardPileSize, main.getAdventureDeck().getDiscardPile().size(),
                "The discard pile should be empty after reshuffling.");
        assertEquals(expectedMainDeckSize, main.getAdventureDeck().getSize(),
                "Main deck size should reflect the cards drawn initially.");
    }

    @Test
    @DisplayName("RESP_03_test_01: Test that 4 players are Initialized")
    public void RESP_03_test_01() {
        // Check if there are exactly 4 players
        assertEquals(4, players.size(), "There should be exactly 4 players.");
    }

    @Test
    @DisplayName("RESP_03_test_02: Test that the 4 players have the correct names")
    public void RESP_03_test_02() {
        // Check the names of the players
        assertEquals("P1", players.get(0).getName(), "Player 1 should be named P1");
        assertEquals("P2", players.get(1).getName(), "Player 2 should be named P2");
        assertEquals("P3", players.get(2).getName(), "Player 3 should be named P3");
        assertEquals("P4", players.get(3).getName(), "Player 4 should be named P4");
    }

    @Test
    @DisplayName("RESP_03_test_03: Test that each player starts with 0 shields")
    public void RESP_03_test_03() {
        for (Player player : players) {
            assertEquals(0, player.getShields(), player.getName() + " should start with 0 shields.");
        }
    }

    @Test
    @DisplayName("RESP_04_test_01: Test that each player gets distributed 12 cards from the deck")
    public void RESP_04_test_01() {
        assertEquals(100, adventureDeck.getCards().size(), "Deck should start with 100 cards");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.distributeCardsToPlayers();

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        // Check if each player has received 12 cards
        for (Player player : players) {
            assertEquals(12, player.getHand().size(), "Each player should have received 12 cards");
        }
        // Check the number of remaining cards in the deck
        assertEquals(52, adventureDeck.getCards().size(), "52 cards should remain in the deck after distribution");
        assertTrue(output.contains("[Game] Each player has drawn 12 cards!"), "Game should output that all players has drawn 12 cards");
    }

    @Test
    @DisplayName("RESP_05_test_01: Test that the current player of the first round is P1")
    public void RESP_05_test_01() {
        main.changeCurrentPlayer(); // P1
        assertEquals("P1", players.get(main.getCurrentPlayerIndex()).getName(),
                "The current player should be P1 at the start.");
    }

    @Test
    @DisplayName("RESP_06_test_01: Test that the next current player of the first round is P2")
    public void RESP_06_test_01() {
        main.changeCurrentPlayer(); // P1
        main.changeCurrentPlayer(); // P2
        assertEquals("P2", players.get(main.getCurrentPlayerIndex()).getName(), "Next player should be P2");
    }

    @Test
    @DisplayName("RESP_06_test_02: Test that the next current player of the first round is P3")
    public void RESP_06_test_02() {
        main.changeCurrentPlayer(); // P1
        main.changeCurrentPlayer(); // P2
        main.changeCurrentPlayer(); // P3
        assertEquals("P3", players.get(main.getCurrentPlayerIndex()).getName(), "Next player should be P3");
    }

    @Test
    @DisplayName("RESP_06_test_03: Test that the next current player of the first round is P4")
    public void RESP_06_test_03() {
        main.changeCurrentPlayer(); // P1
        main.changeCurrentPlayer(); // P2
        main.changeCurrentPlayer(); // P3
        main.changeCurrentPlayer(); // P4
        assertEquals("P4", players.get(main.getCurrentPlayerIndex()).getName(), "Next player should be P4");
    }

    @Test
    @DisplayName("RESP_06_test_04: Test that the current player goes back to P1")
    public void RESP_06_test_04() {
        main.changeCurrentPlayer(); // P1
        main.changeCurrentPlayer(); // P2
        main.changeCurrentPlayer(); // P3
        main.changeCurrentPlayer(); // P4
        main.changeCurrentPlayer(); // Back to P1
        assertEquals("P1", players.get(main.getCurrentPlayerIndex()).getName(),
                "Current player should be back to P1 after full cycle");
    }

    @Test
    @DisplayName("RESP_07_test_01: Test player wins when accumulating 7 shields")
    public void RESP_07_test_01() {
        Player p1 = players.get(0);
        main.setCurrentPlayerIndex(0);
        p1.addShields(7); // Give P1 7 shields

        // Check if the game correctly identifies the winner
        assertEquals(7, p1.getShields(), "Player 1 should have 7 shields.");
        assertTrue(p1.hasWon(), "Player 1 should be declared the winner.");
    }

    @Test
    @DisplayName("RESP_07_test_02: Test no player wins with less than 7 shields")
    public void RESP_07_test_02() {
        Player p1 = players.get(0);
        main.setCurrentPlayerIndex(0);
        p1.addShields(6);

        // Check that player hasn't won
        assertEquals(6, p1.getShields(), "Player 1 should have 6 shields.");
        assertFalse(p1.hasWon(), "Player 1 should not be declared the winner.");
    }

    @Test
    @DisplayName("RESP_07_test_03: Test multiple players win when they have 7 or more shields")
    public void RESP_07_test_03() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);

        main.setCurrentPlayerIndex(0);
        p1.addShields(7); // P1 wins
        p2.addShields(8); // P2 wins with more than 7 shields
        p3.addShields(6); // P3 does not win

        // P1 and P2 should be declared winners
        assertTrue(p1.hasWon(), "Player 1 should be declared the winner.");
        assertTrue(p2.hasWon(), "Player 2 should be declared the winner.");
        assertFalse(p3.hasWon(), "Player 3 should not be declared the winner.");
    }

    @Test
    @DisplayName("RESP_08_test_01: Test game stops when there is a winner")
    public void RESP_08_test_01() {
        Player p1 = players.get(0);
        main.setCurrentPlayerIndex(0);
        p1.addShields(7); // Give P1 7 shields (winning condition)

        main.checkForWinners();

        // Verify that the game has ended
        assertFalse(main.isOngoing(), "Game should stop after a winner is declared.");
    }

    @Test
    @DisplayName("RESP_08_test_02: Test game stops when there are multiple winners")
    public void RESP_08_test_02() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        p1.addShields(7); // P1 wins
        p2.addShields(7); // P2 also wins

        main.checkForWinners();

        // Verify that the game has ended
        assertFalse(main.isOngoing(), "Game should stop after multiple winners are declared.");

        // Check that P1 and P2 are in the winners list
        assertTrue(p1.hasWon(), "Player 1 should be a winner.");
        assertTrue(p2.hasWon(), "Player 2 should be a winner.");
    }

    @Test
    @DisplayName("RESP_08_test_03: Test correct winner message display")
    public void RESP_08_test_03() {
        Player p1 = players.get(0);
        p1.addShields(7);

        // Capture output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.checkForWinners();

        String output = outputStream.toString();
        assertTrue(output.contains("[Game] P1 has won the game with 7 shields!!"),
                "Correct winner message should be displayed.");
    }

    @Test
    @DisplayName("RESP_08_test_04: Test game continues when there are no winners")
    public void RESP_08_test_04() {
        Player p1 = players.get(0);
        p1.addShields(6); // P1 does not win
        main.checkForWinners();
        assertTrue(main.isOngoing(), "Game should continue if no player has won.");
    }

    @Test
    @DisplayName("RESP_09_test_01: Test that the player's hand is displayed in sorted order")
    public void RESP_09_test_01() {
        Player player = players.get(0);
        main.setCurrentPlayerIndex(0);
        // Add Cards to hand
        player.addCardToHand(new FoeCard("F10", 10)); // Foe with value 10
        player.addCardToHand(new FoeCard("F5", 5)); // Foe with value 5
        player.addCardToHand(new WeaponCard("S10", 10)); // Sword with value 10
        player.addCardToHand(new WeaponCard("H10", 10)); // Horse with value 10
        player.addCardToHand(new WeaponCard("D5", 5)); // Dagger with value 5

        // Display the sorted hand
        player.displayHand();

        // Check if P1's hand is correctly sorted
        List<Card> hand = player.getHand();
        assertTrue(hand.get(0) instanceof FoeCard && ((FoeCard) hand.get(0)).getValue() == 5,
                "First card should be Foe with value 5");
        assertTrue(hand.get(1) instanceof FoeCard && ((FoeCard) hand.get(1)).getValue() == 10,
                "Second card should be Foe with value 10");
        assertTrue(hand.get(2) instanceof WeaponCard && hand.get(2).getName().equals("D5"),
                "Third card should be Dagger (D5)");
        assertTrue(hand.get(3) instanceof WeaponCard && hand.get(3).getName().equals("S10"),
                "Fourth card should be Sword (S10)");
        assertTrue(hand.get(4) instanceof WeaponCard && hand.get(4).getName().equals("H10"),
                "Fifth card should be Horse (H10)");
    }

    @Test
    @DisplayName("RESP_09_test_02: Test displaying an empty hand")
    public void RESP_09_test_02() {
        Player player = players.get(0);
        player.setHand(new ArrayList<>()); // Set an empty hand
        player.displayHand();
    }

    @Test
    @DisplayName("RESP_09_test_03: Test displaying a single card hand")
    public void RESP_09_test_03() {
        Player player = players.get(0);
        player.addCardToHand(new FoeCard("F20", 20)); // Only one card
        player.displayHand();

        List<Card> hand = player.getHand();
        assertEquals(1, hand.size(), "Hand should contain one card.");
        assertTrue(hand.get(0) instanceof FoeCard && ((FoeCard) hand.get(0)).getValue() == 20,
                "The only card should be F20.");
    }

    @Test
    @DisplayName("RESP_09_test_04: Test displaying a hand with duplicate cards")
    public void RESP_09_test_04() {
        Player player = players.get(0);
        main.setCurrentPlayerIndex(0);

        // Add duplicate cards to hand
        player.addCardToHand(new FoeCard("F10", 10)); // Foe with value 10
        player.addCardToHand(new FoeCard("F10", 10)); // Duplicate Foe with value 10
        player.addCardToHand(new WeaponCard("D5", 5)); // Dagger with value 5
        player.addCardToHand(new WeaponCard("D5", 5)); // Duplicate Dagger with value 5

        // Display the sorted hand
        player.displayHand();

        List<Card> hand = player.getHand();
        assertTrue(hand.get(0) instanceof FoeCard && ((FoeCard) hand.get(0)).getValue() == 10,
                "First card should be Foe with value 10");
        assertTrue(hand.get(1) instanceof FoeCard && ((FoeCard) hand.get(1)).getValue() == 10,
                "Second card should be duplicate Foe with value 10");
        assertTrue(hand.get(2) instanceof WeaponCard && hand.get(2).getName().equals("D5"),
                "Third card should be Dagger (D5)");
        assertTrue(hand.get(3) instanceof WeaponCard && hand.get(3).getName().equals("D5"),
                "Fourth card should be duplicate Dagger (D5)");
    }

    @Test
    @DisplayName("RESP_10_test_01: Test Player draws a card from event deck")
    public void RESP_10_test_01() {
        int initialDeckSize = eventDeck.getSize();

        assertTrue(initialDeckSize > 0, "Event deck should have cards before drawing");

        Card drawnCard = eventDeck.drawCard();

        assertEquals(initialDeckSize - 1, eventDeck.getSize(), "Event deck size should decrease by 1 after drawing");

        assertNotNull(drawnCard, "A card should be drawn from the deck");
    }

    @Test
    @DisplayName("RESP_10_test_02: Test drawing the last card from the event deck")
    public void RESP_10_test_02() {
        // Set up a deck with only one card
        eventDeck.getCards().clear(); // Method to clear the deck
        eventDeck.addCard(new EventCard("Prosperity")); // Add one final card

        assertEquals(1, eventDeck.getSize(), "Deck should have exactly one card");

        Card drawnCard = eventDeck.drawCard();

        assertEquals(0, eventDeck.getSize(), "Deck should have no cards left after drawing the last card");
        assertNotNull(drawnCard, "The last card should be drawn successfully");
    }

    @Test
    @DisplayName("RESP_11_test_01: Test Plague event causes the player to lose 2 shields")
    public void RESP_11_test_01() {
        Player currentPlayer = players.get(0);
        main.setCurrentPlayerIndex(0);
        currentPlayer.addShields(5); // Give P1 5 shields

        // No input
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EventCard plagueCard = new EventCard("Plague");
        main.handleEventCard(plagueCard, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] P1 loses 2 shields!"));
        assertEquals(3, currentPlayer.getShields(),
                "Player should have 3 shields after losing 2 due to the Plague event.");
    }

    @Test
    @DisplayName("RESP_11_test_02: Test Plague card does not reduce shields below 0")
    public void RESP_11_test_02() {
        Player currentPlayer = players.get(0);
        main.setCurrentPlayerIndex(0);

        // No input
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        currentPlayer.addShields(1); // Give P1 shields (less than 2)
        EventCard plagueCard = new EventCard("Plague");
        main.handleEventCard(plagueCard, scanner);
        assertEquals(0, currentPlayer.getShields(), "Player should not have negative shields.");
    }

    @Test
    @DisplayName("RESP_11_test_03: Test Plague event with 0 shields")
    public void RESP_11_test_03() {
        Player currentPlayer = players.get(0);
        main.setCurrentPlayerIndex(0);

        // No input
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Player starts with 0 shields
        EventCard plagueCard = new EventCard("Plague");
        main.handleEventCard(plagueCard, scanner);
        assertEquals(0, currentPlayer.getShields(), "Player should still have 0 shields after Plague event.");
    }

    @Test
    @DisplayName("RESP_12_test_01: Test player trims their hand to 12 cards")
    public void RESP_12_test_01() {
        Player currentPlayer = players.get(0);
        List<Card> fullHand = main.getAdventureDeck().drawMultipleCards(15); // Give P1 15 cards
        currentPlayer.setHand(fullHand);
        assertEquals(15, currentPlayer.getHand().size(), "Player should have 15 cards initially.");

        // Input positions 0, 1, 2
        String input = "0\n1\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        main.trimHand(currentPlayer, scanner);
        assertEquals(12, currentPlayer.getHand().size(), "Player's hand should be trimmed to 12 cards.");
    }

    @Test
    @DisplayName("RESP_12_test_02: Test cards are discarded and moved to discard pile during hand trimming")
    public void RESP_12_test_02() {
        Player currentPlayer = players.get(0);
        List<Card> fullHand = main.getAdventureDeck().drawMultipleCards(15); // Give P1 15 cards
        currentPlayer.setHand(fullHand);
        assertEquals(15, currentPlayer.getHand().size(), "Player should have 15 cards initially.");

        // Input positions 2, 4, 6
        String input = "2\n4\n6\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int initialMainDeckSize = main.getAdventureDeck().getSize();
        int initialDiscardPileSize = main.getAdventureDeck().getDiscardPile().size();
        main.trimHand(currentPlayer, scanner);
        assertEquals(12, currentPlayer.getHand().size(), "Player should have exactly 12 cards after trimming.");
        int expectedDiscardPileSize = initialDiscardPileSize + 3;
        assertEquals(expectedDiscardPileSize, main.getAdventureDeck().getDiscardPile().size(),
                "3 cards should be in the discard pile.");
        int expectedMainDeckSize = initialMainDeckSize; // This will depend on your game logic if cards are removed from
        // the main deck
        assertEquals(expectedMainDeckSize, main.getAdventureDeck().getSize(),
                "Main deck size should reflect the cards drawn initially.");
    }

    @Test
    @DisplayName("RESP_12_test_03: Test no trimming is needed if player already has 12 cards")
    public void RESP_12_test_03() {
        Player currentPlayer = players.get(0);
        List<Card> hand = main.getAdventureDeck().drawMultipleCards(12);
        currentPlayer.setHand(hand);
        assertEquals(12, currentPlayer.getHand().size(), "Player should have 12 cards initially.");

        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));

        assertEquals(12, currentPlayer.getHand().size(), "Player's hand should remain at 12 cards.");
    }

    @Test
    @DisplayName("RESP_12_test_04: Test no trimming is needed if player has less than 12 cards")
    public void RESP_12_test_04() {
        Player currentPlayer = players.get(0);
        List<Card> hand = main.getAdventureDeck().drawMultipleCards(10);
        currentPlayer.setHand(hand);
        assertEquals(10, currentPlayer.getHand().size(), "Player should have 10 cards initially.");

        Scanner scanner = new Scanner(new ByteArrayInputStream("".getBytes()));
        main.trimHand(currentPlayer, scanner);

        assertEquals(10, currentPlayer.getHand().size(), "Player's hand should remain at 10 cards.");
    }

    @Test
    @DisplayName("RESP_13_test_01: Test Queen's Favor event adds 2 cards to the player's hand")
    public void RESP_13_test_01() {
        Player currentPlayer = players.get(0);
        main.setCurrentPlayerIndex(0);
        int initialHandSize = currentPlayer.getHand().size();

        // No input
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard queensFavorCard = new EventCard("Queen's Favor");
        main.handleEventCard(queensFavorCard, scanner);
        assertEquals(initialHandSize + 2, currentPlayer.getHand().size(),
                "Player should have 2 more cards in their hand after drawing Queen's Favor.");
    }

    @Test
    @DisplayName("RESP_13_test_02: Test Queen's Favor does not exceed maximum hand size")
    public void RESP_13_test_02() {
        Player currentPlayer = players.get(0);
        main.setCurrentPlayerIndex(0);
        currentPlayer.setHand(main.getAdventureDeck().drawMultipleCards(12));

        assertEquals(12, currentPlayer.getHand().size(), "Player should have 12 cards initially.");

        // Input positions 2, 4
        String input = "2\n4\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard queensFavorCard = new EventCard("Queen's Favor");
        main.handleEventCard(queensFavorCard, scanner);
        assertEquals(12, currentPlayer.getHand().size(),
                "Player's hand should not exceed the maximum size of 12 cards.");
    }

    @Test
    @DisplayName("RESP_14_test_01: Test Prosperity event makes all players draw 2 cards")
    public void RESP_14_test_01() {
        main.setCurrentPlayerIndex(0);
        // Store the initial hand sizes of all players
        List<Integer> initialHandSizes = new ArrayList<>();
        for (int i = 0; i < main.getNumPlayers(); i++) {
            initialHandSizes.add(players.get(i).getHand().size());
        }

        // No input
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, scanner);

        // Check that each player has drawn 2 cards
        for (int i = 0; i < main.getNumPlayers(); i++) {
            Player player = players.get(i);
            assertEquals(initialHandSizes.get(i) + 2, player.getHand().size(),
                    player.getName() + " should have 2 more cards.");
        }
    }

    @Test
    @DisplayName("RESP_14_test_02: Test Prosperity event when P1 has more than 12 cards")
    public void RESP_14_test_02() {
        Player player = players.get(0);
        main.setCurrentPlayerIndex(0);
        player.setHand(main.getAdventureDeck().drawMultipleCards(12));
        assertEquals(12, player.getHand().size(), player.getName() + " should have exactly 12 cards initially.");

        String input = "1\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, scanner);

        // Check that P1 has more than 12 cards after the event
        assertEquals(12, player.getHand().size(), player.getName() + "'s hand should not exceed 12 cards.");

    }

    @Test
    @DisplayName("RESP_14_test_03: Test Prosperity event when P2 has more than 12 cards")
    public void RESP_14_test_03() {
        Player player = players.get(1);
        main.setCurrentPlayerIndex(0);
        player.setHand(main.getAdventureDeck().drawMultipleCards(12));
        assertEquals(12, player.getHand().size(), player.getName() + " should have exactly 12 cards initially.");

        String input = "1\n2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, scanner);

        // Check that P2 has more than 12 cards after the event
        assertEquals(12, player.getHand().size(), player.getName() + "'s hand should not exceed 12 cards.");

    }

    @Test
    @DisplayName("RESP_14_test_04: Test Prosperity event when P3 and P4 has more than 12 cards")
    public void RESP_14_test_04() {
        Player p3 = players.get(2);
        Player p4 = players.get(3);
        main.setCurrentPlayerIndex(0);

        p3.setHand(main.getAdventureDeck().drawMultipleCards(12));
        p4.setHand(main.getAdventureDeck().drawMultipleCards(12));

        assertEquals(12, p3.getHand().size(), p3.getName() + " should have exactly 12 cards initially.");
        assertEquals(12, p4.getHand().size(), p4.getName() + " should have exactly 12 cards initially.");

        // All inputs
        String input = "\n2\n4\n1\n3\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, scanner);

        // Check that both P3 and P4 now have exactly 12 cards
        assertEquals(12, p3.getHand().size(), p3.getName() + "'s hand should not exceed 12 cards.");
        assertEquals(12, p4.getHand().size(), p4.getName() + "'s hand should not exceed 12 cards.");
    }

    @Test
    @DisplayName("RESP_14_test_05: Test Prosperity event when all players have more than 12 cards")
    public void RESP_14_test_05() {
        main.setCurrentPlayerIndex(0);
        // Draw cards for all players and set their hands to 12 cards
        for (Player player : players) {
            player.setHand(main.getAdventureDeck().drawMultipleCards(12));
            assertEquals(12, player.getHand().size(), player.getName() + " should have exactly 12 cards initially.");
        }

        String input = "2\n4\n0\n1\n8\n3\n6\n7\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, scanner);

        // Check that all players now have exactly 12 cards
        for (Player player : players) {
            assertEquals(12, player.getHand().size(), player.getName() + "'s hand should not exceed 12 cards.");
        }
    }

    @Test
    @DisplayName("RESP_14_test_06: Test Prosperity event that P2 draws card first then P3, then P4, then P1")
    public void RESP_14_test_06() {
        PrintStream originalOut = System.out;
        main.setCurrentPlayerIndex(1); // P2
        for (Player player : players) {
            player.setHand(main.getAdventureDeck().drawMultipleCards(10));
            assertEquals(10, player.getHand().size(), player.getName() + " should have exactly 10 cards initially.");
        }

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        EventCard prosperityCard = new EventCard("Prosperity");
        main.handleEventCard(prosperityCard, new Scanner(System.in)); // Scanner for handling input

        // Get captured output
        String output = outputStream.toString();

        System.setOut(originalOut);
        System.out.println("Captured Output: " + output);

        assertTrue(output.contains("[Game] P2 draws 2 adventure cards!"),
                "P2 draws 2 adventure cards should be displayed first");
        assertTrue(output.contains("[Game] P3 draws 2 adventure cards!"),
                "P3 draws 2 adventure cards should be displayed second");
        assertTrue(output.contains("[Game] P4 draws 2 adventure cards!"),
                "P4 draws 2 adventure cards should be displayed third");
        assertTrue(output.contains("[Game] P1 draws 2 adventure cards!"),
                "P1 draws 2 adventure cards should be displayed last");
    }

    @Test
    @DisplayName("RESP_15_test_01: Test that the current player's turn ends and the hot seat is cleared after pressing <return>")
    public void RESP_15_test_01() {
        main.setCurrentPlayerIndex(0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        main.moveToNextPlayer(scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Your turn is complete, please pass the device to the next player."),
                "The turn completion message should be displayed.");
        assertTrue(output.contains("[Game] Press 'Enter' to continue to the next player's turn."),
                "The prompt to press 'Enter' should be displayed.");
        assertTrue(output.contains("[P1] <Enter>"));
        assertTrue(output.contains("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"),
                "The hotseat display should be cleared after pressing <return>.");
    }

    @Test
    @DisplayName("RESP_16_test_01: Test if player is a valid sponsor")
    public void RESP_16_test_01() {
        Player p1 = players.get(0);
        main.setCurrentPlayerIndex(0); // P1

        int numberOfStages = 3;

        FoeCard f10 = new FoeCard("F10", 10);
        FoeCard f15 = new FoeCard("F15", 15);
        FoeCard f20 = new FoeCard("F20", 20);
        WeaponCard d5 = new WeaponCard("D5", 5);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        p1.addCardToHand(f10);
        p1.addCardToHand(f15);
        p1.addCardToHand(f20);
        p1.addCardToHand(d5);
        p1.addCardToHand(h10);
        p1.addCardToHand(s10);

        boolean isValid = main.isValidSponsor(p1, numberOfStages);

        // P1 is a valid sponsor
        assertTrue(isValid, "P1 should be a valid sponsor with the current hand.");
    }

    @Test
    @DisplayName("RESP_16_test_02: Test if player cannot sponsor with insufficient total cards")
    public void RESP_16_test_02() {
        Player p2 = players.get(1);
        main.setCurrentPlayerIndex(1); // P2

        int numberOfStages = 3;

        FoeCard f10 = new FoeCard("F10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        p2.addCardToHand(f10);
        p2.addCardToHand(s10);

        boolean isInvalid = main.isValidSponsor(p2, numberOfStages);

        // P2 is not a valid sponsor
        assertFalse(isInvalid, "Player should not be a valid sponsor with insufficient cards.");
    }

    @Test
    @DisplayName("RESP_16_test_03: Test if player cannot sponsor with insufficient Foe cards")
    public void RESP_16_test_03() {
        Player p1 = players.get(0);
        main.setCurrentPlayerIndex(0); // P1

        int numberOfStages = 3;

        // One Foe card
        FoeCard f10 = new FoeCard("F10", 10);
        p1.addCardToHand(f10);

        boolean isValid = main.isValidSponsor(p1, numberOfStages);

        // P1 is not a valid sponsor
        assertFalse(isValid, "P1 should not be a valid sponsor with insufficient Foe cards.");
    }

    @Test
    @DisplayName("RESP_16_test_04: Test if player can sponsor with repeated Weapon cards for a single stage")
    public void RESP_16_test_04() {
        Player p3 = players.get(2);
        main.setCurrentPlayerIndex(2); // P3

        int numberOfStages = 1; // Only one stage

        // Add Foe and repeated Weapon cards
        FoeCard f10 = new FoeCard("F10", 10);
        WeaponCard s10a = new WeaponCard("S10", 10);
        WeaponCard s10b = new WeaponCard("S10", 10); // Repeated

        p3.addCardToHand(f10);
        p3.addCardToHand(s10a);
        p3.addCardToHand(s10b);

        boolean isValid = main.isValidSponsor(p3, numberOfStages);

        // P3 is a valid sponsor
        assertTrue(isValid, "P3 should be a valid sponsor even with repeated Weapon cards for a single stage.");
    }

    @Test
    @DisplayName("RESP_16_test_05: Test if player cannot sponsor without Weapon cards")
    public void RESP_16_test_05() {
        Player p4 = players.get(3);
        main.setCurrentPlayerIndex(3); // P4

        int numberOfStages = 1;

        // Only add Foe cards
        FoeCard f10 = new FoeCard("F10", 10);
        FoeCard f15 = new FoeCard("F15", 15);

        p4.addCardToHand(f10);
        p4.addCardToHand(f15);

        boolean isInvalid = main.isValidSponsor(p4, numberOfStages);

        // P4 is not a valid sponsor
        assertFalse(isInvalid, "P4 should not be a valid sponsor with sufficient Foe cards.");
    }

    @Test
    @DisplayName("RESP_16_test_07: Test if player cannot sponsor with no cards")
    public void RESP_16_test_07() {
        Player p2 = players.get(1);
        main.setCurrentPlayerIndex(1); // P2

        int numberOfStages = 1;

        // Player has no cards
        boolean isInvalid = main.isValidSponsor(p2, numberOfStages);

        // P5 is not a valid sponsor
        assertFalse(isInvalid, "P2 should not be a valid sponsor with no cards.");
    }
}