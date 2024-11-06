Feature: Assignment Scenarios

  Scenario: A1_scenario
    # Compulsory Scenario for Quest with 4 Stages

    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "a1" scenario
    When "P1" draws a quest of 4 stages
    Then "P1" declines to sponsor the quest
    And "P2" accepts to sponsor the quest
    # Stage 1
    # Stage 2
    # Stage 3
    # Stage 4
    # Post-Quest Sponsorship
    # Then

  Scenario: 2winner_game_2winner_quest
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "2 winners" scenario
    # When
    # Then

  Scenario: 1winner_game_with_events
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "1 winner" scenario
    # When
    # Then

  Scenario: 0_winner_quest
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "no winners" scenario
    # When "P1" draws a quest of "2" stages
    # And "P1" sponsors a quest
    # And Sponsor builds "2" stages
    # Then