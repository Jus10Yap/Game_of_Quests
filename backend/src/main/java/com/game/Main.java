package com.game;

import com.game.cards.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class Main {
    // Variables
    private Deck adventureDeck;
    private Deck eventDeck;
    private List<Player> players;
    private final int NUM_PLAYERS = 4;
    private int currentPlayerIndex;
    private boolean ongoing;
    private Card currentDrawnCard;
    private int sponsorIndex;
    List<List<Card>> questStages;
    List<Player> previousWinners;
    Set<Player> withdrawnPlayers;
    List<Player> eligibleParticipants;
    List<Player> participants;
    List<List<Card>> attackingCards;
    List<Integer> attackIndices;

    public Main() {
        setupGame();
    }

    // Initialize the game
    private void setupGame() {
        adventureDeck = new Deck(100);
        eventDeck = new Deck(17);
        currentDrawnCard = null;
        setupDecks();
        setupPlayers();
        currentPlayerIndex = -1;
        ongoing = true;
        sponsorIndex = -1;
        questStages = new ArrayList<>();
        previousWinners = new ArrayList<>();
        withdrawnPlayers = new HashSet<>();
        eligibleParticipants = new ArrayList<>();
        participants = new ArrayList<>();
        attackingCards = new ArrayList<>();
        attackIndices = new ArrayList<>();
    }

    // Functions
    public void setupDecks() {
        // Add Foe cards
        int[] foeValues = {5, 10, 15, 20, 25, 30, 35, 40, 50, 70};
        int[] foeCounts = {8, 7, 8, 7, 7, 4, 4, 2, 2, 1};
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
        adventureDeck.shuffle();
        eventDeck.shuffle();

        System.out.println("[Game] Adventure and Event Deck has been setup!");
    }

    private void a1ScenarioDecks() {
        // Rig event deck
        eventDeck.moveCardToTop("Q4");

        // Rig adventure deck
        adventureDeck.getCards().clear();

        // P1's Hand: F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // P2's Hand: F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("E30", 30));

        // P3's Hand: F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // P4's Hand: F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("E30", 30));

        // P1 draws - F30
        adventureDeck.addCard(new FoeCard("F30", 30));

        // P3 draws - S10
        adventureDeck.addCard(new WeaponCard("S10", 10));

        // P4 draws - B15
        adventureDeck.addCard(new WeaponCard("B15", 15));

        // P1 draws - F10
        adventureDeck.addCard(new FoeCard("F10", 10));

        // P3 draws - L20
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // P4 draws - L20
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // P3 draws - B15
        adventureDeck.addCard(new WeaponCard("B15", 15));

        // P4 draws - S10
        adventureDeck.addCard(new WeaponCard("S10", 10));

        // P3 draws - F30
        adventureDeck.addCard(new FoeCard("F30", 30));

        // P4 draws - L20
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // Remaining Cards
        adventureDeck.addMultipleCards(new FoeCard("F10", 10), 6); // 6 F10 cards
        adventureDeck.addCard(new FoeCard("F15",15)); // 1 F15 card
        adventureDeck.addMultipleCards(new FoeCard("F20", 20), 7); // 7 F20 cards
        adventureDeck.addMultipleCards(new FoeCard("F25", 25), 7); // 7 F25 cards
        adventureDeck.addMultipleCards(new FoeCard("F30", 30), 2); // 2 F30 cards
        adventureDeck.addMultipleCards(new FoeCard("F35", 35), 4); // 4 F35 cards
        adventureDeck.addMultipleCards(new FoeCard("F50", 50), 2); // 2 F50 cards
        adventureDeck.addCard(new FoeCard("F70",70)); // 1 F70 card

        adventureDeck.addCard(new WeaponCard("D5", 5)); // 1 D5 card
        adventureDeck.addMultipleCards(new WeaponCard("H10", 10), 4); // 4 H10 cards
        adventureDeck.addMultipleCards(new WeaponCard("S10", 10), 7); // 7 S10 cards
        System.out.println("[Game] A1_scenario Adventure and Event Deck has been setup!");
    }

    private void twoWinnersScenarioDecks() {
        // Rig event deck
        eventDeck.moveCardToTop("Q3");
        eventDeck.moveCardToTop("Q4");

        // Rig adventure deck
        adventureDeck.getCards().clear();

        // P1's Hand: 2xF5, 2xF10, 2xF15, 1 dagger, 2 horses, 2 axes, 1 lance
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // P2's Hand: 1xF40, 1xF50, 2 horses, 3 swords, 2 axes, 2 lances, 1 excalibur
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new FoeCard("F50", 50));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("E30", 30));

        // P3's Hand: 4xF5, 3 daggers, 5 horses
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        // P4's Hand:  1xF50, 1xF70, 2 horses, 3 swords, 2 axes, 2 lances, 1 excalibur
        adventureDeck.addCard(new FoeCard("F50", 50));
        adventureDeck.addCard(new FoeCard("F70", 70));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("E30", 30));
        // Q3
        // Stage 1
        // P2 draws F5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P3 draws F40
        adventureDeck.addCard(new FoeCard("F40", 40));
        // P4 draws F10
        adventureDeck.addCard(new FoeCard("F10", 10));

        // Stage 2
        // P2 draws F10
        adventureDeck.addCard(new FoeCard("F10", 10));
        // P4 draws F30
        adventureDeck.addCard(new FoeCard("F30", 30));

        // Stage 3
        // P2 draws F30
        adventureDeck.addCard(new FoeCard("F30", 30));
        // P4 draws F15
        adventureDeck.addCard(new FoeCard("F15", 15));

        // Stage 4
        // P2 draws F15
        adventureDeck.addCard(new FoeCard("F15", 15));
        // P4 draws F20
        adventureDeck.addCard(new FoeCard("F20", 20));

        // P1 draws 11 cards: 1xF5, 1xF10, 2xF15, 4xF20, 2xF25, 1xF30
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));

        // Q4
        // Stage 1
        // P2 draws dagger
        adventureDeck.addCard(new WeaponCard("D5", 5));
        // P4 draws dagger
        adventureDeck.addCard(new WeaponCard("D5", 5));

        // Stage 2
        // P2 draws F15
        adventureDeck.addCard(new FoeCard("F15", 15));
        // P4 draws F15
        adventureDeck.addCard(new FoeCard("F15", 15));

        // Stage 3
        // P2 draws F25
        adventureDeck.addCard(new FoeCard("F25", 25));
        // P4 draws F25
        adventureDeck.addCard(new FoeCard("F25", 25));

        // P3 draws 8 cards: 2xF20, 1xF25, 1xF30, 1 sword, 2 axes, 1 lance
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // Remaining cards
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
    }

    private void oneWinnersScenarioDecks() {
        // Rig event deck
        eventDeck.moveCardToTop("Q3");
        eventDeck.moveCardToTop("Queen's Favor");
        eventDeck.moveCardToTop("Prosperity");
        eventDeck.moveCardToTop("Plague");
        eventDeck.moveCardToTop("Q4");
        // Rig adventure deck
        adventureDeck.getCards().clear();

        // P1's Hand: 2xF5, 2xF10, 2xF15, 2xF20 ,4 daggers
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        // P2's Hand: 1xF25, 1xF30, 2 horses, 3 swords, 2 axes, 2 lances, 1 excalibur
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("E30", 30));
        // P3's Hand: 1xF25, 1xF30, 2 horses, 3 swords, 2 axes, 2 lances, 1 excalibur
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("E30", 30));
        // P4's Hand: 1xF25, 1xF30, 1xF70, 2 horses, 3 swords, 2 axes, 2 lances
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F70", 70));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));

        // Q4
        // Stage 1
        // P2 draws F5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P3 draws F10
        adventureDeck.addCard(new FoeCard("F10", 10));
        // P4 draws F20
        adventureDeck.addCard(new FoeCard("F20", 20));

        // Stage 2
        // P2 draws F15
        adventureDeck.addCard(new FoeCard("F15", 15));
        // P3 draws F5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P4 draws F25
        adventureDeck.addCard(new FoeCard("F25", 25));

        // Stage 3
        // P2 draws F5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P3 draws F10
        adventureDeck.addCard(new FoeCard("F10", 10));
        // P4 draws F20
        adventureDeck.addCard(new FoeCard("F20", 20));


        // Stage 4
        // P2 draws F5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P3 draws F10
        adventureDeck.addCard(new FoeCard("F10", 10));
        // P4 draws F20
        adventureDeck.addCard(new FoeCard("F20", 20));

        // P1  draws 8 cards: 2xF5, 2xF10, 4xF15
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));

        // P3 draws ‘Prosperity’: All 4 players receive 2 adventure cards
        // P1 draws 2 cards: 2xF25
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        // P2 draws 2 cards: horse, sword
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        // P3 draws 2 cards: 1 axe, 1xF40
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new FoeCard("F40", 40));
        // P4 draws 2 cards: 2 daggers
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        // P4 draws ‘Queen’s favor’ : Draws 1xF30, 1xF25
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F25", 25));

        // Q3
        // Stage 1
        // P2 draws axe
        adventureDeck.addCard(new WeaponCard("B15", 15));
        // P3 draws horse
        adventureDeck.addCard(new WeaponCard("H10", 10));
        // P4 draws F50
        adventureDeck.addCard(new FoeCard("F50", 50));

        // Stage 2
        // P2 draws: sword
        // P3 draws: sword
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));

        // Stage 3
        // P2 draws: F40
        // P3 draws: F50
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new FoeCard("F50", 50));

        // P1 draws 8 cards: 3 horses, 4 swords, 1 F35
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new FoeCard("F35", 35));
        // Remaining cards
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new WeaponCard("H10", 10));
    }

    private void noWinnersScenarioDecks() {
        // Rig event deck
        eventDeck.moveCardToTop("Q2");

        // Rig adventure deck
        adventureDeck.getCards().clear();

        // P1's Hand: 1xF50, 1xF70, 2 daggers, 2 horses, 2 swords, 2 axes, 2 lances
        adventureDeck.addCard(new FoeCard("F50", 50));
        adventureDeck.addCard(new FoeCard("F70", 70));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        // P2's Hand: 2xF5, 1xF10, 2xF15, 2xF20, 1xF25, 2xF30, 1xF40, 1 excalibur
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new WeaponCard("E30", 30));
        // P3's Hand: 2xF5, 1xF10, 2xF15, 2xF20, 2xF25, 1xF30, 1xF40, 1 lance
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F40", 40));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        // P4's Hand: 2xF5, 1xF10, 2xF15, 2xF20, 2xF25, 1xF30, 1xF50, 1 excalibur
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F30", 30));
        adventureDeck.addCard(new FoeCard("F50", 50));
        adventureDeck.addCard(new WeaponCard("E30", 30));

        // Stage 1
        // P2  draws 1xF5
        adventureDeck.addCard(new FoeCard("F5", 5));
        // P3  draws 1xF15
        adventureDeck.addCard(new FoeCard("F15", 15));
        // P4 draws 1xF10
        adventureDeck.addCard(new FoeCard("F10", 10));
        // P1 draws 14 cards: 1xF5, 1xF10, 1xF15, 4 daggers, 4 horses, 3 swords
        adventureDeck.addCard(new FoeCard("F5", 5));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F15", 15));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("D5", 5));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));

        // Remaining cards
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F10", 10));
        adventureDeck.addCard(new FoeCard("F20", 20));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F25", 25));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new FoeCard("F35", 35));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("H10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("S10", 10));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("B15", 15));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
        adventureDeck.addCard(new WeaponCard("L20", 20));
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

    // Helper method to format player data
    private Map<String, Object> getPlayerData(Player player) {
        Map<String, Object> playerData = new HashMap<>();
        playerData.put("name", player.getName());
        playerData.put("cardsCount", player.getHand().size());
        List<String> handDetails = new ArrayList<>();
        for (Card card : player.getHand()) {
            handDetails.add(card.getName());
        }
        playerData.put("hand", handDetails);
        playerData.put("shields", player.getShields());
        return playerData;
    }

    @PostMapping("/getPlayerData")
    public Map<String, Object> getPlayerData(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String playerName = (String) request.get("playerName");
        Player player = getPlayerByName(playerName);

        if (player == null) {
            response.put("error", "Player not found");
            return response;
        }
        player.displayHand();
        response.put("playerData", getPlayerData(player));

        return response;
    }

    private List<Map<String, Object>> getPlayersData() {
        List<Map<String, Object>> playersInfo = new ArrayList<>();
        for (Player player : players) {
            Map<String, Object> playerInfo = getPlayerData(player);
            playersInfo.add(playerInfo);
        }
        return playersInfo;
    }

    @GetMapping("/start")
    public String startGame() {
        setupGame();
        return "Game Started!";
    }

    @GetMapping("/a1Scenario")
    public String a1Scenario() {
        setupGame();
        a1ScenarioDecks();
        return "Game Started!";
    }

    @GetMapping("/twoWinnersScenario")
    public String twoWinnersScenario() {
        setupGame();
        twoWinnersScenarioDecks();
        return "Game Started!";
    }

    @GetMapping("/oneWinnerScenario")
    public String oneWinnerScenario() {
        setupGame();
        oneWinnersScenarioDecks();
        return "Game Started!";
    }

    @GetMapping("/noWinnerScenario")
    public String noWinnerScenario() {
        setupGame();
        noWinnersScenarioDecks();
        return "Game Started!";
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
    @PostMapping("/changeCurrentPlayer")
    public Map<String, Object> changeCurrentPlayerWeb() {

        Map<String, Object> response = new HashMap<>();
        if (currentPlayerIndex == -1) { // First round
            distributeCardsToPlayers();
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % NUM_PLAYERS;
        }
        System.out.println("[Game] Current Player is now: "+ players.get(currentPlayerIndex));
        response.put("players", getPlayersData());
        response.put("currentPlayerData", getPlayerData(players.get(currentPlayerIndex)));
        response.put("adventureDeckCount", adventureDeck.getSize());
        response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
        response.put("eventDeckCount", eventDeck.getSize());
        response.put("eventDiscardPile", eventDeck.getDiscardPile());
        return response;
    }

    public void changeCurrentPlayer() {
        if (currentPlayerIndex == -1) { // First round
            distributeCardsToPlayers();
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % NUM_PLAYERS;
        }
    }

    @PostMapping("/checkForWinners")
    public Map<String, Object> checkForWinnersWeb() {
        Map<String, Object> response = new HashMap<>();
        List<Player> winners = new ArrayList<>();
        List<String> winnerNames = new ArrayList<>();
        List<String> loserNames = new ArrayList<>();
        // Check if any player has won
        for (Player player : players) {
            if (player.hasWon()) {
                winners.add(player); // Add all players with 7 or more shields to the winners list
                winnerNames.add(player.getName());
            } else {
                loserNames.add(player.getName());
            }
        }

        if (!winners.isEmpty()) {
            System.out.println("\n\n[Game] Congratulations! Here are our Winner(s): ");
            for (Player winner : winners) {
                System.out.println(
                        "[Game] " + winner.getName() + " has won the game with " + winner.getShields() + " shields!!");
            }
            System.out.println("\n[Game] The game has ended! GG!");
            response.put("message", "yes");
            response.put("winnerNames", winnerNames);
            response.put("loserNames", loserNames);
            ongoing = false; // Stop the game
        } else {
            response.put("message", "no");
            response.put("loserNames", loserNames);
        }
        return response;
    }

    public List<String> checkForWinners() {
        List<Player> winners = new ArrayList<>();
        List<String> winnerNames = new ArrayList<>();
        // Check if any player has won
        for (Player player : players) {
            if (player.hasWon()) {
                winners.add(player); // Add all players with 7 or more shields to the winners list
                winnerNames.add(player.getName());
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
        return winnerNames;
    }

    // Handle the event card and pass the Scanner object
    @PostMapping("/handleEvent")
    public Map<String, Object> handleEventCard() {
        String eventType = currentDrawnCard.getName();
        Player currentPlayer = players.get(currentPlayerIndex);

        Map<String, Object> response = new HashMap<>();


        switch (eventType) {
            case "Plague" -> {
                System.out.println("\n[Game] " + currentPlayer.getName() + " loses 2 shields!");

                // Player loses 2 shields, ensuring shields don't go below 0
                int shieldsLost = Math.min(2, currentPlayer.getShields());
                currentPlayer.addShields(-shieldsLost); // Deduct 2 shields or whatever is available

                System.out.println(
                        "[Game] " + currentPlayer.getName() + " now has " + currentPlayer.getShields() + " shields.");

                response.put("currentPlayerData", getPlayerData(currentPlayer));
                response.put("updatedShields", currentPlayer.getShields());
            }
            case "Queen's Favor" -> {
                System.out.println("\n[Game] " + currentPlayer.getName() + " draws 2 adventure cards!");

                // Draw 2 adventure cards
                for (int i = 0; i < 2; i++) {
                    Map<String, Object> drawnCardResponse = playerDrawsCard(currentPlayer);  // Draw a card
                    response.put("card" + i, drawnCardResponse.get("drawnCardName"));
                }
                // Display player's hand after drawing
                currentPlayer.displayHand();
                response.put("adventureDeckCount", adventureDeck.getSize());
                response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
                response.put("currentPlayerData", getPlayerData(currentPlayer));
                // Check if the player needs to trim their hand
                boolean needTrim = trimPlayerHand(currentPlayer);
                response.put("needTrim", needTrim);
            }
            case "Prosperity" -> {
                System.out.println("\n[Game] All players will draw 2 adventure cards!");

                for (int i = 0; i < NUM_PLAYERS; i++) {
                    Player player = players.get(i);
                    System.out.println("\n[Game] " + player.getName() + " draws 2 adventure cards!");
                    // Draw 2 adventure cards
                    for (int j = 0; j < 2; j++) {
                        Map<String, Object> drawnCardResponse = playerDrawsCard(player);  // Draw a card
                        response.put(player.getName() + "card" + j, drawnCardResponse.get("drawnCardName"));
                    }

                    // Display player's hand after drawing
                    player.displayHand();

                    // Check if the player needs to trim their hand
                    boolean needTrim = trimPlayerHand(player);
                    response.put(player.getName() + "needTrim", needTrim);
                }
                response.put("adventureDeckCount", adventureDeck.getSize());
                response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
                response.put("currentPlayerIndex", currentPlayerIndex);
                response.put("currentPlayerData", getPlayerData(currentPlayer));
                response.put("players", getPlayersData());
            }
        }
        return response;
    }

    @PostMapping("/drawAdventureDeck")
    public Map<String, Object> drawingAdventureDeck(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String playerName = (String) request.get("playerName");
        Player player = getPlayerByName(playerName);

        if (player == null) {
            response.put("error", "Player not found");
            return response;
        }
        Map<String, Object> drawnCardResponse = playerDrawsCard(player);  // Draw a card
        response.put("drawnCard", drawnCardResponse.get("drawnCardName"));
        System.out.println("[Game] "+player.getName()+" draws "+ drawnCardResponse.get("drawnCardName"));

        response.put("players", getPlayersData());
        response.put("adventureDeckCount", adventureDeck.getSize());
        response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
        response.put("playerData", getPlayerData(player));
        response.put("currentPlayerIndex", currentPlayerIndex);
        response.put("currentPlayerData", getPlayerData(players.get(currentPlayerIndex)));
        boolean needTrim = trimPlayerHand(player);
        response.put("needTrim", needTrim);
        return response;
    }

    public void playerDrawsTwoCards(Player player, Scanner scanner) {
        System.out.println("\n[Game] " + player.getName() + " draws 2 adventure cards!");

        // Draw 2 adventure cards
        for (int i = 0; i < 2; i++) {
            playerDrawsCard(player);
        }

        // Display player's hand after drawing
        player.displayHand();

        // Check if the player needs to trim their hand
        trimPlayerHand(player, scanner);
    }

    // Handle the event card and pass the Scanner object
    public void handleEventCard(EventCard card, Scanner scanner) {
        String eventType = card.getName();
        Player currentPlayer = players.get(currentPlayerIndex);

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
                playerDrawsTwoCards(currentPlayer, scanner);
            }
            case "Prosperity" -> {
                System.out.println("\n[Game] All players will draw 2 adventure cards!");

                int index = this.currentPlayerIndex;
                for (int i = 0; i < NUM_PLAYERS; i++) {
                    Player player = players.get((index + i) % NUM_PLAYERS);
                    playerDrawsTwoCards(player, scanner);
                }
            }
        }
    }

    private Player getPlayerByName(String name) {
        for (Player player : players) {
            if (name.equalsIgnoreCase(player.getName())) {
                System.out.println("Found the player: " + player.getName());
                return player;
            }
        }
        return null;
    }

    public List<Integer> returnCardPositions(Player player, List<String> cardNames) {
        List<Card> hand = new ArrayList<>(player.getHand());
        List<Integer> positions = new ArrayList<>();

        for (String cardName : cardNames) {
            boolean cardFound = false;

            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getName().equals(cardName)) {
                    positions.add(i);
                    hand.remove(i);
                    cardFound = true;
                    break;
                }
            }

            if (!cardFound) {
                System.out.println("[Game] Card " + cardName + " not found in player's hand.");
            }
        }
        return positions;
    }

    @PostMapping("/trimHand")
    public Map<String, Object> trimHand(@RequestBody Map<String, Object> request) {
        String playerName = (String) request.get("playerName");
        List<String> cardsToDiscard = (List<String>) request.get("cardsToDiscard");
        System.out.println("PlayerName: " + playerName);
        System.out.println("cardsToDiscard received: " + cardsToDiscard);

        Player player = getPlayerByName(playerName);
        Map<String, Object> response = new HashMap<>();

        if (player == null) {
            response.put("error", "Player not found");
            return response;
        }


        List<Integer> positions = returnCardPositions(player, cardsToDiscard);
        for (int position : positions) {
            if (position >= 0 && position < player.getHand().size()) {
                Card removedCard = player.getHand().remove(position); // Remove the card at the position
                adventureDeck.discardCard(removedCard); // Add the card to the discard pile
                System.out.println("[Game] Discarded card: "+ removedCard.getName());

            } else {
                response.put("error", "Invalid card position detected");
                return response;
            }
        }

        if(player.getHand().size()>12){
            response.put("error", "Player did not trim hand to 12 or less cards");
            return response;
        }
        player.displayHand();
        response.put("adventureDeckCount", adventureDeck.getSize());
        response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
        response.put("message", "Hand trimmed successfully");
        response.put("playerData", getPlayerData(player));
        return response;
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
                if (askPlayerToSponsor(player, scanner)) {
                    this.sponsorIndex = (index + i) % NUM_PLAYERS;
                    return (index + i) % NUM_PLAYERS;
                }
            }
        }
        System.out.println("[Game] No player has chosen to sponsor the quest."); // All players said no
        return -1;
    }

    @PostMapping("/sponsorship")
    public Map<String, Object> askedPlayerToSponsor(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String playerName = (String) request.get("playerName");
        Player player = getPlayerByName(playerName);
        String res = (String) request.get("answer");

        if(player == null){
            response.put("error", "player cannot be found.");
            return response;
        }

        if (res.equals("y")) {
            setSponsorIndex(Integer.parseInt(player.getName().substring(1)) - 1);
            System.out.println("[Game] " + player.getName() + " is sponsoring the quest!");
            response.put("message", player.getName() + " is sponsoring the quest!");
        } else {
            System.out.println("[Game] " + player.getName() + " has chosen not to sponsor the quest.");
            response.put("message", player.getName() + " is not sponsoring the quest!");
        }
        return response;
    }

    public boolean askPlayerToSponsor(Player player, Scanner scanner) {
        String message = "[Game] " + player.getName() + ", do you want to sponsor the current quest? (y/n)";
        String response = promptForInput(scanner, player, message).toLowerCase();

        if (response.equals("y")) {
            System.out.println("[Game] " + player.getName() + " is sponsoring the quest!");
            return true; // Return the index of the player who said yes
        } else if (response.equals("n")) {
            System.out.println("[Game] " + player.getName() + " has chosen not to sponsor the quest.");
            return false;
        } else {
            System.out.println("[Game] Invalid input. Please enter 'y' or 'n'.");
            return askPlayerToSponsor(player, scanner);
        }
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

    public List<Card> addCardToStage(String input, int sponsorIndex, List<Card> stageCards, Scanner scanner) {
        Player sponsor = players.get(sponsorIndex);

        while (true) {
            if (input.equals("done")) {
                System.out.println("[Game] Current stage cards:");
                for (Card card : stageCards) {
                    System.out.println(card.getName());
                }
                return stageCards;
            }

            if (!isValidCardPosition(input, sponsorIndex)) {
                input = promptForInput(scanner, sponsor,
                        "[Game] Invalid input. Please enter a valid card position or press 'Done' to exit:")
                        .toLowerCase();
                continue;
            }

            int pos = Integer.parseInt(input);
            Card selectedCard = sponsor.getHand().get(pos);

            if (selectedCard == null) {
                input = promptForInput(scanner, sponsor,
                        "[Game] Selected card does not exist. Please try another position:");
                continue;
            }

            if (!isValidCardForStage(selectedCard, stageCards)) {
                System.out.println(
                        "[Game] Invalid card for this stage. Only 1 Foe card is allowed and no repeated Weapon cards per stage.");
                input = promptForInput(scanner, sponsor,
                        "[Game] Enter the position of the card you want to add to the stage or press 'Done' to exit:")
                        .toLowerCase();
                continue;
            }

            stageCards.add(selectedCard);
            sponsor.getHand().remove(pos);

            System.out.println("[Game] Sponsor has added " + stageCards.size() + " card(s) to this stage.");
            System.out.println("[Game] Card added to stage: " + selectedCard.getName());

            // Display current stage cards
            System.out.println("[Game] Current stage cards:");
            for (Card card : stageCards) {
                System.out.println(card.getName());
            }

            break;
        }

        return stageCards;
    }

    public boolean handleQuit(List<Card> stageCards, int currentStageValue, int prevStageValue, int stageNumber) {
        if (stageNumber == 1 && stageCards.isEmpty()) {
            System.out.println("[Game] A stage cannot be empty.");
            return false;
        } else if (stageNumber > 1 && currentStageValue <= prevStageValue) {
            System.out.println(
                    "[Game] Insufficient value for this stage. You must create a stage with greater value than the previous one.");
            return false;
        } else {
            boolean hasFoeCard = false;
            Set<String> weaponNames = new HashSet<>();

            for (Card card : stageCards) {
                if (card instanceof FoeCard) {
                    hasFoeCard = true;
                } else if (card instanceof WeaponCard) {
                    weaponNames.add(card.getName());
                }
            }

            if (!hasFoeCard) {
                System.out.println("[Game] You must include one foe card in the stage.");
                return false;
            }
            // else if (weaponNames.isEmpty()) {
            // System.out.println("[Game] You must include at least one unique weapon card
            // in the stage.");
            // return false;
            // }
            // Stage is valid
            System.out.println("[Game] Stage " + stageNumber + " complete. Cards used in this stage:");
            for (Card card : stageCards) {
                System.out.println(card.getName());
            }
            System.out.println("[Game] Stage " + stageNumber + " total value: " + currentStageValue);
            return true;
        }
    }

    public int calculateStageValue(List<Card> stageCards) {
        int value = 0;
        for (Card card : stageCards) {
            if (card instanceof FoeCard) {
                value += ((FoeCard) card).getValue();
            } else if (card instanceof WeaponCard) {
                value += ((WeaponCard) card).getValue();
            }
        }
        return value;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @PostMapping("/buildStage")
    public Map<String, Object> buildStage(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<>();
        String sponsorName = (String) request.get("sponsorName");
        Player sponsor = getPlayerByName(sponsorName);
        List<String> cards = (List<String>) request.get("cards");
        System.out.println("SponsorName: " + sponsorName);
        System.out.println("cards received: " + cards);

        if(sponsor == null){
            response.put("error", "no such player");
            return response;
        }
        if (cards.isEmpty()) {
            response.put("error", "No cards provided.");
            return response;
        }

        List<Integer> positions = returnCardPositions(sponsor, cards);
        if (positions.isEmpty()) {
            response.put("error", "Some cards are not in the player's hand.");
            return response;
        }

        if(sponsor!=players.get(sponsorIndex)){
            response.put("error", "player and sponsor does not match");
            return response;
        }

        List<Card> stageCards = new ArrayList<>();
        for(int position: positions){
            Card selectedCard = sponsor.getHand().get(position);
            stageCards.add(selectedCard);
            sponsor.getHand().remove(position);
        }

        int currentStageValue = calculateStageValue(stageCards);
        sponsor.displayHand();
        addStageCardsOntoQuest(stageCards);
        response.put("sponsorData", getPlayerData(sponsor));
        response.put("stageCards", stageCards);
        response.put("stageValue", currentStageValue);
        return response;

    }

    public List<Card> buildStage(int sponsorIndex, int prevStageValue, int stageNumber, Scanner scanner) {
        Player sponsor = players.get(sponsorIndex);
        System.out.println("[Game] Building stage number " + stageNumber);
        List<Card> stageCards = new ArrayList<>();
        boolean stageComplete = false;
        int currentStageValue = 0;

        while (!stageComplete) {
            sponsor.displayHand();

            String input = promptForInput(scanner, sponsor,
                    "[Game] Enter the position of the card you want to add to the stage or 'Quit' to finish: ")
                    .toLowerCase();

            if (input.equals("quit")) {
                stageComplete = handleQuit(stageCards, currentStageValue, prevStageValue, stageNumber);
            } else if (isInteger(input)) {
                stageCards = addCardToStage(input, sponsorIndex, stageCards, scanner);
                currentStageValue = calculateStageValue(stageCards);
            } else {
                System.out.println("[Game] Invalid input.");
            }
        }

        return stageCards;
    }

    public List<Card> setupStage(int sponsorIndex, int prevStageValue, int stageNumber, Scanner scanner) {
        return buildStage(sponsorIndex, prevStageValue, stageNumber, scanner); // Reuses the existing `buildStage`
        // method
    }

    public boolean confirmStageSetup(List<Card> stage) {
        return !stage.isEmpty();
    }

    public void addStageCardsOntoQuest(List<Card> stage) {
        questStages.add(stage);
    }

    public void printQuestSetupComplete() {
        if (currentDrawnCard instanceof QuestCard) {
            if (questStages.size() == ((QuestCard) currentDrawnCard).getStages()) {
                System.out.println("[Game] Quest setup is complete! All stages have been successfully configured.");
            }
        }
    }

    @PostMapping("/questInfo")
    public Map<String, Object> questInfo(){
        Map<String, Object> response = new HashMap<>();
        response.put("players", getPlayersData());
        if (currentDrawnCard instanceof QuestCard){
            QuestCard q = (QuestCard) currentDrawnCard;
            response.put("numStages", q.getStages());
        } else {
            response.put("error", "drawn card is not a quest card");
        }

        return response;

    }

    @PostMapping("/startQuest")
    public Map<String, Object> startQuest(){
        Map<String, Object> response = new HashMap<>();
        response.put("players", getPlayersData());
        response.put("currentPlayerIndex", currentPlayerIndex);
        if (currentDrawnCard instanceof QuestCard){
            QuestCard q = (QuestCard) currentDrawnCard;
            response.put("numStages", q.getStages());
        } else {
            response.put("error", "drawn card is not a quest card");
        }

        if(questStages.isEmpty()){
            response.put("error", "quest is not built");

        } else {
            response.put("questStages", getQuestStages());
            List<Integer> stageValues = new ArrayList<>();
            for(List<Card> stage: questStages){
                stageValues.add(calculateStageValue(stage));
            }
            response.put("stageValues", stageValues);
        }

        return response;

    }

    public List<List<Card>> buildQuest(int sponsorIndex, QuestCard questCard, Scanner scanner) {
        questStages = new ArrayList<>(); // List of stages, each containing a list of cards
        int numStages = questCard.getStages(); // Number of stages in the quest
        int prevStageValue = 0;

        for (int i = 1; i <= numStages; i++) {
            System.out.println("[Game] Setting up stage " + i + " of " + numStages + ".");
            List<Card> stage = setupStage(sponsorIndex, prevStageValue, i, scanner);

            if (!confirmStageSetup(stage)) {
                System.out.println("[Game] Stage setup was incomplete or failed.");
                return null;
            }

            addStageCardsOntoQuest(stage);
            prevStageValue = calculateStageValue(stage); // Update the value of the previous stage
        }

        printQuestSetupComplete();
        return questStages;
    }

    @PostMapping("/eligibleParticipants")
    public Map<String, Object> eligibleParticipants(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<>();

        int stageNumber = (int) request.get("stageNumber");
        eligibleParticipants.clear();
        participants.clear();

        if (stageNumber == 1) {
            // At the first stage, all players except the sponsor are eligible
            for (int i = 0; i < players.size(); i++) {
                if (i != sponsorIndex) {
                    eligibleParticipants.add(players.get(i));
                }
            }
        } else {
            // Subsequent stages: Only the previous stage's winners are eligible
            for (Player player : previousWinners) {
                if (!withdrawnPlayers.contains(player)) {
                    eligibleParticipants.add(player);
                }
            }
        }
        response.put("stageNumber", stageNumber);
        response.put("eligibleParticipants", eligibleParticipants);
        displayEligibleParticipants(eligibleParticipants,stageNumber);
        return response;

    }

    public List<Player> getEligibleParticipants(int sponsorIndex, List<Player> players, List<Player> previousWinners,
                                                Set<Player> withdrawnPlayers, int stageNumber) {
        eligibleParticipants = new ArrayList<>();

        if (stageNumber == 1) {
            // At the first stage, all players except the sponsor are eligible
            for (int i = 0; i < players.size(); i++) {
                if (i != sponsorIndex) {
                    eligibleParticipants.add(players.get(i));
                }
            }
        } else {
            // Subsequent stages: Only the previous stage's winners are eligible
            for (Player player : previousWinners) {
                if (!withdrawnPlayers.contains(player)) {
                    eligibleParticipants.add(player);
                }
            }
        }

        return eligibleParticipants;
    }

    public void displayEligibleParticipants(List<Player> eligibleParticipants, int stageNumber) {
        System.out.println("[Game] Eligible participants for stage " + stageNumber + ":");

        if (eligibleParticipants.isEmpty()) {
            System.out.println("[Game] There are no eligible participants for this stage.");
        } else {
            for (Player player : eligibleParticipants) {
                System.out.println(player.getName());
            }
        }
    }
    @PostMapping("/participation")
    public Map<String, Object> playerParticipation(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<>();
        String playerName = (String) request.get("playerName");
        Player player = getPlayerByName(playerName);
        String res = (String) request.get("answer");

        if(player == null){
            response.put("error", "player cannot be found.");
            return response;
        }
        System.out.println("res: "+res);

        if (res.equals("y")) {
            System.out.println("eligibleParticipants.contains(player): "+eligibleParticipants.contains(player));
            System.out.println("!withdrawnPlayers.contains(player): "+!withdrawnPlayers.contains(player));

            if (eligibleParticipants.contains(player) && !withdrawnPlayers.contains(player)) {
                participants.add(player);
                response.put("message", player.getName() + " will participate in the stage!");
                System.out.println("[Game] " + player.getName() + " will participate in the stage.");
            }
        } else if (res.equals("n")) {
            withdrawnPlayers.add(player);
            response.put("message", player.getName() + " has withdrawn from the quest.");
            System.out.println("[Game] " + player.getName() + " has withdrawn from the quest.");

        }
        return response;

    }

    public void askPlayerToParticipate(Player player, Scanner scanner) {
        if (eligibleParticipants.contains(player) && !withdrawnPlayers.contains(player)) {
            String input = promptForInput(scanner, player,
                    "[Game] " + player.getName() + ", do you want to 'Play' or 'Withdraw'?").toLowerCase();

            if (input.equals("withdraw")) {
                withdrawnPlayers.add(player);
                System.out.println("[Game] " + player.getName() + " has withdrawn from the quest.");
            } else if (input.equalsIgnoreCase("play")) {
                participants.add(player);
                System.out.println("[Game] " + player.getName() + " will participate in the stage.");
            } else {
                System.out.println("[Game] Invalid input. Please enter 'play' or 'withdraw'.");
                askPlayerToParticipate(player, scanner); // Recursive call to retry if input is invalid
            }
        }
    }

    public List<Player> promptForParticipation(List<Player> eligibleParticipants, Set<Player> withdrawnPlayers,
                                               Scanner scanner) {
        int index = getCurrentPlayerIndex();
        participants = new ArrayList<>();
        for (int i = 0; i < NUM_PLAYERS; i++) {
            Player player = players.get((index + i) % NUM_PLAYERS);

            askPlayerToParticipate(player, scanner);

        }
        return participants; // Return the list of players who chose to play
    }

    public void trimPlayerHand(Player player, Scanner scanner) {
        if (player.getHand().size() > 12) {
            System.out.println("[Game] " + player.getName() + " has more than 12 cards and needs to trim their hand.");
            trimHand(player, scanner);
        }
    }

    public boolean trimPlayerHand(Player player) {
        if (player.getHand().size() > 12) {
            System.out.println("[Game] " + player.getName() + " has more than 12 cards and needs to trim their hand.");
            return true;
        } else
            return false;
    }


    public Map<String, Object> playerDrawsCard(Player player) {
        Map<String, Object> response = new HashMap<>();
        Card drawnCard = adventureDeck.drawCard();
        player.addCardToHand(drawnCard);
        response.put("drawnCardName", drawnCard.getName());
        response.put("drawnCardValue", drawnCard.getName());
        System.out.println("[Game] " + player.getName() + " draws a card: " + drawnCard.getName());
        return response;
    }

    public void participantDrawsCard(Player player) {
        if (participants.contains(player)) {
            playerDrawsCard(player);
        }
    }

    public void handleParticipation(Player player, Scanner scanner) {
        participantDrawsCard(player);
        trimPlayerHand(player);
    }

    public boolean isValidCardForAttack(Card card, List<Card> attackCards) {
        if (card instanceof WeaponCard) {
            // No repeated weapon cards
            for (Card c : attackCards) {
                if (c.getName().equals(card.getName())) {
                    return false;
                }
            }
        } else {
            return false; // Card is not a WeaponCard
        }
        return true;
    }

    public List<Card> addCardToAttack(String input, int participantIndex, List<Card> attackCards, Scanner scanner) {
        Player participant = players.get(participantIndex);
        while (true) {
            if (input.equals("done")) {
                System.out.println("[Game] Current attack cards:");
                for (Card card : attackCards) {
                    System.out.println(card.getName());
                }
                return attackCards;
            }

            if (!isValidCardPosition(input, participantIndex)) {
                input = promptForInput(scanner, participant,
                        "[Game] Invalid input. Please enter a valid card position:").toLowerCase();
                continue;
            }

            int pos = Integer.parseInt(input);
            Card selectedCard = participant.getHand().get(pos);

            if (selectedCard == null) {
                input = promptForInput(scanner, participant,
                        "[Game] Selected card does not exist. Please try another position:");
                continue;
            }

            if (!isValidCardForAttack(selectedCard, attackCards)) {
                System.out.println("[Game] Invalid card for this stage. No repeated Weapon cards per attack.");
                input = promptForInput(scanner, participant,
                        "[Game] Enter the position of the card you want to add to the attack or press 'Done' to exit:")
                        .toLowerCase();
                continue;
            }

            attackCards.add(selectedCard);
            participant.getHand().remove(pos);

            System.out.println("[Game] " + participant.getName() + " has added " + attackCards.size()
                    + " card(s) for his/her attack.");
            System.out.println("[Game] Card added to attack: " + selectedCard.getName());

            // Display current attack cards
            System.out.println("[Game] Current attack cards:");
            for (Card card : attackCards) {
                System.out.println(card.getName());
            }

            break;
        }

        return attackCards;
    }

    public int calculateAttackStrength(List<Card> attackCards) {
        int strength = 0;
        for (Card card : attackCards) {
            if (card instanceof WeaponCard) {
                strength += ((WeaponCard) card).getValue();
            }
        }
        return strength;
    }

    @PostMapping("/buildAttack")
    public Map<String, Object> buildAttack(@RequestBody Map<String, Object> request){
        Map<String, Object> response = new HashMap<>();
        String playerName = (String) request.get("playerName");
        Player player = getPlayerByName(playerName);
        List<String> cards = (List<String>) request.get("cards");
        List<Card> attackCards = new ArrayList<>();

        System.out.println("playerName: " + playerName);
        System.out.println("cards received: " + cards);

        if(player == null){
            response.put("error", "no such player");
            return response;
        }

        int index = Integer.parseInt(player.getName().substring(1)) - 1;
        System.out.println("participants.contains(player) "+ participants.contains(player));
        System.out.println("!attackIndices.contains(index) "+!attackIndices.contains(index));
        if (participants.contains(player) && !attackIndices.contains(index)) {
            if(player==players.get(sponsorIndex)){
                response.put("error", "player is the sponsor");
                return response;
            }
            if (cards.isEmpty()) {
                System.out.println("No cards selected for attack.");
                response.put("playerData", getPlayerData(player));
                response.put("attackCards", attackCards);
                response.put("attackValue", 0);
                attackingCards.add(attackCards);
                attackIndices.add(index);
                return response;
            }

            List<Integer> positions = returnCardPositions(player, cards);
            if (positions.isEmpty()) {
                response.put("error", "Some cards are not in the player's hand.");
                return response;
            }

            for(int position: positions){
                Card selectedCard = player.getHand().get(position);
                attackCards.add(selectedCard);
                player.getHand().remove(position);
            }



            int attackValue = calculateAttackStrength(attackCards);
            attackingCards.add(attackCards);
            attackIndices.add(index);
            System.out.println("[Game] Attack complete.");
            response.put("playerData", getPlayerData(player));
            response.put("attackCards", attackCards);
            response.put("attackValue", attackValue);
        } else {
            response.put("error", "player is not a participant.");
            return response;
        }


        return response;

    }

    public List<Card> setupAttack(int participantIndex, Scanner scanner) {
        Player participant = players.get(participantIndex);
        System.out.println("[Game] " + participant.getName() + " is setting up an attack for the stage.");
        List<Card> attackCards = new ArrayList<>();
        boolean attackComplete = false;

        while (!attackComplete) {
            // Display the hand of the player
            participant.displayHand();
            // Prompt the participant for the position of the next card to include in the
            // attack or ‘quit’ (to end building this attack)
            String input = promptForInput(scanner, participant,
                    "[Game] Enter the position of the card you want to add to your attack or 'Quit' to finish: ")
                    .toLowerCase();

            if (input.equals("quit")) {
                System.out.println("[Game] Attack complete.");
                System.out.println("[Game] Total attack value: " + calculateAttackStrength(attackCards));
                System.out.println("[Game] Cards used for this attack: ");
                for (Card card : attackCards) {
                    System.out.println(card.getName());
                }
                attackComplete = true;
            } else if (isInteger(input)) {
                attackCards = addCardToAttack(input, participantIndex, attackCards, scanner);
                System.out.println("[Game] Total attack value: " + calculateAttackStrength(attackCards));
            } else {
                System.out.println("[Game] Invalid input.");
            }
        }

        return attackCards;
    }

    @PostMapping("/resolveAttacks")
    public Map<String, Object> resolveAttacks(@RequestBody Map<String, Object> request) {
        int stageIndex = (int) request.get("stageIndex");
        Map<String, Object> response = new HashMap<>();
        List<String> stageWinners = new ArrayList<>();
        List<String> stageLosers = new ArrayList<>();
        for (int i = 0; i < attackIndices.size(); i++) {
            int attackStrength = calculateAttackStrength(attackingCards.get(i));
            Player participant = players.get(attackIndices.get(i));
            System.out.println("[Game] Attack Strength: " + attackStrength + ", Stage Value: " + calculateStageValue(questStages.get(stageIndex)));

            if (attackStrength >= calculateStageValue(questStages.get(stageIndex))) {
                System.out.println("[Game] " + participant.getName() + " wins this stage!");
                if (!previousWinners.contains(participant)) {
                    previousWinners.add(participant);
                }
                stageWinners.add(participant.getName());
            } else {
                // Participants with an attack less than the value of the current stage lose and
                // become ineligible to further participate in this quest
                System.out.println("[Game] " + participant.getName()
                        + " loses this stage and is ineligible to further participate in this quest.");
                previousWinners.remove(participant);
                withdrawnPlayers.add(participant);
                stageLosers.add(participant.getName());
            }
        }

        response.put("players", getPlayersData());
        response.put("currentPlayerData", players.get(currentPlayerIndex));
        response.put("stageLosers", stageLosers);
        response.put("stageWinners", stageWinners);
        return response;
    }

    public void resolveAttacks(List<List<Card>> questCards, int stageIndex, List<List<Card>> attackingCards,
                               List<Integer> attackIndices, List<Player> previousWinners, Set<Player> withdrawnPlayers) {
        for (int i = 0; i < attackIndices.size(); i++) {
            int attackStrength = calculateAttackStrength(attackingCards.get(i));
            Player participant = players.get(attackIndices.get(i));

            if (attackStrength >= calculateStageValue(questCards.get(stageIndex))) {
                // participants with an attack equal or greater to the value of the current
                // stage are
                // eligible for the next stage (if any).
                System.out.println("[Game] " + participant.getName() + " wins this stage!");
                if (!previousWinners.contains(participant)) {
                    previousWinners.add(participant);
                }
            } else {
                // Participants with an attack less than the value of the current stage lose and
                // become ineligible to further participate in this quest
                System.out.println("[Game] " + participant.getName()
                        + " loses this stage and is ineligible to further participate in this quest.");
                previousWinners.remove(participant);
                withdrawnPlayers.add(participant);
            }
        }

    }
    @PostMapping("/discardAttackCards")
    public Map<String, Object> discardAttackCards() {
        Map<String, Object> response = new HashMap<>();
        List<String> discardedCardNames = new ArrayList<>();

        for (List<Card> attacks : attackingCards) {
            for (Card card : attacks) {
                System.out.println("[Game] Discarding card: " + card.getName());
                adventureDeck.discardCard(card);
                discardedCardNames.add(card.getName());
            }
        }

        attackingCards.clear();
        attackIndices.clear();

        response.put("message", "Cards successfully discarded");
        response.put("discardedCards", discardedCardNames);
        response.put("adventureDeckCount", adventureDeck.getSize());
        response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());

        return response;
    }


    public void discardAttackCards(List<List<Card>> attackingCards) {
        for (List<Card> attacks : attackingCards) {
            for (Card card : attacks) {
                System.out.println("[Game] Discarding card: " + card.getName());
                adventureDeck.discardCard(card);
            }
        }
        attackingCards.clear();
        attackIndices.clear();
    }

    public void resolveQuestPerPlayer(Player player, int numStages) {
        if (previousWinners.contains(player)) {
            System.out.println("[Game] "+ player.getName()+" wins the quest and gains "+numStages+" shields!");
            player.addShields(numStages);
        }
    }
    @PostMapping("/resolveQuest")
    public Map<String, Object> resolveQuest() {
        Map<String, Object> response = new HashMap<>();
        for (Player winner : previousWinners) {
            resolveQuestPerPlayer(winner, questStages.size());
        }
        response.put("questWinners", previousWinners);
        response.put("players", getPlayersData());
        response.put("numStages", questStages.size());
        response.put("currentPlayerData", getPlayerData(players.get(currentPlayerIndex)));

        previousWinners = new ArrayList<>();
        withdrawnPlayers = new HashSet<>();
        return response;
    }

    public void resolveQuest(List<Player> previousWinners, int numStages) {
        for (Player winner : previousWinners) {
            resolveQuestPerPlayer(winner, numStages);
        }
    }
    @PostMapping("/endQuest")
    public Map<String, Object> endQuest() {
        Map<String, Object> response = new HashMap<>();
        int discardedCards = 0;
        int stages = questStages.size();
        Player sponsor = players.get(sponsorIndex);
        System.out.println("[Game] The quest has ended.");
        List<String> discardedCardsList = new ArrayList<>();
        List<String> drawnCardNames = new ArrayList<>();
        // All cards used by the sponsor to build the quest are discarded
        for (List<Card> stageCards : questStages) {
            for (Card card : stageCards) {
                System.out.println("[Game] Discarding card: " + card.getName());
                adventureDeck.discardCard(card);
                discardedCardsList.add(card.getName());
                discardedCards++;
            }
        }


        questStages.clear();

        int totalCardsToDraw = discardedCards + stages;
        for (int i = 0; i < totalCardsToDraw; i++) {
            Map<String, Object> drawnCardResponse = playerDrawsCard(sponsor);
            String drawnCardName = (String) drawnCardResponse.get("drawnCardName");
            drawnCardNames.add(drawnCardName);
            response.put("card" + i, drawnCardResponse.get("drawnCardName"));
        }
        boolean needTrim = trimPlayerHand(sponsor);
        response.put("needTrim", needTrim);
        response.put("discardedCardsList", discardedCardsList);
        response.put("discardedCardsCount", discardedCards);
        response.put("adventureDeckCount", adventureDeck.getSize());
        response.put("adventureDiscardCount", adventureDeck.getDiscardPile().size());
        response.put("currentPlayerIndex", currentPlayerIndex);
        response.put("currentPlayerData", getPlayerData(players.get(currentPlayerIndex)));
        response.put("playerData", getPlayerData(sponsor));
        response.put("players", getPlayersData());
        response.put("drawnCardNames", drawnCardNames);

        this.sponsorIndex = -1;
        return response;
    }

    public void endQuest(int sponsorIndex, List<List<Card>> questCards, int stages, Scanner scanner) {
        int discardedCards = 0;
        Player sponsor = players.get(sponsorIndex);
        System.out.println("[Game] The quest has ended.");

        // All cards used by the sponsor to build the quest are discarded
        for (List<Card> stageCards : questCards) {
            for (Card card : stageCards) {
                System.out.println("[Game] Discarding card: " + card.getName());
                adventureDeck.discardCard(card);
                discardedCards++;
            }
        }

        questCards.clear();

        // Draws the same number of cards + the number of stages, and then possibly
        // trims their hand
        int totalCardsToDraw = discardedCards + stages;
        for (int i = 0; i < totalCardsToDraw; i++) {
            Card drawnCard = adventureDeck.drawCard();
            if (drawnCard != null) {
                sponsor.addCardToHand(drawnCard);
                System.out.println("[Game] " + sponsor.getName() + " draws a card: " + drawnCard.getName());
            } else {
                System.out.println("[WARNING] No card was drawn???");
                break;
            }
        }

        // Check if the sponsor's hand exceeds 12 cards and trim if necessary
        trimPlayerHand(sponsor);

        this.sponsorIndex = -1;
    }

    public void askParticipantToSetupAttack(Player participant, Scanner scanner, int index) {
        if (participants.contains(participant) && !attackIndices.contains(index)) {
            List<Card> attackCards = setupAttack(index, scanner); // Player sets up attack
            attackingCards.add(attackCards);
            attackIndices.add(index);
        }
    }

    public void playQuest(QuestCard questCard, int sponsorIndex, List<List<Card>> questCards, Scanner scanner) {
        previousWinners = new ArrayList<>();
        withdrawnPlayers = new HashSet<>();
        int stageNumber;
        int index = currentPlayerIndex;

        // Iterate through each stage of the quest
        for (int stageIndex = 0; stageIndex < questCard.getStages(); stageIndex++) {
            stageNumber = stageIndex + 1;

            // Determine and display eligible participants
            eligibleParticipants = getEligibleParticipants(sponsorIndex, players, previousWinners, withdrawnPlayers,
                    stageNumber);
            displayEligibleParticipants(eligibleParticipants, stageNumber);

            // Prompt each eligible participant whether they withdraw or continue
            List<Player> participants = promptForParticipation(eligibleParticipants, withdrawnPlayers, scanner);

            // Check if no participants remain for the current stage
            if (eligibleParticipants.isEmpty() || participants.isEmpty()) {
                System.out.println("[Game] No participants left for stage " + stageNumber + ".");
                endQuest(sponsorIndex, questCards, questCard.getStages(), scanner);
                return;
            }

            for (int i = 0; i < NUM_PLAYERS; i++) {
                Player participant = players.get((index + i) % NUM_PLAYERS);

                if (participants.contains(participant)) {
                    handleParticipation(participant, scanner); // Participant draws 1 adventure card
                }
            }

            System.out.println("[Game] Playing stage " + stageNumber);
            attackingCards = new ArrayList<>();
            attackIndices = new ArrayList<>();

            // Each participant for the current stage in turn sets up a valid attack
            for (int i = 0; i <= NUM_PLAYERS; i++) {
                Player participant = players.get((index + i) % NUM_PLAYERS);
                askParticipantToSetupAttack(participant, scanner, (index + i) % NUM_PLAYERS);
            }

            // Game resolves the attack(s) against the current stage
            resolveAttacks(questCards, stageIndex, attackingCards, attackIndices, previousWinners, withdrawnPlayers);

            // Discard all attack cards used by participants
            discardAttackCards(attackingCards);

            // If no one wins the stage, end the quest
            if (previousWinners.isEmpty()) {
                System.out.println("[Game] No participants won stage " + stageNumber + ".");
                endQuest(sponsorIndex, questCards, questCard.getStages(), scanner);
                return;
            }

            // Unless this is the last stage, the quest ends if there no eligible
            // participants for the next
            // stage. If this is the last stage, the shield total of each winner (if any) is
            // increased and the
            // quest ends.
            if (stageNumber == questCard.getStages() && !previousWinners.isEmpty()) {
                resolveQuest(previousWinners, questCard.getStages());
                endQuest(sponsorIndex, questCards, questCard.getStages(), scanner);
            }

        }
    }

    @PostMapping("/handleQuest")
    public Map<String, Object> handleQuest() {
        Map<String, Object> response = new HashMap<>();
        response.put("currentPlayerIndex", currentPlayerIndex);
        response.put("players", getPlayersData());
        return response;
    }

    public void handleQuestCard(QuestCard questCard, Scanner scanner) {
        if (questCard.getName().equals(currentDrawnCard.getName())) {
            sponsorIndex = promptForSponsorship(questCard, scanner);
            if (sponsorIndex != -1) {
                System.out.println("[Game] Sponsor is: " + players.get(sponsorIndex).getName());
                List<List<Card>> questCards = buildQuest(sponsorIndex, questCard, scanner);

                // play the quest
                playQuest(questCard, sponsorIndex, questCards, scanner);
            } else {
                System.out
                        .println("[Game] All players have declined to sponsor the quest. The quest card is discarded.");
            }
        }
    }

    @PostMapping("/drawEventCard")
    public Map<String, Object> drawCard() {
        Map<String, Object> response = new HashMap<>();

        currentDrawnCard = eventDeck.drawCard();

        String currentPlayerName = players.get(currentPlayerIndex).getName();
        System.out.println("\n[Game] " + currentPlayerName + " has drawn the " + currentDrawnCard.getName() + " card!");

        response.put("currentPlayer", currentPlayerName);
        response.put("drawnCard", currentDrawnCard.getName());
        response.put("cardType", currentDrawnCard.getClass().getSimpleName());
        response.put("eventDeckCount", eventDeck.getSize());
        response.put("discardPile", eventDeck.getDiscardPile());
        return response;
    }

    public void discardDrawnCard() {
        eventDeck.discardCard(currentDrawnCard);
        currentDrawnCard = null;
    }

    @PostMapping("/discardEventCard")
    public Map<String, Object> discardEventCard() {
        Map<String, Object> response = new HashMap<>();
        if (currentDrawnCard == null) {
            response.put("message", "no card to discard");
            return response;
        }

        eventDeck.discardCard(currentDrawnCard);
        currentDrawnCard = null;

        response.put("message", "card discarded successfully");
        response.put("eventDeckCount", eventDeck.getSize());
        response.put("eventDiscardPile", eventDeck.getDiscardPile());

        return response;
    }


    // Handle the next event or quest card
    public void handleDrawnCard() {
        if (currentDrawnCard instanceof EventCard) {
            Scanner scanner = new Scanner(System.in);
            handleEventCard();
        } else if (currentDrawnCard instanceof QuestCard) {
            Scanner scanner = new Scanner(System.in);
            handleQuestCard((QuestCard) currentDrawnCard, scanner);
        } else {
            System.out.println("\n[WARNING] Incorrect card found in event deck!!!\n");
        }
    }

    public void greetCurrentPlayer() {
        System.out.println(
                "\n[Game] Hello " + players.get(currentPlayerIndex).getName() + "! Here is your current hand:");
        players.get(currentPlayerIndex).displayHand();
    }

    public void playRound() {
        // Player takes their turn
        changeCurrentPlayer();

        // Indicate whose turn it is
        // Display current player hand in sorted order
        greetCurrentPlayer();

        // The game ‘draws’ (i.e., displays) the next event card
        drawCard();

        // Handle Event or Quest Card
        handleDrawnCard();

        // Put drawn card on discard pile
        discardDrawnCard();

        // Check for winners at the end of the round
        checkForWinners();

        // End round and move to the next player
        Scanner scanner = new Scanner(System.in);
        moveToNextPlayer(scanner);
    }

    public void playGame() {
        System.out.println("[Game] Welcome Players! Starting game..");
        while (ongoing) {
            playRound();
        }
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

    @GetMapping("/isOngoing")
    public boolean isOngoing() {
        return ongoing;
    }

    public int getNumPlayers() {
        return NUM_PLAYERS;
    }

    public int getNextPlayerIndex() {
        return (currentPlayerIndex + 1) % NUM_PLAYERS;
    }

    public Card getCurrentDrawnCard() {
        return currentDrawnCard;
    }

    public int getSponsorIndex() {
        return sponsorIndex;
    }

    public Set<Player> getWithdrawnPlayers() {
        return withdrawnPlayers;
    }

    public List<Player> getPreviousWinners() {
        return previousWinners;
    }

    public List<Player> getParticipants() {
        return participants;
    }

    public List<Player> getEligibleParticipants() {
        return eligibleParticipants;
    }

    public List<List<Card>> getQuestStages() {
        return questStages;
    }

    public List<List<Card>> getAttackingCards() {
        return attackingCards;
    }

    public List<Integer> getAttackIndices() {
        return attackIndices;
    }

    // Setters
    public void setCurrentPlayerIndex(int index) {
        currentPlayerIndex = index;
    }

    public void setCurrentDrawnCard(Card card) {
        currentDrawnCard = card;
    }

    public void setSponsorIndex(int index) {
        sponsorIndex = index;
    }

    public static void main(String[] args) {
        System.out.println("Iteration 1 of a game of quests by Justine");
        Main game = new Main();
        game.playGame();
    }
}