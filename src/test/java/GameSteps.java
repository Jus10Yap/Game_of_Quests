import cards.Card;
import cards.FoeCard;
import cards.QuestCard;
import cards.WeaponCard;
import io.cucumber.java.en.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameSteps {
    private Main game;

    public GameSteps() {}

    @Given("the game has started with 4 players and initialized decks")
    public void the_game_has_started_with_4_players_and_initialized_decks() {
        game = new Main();
        game = Main.getInstance();
    }

    @Given("each player's hand is rigged for the {string} scenario")
    public void each_players_hand_is_rigged_for_scenario(String scenario) {
        switch(scenario) {
            case "a1":
                a1Scenario();
                break;
            case "2 winners":
                twoWinnersScenario();
                break;
            case "1 winner":
                oneWinnersScenario();
                break;
            case "no winners":
                noWinnersScenario();
                break;
            default:
                System.out.println("[WARNING] No such scenario.");
        }
    }

    @When("{string} draws a quest of {int} stages")
    public void player_draws_quest_of_stages(String name, int numStages) {
        Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
        assertEquals("Current player does not match with player name", player.getName(), name);

        QuestCard questCard = new QuestCard("Q"+numStages, numStages);
        game.setCurrentDrawnCard(questCard);
        game.getEventDeck().removeCard(questCard.getName());

        assertEquals(numStages, questCard.getStages());
    }


    @Then("{string} declines to sponsor the quest")
    public void player_declines_to_sponsor_the_quest(String playerName) {
        Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
        assertEquals(playerName, player.getName());


        String response = "n";

        game.setCurrentPlayerIndex((game.getCurrentPlayerIndex() + 1) % 4); // Move to the next player

    }

    @Then("{string} accepts to sponsor the quest")
    public void player_accepts_to_sponsor_the_quest(String playerName) {
        Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
        assertEquals(playerName, player.getName());

        // Simulate the response for accepting sponsorship
        String response = "y"; // Simulating input as 'y'
        if (response.equals("y")) {
            System.out.println("[Game] " + playerName + " is sponsoring the quest!");
            game.setSponsorIndex(game.getCurrentPlayerIndex());
            // Additional logic to handle the sponsorship setup can go here
        }
    }


    private void a1Scenario() {
        game.getEventDeck().moveCardToTop("Q4");

        // Rig the 4 hands to the hold the cards of the 4 posted initial hands
        // P1's Hand: F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20
        Player p1 = game.getPlayers().get(0);
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
        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("D5");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("L20");

        // P2's Hand: F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30
        Player p2 = game.getPlayers().get(1);
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

        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("F40");
        game.getAdventureDeck().removeCard("D5");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("E30");

        // P3's Hand: F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, B15
        Player p3 = game.getPlayers().get(2);
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

        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("D5");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("L20");

        // P4's Hand: F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30
        Player p4 = game.getPlayers().get(3);
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

        game.getAdventureDeck().removeCard("F5");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("F15");
        game.getAdventureDeck().removeCard("F40");
        game.getAdventureDeck().removeCard("D5");
        game.getAdventureDeck().removeCard("D5");
        game.getAdventureDeck().removeCard("S10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("H10");
        game.getAdventureDeck().removeCard("B15");
        game.getAdventureDeck().removeCard("L20");
        game.getAdventureDeck().removeCard("E30");


    }
    private void twoWinnersScenario() {

    }
    private void oneWinnersScenario() {

    }

    private void noWinnersScenario() {

    }
}
