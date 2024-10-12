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
}