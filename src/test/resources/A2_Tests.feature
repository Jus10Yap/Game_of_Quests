Feature: Assignment Scenarios

  Scenario: A1_scenario
    # Compulsory Scenario for Quest with 4 Stages

    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "a1" scenario
    When P1 draws a quest of 4 stages
    Then P1 declines to sponsor the quest
    And P2 accepts to sponsor the quest
    # Building Quest
    # [F5,H10], [F15,S10], [F15,D5, B15], [F40,B15]
    And P2 builds stage 1 with "F5, H10"
    And P2 builds stage 2 with "F15, S10"
    And P2 builds stage 3 with "F15, D5, B15"
    And P2 builds stage 4 with "F40, B15"
    # Playing Quest
    # Stage 1
    And Stage 1 begins and displays eligible participants
    And P1 is asked and decides to participate
    And P1 draws a "F30" and discards a "F5"
    And P3 is asked and decides to participate
    And P3 draws a "S10" and discards a "F5"
    And P4 is asked and decides to participate
    And P4 draws a "B15" and discards a "F5"
    And P1 sees their hand and builds an attack with "D5, S10"
    And P3 sees their hand and builds an attack with "S10, D5"
    And P4 sees their hand and builds an attack with "D5, H10"
    And the game resolves all attacks for stage 1
    And All participants discard the cards used for their attacks
    # Stage 2
    And Stage 2 begins and displays eligible participants
    And P1 is asked and decides to participate
    And P1 draws a "F10"
    And P3 is asked and decides to participate
    And P3 draws a "L20"
    And P4 is asked and decides to participate
    And P4 draws a "L20"
    And P1 sees their hand and builds an attack with "H10, S10"
    And P3 sees their hand and builds an attack with "B15, S10"
    And P4 sees their hand and builds an attack with "H10, B15"
    And the game resolves all attacks for stage 2
    And P1 loses the stage and has 0 shields and has the remaining cards "F5, F10, F15, F15, F30, H10, B15, B15, L20"
    And All participants discard the cards used for their attacks
    # Stage 3
    And Stage 3 begins and displays eligible participants
    And P3 is asked and decides to participate
    And P3 draws a "B15"
    And P4 is asked and decides to participate
    And P4 draws a "S10"
    And P3 sees their hand and builds an attack with "L20, H10, S10"
    And P4 sees their hand and builds an attack with "B15, S10, L20"
    And the game resolves all attacks for stage 3
    And All participants discard the cards used for their attacks
    # Stage 4
    And Stage 4 begins and displays eligible participants
    And P3 is asked and decides to participate
    And P3 draws a "F30"
    And P4 is asked and decides to participate
    And P4 draws a "L20"
    And P3 sees their hand and builds an attack with "B15, H10, L20"
    And P4 sees their hand and builds an attack with "D5, S10, L20, E30"
    And the game resolves all attacks for stage 4
    And P3 loses the stage and has 0 shields and has the remaining cards "F5, F5, F15, F30, S10"
    And P4 wins the quest and gains 4 shields and has the remaining cards "F15, F15, F40, L20"
    And All participants discard the cards used for their attacks
    # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 13 random cards and then trims down to 12 cards

  Scenario: 2winner_game_2winner_quest
#    Given the game has started with 4 players and initialized decks
#    And each player's hand is rigged for the "2 winners" scenario
#    # P1 draws a 4-stage quest and decides to sponsor it.
#    When P1 draws a quest of 4 stages
#    Then P1 accepts to sponsor the quest
#    # P1 builds 4 stages, the first of
#    # which only has a foe and no weapon.
#    And P1 builds stage 1 with "F"
#    And P1 builds stage 2 with ""
#    And P1 builds stage 3 with ""
#    And P1 builds stage 4 with ""
#    # P2, P3 and P4 participate in stage 1 and build their attack.
#    And Stage 1 begins and displays eligible participants
#    And P2 is asked and decides to participate
#    And P2 draws a ""
#    And P3 is asked and decides to participate
#    And P3 draws a ""
#    And P4 is asked and decides to participate
#    And P4 draws a ""
#    And P2 sees their hand and builds an attack with ""
#    And P3 sees their hand and builds an attack with ""
#    And P4 sees their hand and builds an attack with ""
#    # P2 and P4 have their attack win over this stage, whereas P3 loses.
#    And the game resolves all attacks for stage 1
#    And P3 loses the stage and has 0 shields and has the remaining cards ""
#    And All participants discard the cards used for their attacks
#
#    # P2 and P4 participate in and win stages 2, then 3 and then 4.
#
#    # P2 and P4 each earn 4 shields.
#    And P2 wins the quest and gains 4 shields and has the remaining cards ""
#    And P4 wins the quest and gains 4 shields and has the remaining cards ""
#    # P2 draws a 3 stage quest and declines to sponsor it. P3 sponsors this quest and builds its stages.
#    And P2 draws a quest of 3 stages
#    And P2 declines to sponsor the quest
#    And P3 accepts to sponsor the quest
#    # P1 declines to participate
#    And P1 is asked and decides not to participate
#    #  P2 and P4 participate in and win stages 1, 2 and 3.
#    And P2 is asked and decides to participate
#    And P2 draws a ""
#    And P4 is asked and decides to participate
#    And P4 draws a ""
#    #  P2 and P4 each earn 3 shields and both are declared (and asserted as) winners
#    And P2 wins the quest and gains 3 shields and has the remaining cards ""
#    And P4 wins the quest and gains 3 shields and has the remaining cards ""

  Scenario: 1winner_game_with_events
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "1 winner" scenario
    # When
    # Then

  Scenario: 0_winner_quest
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "no winners" scenario
    # P1 draws a 2 stage quest and decides to sponsor it. P1 builds 2 stages
    When P1 draws a quest of 2 stages
    Then P1 accepts to sponsor the quest
    And P1 builds stage 1 with "F50, L20"
    And P1 builds stage 2 with "F70, B15"
    # P2, P3 and P4 participate in stage 1 and all lose stage 1!
    # Stage 1
    And Stage 1 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F30" and discards a "E30"
    And P3 is asked and decides to participate
    And P3 draws a "S10" and discards a "L20"
    And P4 is asked and decides to participate
    And P4 draws a "B15" and discards a "E30"
    And P2 sees their hand and builds an attack with "D5"
    And P3 sees their hand and builds an attack with "S10, D5"
    And P4 sees their hand and builds an attack with "D5, H10"
    And the game resolves all attacks for stage 1
    And P2 loses the stage and has 0 shields and has the remaining cards "F5, F5, F15, F15, F30, F40, S10, H10, H10, B15, B15"
    And P3 loses the stage and has 0 shields and has the remaining cards "F5, F5, F5, F15, S10, S10, S10, H10, H10, B15"
    And P4 loses the stage and has 0 shields and has the remaining cards "F5, F15, F15, F40, D5, S10, H10, B15, B15, L20"
    And All participants discard the cards used for their attacks
    # The quest ends with no winner but P1 does discards and draws.
    And Sponsor discards all cards used in quest and draws 6 random cards and then trims down to 12 cards