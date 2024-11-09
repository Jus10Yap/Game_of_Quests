import cards.Card;
import cards.FoeCard;
import cards.QuestCard;
import cards.WeaponCard;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameSteps {
    private Main game;

    public GameSteps() {}

    @Given("the game has started with 4 players and initialized decks")
    public void the_game_has_started_with_4_players_and_initialized_decks() {
        game = new Main();
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

    @When("P{int} draws a quest of {int} stages")
    public void player_draws_quest_of_stages(int playerNumber, int numStages) {
        game.changeCurrentPlayer();
        game.greetCurrentPlayer();
        game.drawCard();
    }


    @Then("P{int} declines to sponsor the quest")
    public void player_declines_to_sponsor_the_quest(int playerNumber) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.askPlayerToSponsor(player, scannerResponse("n"));
    }

    @Then("P{int} accepts to sponsor the quest")
    public void player_accepts_to_sponsor_the_quest(int playerNumber) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.askPlayerToSponsor(player, scannerResponse("y"));
        game.setSponsorIndex(playerNumber - 1);
    }

    public String returnCardPositions(Player player, List<String> cardNames) {
        List<Card> hand = new ArrayList<>(player.getHand());
        StringBuilder positions = new StringBuilder();

        for (String cardName : cardNames) {
            boolean cardFound = false;

            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getName().equals(cardName)) {
                    positions.append(i).append("\n");
                    hand.remove(i);
                    cardFound = true;
                    break;
                }
            }

            if (!cardFound) {
                System.out.println("[Cucumber] Card " + cardName + " not found in player's hand.");
            }
        }
        positions.append("quit\n");
        return positions.toString().trim();
    }


    @And("P{int} builds stage {int} with {string}")
    public void player_builds_stage_with_cards(int playerNumber, int stageNumber, String cardList) {
        Player player = game.getPlayers().get(playerNumber - 1);
        List<String> cards = Arrays.asList(cardList.split(", "));

        String positions = returnCardPositions(player, cards);

        int prevStageValue = 0;
        if (!game.getQuestStages().isEmpty()) {
            prevStageValue = game.calculateStageValue(game.getQuestStages().getLast());
        }
        List<Card> stageCards = game.setupStage(game.getSponsorIndex(), prevStageValue,stageNumber, scannerResponse(positions));
        game.addStageCardsOntoQuest(stageCards);
        game.printQuestSetupComplete();
    }

    @And("Stage {int} begins and displays eligible participants")
    public void stage_begins(int stageNumber) {
        List<Player> eligibleParticipants = game.getEligibleParticipants(game.getSponsorIndex(), game.getPlayers(), game.getPreviousWinners(), game.getWithdrawnPlayers(), stageNumber);
        game.displayEligibleParticipants(eligibleParticipants, stageNumber);
    }

    @And("P{int} is asked and decides to participate")
    public void player_decides_to_participate_stage(int playerNumber) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.askPlayerToParticipate(player,scannerResponse("play"));
    }

    @And("P{int} is asked and decides not to participate")
    public void player_decides_not_to_participate_stage(int playerNumber) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.askPlayerToParticipate(player,scannerResponse("withdraw"));
    }

    @And("P{int} draws a {string} and discards a {string}")
    public void player_draws_and_discards_card(int playerNumber, String cardDrawn, String cardDiscarded) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.participantDrawsCard(player);
        List<String> cardPosition = Arrays.asList(cardDiscarded);
        game.trimPlayerHand(player, scannerResponse(returnCardPositions(player,cardPosition)));

    }

    @And("P{int} draws a {string}")
    public void player_draws_card(int playerNumber, String cardDrawn) {
        Player player = game.getPlayers().get(playerNumber - 1);
        game.participantDrawsCard(player);
    }

    @And("P{int} sees their hand and builds an attack with {string}")
    public void player_sees_hand_and_builds_attack(int playerNumber, String cardList) {
        Player player = game.getPlayers().get(playerNumber - 1);
        List<String> cards = Arrays.asList(cardList.split(", "));

        String positions = returnCardPositions(player, cards);
        game.askParticipantToSetupAttack(player,scannerResponse(positions), playerNumber-1);
    }

    @And("the game resolves all attacks for stage {int}")
    public void game_resolves_attacks_for_stage(int stageNumber) {
        game.resolveAttacks(game.getQuestStages(), stageNumber-1, game.getAttackingCards(), game.getAttackIndices(), game.getPreviousWinners(), game.getWithdrawnPlayers());
    }

    @And("All participants discard the cards used for their attacks")
    public void participants_discard_cards_used_for_attacks(){
        game.discardAttackCards(game.getAttackingCards());
    }

    @And("P{int} loses the stage and has {int} shields and has the remaining cards {string}")
    public void player_loses_stage_and_has_no_shields_and_these_cards(int playerNumber, int shields, String cardList) {
        Player player = game.getPlayers().get(playerNumber - 1);
        System.out.println("[Cucumber] "+ player.getName() + " has "+player.getShields() + " shields");
        List<String> expectedHand = Arrays.asList(cardList.split(", "));
        List<String> hand = new ArrayList<>();
        System.out.println("[Cucumber] "+ player.getName() + " hand has: ");
        for (Card card : player.getHand()) {
            System.out.println("[Cucumber] "+card.getName());
            hand.add(card.getName());
        }
        assertTrue("Player should have "+ shields + " shields",player.getShields()==shields);
        assertEquals(expectedHand, hand, "Player does not have the correct cards");
        assertEquals(expectedHand.size(),hand.size(), "Player does not have the correct number of cards");
    }

    @And("P{int} wins the quest and gains {int} shields and has the remaining cards {string}")
    public void player_wins_the_quest_and_gains_blank_shields_and_these_cards(int playerNumber, int shieldsWon, String cardList) {
        Player player = game.getPlayers().get(playerNumber - 1);
        int initialShields = player.getShields();
        System.out.println("[Cucumber] "+ player.getName() + " initially has "+ initialShields + " shields");
        game.resolveQuestPerPlayer(player, game.getQuestStages().size());

        int shieldsGained = player.getShields() - initialShields;

        System.out.println("[Cucumber] "+ player.getName() + " now has "+ player.getShields() + " shields");
        List<String> expectedHand = Arrays.asList(cardList.split(", "));
        List<String> hand = new ArrayList<>();
        System.out.println("[Cucumber] "+ player.getName() + " hand has: ");
        for (Card card : player.getHand()) {
            System.out.println("[Cucumber] "+card.getName());
            hand.add(card.getName());
        }
        assertEquals(shieldsGained, shieldsWon, "Player should have gained" + shieldsWon + " shields");
        assertEquals(expectedHand, hand, "Player does not have the correct cards");
    }

    @And("Sponsor discards all cards used in quest and draws {int} random cards and then trims down to {int} cards")
    public void sponsor_discard_and_draws_cards(int numberOfCardsDrawn, int totalCardsLeft) {
        Player sponsor =  game.getPlayers().get(game.getSponsorIndex());
        int amountToTrim = sponsor.getHand().size() + numberOfCardsDrawn - totalCardsLeft;
        StringBuilder trimmerPositions = new StringBuilder();
        for(int i=0; i<amountToTrim; i++){
            trimmerPositions.append("0\n");
        }
        game.endQuest(game.getSponsorIndex(), game.getQuestStages(), game.getQuestStages().size(), scannerResponse(trimmerPositions.toString()));

        assertTrue("All cards used for quest should be discarded.", game.getQuestStages().isEmpty());
        assertEquals(sponsor.getHand().size(), totalCardsLeft, "Sponsor should have "+ totalCardsLeft + " cards left");
        game.discardDrawnCard();
    }

    @And("the round ends")
    public void round_ends() {
        game.moveToNextPlayer(scannerResponse("\n"));
    }

    @And("P{int} should have {int} shields in total")
    public void player_should_have_blank_shields(int playerNumber, int shields) {
        Player player = game.getPlayers().get(playerNumber-1);
        assertEquals(shields, player.getShields(), "Number of shields don't match");
    }

    @And("{string} wins the game")
    public void player_wins_the_game(String playersList) {
        List<String> expectedWinners = Arrays.asList(playersList.split(", "));
        List<String> actualWinners = game.checkForWinners();

        assertEquals(expectedWinners, actualWinners, "Winners don't match");
    }


    private void a1Scenario() {
        // Rig event deck
        game.getEventDeck().moveCardToTop("Q4");

        // Rig adventure deck
        game.getAdventureDeck().getCards().clear();

        // P1's Hand: F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P2's Hand: F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P3's Hand: F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P4's Hand: F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P1 draws - F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P3 draws - S10
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));

        // P4 draws - B15
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        // P1 draws - F10
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));

        // P3 draws - L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P4 draws - L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P3 draws - B15
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        // P4 draws - S10
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));

        // P3 draws - F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P4 draws - L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // Remaining Cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F10", 10), 6); // 6 F10 cards
        game.getAdventureDeck().addCard(new FoeCard("F15",15)); // 1 F15 card
        game.getAdventureDeck().addMultipleCards(new FoeCard("F20", 20), 7); // 7 F20 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F25", 25), 7); // 7 F25 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F30", 30), 2); // 2 F30 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F35", 35), 4); // 4 F35 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F50", 50), 2); // 2 F50 cards
        game.getAdventureDeck().addCard(new FoeCard("F70",70)); // 1 F70 card

        game.getAdventureDeck().addCard(new WeaponCard("D5", 5)); // 1 D5 card
        game.getAdventureDeck().addMultipleCards(new WeaponCard("H10", 10), 4); // 4 H10 cards
        game.getAdventureDeck().addMultipleCards(new WeaponCard("S10", 10), 7); // 7 S10 cards
    }
    private void twoWinnersScenario() {
        // Rig event deck
        game.getEventDeck().moveCardToTop("Q3");
        game.getEventDeck().moveCardToTop("Q4");

        // Rig adventure deck
        game.getAdventureDeck().getCards().clear();

        // P1's Hand: F5, F5, F5, F15, F25, S10, S10, H10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));


        // P2's Hand: F5, F15, F20, F20, D5, S10, S10, H10, H10, B15, B15, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P3's Hand: F5, F10, F10, F15, F20, F20, F25, H10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P4's Hand:  F5, F5, F10, F10, F20, F25, F70, S10, S10, S10, B15, B15
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F70", 70));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        // Stage 1
        // P2 draws L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P3 draws F5
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));

        // P4 draws D5
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));

        // Stage 2
        // P2 draws D5
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));

        // P4 draws D5
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));

        // Stage 3
        // P2 draws L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        // P4 draws E30
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // Stage 4
        // P2 draws F25
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        // P4 draws L20
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));


        // P1 draws 12 cards
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));

        // P2 draws F25
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));

        // P4 draws F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P2 draws F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P4 draws F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P2 draws F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P4 draws F35
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));

        game.getAdventureDeck().addCard(new FoeCard("F35", 35));
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));

        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));

        game.getAdventureDeck().addCard(new FoeCard("F50", 50));
        game.getAdventureDeck().addCard(new FoeCard("F50", 50));

        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));

        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));

        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));

        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
    }
    private void oneWinnersScenario() {
        // Rig event deck
        game.getEventDeck().moveCardToTop("Q3");
        game.getEventDeck().moveCardToTop("Queen's Favor");
        game.getEventDeck().moveCardToTop("Prosperity");
        game.getEventDeck().moveCardToTop("Plague");
        game.getEventDeck().moveCardToTop("Q4");

        // Rig adventure deck
        game.getAdventureDeck().getCards().clear();

        // P1's Hand: F5, F5, F5, F15, F25, S10, S10, H10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));


        // P2's Hand: F5, F15, F20, F20, D5, S10, S10, H10, H10, B15, B15, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P3's Hand: F5, F10, F10, F15, F20, F20, F25, H10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P4's Hand:  F5, F5, F10, F10, F20, F25, F70, S10, S10, S10, B15, B15
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F70", 70));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        // P2 draws L20

        // P3 draws F5
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));

        // P4 draws D5
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));

        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));

        game.getAdventureDeck().addCard(new FoeCard("F20", 20));
        game.getAdventureDeck().addCard(new FoeCard("F20", 20));

        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));
        game.getAdventureDeck().addCard(new FoeCard("F25", 25));

        game.getAdventureDeck().addCard(new FoeCard("F30", 30));
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        game.getAdventureDeck().addCard(new FoeCard("F35", 35));
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));
        game.getAdventureDeck().addCard(new FoeCard("F35", 35));

        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));

        game.getAdventureDeck().addCard(new FoeCard("F50", 50));
        game.getAdventureDeck().addCard(new FoeCard("F50", 50));

        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));

        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));

        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));

        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));


        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

    }

    private void noWinnersScenario() {
        // Rig event deck
        game.getEventDeck().moveCardToTop("Q2");

        // Rig adventure deck
        game.getAdventureDeck().getCards().clear();

        // P1's Hand: F5, F5, F50, F70, D5, S10, S10, H10, H10, B15, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F50", 50));
        game.getAdventureDeck().addCard(new FoeCard("F70", 70));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P2's Hand: F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P3's Hand: F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));

        // P4's Hand: F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30
        game.getAdventureDeck().addCard(new FoeCard("F5", 5));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F15", 15));
        game.getAdventureDeck().addCard(new FoeCard("F40", 40));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("D5", 5));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("H10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("E30", 30));

        // P2 draws - F30
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // P3 draws - S10
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));

        // P4 draws - B15
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));

        // P1 draws after quest
        game.getAdventureDeck().addCard(new FoeCard("F10", 10));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addCard(new WeaponCard("B15", 15));
        game.getAdventureDeck().addCard(new WeaponCard("S10", 10));
        game.getAdventureDeck().addCard(new FoeCard("F30", 30));

        // Remaining Cards
        game.getAdventureDeck().addCard(new WeaponCard("L20", 20));
        game.getAdventureDeck().addMultipleCards(new FoeCard("F10", 10), 6); // 6 F10 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F15",15), 3); // 3 F15 card
        game.getAdventureDeck().addMultipleCards(new FoeCard("F20", 20), 7); // 7 F20 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F25", 25), 7); // 7 F25 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F30", 30), 2); // 2 F30 cards
        game.getAdventureDeck().addMultipleCards(new FoeCard("F35", 35), 4); // 4 F35 cards

        game.getAdventureDeck().addCard(new FoeCard("F50",50)); // 1 F50 card

        game.getAdventureDeck().addCard(new WeaponCard("D5", 5)); // 1 D5 card
        game.getAdventureDeck().addMultipleCards(new WeaponCard("H10", 10), 4); // 4 H10 cards
        game.getAdventureDeck().addMultipleCards(new WeaponCard("S10", 10), 7); // 7 S10 cards

    }

    private Scanner scannerResponse(String response) {
        return new Scanner(new ByteArrayInputStream(response.getBytes()));
    }
}
