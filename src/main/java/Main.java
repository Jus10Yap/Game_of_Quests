import cards.*;

import java.util.*;

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
            } else if (weaponNames.isEmpty()) {
                System.out.println("[Game] You must include at least one unique weapon card in the stage.");
                return false;
            }
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

    public List<List<Card>> buildQuest(int sponsorIndex, QuestCard questCard, Scanner scanner) {
        List<List<Card>> questStages = new ArrayList<>(); // List of stages, each containing a list of cards
        int numStages = questCard.getStages(); // Number of stages in the quest
        int prevStageValue = 0;

        for (int i = 1; i <= numStages; i++) {
            System.out.println("[Game] Setting up stage " + i + " of " + numStages + ".");

            List<Card> stage = buildStage(sponsorIndex, prevStageValue, i, scanner);

            if (stage.isEmpty()) {
                System.out.println("[Game] Stage setup was incomplete or failed.");
                return null; // Quest setup failed
            }

            questStages.add(stage);
            prevStageValue = calculateStageValue(stage); // Update the value of the previous stage
        }

        System.out.println("[Game] Quest setup is complete! All stages have been successfully configured.");
        return questStages;
    }

    public List<Player> getEligibleParticipants(int sponsorIndex, List<Player> players, List<Player> previousWinners, Set<Player> withdrawnPlayers, int stageNumber) {
        List<Player> eligibleParticipants = new ArrayList<>();

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

    public List<Player> promptForParticipation(List<Player> eligibleParticipants, Set<Player> withdrawnPlayers, Scanner scanner) {
        int index = getCurrentPlayerIndex();

        List<Player> participants = new ArrayList<>();

        for (int i = 0; i < NUM_PLAYERS; i++) {
            Player player = players.get((index + i) % NUM_PLAYERS);

            if (eligibleParticipants.contains(player) && !withdrawnPlayers.contains(player)) {

                String input;
                boolean validInput = false; // Flag to track valid input

                while (!validInput) {
                    String message = "[Game] " + player.getName() + ", do you want to 'Play' or 'Withdraw'?";
                    input = promptForInput(scanner, player, message).toLowerCase();

                    if (input.equals("withdraw")) {
                        withdrawnPlayers.add(player);
                        System.out.println("[Game] " + player.getName() + " has withdrawn from the quest.");
                        validInput = true;
                    } else if (input.equalsIgnoreCase("play")) {
                        participants.add(player);
                        System.out.println("[Game] " + player.getName() + " will participate in the stage.");
                        validInput = true;
                    } else {
                        System.out.println("[Game] Invalid input. Please enter 'play' or 'withdraw'.");
                    }
                }
            }
        }

        return participants; // Return the list of players who chose to play
    }

    public void handleParticipation(Player player, Scanner scanner) {
        Card drawnCard = adventureDeck.drawCard();
        player.addCardToHand(drawnCard);
        System.out.println("[Game] " + player.getName() + " draws a card: " + drawnCard.getName());

        if (player.getHand().size() > 12) {
            System.out.println("[Game] " + player.getName() + " has more than 12 cards and needs to trim their hand.");
            trimHand(player, scanner);
        }
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

    public void handleQuestCard(QuestCard questCard, Scanner scanner) {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("[Game] " + currentPlayer.getName() + " has drawn the " + questCard.getName() + " card!");

        int sponsorIndex = promptForSponsorship(questCard, scanner);
        if (sponsorIndex != -1) {
            System.out.println("[Game] Sponsor is: " + players.get(sponsorIndex).getName());
            List<List<Card>> questCards = buildQuest(sponsorIndex, questCard, scanner);

            // play the quest

        } else {
            System.out.println("[Game] All players have declined to sponsor the quest. The quest card is discarded.");
        }
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
            Scanner scanner = new Scanner(System.in);
            handleQuestCard((QuestCard) drawnCard, scanner);
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