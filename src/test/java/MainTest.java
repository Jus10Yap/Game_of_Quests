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

    @Test
    @DisplayName("RESP_17_test_01: Test when P1 agrees to sponsor the quest")
    public void RESP_17_test_01() throws IOException {
        QuestCard questCard = new QuestCard("Q2", 2); // Quest with 2 stages

        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);

        FoeCard f10 = new FoeCard("F10", 10);
        FoeCard f15 = new FoeCard("F15", 15);
        WeaponCard d5 = new WeaponCard("D5", 5);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        p1.addCardToHand(f10);
        p1.addCardToHand(f15);
        p1.addCardToHand(d5);
        p1.addCardToHand(h10);
        p1.addCardToHand(s10);

        assertTrue(main.isValidSponsor(p1, questCard.getStages()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "y\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Prompt for sponsorship
        int sponsorIndex = main.promptForSponsorship(questCard, scanner);

        assertEquals(0, sponsorIndex, "The first player (P1) should sponsor the quest.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        // Check if expected message is in captured output
        assertTrue(output.contains("[Game] P1 is sponsoring the quest!"), "P1 should be the sponsor.");
    }

    @Test
    @DisplayName("RESP_17_test_02: Test when all players decline sponsorship")
    public void RESP_17_test_02() {
        String input = "n\nn\nn\nn\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        QuestCard questCard = new QuestCard("Q2", 2); // Quest with 2 stages

        main.setCurrentPlayerIndex(0); // Start with P1
        for (Player player : players) {
            FoeCard f10 = new FoeCard("F10", 10);
            FoeCard f15 = new FoeCard("F15", 15);
            WeaponCard d5 = new WeaponCard("D5", 5);
            WeaponCard h10 = new WeaponCard("H10", 10);
            WeaponCard s10 = new WeaponCard("S10", 10);
            player.addCardToHand(f10);
            player.addCardToHand(f15);
            player.addCardToHand(d5);
            player.addCardToHand(h10);
            player.addCardToHand(s10);
            assertTrue(main.isValidSponsor(player, questCard.getStages()));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Prompt for sponsorship
        int sponsorIndex = main.promptForSponsorship(questCard, scanner);

        assertEquals(-1, sponsorIndex, "No player should sponsor the quest.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] No player has chosen to sponsor the quest."),
                "The game should announce no sponsor.");
    }

    @Test
    @DisplayName("RESP_17_test_03: Test when invalid input is given and game prompts again")
    public void RESP_17_test_03() {
        String input = "invalid\ny\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        QuestCard questCard = new QuestCard("Q2", 2); // Quest with 2 stages

        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);

        FoeCard f10 = new FoeCard("F10", 10);
        FoeCard f15 = new FoeCard("F15", 15);
        WeaponCard d5 = new WeaponCard("D5", 5);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        p1.addCardToHand(f10);
        p1.addCardToHand(f15);
        p1.addCardToHand(d5);
        p1.addCardToHand(h10);
        p1.addCardToHand(s10);

        assertTrue(main.isValidSponsor(p1, questCard.getStages()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Prompt for sponsorship
        int sponsorIndex = main.promptForSponsorship(questCard, scanner);

        assertEquals(0, sponsorIndex, "P1 should sponsor the quest after giving correct input.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] Invalid input. Please enter 'y' or 'n'."),
                "The game should prompt again for valid input.");
        assertTrue(output.contains("[Game] P1 is sponsoring the quest!"),
                "P1 should be confirmed as the sponsor after valid input.");
    }

    @Test
    @DisplayName("RESP_17_test_04: Test P2 is current player but declines to sponsor and P2 accepts to sponsor")
    public void RESP_17_test_04() {
        QuestCard questCard = new QuestCard("Q2", 2); // Quest with 2 stages

        String input = "n\ny\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        main.setCurrentPlayerIndex(1); // P2
        Player p2 = players.get(1);
        Player p3 = players.get(2);

        FoeCard f10 = new FoeCard("F10", 10);
        FoeCard f15 = new FoeCard("F15", 15);
        WeaponCard d5 = new WeaponCard("D5", 5);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        p2.addCardToHand(f10);
        p2.addCardToHand(f15);
        p2.addCardToHand(d5);
        p2.addCardToHand(h10);
        p2.addCardToHand(s10);

        p3.addCardToHand(f10);
        p3.addCardToHand(f15);
        p3.addCardToHand(d5);
        p3.addCardToHand(h10);
        p3.addCardToHand(s10);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Prompt for sponsorship
        int sponsorIndex = main.promptForSponsorship(questCard, scanner);

        assertEquals(2, sponsorIndex, "P3 should sponsor the quest P2 declines to sponsor");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] P2 has chosen not to sponsor the quest."),
                "P2 should choose not to sponsor the quest");
        assertTrue(output.contains("[Game] P3 is sponsoring the quest!"), "P3 should be confirmed as the sponsor");
    }

    @Test
    @DisplayName("RESP_18_test_01: Test valid card position input")
    public void RESP_18_test_01() {
        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);

        p1.addCardToHand(new FoeCard("F10", 10));
        p1.addCardToHand(new WeaponCard("D5", 5));

        String validInput = "1";
        assertTrue(main.isValidCardPosition(validInput, 0), "Position 1 should be valid.");
    }

    @Test
    @DisplayName("RESP_18_test_02: Test invalid card position with non-integer input")
    public void RESP_18_test_02() {
        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);

        p1.addCardToHand(new FoeCard("F10", 10));

        String invalidInput = "a";
        assertFalse(main.isValidCardPosition(invalidInput, 0), "Non-integer input should be invalid.");
    }

    @Test
    @DisplayName("RESP_18_test_03: Test invalid card position with out-of-range input")
    public void RESP_18_test_03() {
        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);
        p1.addCardToHand(new FoeCard("F10", 10));
        p1.addCardToHand(new WeaponCard("D5", 5));

        String outOfRangeInput = "5";
        assertFalse(main.isValidCardPosition(outOfRangeInput, 0), "Out-of-range position should be invalid.");
    }

    @Test
    @DisplayName("RESP_18_test_04: Test invalid card position with negative input")
    public void RESP_18_test_04() {
        main.setCurrentPlayerIndex(0); // P1
        Player p1 = players.get(0);
        p1.addCardToHand(new FoeCard("F10", 10));

        String negativeInput = "-1";
        assertFalse(main.isValidCardPosition(negativeInput, 0), "Negative position should be invalid.");
    }

    @Test
    @DisplayName("RESP_19_test_01: Test validity of Foe Card for stage")
    void RESP_19_test_01() {
        List<Card> stageCards = new ArrayList<>();
        FoeCard f10 = new FoeCard("F10", 10);
        assertTrue(main.isValidCardForStage(f10, stageCards), "Foe card should be valid when stage is empty");

        stageCards.add(f10);
        FoeCard f15 = new FoeCard("F15", 15);
        assertFalse(main.isValidCardForStage(f15, stageCards), "Only one Foe card is allowed in the stage");
    }

    @Test
    @DisplayName("RESP_19_test_02: Test validity of Weapon Card for stage")
    void RESP_19_test_02() {
        List<Card> stageCards = new ArrayList<>();
        WeaponCard s10 = new WeaponCard("S10", 10);
        assertTrue(main.isValidCardForStage(s10, stageCards), "Weapon card should be valid when stage is empty");

        stageCards.add(s10);
        assertFalse(main.isValidCardForStage(s10, stageCards), "No repeated Weapon cards are allowed");

        WeaponCard b15 = new WeaponCard("B15", 15);
        assertTrue(main.isValidCardForStage(b15, stageCards), "Different Weapon card should be valid");
    }

    @Test
    @DisplayName("RESP_19_test_03: Test validity of Card for stage")
    void RESP_19_test_03() {
        List<Card> stageCards = new ArrayList<>();
        QuestCard q2 = new QuestCard("Q2", 2);
        assertFalse(main.isValidCardForStage(q2, stageCards), "Card must be either a FoeCard or WeaponCard");
    }

    @Test
    @DisplayName("RESP_19_test_04: Test validity of Foe Card when Weapon Cards are present")
    void RESP_19_test_04() {
        List<Card> stageCards = new ArrayList<>();
        WeaponCard s10 = new WeaponCard("S10", 10);
        stageCards.add(s10);

        FoeCard f10 = new FoeCard("F10", 10);
        assertTrue(main.isValidCardForStage(f10, stageCards), "Foe card is valid since there is only a weapon card");
    }

    @Test
    @DisplayName("RESP_20_test_01: Test adding a valid FoeCard to the stage")
    void RESP_20_test_01() {
        Scanner scanner = new Scanner(System.in);
        List<Card> stageCards = new ArrayList<>();
        Player sponsor = players.get(0);
        sponsor.addCardToHand(new FoeCard("F10", 10));
        sponsor.addCardToHand(new WeaponCard("S10", 10));

        String input = "0";

        List<Card> updatedStageCards = main.addCardToStage(input, 0, stageCards, scanner);

        assertEquals(1, updatedStageCards.size(), "Stage should contain one card.");
        assertTrue(updatedStageCards.get(0) instanceof FoeCard, "The added card should be a FoeCard.");
    }

    @Test
    @DisplayName("RESP_20_test_02: Test adding a valid WeaponCard to the stage")
    void RESP_20_test_02() {
        Scanner scanner = new Scanner(System.in);
        List<Card> stageCards = new ArrayList<>();
        Player sponsor = players.get(0);
        sponsor.addCardToHand(new WeaponCard("S10", 10));
        sponsor.addCardToHand(new FoeCard("F10", 10));

        String input = "0";

        List<Card> updatedStageCards = main.addCardToStage(input, 0, stageCards, scanner); // Pass the scanner instance

        assertEquals(1, updatedStageCards.size(), "Stage should contain one card.");
        assertTrue(updatedStageCards.get(0) instanceof WeaponCard, "The added card should be a WeaponCard.");
    }

    @Test
    @DisplayName("RESP_20_test_03: Test stage displays correct cards after additions")
    void RESP_20_test_03() {
        Scanner scanner = new Scanner(System.in);
        List<Card> stageCards = new ArrayList<>();
        Player sponsor = players.get(0);
        FoeCard f10 = new FoeCard("F10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);

        sponsor.addCardToHand(f10);
        sponsor.addCardToHand(s10);

        String input = "0";
        main.addCardToStage(input, 0, stageCards, scanner);

        input = "0";
        main.addCardToStage(input, 0, stageCards, scanner);

        assertEquals(2, stageCards.size(), "Stage should contain two cards.");
        assertTrue(stageCards.contains(f10), "Stage should contain the FoeCard.");
        assertTrue(stageCards.contains(s10), "Stage should contain the WeaponCard.");
    }

    @Test
    @DisplayName("RESP_21_test_01: Test handling quit with an empty stage")
    void RESP_21_test_01() {
        List<Card> stageCards = new ArrayList<>();
        int currentStageValue = 0;
        int prevStageValue = 10;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 1);

        assertFalse(result, "The result should be false for an empty stage.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] A stage cannot be empty."), "A stage should not be empty");
    }

    @Test
    @DisplayName("RESP_21_test_02: Test handling quit with insufficient stage value")
    void RESP_21_test_02() {
        List<Card> stageCards = new ArrayList<>();
        stageCards.add(new WeaponCard("S10", 10));
        int currentStageValue = 5; // Less than the previous stage value
        int prevStageValue = 10;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 2);

        assertFalse(result, "The result should be false for insufficient stage value.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains(
                        "[Game] Insufficient value for this stage. You must create a stage with greater value than the previous one."),
                "Stage value has to be greater than previous one");
    }

    @Test
    @DisplayName("RESP_21_test_03: Test handling quit with a valid stage")
    void RESP_21_test_03() {
        List<Card> stageCards = new ArrayList<>();
        stageCards.add(new FoeCard("F10", 10));
        stageCards.add(new WeaponCard("S10", 10));
        int currentStageValue = 20; // Greater than previous stage value
        int prevStageValue = 15;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 2);

        assertTrue(result, "The result should be true for a valid stage.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] Stage 2 complete."), "Stage should be valid");
    }

    @Test
    @DisplayName("RESP_21_test_04: Test handling quit with a stage containing only a foe card and no weapon cards.")
    void RESP_21_test_04() {
        List<Card> stageCards = new ArrayList<>();
        stageCards.add(new FoeCard("F10", 10));
        int currentStageValue = 10;
        int prevStageValue = 0;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 1);

        assertFalse(result, "The result should be false.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] You must include at least one unique weapon card in the stage."),
                "Stage should be invalid");
    }

    @Test
    @DisplayName("RESP_21_test_05: Test handling quit with a stage containing only a weapon cards and no foe card.")
    void RESP_21_test_05() {
        List<Card> stageCards = new ArrayList<>();
        stageCards.add(new WeaponCard("S10", 10));
        stageCards.add(new WeaponCard("H10", 10));
        int currentStageValue = 20; // Greater than previous stage value
        int prevStageValue = 15;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 2);

        assertFalse(result, "The result should be false.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] You must include one foe card in the stage."), "Stage should be invalid");
    }

    @Test
    @DisplayName("RESP_21_test_06: Test handling quit with multiple unique weapons and one foe card")
    void RESP_21_test_06() {
        List<Card> stageCards = new ArrayList<>();
        stageCards.add(new FoeCard("F10", 10));
        stageCards.add(new WeaponCard("S10", 10));
        stageCards.add(new WeaponCard("H10", 10));
        int currentStageValue = 30;
        int prevStageValue = 20;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean result = main.handleQuit(stageCards, currentStageValue, prevStageValue, 2);

        assertTrue(result, "The result should be true for a valid stage with unique weapons and a foe.");
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Stage 2 complete."),
                "Stage should be valid with unique weapons and a foe card.");
    }

    @Test
    @DisplayName("RESP_22_test_01: Test building a valid stage")
    void RESP_22_test_01() {
        Player sponsor = players.get(0); // P1
        sponsor.addCardToHand(new FoeCard("F5", 5));
        sponsor.addCardToHand(new WeaponCard("S10", 10));

        String input = "0\n0\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Card> stageCards;
        int prevStageValue = 0;
        int stageNumber = 1;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        stageCards = main.buildStage(0, prevStageValue, stageNumber, scanner);
        assertNotNull(stageCards, "Stage setup should be successful and not return null.");
        assertEquals(2, stageCards.size(), "Stage should contain 2 cards.");

        boolean result = stageCards.isEmpty();
        assertFalse(result, "Stage should have cards");
        assertTrue(stageCards.get(0) instanceof FoeCard && stageCards.get(0).getName().equals("F5"),
                "First stage should contain F5.");
        assertTrue(stageCards.get(1) instanceof WeaponCard && stageCards.get(1).getName().equals("S10"),
                "First stage should contain S10.");

        int stageValue = main.calculateStageValue(stageCards);

        assertEquals(15, stageValue, "Stage value should be 15");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] Building stage number 1"), "Should be building 1st stage");
        assertTrue(output.contains("[Game] P1's hand:"), "The game should display the hand of the sponsor");
        assertTrue(
                output.contains(
                        "[Game] Enter the position of the card you want to add to the stage or 'Quit' to finish:"),
                "The game should prompt the sponsor for the position of the next card to include in that stage or 'Quit'");
    }

    @Test
    @DisplayName("RESP_23_test_01: Test successful quest setup with multiple stages")
    void RESP_23_test_01() {
        QuestCard questCard = new QuestCard("Q2", 2); // Quest with 2 stages
        Player sponsor = players.get(0); // P1
        sponsor.addCardToHand(new FoeCard("F5", 5));
        sponsor.addCardToHand(new WeaponCard("S10", 10));
        sponsor.addCardToHand(new FoeCard("F10", 10));
        sponsor.addCardToHand(new WeaponCard("B15", 15));

        String input = "0\n1\nquit\n0\n0\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<List<Card>> questStages = main.buildQuest(0, questCard, scanner);

        assertNotNull(questStages, "Quest setup should be successful and not return null.");
        assertEquals(2, questStages.size(), "Quest should contain 2 stages.");

        List<Card> firstStage = questStages.get(0);
        assertEquals(2, firstStage.size(), "First stage should contain 2 cards.");
        assertTrue(firstStage.get(0) instanceof FoeCard && firstStage.get(0).getName().equals("F5"),
                "First stage should contain F5.");
        assertTrue(firstStage.get(1) instanceof WeaponCard && firstStage.get(1).getName().equals("S10"),
                "First stage should contain S10.");

        List<Card> secondStage = questStages.get(1);
        assertEquals(2, secondStage.size(), "Second stage should contain 2 cards.");
        assertTrue(secondStage.get(0) instanceof FoeCard && secondStage.get(0).getName().equals("F10"),
                "Second stage should contain F10.");
        assertTrue(secondStage.get(1) instanceof WeaponCard && secondStage.get(1).getName().equals("B15"),
                "Second stage should contain B15.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Setting up stage 1 of 2."),
                "Output should indicate the first stage is being set up.");
        assertTrue(output.contains("[Game] Setting up stage 2 of 2."),
                "Output should indicate the second stage is being set up.");
        assertTrue(output.contains("[Game] Quest setup is complete!"),
                "Output should indicate the quest setup is complete.");
    }

    @Test
    @DisplayName("RESP_24_test_01: Test all players except sponsor are eligible for the first stage")
    void RESP_24_test_01() {
        Player sponsor = players.get(0); // Sponsor is P1
        List<Player> previousWinners = new ArrayList<>();
        Set<Player> withdrawnPlayers = new HashSet<>();

        List<Player> eligibleParticipants = main.getEligibleParticipants(0, players, previousWinners, withdrawnPlayers,
                1); // Stage 1

        assertFalse(eligibleParticipants.contains(sponsor), "Sponsor should not be eligible");
        assertEquals(3, eligibleParticipants.size(), "There should be 3 eligible participants");
        for (int i = 1; i < players.size(); i++) {
            assertTrue(eligibleParticipants.contains(players.get(i)), "Player P" + (i + 1) + " should be eligible");
        }
    }

    @Test
    @DisplayName("RESP_24_test_02: Test only winners of the previous stage are eligible for the next stage")
    void RESP_24_test_02() {
        List<Player> previousWinners = new ArrayList<>();
        previousWinners.add(players.get(2)); // P3 wins first stage

        Set<Player> withdrawnPlayers = new HashSet<>();

        List<Player> eligibleParticipants = main.getEligibleParticipants(0, players, previousWinners, withdrawnPlayers,
                2); // Stage 2

        assertEquals(1, eligibleParticipants.size(), "Only one player should be eligible for the second stage");
        assertTrue(eligibleParticipants.contains(players.get(2)), "Player P3 should be eligible for the second stage");
    }

    @Test
    @DisplayName("RESP_24_test_03: Test a player who withdrew cannot participate in the next stage")
    void RESP_24_test_03() {
        List<Player> previousWinners = new ArrayList<>();
        previousWinners.add(players.get(1)); // P2 wins first stage

        Set<Player> withdrawnPlayers = new HashSet<>();
        withdrawnPlayers.add(players.get(2)); // P3 withdrew

        List<Player> eligibleParticipants = main.getEligibleParticipants(0, players, previousWinners, withdrawnPlayers,
                2); // Stage 2

        assertEquals(1, eligibleParticipants.size(), "Only one player should be eligible for the second stage");
        assertTrue(eligibleParticipants.contains(players.get(1)), "Player P2 should be eligible for the second stage");
        assertFalse(eligibleParticipants.contains(players.get(2)),
                "Player P3 should not be eligible since they withdrew");
    }

    @Test
    @DisplayName("RESP_24_test_04: Test displaying eligible participants")
    void RESP_24_test_04() {
        List<Player> previousWinners = new ArrayList<>();
        previousWinners.add(players.get(1)); // P2 wins first stage
        previousWinners.add(players.get(2)); // P3 wins first stage

        Set<Player> withdrawnPlayers = new HashSet<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Player> eligibleParticipants = main.getEligibleParticipants(0, players, previousWinners, withdrawnPlayers,
                2); // Stage 2
        main.displayEligibleParticipants(eligibleParticipants, 2); // Display eligible participants

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("P2"), "P2 should be displayed as eligible");
        assertTrue(output.contains("P3"), "P3 should be displayed as eligible");
        assertFalse(output.contains("P1"), "P1 (the sponsor) should not be displayed");
    }

    @Test
    @DisplayName("RESP_25_test_01: Test player participates when input is 'Play'")
    void RESP_25_test_01() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1)); // P1 and P2 are eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "play\nwithdraw\n"; // P1 chooses to play
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, participants.size(), "Only one player in list of participants");
        // Check output
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
    }

    @Test
    @DisplayName("RESP_25_test_02: Test player withdraws when input is 'Withdraw'")
    void RESP_25_test_02() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1)); // P1 and P2 are eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Simulate user input
        String input = "withdraw\nplay\n"; // P1 chooses to play
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, withdrawnPlayers.size(), "Only one player withdrew");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 has withdrawn from the quest."), "P1 should choose to withdraw.");
        assertTrue(withdrawnPlayers.contains(players.get(0)), "P1 should be added to the withdrawn players.");
    }

    @Test
    @DisplayName("RESP_25_test_03: Test player is prompted again for invalid input")
    void RESP_25_test_03() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1)); // P1 and P2 are eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Simulate user input with invalid response followed by valid response
        String input = "invalid\ninvalid\nplay\nwithdraw\n"; // P1 enters invalid input first
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, participants.size(), "Only one player in list of participants");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("Invalid input. Please enter 'play' or 'withdraw'."),
                "Player should see an invalid input message.");
        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should eventually choose to play.");
    }

    @Test
    @DisplayName("RESP_25_test_04: Test withdrawn players are skipped")
    void RESP_25_test_04() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1), players.get(2)); // P1, P2,
        // and P3 are
        // eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // P1 chooses to play, P2 chooses to withdraw, and P3 chooses to play
        String input = "play\nwithdraw\nplay\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(2, participants.size(), "Should be two players in list of participants");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
        assertTrue(withdrawnPlayers.contains(players.get(1)), "P2 should be added to the withdrawn players.");
        assertFalse(output.contains("[Game] P2 will participate in the stage."),
                "P2 should be skipped since they have withdrawn.");
        assertTrue(output.contains("[Game] P3 will participate in the stage."), "P3 should choose to play.");
    }

    @Test
    @DisplayName("RESP_25_test_05: Test all players withdraw")
    void RESP_25_test_05() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1), players.get(2)); // P1, P2,
        // and P3 are
        // eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // All players choose to withdraw
        String input = "withdraw\nwithdraw\nwithdraw\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(0, participants.size(), "No players in list of participants");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(withdrawnPlayers.contains(players.get(0)), "P1 should be withdrawn.");
        assertTrue(withdrawnPlayers.contains(players.get(1)), "P2 should be withdrawn.");
        assertTrue(withdrawnPlayers.contains(players.get(2)), "P3 should be withdrawn.");
    }

    @Test
    @DisplayName("RESP_25_test_06: Test multiple invalid inputs before valid response")
    void RESP_25_test_06() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0)); // P1 is eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Simulate invalid inputs followed by valid input
        String input = "invalid\ninvalid\nwithdraw\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(0, participants.size(), "No players in list of participants");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Invalid input. Please enter 'play' or 'withdraw'."),
                "Player should see an invalid input message.");
        assertTrue(withdrawnPlayers.contains(players.get(0)), "P1 should be added to the withdrawn players.");
    }

    @Test
    @DisplayName("RESP_25_test_07: Test mixed responses from players")
    void RESP_25_test_07() {
        main.setCurrentPlayerIndex(0);
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(1), players.get(2)); // P1, P2,
        // and P3 are
        // eligible

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // P1 chooses to play, P2 chooses to withdraw, and P3 chooses to play
        String input = "play\nwithdraw\nplay\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(2, participants.size(), "Should be two players in list of participants");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
        assertTrue(withdrawnPlayers.contains(players.get(1)), "P2 should be added to the withdrawn players.");
        assertFalse(output.contains("[Game] P2 will participate in the stage."),
                "P2 should be skipped since they have withdrawn.");
        assertTrue(output.contains("[Game] P3 will participate in the stage."), "P3 should choose to play.");
    }

    @Test
    @DisplayName("RESP_25_test_08: Test correct player is prompted first based on currentPlayerIndex")
    void RESP_25_test_08() {
        main.setCurrentPlayerIndex(2); // P3
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants = Arrays.asList(players.get(0), players.get(2), players.get(1)); // P1, P3, P2

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "play\nwithdraw\nplay\n"; // P3 chooses to play, P1 withdraws, and P2 plays
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P3"), "P3 should be prompted first as the current player.");
        assertTrue(output.contains("[Game] P1"), "P1 should be prompted second.");
        assertTrue(output.contains("[Game] P2"), "P2 should be prompted last.");

        assertEquals(2, participants.size(), "Should be two players in list of participants");
        assertFalse(participants.contains(players.get(0)), "P1 should not be in the list since they withdrew.");
        assertTrue(participants.contains(players.get(1)), "P2 should participate.");
        assertTrue(participants.contains(players.get(2)), "P3 should participate.");
    }

    @Test
    @DisplayName("RESP_26_test_01: Test player draws a card when they choose to participate")
    void RESP_26_test_01() {
        main.setCurrentPlayerIndex(0);
        Player p1 = players.get(0);
        List<Player> eligibleParticipants = Arrays.asList(p1); // P1 is eligible
        Set<Player> withdrawnPlayers = new HashSet<>();

        List<Card> hand = main.getAdventureDeck().drawMultipleCards(11); // Give P1 11 cards
        p1.setHand(hand);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "play\n"; // P1 chooses to play
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, participants.size(), "P1 should choose to participate.");

        main.handleParticipation(p1, scanner);
        assertEquals(12, p1.getHand().size(), "P1 should have drawn 1 card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
    }

    @Test
    @DisplayName("RESP_26_test_02: Test player's hand is trimmed when exceeding limit after drawing")
    void RESP_26_test_02() {
        main.setCurrentPlayerIndex(0);
        Player p1 = players.get(0);
        List<Player> eligibleParticipants = Arrays.asList(p1); // P1 is eligible
        Set<Player> withdrawnPlayers = new HashSet<>();

        List<Card> hand = main.getAdventureDeck().drawMultipleCards(12); // Give P1 12 cards
        p1.setHand(hand);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "play\n0\n1"; // P1 chooses to play
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, participants.size(), "P1 should choose to participate.");

        main.handleParticipation(p1, scanner);
        assertEquals(12, p1.getHand().size(), "P1 should trim hand to 12 cards.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
        assertTrue(output.contains("[Game] P1 has more than 12 cards and needs to trim their hand."),
                "P1 should be prompted to trim their hand.");
    }

    @Test
    @DisplayName("RESP_26_test_03: Test player draws a card and does not trim when hand is below limit")
    void RESP_26_test_03() {
        main.setCurrentPlayerIndex(0);
        Player p1 = players.get(0);
        List<Player> eligibleParticipants = Arrays.asList(p1); // P1 is eligible
        Set<Player> withdrawnPlayers = new HashSet<>();

        List<Card> hand = main.getAdventureDeck().drawMultipleCards(10); // Give P1 10 cards
        p1.setHand(hand);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "play\n"; // P1 chooses to play
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);
        assertEquals(1, participants.size(), "P1 should choose to participate.");

        main.handleParticipation(p1, scanner);
        assertEquals(11, p1.getHand().size(), "P1 should have 11 cards, no trimming required.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] P1 will participate in the stage."), "P1 should choose to play.");
    }

    @Test
    @DisplayName("RESP_27_test_01: Test validity of Weapon Card for attack")
    void RESP_27_test_01() {
        List<Card> attackCards = new ArrayList<>();
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard b15 = new WeaponCard("B15", 15);
        WeaponCard d5 = new WeaponCard("D5", 5);
        assertTrue(main.isValidCardForAttack(s10, attackCards), "Weapon card should be valid when attack is empty");
        attackCards.add(s10);
        assertFalse(main.isValidCardForAttack(s10, attackCards), "No repeated Weapon cards are allowed");

        assertTrue(main.isValidCardForAttack(b15, attackCards), "B15 Weapon card should be valid");
        attackCards.add(b15);
        assertTrue(main.isValidCardForAttack(d5, attackCards), "D5 Weapon card should be valid");

    }

    @Test
    @DisplayName("RESP_27_test_02: Test validity of Card type for attack")
    void RESP_27_test_02() {
        List<Card> attackCards = new ArrayList<>();
        QuestCard q2 = new QuestCard("Q2", 2);
        assertFalse(main.isValidCardForAttack(q2, attackCards),
                "This card is a Quest Card. Card must be a non-repeated Weapon Card.");

        FoeCard f5 = new FoeCard("F5", 5);
        assertFalse(main.isValidCardForAttack(f5, attackCards),
                "This card is a Foe Card. Card must be a non-repeated Weapon Card.");

        EventCard pros = new EventCard("Prosperity");
        assertFalse(main.isValidCardForAttack(pros, attackCards),
                "This card is an Event Card. Card must be a non-repeated Weapon Card.");

        WeaponCard d5 = new WeaponCard("D5", 5);
        assertTrue(main.isValidCardForAttack(d5, attackCards),
                "This is a non-repeated Weapon Card. This card should be valid.");
    }

    @Test
    @DisplayName("RESP_28_test_01: Test adding a valid card to the attack")
    void RESP_28_test_01() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        WeaponCard s10 = new WeaponCard("S10", 10);
        participant.addCardToHand(s10);

        List<Card> attackCards = new ArrayList<>();
        String input = "0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.addCardToAttack(input, 0, attackCards, scanner);

        assertEquals(1, attackCards.size(), "The attack should contain 1 card.");
        assertTrue(attackCards.contains(s10), "The attack should include the selected weapon card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] " + participant.getName() + " has added 1 card(s) for his/her attack."),
                "Output should confirm the card was added.");
        assertTrue(output.contains("[Game] Card added to attack: " + s10.getName()),
                "Output should indicate which card was added.");
    }

    @Test
    @DisplayName("RESP_28_test_02: Test adding a valid card to the attack after invalid card position")
    void RESP_28_test_02() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        WeaponCard s10 = new WeaponCard("S10", 10);
        participant.addCardToHand(s10);

        List<Card> attackCards = new ArrayList<>();
        String input = "4\n0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.addCardToAttack(input, 0, attackCards, scanner);

        assertEquals(1, attackCards.size(), "The attack should contain 1 card.");
        assertTrue(attackCards.contains(s10), "The attack should include the selected weapon card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Invalid input. Please enter a valid card position:"),
                "Output should re-prompt and explain the invalidity.");
        assertTrue(output.contains("[Game] " + participant.getName() + " has added 1 card(s) for his/her attack."),
                "Output should confirm the card was added.");
        assertTrue(output.contains("[Game] Card added to attack: " + s10.getName()),
                "Output should indicate which card was added.");
    }

    @Test
    @DisplayName("RESP_28_test_03: Test adding a valid card to the attack after invalid card type")
    void RESP_28_test_03() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        FoeCard f15 = new FoeCard("F15", 15);
        WeaponCard s10 = new WeaponCard("S10", 10);
        participant.addCardToHand(f15);
        participant.addCardToHand(s10);

        List<Card> attackCards = new ArrayList<>();
        String input = "0\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.addCardToAttack(input, 0, attackCards, scanner);

        assertEquals(1, attackCards.size(), "The attack should contain 1 card.");
        assertTrue(attackCards.contains(s10), "The attack should include the selected weapon card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Invalid card for this stage. No repeated Weapon cards per attack."),
                "Output should re-prompt and explain the invalidity.");
        assertTrue(output.contains("[Game] " + participant.getName() + " has added 1 card(s) for his/her attack."),
                "Output should confirm the card was added.");
        assertTrue(output.contains("[Game] Card added to attack: " + s10.getName()),
                "Output should indicate which card was added.");
    }

    @Test
    @DisplayName("RESP_29_test_01: Test displaying an empty set of attack cards")
    void RESP_29_test_01() {
        main.setCurrentPlayerIndex(0);

        String input = "quit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Card> attackCards = main.setupAttack(0, scanner);

        assertEquals(0, attackCards.size(), "The attack should contain no cards.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[P1] quit"), "P1 should enter quit");
        assertTrue(output.contains("[Game] Attack complete."), "Attack setup should be completed");
        assertTrue(output.contains("[Game] Total attack value: 0"), "Total attack value should be 0");
        assertTrue(output.contains("[Game] Cards used for this attack: "),
                "Output should display the cards used for this attack");
    }

    @Test
    @DisplayName("RESP_29_test_02: Test displaying a set of attack cards")
    void RESP_29_test_02() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard s10 = new WeaponCard("S10", 10);
        participant.addCardToHand(h10);
        participant.addCardToHand(s10);

        String input = "0\n0\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Card> attackCards = main.setupAttack(0, scanner);

        assertEquals(2, attackCards.size(), "The attack should contain 2 cards.");
        assertTrue(attackCards.contains(s10), "The attack should include the sword weapon card.");
        assertTrue(attackCards.contains(h10), "The attack should include the horse weapon card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Card added to attack: S10"), "S10 should be added to attack");
        assertTrue(output.contains("[Game] Card added to attack: H10"), "H10 should be added to attack");
        assertTrue(output.contains("[P1] quit"), "P1 should enter quit");
        assertTrue(output.contains("[Game] Attack complete."), "Attack setup should be completed");
        assertTrue(output.contains("[Game] Total attack value: 20"), "Total attack value should be 20");
        assertTrue(output.contains("[Game] Cards used for this attack: "),
                "Output should display the cards used for this attack");
    }

    @Test
    @DisplayName("RESP_29_test_03: Test invalid card position input")
    void RESP_29_test_03() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        WeaponCard h10 = new WeaponCard("H10", 10);
        participant.addCardToHand(h10);

        String input = "2\ndone\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Card> attackCards = main.setupAttack(0, scanner);

        assertEquals(0, attackCards.size(), "The attack should contain no cards after invalid input.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Invalid input. Please enter a valid card position:"),
                "Should prompt for valid position.");
    }

    @Test
    @DisplayName("RESP_29_test_04: Test preventing repeated card addition")
    void RESP_29_test_04() {
        main.setCurrentPlayerIndex(0);
        Player participant = players.get(0);
        WeaponCard s10 = new WeaponCard("S10", 10);
        participant.addCardToHand(s10);
        participant.addCardToHand(s10);

        String input = "0\n0\ndone\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        List<Card> attackCards = main.setupAttack(0, scanner);

        assertEquals(1, attackCards.size(), "The attack should contain 1 card after trying to add a repeated card.");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Invalid card for this stage. No repeated Weapon cards per attack."),
                "Should indicate repeated card addition.");
    }

    @Test
    @DisplayName("RESP_30_test_01: Test all players losing the stage")
    void RESP_30_test_01() {
        List<List<Card>> attackingCards = new ArrayList<>(); // ["S10"],["S10"],["H10"]
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard h10 = new WeaponCard("H10", 10);
        attackingCards.add(Arrays.asList(s10));
        attackingCards.add(Arrays.asList(s10));
        attackingCards.add(Arrays.asList(h10));
        List<Integer> attackIndices = new ArrayList<>(); // P2, P3, P4
        attackIndices.add(1);
        attackIndices.add(2);
        attackIndices.add(3);
        List<List<Card>> questCards = new ArrayList<>(); // For stage 1: ["F5", "S10"]
        FoeCard f5 = new FoeCard("F5", 5);
        questCards.add(Arrays.asList(f5, s10));
        List<Player> previousWinners = new ArrayList<>(); // Empty
        Set<Player> withdrawnPlayers = new HashSet<>(); // Empty
        int stageIndex = 0;
        main.resolveAttacks(questCards, stageIndex, attackingCards, attackIndices, previousWinners, withdrawnPlayers);
        // P1 is not in either
        assertFalse(withdrawnPlayers.contains(players.get(0)));
        assertFalse(previousWinners.contains(players.get(0)));
        // P2, P3, P4 lost
        assertTrue(withdrawnPlayers.contains(players.get(1)));
        assertFalse(previousWinners.contains(players.get(1)));
        assertTrue(withdrawnPlayers.contains(players.get(2)));
        assertFalse(previousWinners.contains(players.get(2)));
        assertTrue(withdrawnPlayers.contains(players.get(3)));
        assertFalse(previousWinners.contains(players.get(3)));
    }

    @Test
    @DisplayName("RESP_30_test_02: Test all players winning the stage")
    void RESP_30_test_02() {
        List<List<Card>> attackingCards = new ArrayList<>(); // ["S10","H10"],["S10","H10"],["S10","H10"]
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard h10 = new WeaponCard("H10", 10);
        attackingCards.add(Arrays.asList(s10, h10));
        attackingCards.add(Arrays.asList(s10, h10));
        attackingCards.add(Arrays.asList(s10, h10));
        List<Integer> attackIndices = new ArrayList<>(); // P2, P3, P4
        attackIndices.add(1);
        attackIndices.add(2);
        attackIndices.add(3);
        List<List<Card>> questCards = new ArrayList<>();
        FoeCard f5 = new FoeCard("F5", 5);
        questCards.add(Arrays.asList(f5, s10));
        List<Player> previousWinners = new ArrayList<>();
        Set<Player> withdrawnPlayers = new HashSet<>();
        int stageIndex = 0;

        main.resolveAttacks(questCards, stageIndex, attackingCards, attackIndices, previousWinners, withdrawnPlayers);

        assertFalse(withdrawnPlayers.contains(players.get(1)));
        assertFalse(withdrawnPlayers.contains(players.get(2)));
        assertFalse(withdrawnPlayers.contains(players.get(3)));
        assertTrue(previousWinners.contains(players.get(1)));
        assertTrue(previousWinners.contains(players.get(2)));
        assertTrue(previousWinners.contains(players.get(3)));
    }

    @Test
    @DisplayName("RESP_30_test_03: Test P1 winning the attack while P2 and P3 lose")
    void RESP_30_test_03() {
        List<List<Card>> attackingCards = new ArrayList<>(); // ["H10"],["S10"],["B15"]
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard h10 = new WeaponCard("H10", 10);
        WeaponCard b15 = new WeaponCard("B15", 15);
        attackingCards.add(Arrays.asList(b15));
        attackingCards.add(Arrays.asList(h10));
        attackingCards.add(Arrays.asList(s10));
        List<Integer> attackIndices = new ArrayList<>(); // P1, P2, P3
        attackIndices.add(0);
        attackIndices.add(1);
        attackIndices.add(2);
        List<List<Card>> questCards = new ArrayList<>();
        FoeCard f5 = new FoeCard("F5", 5);
        questCards.add(Arrays.asList(f5, s10));
        List<Player> previousWinners = new ArrayList<>();
        Set<Player> withdrawnPlayers = new HashSet<>();
        int stageIndex = 0;
        main.resolveAttacks(questCards, stageIndex, attackingCards, attackIndices, previousWinners, withdrawnPlayers);
        // P1 wins
        assertTrue(previousWinners.contains(players.get(0)));
        assertFalse(withdrawnPlayers.contains(players.get(0)));
        // P2, P3 lose
        assertFalse(previousWinners.contains(players.get(1)));
        assertTrue(withdrawnPlayers.contains(players.get(1)));
        assertFalse(previousWinners.contains(players.get(2)));
        assertTrue(withdrawnPlayers.contains(players.get(2)));
    }

    @Test
    @DisplayName("RESP_30_test_04: Test no participants")
    void RESP_30_test_04() {
        List<List<Card>> attackingCards = new ArrayList<>();
        List<Integer> attackIndices = new ArrayList<>(); // No participants
        List<List<Card>> questCards = new ArrayList<>();
        FoeCard f5 = new FoeCard("F5", 5);
        WeaponCard s10 = new WeaponCard("S10", 10);
        questCards.add(Arrays.asList(f5, s10));
        List<Player> previousWinners = new ArrayList<>();
        Set<Player> withdrawnPlayers = new HashSet<>();
        int stageIndex = 0;

        main.resolveAttacks(questCards, stageIndex, attackingCards, attackIndices, previousWinners, withdrawnPlayers);

        assertTrue(previousWinners.isEmpty());
        assertTrue(withdrawnPlayers.isEmpty());
    }

    @Test
    @DisplayName("RESP_31_test_01: Test discarding attack cards after stage resolution")
    void RESP_31_test_01() {
        List<List<Card>> attackingCards = new ArrayList<>();
        attackingCards.add(adventureDeck.drawMultipleCards(5));
        attackingCards.add(adventureDeck.drawMultipleCards(5));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.discardAttackCards(attackingCards);
        assertEquals(0, attackingCards.size(), "attacking cards should be empty and completely discarded");
        assertEquals(10, adventureDeck.getDiscardPile().size(), "Adventure deck discard pile should have 10 cards");

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Discarding card: "), "Game should display what cards are being discarded.");
    }

    @Test
    @DisplayName("RESP_32_test_01: Test shield total of each winner is increased when all players(except sponsor) win")
    void RESP_32_test_01() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p4 = players.get(3);
        List<Player> previousWinners = new ArrayList<>();
        previousWinners.add(p1);
        previousWinners.add(p2);
        previousWinners.add(p4);

        assertEquals(0, p1.getShields(), "P1 should not have shields yet.");
        assertEquals(0, p2.getShields(), "P2 should not have shields yet.");
        assertEquals(0, p4.getShields(), "P4 should not have shields yet.");

        main.resolveQuest(previousWinners, 3);

        assertEquals(3, p1.getShields(), "P1 should have 3 shields.");
        assertEquals(3, p2.getShields(), "P2 should have 3 shields.");
        assertEquals(3, p4.getShields(), "P4 should have 3 shields.");

    }

    @Test
    @DisplayName("RESP_32_test_02: Test shield total of each winner with different amount of shields")
    void RESP_32_test_02() {
        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p4 = players.get(3);
        List<Player> previousWinners = new ArrayList<>();
        previousWinners.add(p1);
        p1.addShields(2);
        previousWinners.add(p2);
        p2.addShields(5);
        previousWinners.add(p4);

        assertEquals(2, p1.getShields(), "P1 should have 2 shields.");
        assertEquals(5, p2.getShields(), "P2 should have 5 shields.");
        assertEquals(0, p4.getShields(), "P4 should not have shields yet.");

        main.resolveQuest(previousWinners, 2);

        assertEquals(4, p1.getShields(), "P1 should have 4 shields.");
        assertEquals(7, p2.getShields(), "P2 should have 7 shields.");
        assertEquals(2, p4.getShields(), "P4 should have 2 shields.");

    }

    @Test
    @DisplayName("RESP_32_test_03: Test no winners")
    void RESP_32_test_03() {
        List<Player> previousWinners = new ArrayList<>(); // No winners

        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);
        Player p4 = players.get(3);

        assertEquals(0, p1.getShields(), "P1 should not have shields.");
        assertEquals(0, p2.getShields(), "P2 should not have shields.");
        assertEquals(0, p3.getShields(), "P3 should not have shields.");
        assertEquals(0, p4.getShields(), "P4 should not have shields.");

        main.resolveQuest(previousWinners, 5);

        assertEquals(0, p1.getShields(), "P1 should still have no shields.");
        assertEquals(0, p2.getShields(), "P2 should still have no shields.");
        assertEquals(0, p3.getShields(), "P3 should still have no shields.");
        assertEquals(0, p4.getShields(), "P4 should still have no shields.");
    }

    @Test
    @DisplayName("RESP_33_test_01: Test Sponsor draws same number of cards + the number of stages")
    void RESP_33_test_01() {
        int sponsorIndex = 0;
        Player sponsor = players.get(sponsorIndex);

        List<List<Card>> questCards = new ArrayList<>(); // [ F5, S10 ] , [ F5, S10, H10 ]
        FoeCard f5 = new FoeCard("F5", 5);
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard h10 = new WeaponCard("H10", 10);
        questCards.add(Arrays.asList(f5, s10)); // Stage 1 cards
        questCards.add(Arrays.asList(f5, s10, h10)); // Stage 2 cards

        int stages = 2;
        String input = "";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.endQuest(sponsorIndex, questCards, stages, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        assertTrue(output.contains("[Game] Discarding card:"), "Game should output what is being discarded");
        assertEquals(5, adventureDeck.getDiscardPile().size(), "There should be 5 discarded cards.");
        assertTrue(adventureDeck.getDiscardPile().contains(f5), "Foe card F5 should be discarded.");
        assertTrue(adventureDeck.getDiscardPile().contains(s10), "Weapon card S10 should be discarded.");
        assertTrue(adventureDeck.getDiscardPile().contains(h10), "Weapon card H10 should be discarded.");
        // 5 discarded + 2 stages = 7 cards
        assertEquals(7, sponsor.getHand().size(), "Sponsor should have drawn 7 new cards.");
        assertTrue(output.contains("[Game] P1 draws a card:"), "P1 should be drawing a card");
    }

    @Test
    @DisplayName("RESP_33_test_02: Test Sponsor draws same number of cards + the number of stages and trims their hand")
    void RESP_33_test_02() {
        int sponsorIndex = 0;
        Player sponsor = players.get(sponsorIndex);
        sponsor.setHand(adventureDeck.drawMultipleCards(6));
        List<List<Card>> questCards = new ArrayList<>(); // [ F5, S10 ] , [ F5, S10, H10 ]
        FoeCard f5 = new FoeCard("F5", 5);
        WeaponCard s10 = new WeaponCard("S10", 10);
        WeaponCard h10 = new WeaponCard("H10", 10);
        questCards.add(Arrays.asList(f5, s10)); // Stage 1 cards
        questCards.add(Arrays.asList(f5, s10, h10)); // Stage 2 cards

        int stages = 2;
        String input = "0\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.endQuest(sponsorIndex, questCards, stages, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);

        // Test card discards
        assertTrue(output.contains("[Game] Discarding card:"), "Game should output what is being discarded");
        assertTrue(output.contains("[Game] P1 has more than 12 cards and needs to trim their hand."),
                "Game should detect and output that P1 has more than 12 cards.");
        assertEquals(6, adventureDeck.getDiscardPile().size(),
                "There should be 6 discarded cards. (including the one trimmed)");
        assertTrue(adventureDeck.getDiscardPile().contains(f5), "Foe card F5 should be discarded.");
        assertTrue(adventureDeck.getDiscardPile().contains(s10), "Weapon card S10 should be discarded.");
        assertTrue(adventureDeck.getDiscardPile().contains(h10), "Weapon card H10 should be discarded.");
        // 5 discarded + 2 stages = 7 cards
        assertEquals(12, sponsor.getHand().size(), "Sponsor should have 12 cards in hand.");
        assertTrue(output.contains("[Game] P1 draws a card:"), "P1 should be drawing a card");
    }

    @Test
    @DisplayName("RESP_34_test_01: Test playing of quest with no eligible participants(all players withdraw)")
    void RESP_34_test_01() {
        main.setCurrentPlayerIndex(0);
        QuestCard q2 = new QuestCard("Q2", 2); // 2 stages
        int sponsorIndex = 0; // P1 is the sponsor
        List<List<Card>> questCards = new ArrayList<>();

        questCards.add(Arrays.asList(new FoeCard("F5", 5), new WeaponCard("S10", 10)));
        questCards.add(Arrays.asList(new FoeCard("F10", 10), new WeaponCard("S10", 10)));
        questCards.add(Arrays.asList(new FoeCard("F15", 15), new WeaponCard("S10", 10)));

        String input = "withdraw\nwithdraw\nwithdraw\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.playQuest(q2, sponsorIndex, questCards, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(output.contains("[Game] No participants left for stage 1"),
                "Game should output no participants for the stage");
    }

    @Test
    @DisplayName("RESP_34_test_02: Test playing quest when one player submits no cards and loses the stage")
    void RESP_34_test_02() {
        main.setCurrentPlayerIndex(0);
        QuestCard q2 = new QuestCard("Q2", 2); // 2 stages
        int sponsorIndex = 0; // P1 is the sponsor
        List<List<Card>> questCards = new ArrayList<>();

        questCards.add(Arrays.asList(new FoeCard("F5", 5), new WeaponCard("S10", 10)));
        questCards.add(Arrays.asList(new FoeCard("F10", 10), new WeaponCard("S10", 10)));
        questCards.add(Arrays.asList(new FoeCard("F15", 15), new WeaponCard("S10", 10)));

        String input = "play\nwithdraw\nwithdraw\nquit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        main.playQuest(q2, sponsorIndex, questCards, scanner);

        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
        assertTrue(
                output.contains("[Game] P2 loses this stage and is ineligible to further participate in this quest."),
                "P2 should lose the stage");
        assertTrue(output.contains("[Game] No participants won stage 1."),
                "Game should output no participants won the stage");
    }
    @Test
    @DisplayName("A-TEST JP-Scenario: Test Compulsory scenario")
    void A_TEST_JP_SCENARIO() {
        // Start game, decks are created, hands of the 4 players are set up with random
        // cards (done by setUp())
        main.setCurrentPlayerIndex(0);

        // Rig the 4 hands to the hold the cards of the 4 posted initial hands
        // P1's Hand: F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20
        Player p1 = players.get(0);
        p1.addCardToHand(new FoeCard("F5", 5));
        p1.addCardToHand(new FoeCard("F5", 5));
        p1.addCardToHand(new FoeCard("F15", 15));
        p1.addCardToHand(new FoeCard("F15", 15));
        p1.addCardToHand(new WeaponCard("D5", 5));
        p1.addCardToHand(new WeaponCard("S10", 10));
        p1.addCardToHand(new WeaponCard("S10", 10));
        p1.addCardToHand(new WeaponCard("H10", 10));
        p1.addCardToHand(new WeaponCard("H10", 10));
        p1.addCardToHand(new WeaponCard("B15", 15));
        p1.addCardToHand(new WeaponCard("B15", 15));
        p1.addCardToHand(new WeaponCard("L20", 20));

        // Remove the cards from the adventure deck
        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("D5");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("L20");

        // P2's Hand: F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30
        Player p2 = players.get(1);
        p2.addCardToHand(new FoeCard("F5", 5));
        p2.addCardToHand(new FoeCard("F5", 5));
        p2.addCardToHand(new FoeCard("F15", 15));
        p2.addCardToHand(new FoeCard("F15", 15));
        p2.addCardToHand(new FoeCard("F40", 40));
        p2.addCardToHand(new WeaponCard("D5", 5));
        p2.addCardToHand(new WeaponCard("S10", 10));
        p2.addCardToHand(new WeaponCard("H10", 10));
        p2.addCardToHand(new WeaponCard("H10", 10));
        p2.addCardToHand(new WeaponCard("B15", 15));
        p2.addCardToHand(new WeaponCard("B15", 15));
        p2.addCardToHand(new WeaponCard("E30", 30));

        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("F40");
        adventureDeck.removeCard("D5");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("E30");

        // P3's Hand: F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, B15
        Player p3 = players.get(2);
        p3.addCardToHand(new FoeCard("F5", 5));
        p3.addCardToHand(new FoeCard("F5", 5));
        p3.addCardToHand(new FoeCard("F5", 5));
        p3.addCardToHand(new FoeCard("F15", 15));
        p3.addCardToHand(new WeaponCard("D5", 5));
        p3.addCardToHand(new WeaponCard("S10", 10));
        p3.addCardToHand(new WeaponCard("S10", 10));
        p3.addCardToHand(new WeaponCard("S10", 10));
        p3.addCardToHand(new WeaponCard("H10", 10));
        p3.addCardToHand(new WeaponCard("H10", 10));
        p3.addCardToHand(new WeaponCard("B15", 15));
        p3.addCardToHand(new WeaponCard("L20", 20));

        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("D5");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("L20");

        // P4's Hand: F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30
        Player p4 = players.get(3);
        p4.addCardToHand(new FoeCard("F5", 5));
        p4.addCardToHand(new FoeCard("F15", 15));
        p4.addCardToHand(new FoeCard("F15", 15));
        p4.addCardToHand(new FoeCard("F40", 40));
        p4.addCardToHand(new WeaponCard("D5", 5));
        p4.addCardToHand(new WeaponCard("D5", 5));
        p4.addCardToHand(new WeaponCard("S10", 10));
        p4.addCardToHand(new WeaponCard("H10", 10));
        p4.addCardToHand(new WeaponCard("H10", 10));
        p4.addCardToHand(new WeaponCard("B15", 15));
        p4.addCardToHand(new WeaponCard("L20", 20));
        p4.addCardToHand(new WeaponCard("E30", 30));

        adventureDeck.removeCard("F5");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("F15");
        adventureDeck.removeCard("F40");
        adventureDeck.removeCard("D5");
        adventureDeck.removeCard("D5");
        adventureDeck.removeCard("S10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("H10");
        adventureDeck.removeCard("B15");
        adventureDeck.removeCard("L20");
        adventureDeck.removeCard("E30");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String input = "n\ny\n0\n6\nquit\n1\n4\nquit\n1\n2\n3\nquit\n1\n2\nquit\nplay\nplay\nplay\n0\n0\n0\n4\n4\nquit\n4\n3\nquit\n3\n5\nquit\nplay\nplay\nplay\n6\n5\nquit\n8\n3\nquit\n5\n5\nquit\nplay\nplay\n9\n5\n3\nquit\n6\n4\n6\nquit\nplay\nplay\n6\n5\n5\nquit\n3\n3\n3\n4\nquit\n0\n0\n0\n0";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // P1 draws a quest of 4 stages
        QuestCard questCard = new QuestCard("Q4", 4);

        // P1 is asked but declines to sponsor
        int sponsorIndex = main.promptForSponsorship(questCard, scanner);

        // P2 is asked and sponsors and then builds the 4 stages of the quest as posted
        List<List<Card>> questCards = main.buildQuest(sponsorIndex, questCard, scanner);
        // Other players do NOT see these stages
        // questCards = [F5,H10], [F15,S10], [F15,D5, B15], [F40,B15]
        // P2's Hand: F5, H10, E30

        List<Player> previousWinners = new ArrayList<>();
        Set<Player> withdrawnPlayers = new HashSet<>();
        List<Player> eligibleParticipants;
        int stageNumber = 1;
        int index = main.getCurrentPlayerIndex();
        // Stage 1:
        eligibleParticipants = main.getEligibleParticipants(sponsorIndex, players, previousWinners, withdrawnPlayers,
                stageNumber);
        main.displayEligibleParticipants(eligibleParticipants, stageNumber);

        List<Player> participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

        // P1 is asked and decides to participate – draws an F30 – discards an F5 (to
        // trim down to 12 cards)
        p1.addCardToHand(new FoeCard("F30", 30));
        adventureDeck.removeCard("F30");
        main.trimHand(p1, scanner);

        // P3 is asked and decides to participate – draws a Sword - discards an F5 (to
        // trim down to 12 cards)
        p3.addCardToHand(new WeaponCard("S10", 10));
        adventureDeck.removeCard("S10");
        main.trimHand(p3, scanner);

        // P4 is asked and decides to participate – draws an Axe - discards an F5 (to
        // trim down to 12 cards)
        p4.addCardToHand(new WeaponCard("B15", 15));
        adventureDeck.removeCard("B15");
        main.trimHand(p4, scanner);

        // Please be sure to build all attacks with the weapons selected in the order
        // presented below
        List<List<Card>> attackingCards = new ArrayList<>();
        List<Integer> attackIndices = new ArrayList<>();

        // P1 sees their hand and builds an attack: Dagger + Sword => value of 15
        // P3 sees their hand and builds an attack: Sword + Dagger => value of 15
        // P4 sees their hand and builds an attack: Dagger + Horse => value of 15
        for (int i = 0; i <= 4; i++) {
            Player participant = players.get((index + i) % 4);
            if (participants.contains(participant) && !attackIndices.contains((index + i) % 4)) {
                List<Card> attackCards = main.setupAttack((index + i) % 4, scanner); // Player sets up attack
                attackingCards.add(attackCards);
                attackIndices.add((index + i) % 4);
            }
        }

        // Resolution: all 3 attacks are sufficient thus all participants can go onto
        // the next stage
        main.resolveAttacks(questCards, 0, attackingCards, attackIndices, previousWinners, withdrawnPlayers);

        // All 3 participants discard the cards used for their attacks
        main.discardAttackCards(attackingCards);

        // Stage 2:
        stageNumber = 2;

        eligibleParticipants = main.getEligibleParticipants(sponsorIndex, players, previousWinners, withdrawnPlayers,
                stageNumber);
        main.displayEligibleParticipants(eligibleParticipants, stageNumber);
        // P1 is asked and decides to participate. P1 draws a F10
        // P3 is asked and decides to participate. P3 draws a Lance
        // P4 is asked and decides to participate. P4 draws a Lance
        participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

        p1.addCardToHand(new FoeCard("F10", 10));
        adventureDeck.removeCard("F10");

        p3.addCardToHand(new WeaponCard("L20", 20));
        adventureDeck.removeCard("L20");

        p4.addCardToHand(new WeaponCard("L20", 20));
        adventureDeck.removeCard("L20");

        // P1 sees their hand and builds an attack: Horse + Sword => value of 20
        // P3 sees their hand and builds an attack: Axe + Sword => value of 25
        // P4 sees their hand and builds an attack: Horse + Axe=> value of 25
        attackingCards.clear();
        attackIndices.clear();
        for (int i = 0; i <= 4; i++) {
            Player participant = players.get((index + i) % 4);
            if (participants.contains(participant) && !attackIndices.contains((index + i) % 4)) {
                List<Card> attackCards = main.setupAttack((index + i) % 4, scanner); // Player sets up attack
                attackingCards.add(attackCards);
                attackIndices.add((index + i) % 4);
            }
        }

        // Resolution:
        main.resolveAttacks(questCards, stageNumber - 1, attackingCards, attackIndices, previousWinners,
                withdrawnPlayers);
        // P1’s attack is insufficient – P1 loses and cannot go to the next stage
        // P3’s and P4’s attack are sufficient go onto the next stage
        // Assert P1 has no shields and their hand is F5 F10 F15 F15 F30 Horse Axe Axe
        // Lance (displayed in this order)
        assertEquals(0, p1.getShields(), "P1 should have no shields");
        List<String> hand = new ArrayList<>();
        for (Card card : p1.getHand()) {
            hand.add(card.getName());
        }
        List<String> expectedHand = Arrays.asList("F5", "F10", "F15", "F15", "F30", "H10", "B15", "B15", "L20");
        assertEquals(expectedHand, hand, "P1 does not have the correct cards");

        // All 3 participants discard the cards used for their attacks
        main.discardAttackCards(attackingCards);
        // Stage 3:
        stageNumber = 3;
        // P3 is asked and decides to participate. P3 draws an Axe
        // P4 is asked and decides to participate. P4 draws a Sword
        eligibleParticipants = main.getEligibleParticipants(sponsorIndex, players, previousWinners, withdrawnPlayers,
                stageNumber);
        main.displayEligibleParticipants(eligibleParticipants, stageNumber);

        participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

        p3.addCardToHand(new WeaponCard("B15", 15));
        adventureDeck.removeCard("B15");

        p4.addCardToHand(new WeaponCard("S10", 10));
        adventureDeck.removeCard("S10");

        // P3 sees their hand and builds an attack: Lance + Horse + Sword => value of 40
        // P4 sees their hand and builds an attack: Axe + Sword + Lance => value of 45
        attackingCards.clear();
        attackIndices.clear();
        for (int i = 0; i <= 4; i++) {
            Player participant = players.get((index + i) % 4);
            if (participants.contains(participant) && !attackIndices.contains((index + i) % 4)) {
                List<Card> attackCards = main.setupAttack((index + i) % 4, scanner); // Player sets up attack
                attackingCards.add(attackCards);
                attackIndices.add((index + i) % 4);
            }
        }

        // Resolution: P3’s and P4’s attack are sufficient go onto the next stage
        main.resolveAttacks(questCards, stageNumber - 1, attackingCards, attackIndices, previousWinners,
                withdrawnPlayers);
        // All 2 participants discard the cards used for their attacks
        main.discardAttackCards(attackingCards);

        // Stage 4:
        stageNumber = 4;
        eligibleParticipants = main.getEligibleParticipants(sponsorIndex, players, previousWinners, withdrawnPlayers,
                stageNumber);
        main.displayEligibleParticipants(eligibleParticipants, stageNumber);
        // P3 is asked and decides to participate. P3 draws a F30
        // P4 is asked and decides to participate. P4 draws a Lance
        participants = main.promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

        p3.addCardToHand(new FoeCard("F30", 30));
        adventureDeck.removeCard("F30");

        p4.addCardToHand(new WeaponCard("L20", 20));
        adventureDeck.removeCard("L20");

        // P3 sees their hand and builds an attack: Axe + Horse + Lance=> value of 45
        // P4 sees their hand and builds an attack: Dagger + Sword + Lance + Excalibur
        // => value of 65
        attackingCards.clear();
        attackIndices.clear();
        for (int i = 0; i <= 4; i++) {
            Player participant = players.get((index + i) % 4);
            if (participants.contains(participant) && !attackIndices.contains((index + i) % 4)) {
                List<Card> attackCards = main.setupAttack((index + i) % 4, scanner); // Player sets up attack
                attackingCards.add(attackCards);
                attackIndices.add((index + i) % 4);
            }
        }

        // Resolution:
        main.resolveAttacks(questCards, stageNumber - 1, attackingCards, attackIndices, previousWinners,
                withdrawnPlayers);

        // P3’s attack is insufficient – P3 loses and receives no shields
        // P4’s attack is sufficient – P4 receives 4 shields
        main.resolveQuest(previousWinners, stageNumber);
        // All 2 participants discard the cards used for their attacks
        main.discardAttackCards(attackingCards);
        // assert P3 has no shields and has the 5 cards: F5 F5 F15 F30 Sword
        assertEquals(0, p3.getShields(), "P3 should have no shields");
        hand = new ArrayList<>();
        for (Card card : p3.getHand()) {
            hand.add(card.getName());
        }
        expectedHand = Arrays.asList("F5", "F5", "F15", "F30", "S10");
        assertEquals(expectedHand, hand, "P3 does not have the correct cards");
        // assert P4 has 4 shields and has the cards: F15 F15 F40 Lance
        assertEquals(4, p4.getShields(), "P4 should have four shields");
        hand = new ArrayList<>();
        for (Card card : p4.getHand()) {
            hand.add(card.getName());
        }
        expectedHand = Arrays.asList("F15", "F15", "F40", "L20");
        assertEquals(expectedHand, hand, "P4 does not have the correct cards");

        // P2 discards all 9 cards used in the quest and draws 9+4 = 13 random cards and
        // then
        // trims down to 12 cards. It does not matter which cards are selected to
        // discard.
        main.endQuest(sponsorIndex, questCards, questCard.getStages(), scanner);

        // assert P2 has 12 cards in hand
        String output = outputStream.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(output);
    }
}