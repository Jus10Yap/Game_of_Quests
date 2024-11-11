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
    And P1 should have 0 shields in total
    And P1 should have 9 cards on their hand
    And P3 should have 0 shields in total
    And P3 should have 5 cards on their hand
    And P4 should have 4 shields in total
    And P4 should have 4 cards on their hand
    And All participants discard the cards used for their attacks
    # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 13 random cards and then trims down to 12 cards

  Scenario: 2winner_game_2winner_quest
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "2 winners" scenario
    # P1 draws a 4-stage quest and decides to sponsor it.
    When P1 draws a quest of 4 stages
    Then P1 accepts to sponsor the quest
    # P1 builds 4 stages, the first of
    # which only has a foe and no weapon.
    And P1 builds stage 1 with "F5"
    And P1 builds stage 2 with "F5, S10"
    And P1 builds stage 3 with "F5, S10, H10"
    And P1 builds stage 4 with "F15, B15"
    # P2, P3 and P4 participate in stage 1 and build their attack.
    And Stage 1 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "L20" and discards a "F5"
    And P3 is asked and decides to participate
    And P3 draws a "F5" and discards a "F25"
    And P4 is asked and decides to participate
    And P4 draws a "D5" and discards a "F70"
    And P2 sees their hand and builds an attack with "D5"
    And P3 sees their hand and builds an attack with ""
    And P4 sees their hand and builds an attack with "D5"
    # P2 and P4 have their attack win over this stage, whereas P3 loses.
    And the game resolves all attacks for stage 1
    And P3 loses the stage and has 0 shields and has the remaining cards "F5, F5, F10, F10, F15, F20, F20, H10, H10, H10, B15, L20"
    And All participants discard the cards used for their attacks
    # P2 and P4 participate in and win stages 2, then 3 and then 4.
    # Stage 2
    And Stage 2 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "D5"
    And P4 is asked and decides to participate
    And P4 draws a "D5"
    And P2 sees their hand and builds an attack with "D5, S10"
    And P4 sees their hand and builds an attack with "D5, S10"
    And the game resolves all attacks for stage 2
    And All participants discard the cards used for their attacks
    # Stage 3
    And Stage 3 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "L20"
    And P4 is asked and decides to participate
    And P4 draws a "E30"
    And P2 sees their hand and builds an attack with "H10, B15"
    And P4 sees their hand and builds an attack with "S10, B15"
    And the game resolves all attacks for stage 3
    And All participants discard the cards used for their attacks
    # Stage 4
    And Stage 4 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F25"
    And P4 is asked and decides to participate
    And P4 draws a "L20"
    And P2 sees their hand and builds an attack with "E30"
    And P4 sees their hand and builds an attack with "E30"
    And the game resolves all attacks for stage 4
     # P2 and P4 each earn 4 shields.
    And P2 wins the quest and gains 4 shields and has the remaining cards "F15, F20, F20, F25, S10, H10, B15, L20, L20"
    And P2 should have 4 shields in total
    And P4 wins the quest and gains 4 shields and has the remaining cards "F5, F5, F10, F10, F20, F25, S10, B15, L20"
    And P4 should have 4 shields in total
    And All participants discard the cards used for their attacks
    # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 12 random cards and then trims down to 12 cards
    And the round ends
    # P2 draws a 3 stage quest and declines to sponsor it. P3 sponsors this quest and builds its stages.
    And P2 draws a quest of 3 stages
    And P2 declines to sponsor the quest
    And P3 accepts to sponsor the quest
    And P3 builds stage 1 with "F5"
    And P3 builds stage 2 with "F10"
    And P3 builds stage 3 with "F15"
    # Stage 1
    And Stage 1 begins and displays eligible participants
    # P1 declines to participate
    And P1 is asked and decides not to participate
    #  P2 and P4 participate in and win stages 1, 2 and 3.
    And P2 is asked and decides to participate
    And P2 draws a "F25"
    And P4 is asked and decides to participate
    And P4 draws a "F30"
    And P2 sees their hand and builds an attack with "S10"
    And P4 sees their hand and builds an attack with "S10"
    And the game resolves all attacks for stage 1
    And All participants discard the cards used for their attacks
    # Stage 2
    And Stage 2 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F30"
    And P4 is asked and decides to participate
    And P4 draws a "F30"
    And P2 sees their hand and builds an attack with "H10"
    And P4 sees their hand and builds an attack with "B15"
    And the game resolves all attacks for stage 2
    And All participants discard the cards used for their attacks
    # Stage 3
    And Stage 3 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F30"
    And P4 is asked and decides to participate
    And P4 draws a "F35"
    And P2 sees their hand and builds an attack with "B15"
    And P4 sees their hand and builds an attack with "L20"
    And the game resolves all attacks for stage 3
    #  P2 and P4 each earn 3 shields and both are declared (and asserted as) winners
    And P2 wins the quest and gains 3 shields and has the remaining cards "F15, F20, F20, F25, F25, F30, F30, L20, L20"
    And P2 should have 7 shields in total
    And P4 wins the quest and gains 3 shields and has the remaining cards "F5, F5, F10, F10, F20, F25, F30, F30, F35"
    And P4 should have 7 shields in total
    And All participants discard the cards used for their attacks
     # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 6 random cards and then trims down to 12 cards
    And "P2, P4" wins the game


  Scenario: 1winner_game_with_events
    Given the game has started with 4 players and initialized decks
    And each player's hand is rigged for the "1 winner" scenario
    #  P1 draws a 4 stage quest and decides to sponsor it. P1 builds 4 stages
    When P1 draws a quest of 4 stages
    Then P1 accepts to sponsor the quest
    And P1 builds stage 1 with "F5"
    And P1 builds stage 2 with "F10"
    And P1 builds stage 3 with "F15"
    And P1 builds stage 4 with "F20"
    #  P2, P3 and P4 participate in and win all stages.
    # Stage 1
    And Stage 1 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F50" and discards a "F20"
    And P3 is asked and decides to participate
    And P3 draws a "D5" and discards a "F25"
    And P4 is asked and decides to participate
    And P4 draws a "D5" and discards a "F25"
    And P2 sees their hand and builds an attack with "D5"
    And P3 sees their hand and builds an attack with "D5"
    And P4 sees their hand and builds an attack with "D5"
    And the game resolves all attacks for stage 1
    And All participants discard the cards used for their attacks
    # Stage 2
    And Stage 2 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "L20"
    And P3 is asked and decides to participate
    And P3 draws a "S10"
    And P4 is asked and decides to participate
    And P4 draws a "S10"
    And P2 sees their hand and builds an attack with "S10"
    And P3 sees their hand and builds an attack with "S10"
    And P4 sees their hand and builds an attack with "S10"
    And the game resolves all attacks for stage 2
    And All participants discard the cards used for their attacks
     # Stage 3
    And Stage 3 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "D5"
    And P3 is asked and decides to participate
    And P3 draws a "D5"
    And P4 is asked and decides to participate
    And P4 draws a "D5"
    And P2 sees their hand and builds an attack with "B15"
    And P3 sees their hand and builds an attack with "B15"
    And P4 sees their hand and builds an attack with "B15"
    And the game resolves all attacks for stage 3
    And All participants discard the cards used for their attacks
    # Stage 4
    And Stage 4 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "L20"
    And P3 is asked and decides to participate
    And P3 draws a "L20"
    And P4 is asked and decides to participate
    And P4 draws a "L20"
    And P2 sees their hand and builds an attack with "L20"
    And P3 sees their hand and builds an attack with "L20"
    And P4 sees their hand and builds an attack with "L20"
    And the game resolves all attacks for stage 4
     #  P2, P3 and P4 each earn 4 shields
    And P2 wins the quest and gains 4 shields and has the remaining cards "F5, F15, F20, F50, D5, S10, H10, H10, B15, L20, E30"
    And P2 should have 4 shields in total
    And P3 wins the quest and gains 4 shields and has the remaining cards "F5, F10, F10, F15, F20, F20, D5, H10, H10, H10, L20"
    And P3 should have 4 shields in total
    And P4 wins the quest and gains 4 shields and has the remaining cards "F5, F5, F5, F15, F30, F30, S10, S10, H10, H10, H10"
    And P4 should have 4 shields in total
    And All participants discard the cards used for their attacks
     # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 8 random cards and then trims down to 12 cards
    And the round ends
    #  P2 draws ‘Plague’ and loses 2 shields
    And P2 draws ‘Plague’ and loses 2 shields
    And the round ends
    #  P3 draws ‘Prosperity’: All 4 players receive 2 adventure cards
    And P3 draws ‘Prosperity’ and all players receive 2 adventure cards
    And the round ends
    #  P4 draws ‘Queen’s favor’ and thus draws 2 adventure cards
    And P4 draws ‘Queen’s favor’ and receives 2 adventure cards
    And the round ends
    #  P1 draws a 3 stage quest and decides to sponsor it. P1 builds 3 stages
    When P1 draws a quest of 3 stages
    Then P1 accepts to sponsor the quest
    And P1 builds stage 1 with "F15"
    And P1 builds stage 2 with "F20"
    And P1 builds stage 3 with "F25"
    #  P2, P3 and P4 participate in stage 1. P2 and P3 win, whereas P4 loses.
    # Stage 1
    And Stage 1 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "B15" and discards a "F50"
    And P3 is asked and decides to participate
    And P3 draws a "B15" and discards a "F10"
    And P4 is asked and decides to participate
    And P4 draws a "F30" and discards a "F25"
    And P2 sees their hand and builds an attack with "D5, S10"
    And P3 sees their hand and builds an attack with "D5, S10"
    And P4 sees their hand and builds an attack with "D5"
    And the game resolves all attacks for stage 1
    And P4 loses the stage and has 4 shields and has the remaining cards "F15, F25, F25, F25, F30, F30, F30, S10, S10, H10, H10, H10"
    And All participants discard the cards used for their attacks
    #  P2 and P3 participate in and win stages 2 and 3
    # Stage 2
    And Stage 2 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "D5"
    And P3 is asked and decides to participate
    And P3 draws a "E30"
    And P2 sees their hand and builds an attack with "H10, S10"
    And P3 sees their hand and builds an attack with "S10, H10"
    And the game resolves all attacks for stage 2
    And All participants discard the cards used for their attacks
     # Stage 3
    And Stage 3 begins and displays eligible participants
    And P2 is asked and decides to participate
    And P2 draws a "F30"
    And P3 is asked and decides to participate
    And P3 draws a "F35"
    And P2 sees their hand and builds an attack with "D5, L20"
    And P3 sees their hand and builds an attack with "B15, L20, E30"
    And the game resolves all attacks for stage 3
    #  P2 and P3 earn 3 shields: P3 is declared (and asserted as) the winner
    And P2 wins the quest and gains 3 shields and has the remaining cards "F15, F20, F30, S10, H10, B15, B15, E30"
    And P2 should have 5 shields in total
    And P3 wins the quest and gains 3 shields and has the remaining cards "F10, F15, F20, F20, F35, H10, H10"
    And P3 should have 7 shields in total
    And P4 should have 4 shields in total
    And All participants discard the cards used for their attacks
     # Post-Quest Sponsorship
    And Sponsor discards all cards used in quest and draws 6 random cards and then trims down to 12 cards
    And "P3" wins the game

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
    And P2 should have 0 shields in total
    And P2 should have 11 cards on their hand
    And P3 loses the stage and has 0 shields and has the remaining cards "F5, F5, F5, F15, S10, S10, S10, H10, H10, B15"
    And P3 should have 0 shields in total
    And P3 should have 10 cards on their hand
    And P4 loses the stage and has 0 shields and has the remaining cards "F5, F15, F15, F40, D5, S10, H10, B15, B15, L20"
    And P4 should have 0 shields in total
    And P4 should have 10 cards on their hand
    And All participants discard the cards used for their attacks
    # The quest ends with no winner but P1 does discards and draws.
    And Sponsor discards all cards used in quest and draws 6 random cards and then trims down to 12 cards
    And the round ends