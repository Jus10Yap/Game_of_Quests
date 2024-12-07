const apiBaseUrl = 'http://localhost:8080'
let currentPlayer
let trimmingPlayer

// Helper
async function waitForPlayerAction (buttonId, callback) {
  return new Promise(resolve => {
    const button = document.getElementById(buttonId)
    console.log(`Setting up listener for button: ${buttonId}`)

    const onClick = async () => {
      console.log(`Button clicked: ${buttonId}`)
      button.removeEventListener('click', onClick)
      await callback()
      resolve()
    }

    button.addEventListener('click', onClick)
  })
}

function addGameMessage (message) {
  const gameLog = document.getElementById('game-log')
  const messageElement = document.createElement('p')
  messageElement.textContent = message
  gameLog.appendChild(messageElement)
  gameLog.scrollTop = gameLog.scrollHeight
}

function clearGameLog () {
  const gameLog = document.getElementById('game-log')
  gameLog.innerHTML = ''
}

function updatePlayer (playerData) {
  console.log('-- UPDATING PLAYER DATA --')
  const playerId = playerData.name.toLowerCase()
  console.log('player name:' + playerData.name.toLowerCase())

  const handElement = document.getElementById(`${playerId}-hand`)
  handElement.innerHTML = playerData.hand.join(', ')
  console.log('player hand:' + playerData.hand.join(', '))

  const shieldsElement = document.getElementById(`${playerId}-shields`)
  shieldsElement.innerHTML = playerData.shields
  console.log('player shields:' + playerData.shields)

  const cardsElement = document.getElementById(`${playerId}-cards`)
  cardsElement.innerHTML = playerData.cardsCount
  console.log('player cards count:' + playerData.cardsCount)
}

function updateCurrentPlayer (playerData) {
  console.log('-- UPDATING CURRENT PLAYER DATA --')
  const playerId = playerData.name.toLowerCase()
  currentPlayer = playerData.name.toLowerCase()
  console.log('current player name:' + currentPlayer)
  const currentPlayerHandElement = document.getElementById(
    'current-player-hand'
  )
  currentPlayerHandElement.innerHTML = ''
  console.log('current player hand:' + playerData.hand.join(', '))
  playerData.hand.forEach(cardName => {
    const listItem = document.createElement('li')
    listItem.innerText = cardName
    currentPlayerHandElement.appendChild(listItem)
  })

  const turnIndicator = document.getElementById('turn-indicator')
  turnIndicator.innerHTML = ''
  const messageElement = document.createElement('p')
  messageElement.textContent = `Hello ${currentPlayer}! Here is your current hand:`
  turnIndicator.appendChild(messageElement)

  const shieldsElement = document.getElementById(`${playerId}-shields`)
  shieldsElement.innerText = playerData.shields

  const cardsElement = document.getElementById(`${playerId}-cards`)
  cardsElement.innerText = playerData.cardsCount
}

function hideTrimHandUI () {
  console.log('-- HIDING TRIM HAND UI --')
  const trimHandDiv = document.getElementById('trim-box')
  trimHandDiv.style.display = 'none'
}

function showTrimHandUI () {
  console.log('-- SHOWING TRIM HAND UI --')
  const trimHandDiv = document.getElementById('trim-box')
  trimHandDiv.style.display = 'block'
}

function updateAdventureDeckData (adventureDeckCount, adventureDiscardCount) {
  console.log('-- UPDATING ADVENTURE DECK UI --')
  const adventureDeck = document.getElementById('adventure-deck-count')
  adventureDeck.innerHTML = ''
  adventureDeck.innerHTML = `${adventureDeckCount}`

  const adventureDiscardDeck = document.getElementById(
    'adventure-discard-deck-count'
  )
  adventureDiscardDeck.innerHTML = ''
  adventureDiscardDeck.innerHTML = `${adventureDiscardCount}`
}

function updateEventDeck (eventDeckCount, eventDiscardPile) {
  console.log('-- UPDATING EVENT DECK UI --')
  const eventDeck = document.getElementById('event-deck-count')
  eventDeck.innerHTML = ''
  eventDeck.innerHTML = `${eventDeckCount}`

  const eventDiscardPileElement = document.getElementById('event-discard-pile')
  eventDiscardPileElement.innerHTML = ''

  eventDiscardPile.forEach(card => {
    console.log(name)
    const listItem = document.createElement('li')
    listItem.textContent = card.name
    eventDiscardPileElement.appendChild(listItem)
  })
}

// Rigged Games
async function a1Scenario () {
  try {
    console.log('-- STARTING A1_scenario GAME --')
    const response = await fetch(`${apiBaseUrl}/a1Scenario`)

    const result = await response.text()

    console.log('a1Scenario Response:', result)
    clearGameLog()
    document.getElementById('button-container').style.display = 'none'
    document.getElementById('turn-indicator').style.display = 'block'
    document.getElementById('current-player-hand').style.display = 'block'
    document.querySelector('h4.current-player-hand').style.display = 'block'

    // Play rounds until the game is over
    let isOngoing = true

    while (isOngoing) {
      await playRound()

      const winnersExist = await checkForWinners()
      if (winnersExist) {
        isOngoing = false
      } else {
        const res = await fetch(`${apiBaseUrl}/isOngoing`)
        isOngoing = await res.json()
      }
    }
  } catch (error) {
    console.error('Error in a1Scenario:', error)
  }
}

async function twoWinnersScenario () {
  try {
    console.log('-- STARTING 2winner_game_2winner_quest GAME --')
    const response = await fetch(`${apiBaseUrl}/twoWinnersScenario`)

    const result = await response.text()

    console.log('twoWinnersScenario Response:', result)
    clearGameLog()
    document.getElementById('button-container').style.display = 'none'
    document.getElementById('turn-indicator').style.display = 'block'
    document.getElementById('current-player-hand').style.display = 'block'
    document.querySelector('h4.current-player-hand').style.display = 'block'

    // Play rounds until the game is over
    let isOngoing = true

    while (isOngoing) {
      await playRound()

      const winnersExist = await checkForWinners()
      if (winnersExist) {
        isOngoing = false
      } else {
        const res = await fetch(`${apiBaseUrl}/isOngoing`)
        isOngoing = await res.json()
      }
    }
  } catch (error) {
    console.error('Error in twoWinnersScenario:', error)
  }
}

async function oneWinnerScenario () {
  try {
    console.log('-- STARTING 1winner_game_with_events GAME --')
    const response = await fetch(`${apiBaseUrl}/oneWinnerScenario`)

    const result = await response.text()

    console.log('oneWinnerScenario Response:', result)
    clearGameLog()
    document.getElementById('button-container').style.display = 'none'
    document.getElementById('turn-indicator').style.display = 'block'
    document.getElementById('current-player-hand').style.display = 'block'
    document.querySelector('h4.current-player-hand').style.display = 'block'

    // Play rounds until the game is over
    let isOngoing = true

    while (isOngoing) {
      await playRound()

      const winnersExist = await checkForWinners()
      if (winnersExist) {
        isOngoing = false
      } else {
        const res = await fetch(`${apiBaseUrl}/isOngoing`)
        isOngoing = await res.json()
      }
    }
  } catch (error) {
    console.error('Error in oneWinnerScenario:', error)
  }
}

async function noWinnerScenario () {
  try {
    console.log('-- STARTING 0_winner_quest GAME --')
    const response = await fetch(`${apiBaseUrl}/noWinnerScenario`)

    const result = await response.text()

    console.log('noWinnerScenario Response:', result)
    clearGameLog()
    document.getElementById('button-container').style.display = 'none'
    document.getElementById('turn-indicator').style.display = 'block'
    document.getElementById('current-player-hand').style.display = 'block'
    document.querySelector('h4.current-player-hand').style.display = 'block'

    // Play rounds until the game is over
    let isOngoing = true

    while (isOngoing) {
      await playRound()

      const winnersExist = await checkForWinners()
      if (winnersExist) {
        isOngoing = false
      } else {
        const res = await fetch(`${apiBaseUrl}/isOngoing`)
        isOngoing = await res.json()
      }
    }
  } catch (error) {
    console.error('Error in noWinnerScenario:', error)
  }
}

// Game
async function startGame () {
  try {
    console.log('-- STARTING NEW GAME --')
    const response = await fetch(`${apiBaseUrl}/start`)

    const result = await response.text()

    console.log('Start Game Response:', result)
    clearGameLog()
    document.getElementById('button-container').style.display = 'none'
    document.getElementById('turn-indicator').style.display = 'block'
    document.getElementById('current-player-hand').style.display = 'block'
    document.querySelector('h4.current-player-hand').style.display = 'block'

    // Play rounds until the game is over
    let isOngoing = true

    while (isOngoing) {
      await playRound()

      const winnersExist = await checkForWinners()
      if (winnersExist) {
        isOngoing = false
      } else {
        const res = await fetch(`${apiBaseUrl}/isOngoing`)
        isOngoing = await res.json()
      }
    }
  } catch (error) {
    console.error('Error in startGame:', error)
  }
}

async function playRound () {
  try {
    console.log('-- STARTING ROUND --')
    hideTrimHandUI()

    // Change current player
    const response = await fetch(`${apiBaseUrl}/changeCurrentPlayer`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Change Player Response:', result)

    updateAdventureDeckData(
      result.adventureDeckCount,
      result.adventureDiscardCount
    )
    updateEventDeck(result.eventDeckCount, result.eventDiscardPile)
    updateCurrentPlayer(result.currentPlayerData)

    addGameMessage(
      `[Game] Hi ${currentPlayer.toUpperCase()}, please draw one card from the event deck`
    )

    result.players.forEach(player => {
      updatePlayer(player)
    })

    // Draw event deck
    await waitForPlayerAction('draw-event-button', drawEventCard)

    addGameMessage(
      `[Game] ${currentPlayer.toUpperCase()}, please Click the End Round button to move to the next player.`
    )
    document.getElementById('end-round-button').style.display = 'block'
    await waitForPlayerAction('end-round-button', endRound)
  } catch (error) {
    console.error('Error in playRound:', error)
  }
}

async function checkForWinners () {
  try {
    // Check for winners
    const response = await fetch(`${apiBaseUrl}/checkForWinners`, {
      method: 'POST'
    })
    const result = await response.json()

    if (result.message === 'yes') {
      addGameMessage(`[Game] Congratulations! The game is over.`)
      addGameMessage(`[Game] Winner(s): ${result.winnerNames.join(', ')}.`)
      addGameMessage(`[Game] Loser(s): ${result.loserNames.join(', ')}.`)
      console.log('Winners: ', result.winnerNames)
      console.log('Losers: ', result.loserNames)
      return true
    } else {
      addGameMessage(`[Game] No winners yet. The game continues!`)
      addGameMessage(`[Game] Non-Winner(s): ${result.loserNames.join(', ')}.`)
      console.log('No winners yet. The game continues.')
      return false
    }
  } catch (error) {
    console.error('Error in checkForWinners:', error)
  }
}

async function endRound () {
  addGameMessage(`[Game] Round ended. Moving to the next player..`)
  clearGameLog()
  document.getElementById('end-round-button').style.display = 'none'
}

async function drawEventCard () {
  try {
    const response = await fetch(`${apiBaseUrl}/drawEventCard`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Draw Event Card Response:', result)

    updateEventDeck(result.eventDeckCount, result.discardPile)

    console.log('Card drawn successfully')

    addGameMessage(
      `[Game] ${result.currentPlayer} has drawn the ${result.drawnCard} card!`
    )
    console.log(result.cardType)
    // Check card type and handle it
    if (result.cardType === 'EventCard') {
      console.log('Handling event card.')
      await handleEvent(result.drawnCard)
    } else if (result.cardType === 'QuestCard') {
      console.log('Handling quest card.')
      await handleQuest(result.drawnCard)
    }

    // Discarding drawn card
    await discardEventCard()
  } catch (error) {
    console.error('Error in drawEventCard:', error)
  }
}

async function discardEventCard () {
  try {
    const response = await fetch(`${apiBaseUrl}/discardEventCard`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Discard Event Response:', result)

    if (result.message === 'no card to discard') {
      console.log(result.message)
    } else if (result.message === 'card discarded successfully') {
      console.log(result.message)
      console.log('Event Deck Count:', result.eventDeckCount)
      console.log('Event Discard Pile:', result.eventDiscardPile)
    }

    updateEventDeck(result.eventDeckCount, result.eventDiscardPile)

    console.log('Card has been discarded successfully.')
    addGameMessage('[Game] Drawn Card has been discard.')
  } catch (error) {
    console.error('Error in discardEventCard: ', error)
  }
}

// Function to handle event
async function handleEvent (eventName) {
  try {
    const response = await fetch(`${apiBaseUrl}/handleEvent`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Handle Event Response:', result)

    if (eventName === 'Plague') {
      addGameMessage(
        `[Game] ${result.currentPlayerData.name} has drawn Plague and now has ${result.currentPlayerData.shields} shields!`
      )
      updateCurrentPlayer(result.currentPlayerData)
      updatePlayer(result.currentPlayerData)
    } else if (eventName == "Queen's Favor") {
      addGameMessage(
        `[Game] ${result.currentPlayerData.name} has drawn the Queen's Favor and draws 2 adventure cards: ${result.card0} and ${result.card1}!`
      )
      updateAdventureDeckData(
        result.adventureDeckCount,
        result.adventureDiscardCount
      )
      updateCurrentPlayer(result.currentPlayerData)
      updatePlayer(result.currentPlayerData)

      if (result.needTrim) {
        addGameMessage(
          `[Game] ${result.currentPlayerData.name}, please trim your hand to 12 cards.`
        )
        await trimHand(result.currentPlayerData)
      }
    } else if (eventName == 'Prosperity') {
      addGameMessage(
        `[Game] ${result.currentPlayerData.name} has drawn Prosperity and all players draws 2 adventure cards!`
      )
      updateAdventureDeckData(
        result.adventureDeckCount,
        result.adventureDiscardCount
      )
      updateCurrentPlayer(result.currentPlayerData)
      result.players.forEach(player => {
        updatePlayer(player)
      })

      for (let i = 0; i < 4; i++) {
        const player = result.players[i]
        console.log(player.name)
        const playerCards = [result[`P${i + 1}card0`], result[`P${i + 1}card1`]]
        const needTrim = result[`P${i + 1}needTrim`]

        addGameMessage(
          `[Game] ${player.name} has drawn cards: ${playerCards.join(
            ', '
          )}. Needs trimming: ${needTrim ? 'Yes' : 'No'}`
        )

        // If the player needs to trim their hand, call trimHand
        if (needTrim) {
          console.log('start trimming for ' + player.name)
          addGameMessage(
            `[Game] ${player.name}, please trim your hand to 12 cards.`
          )
          await trimHand(player)
        }
      }
    }
  } catch (error) {
    console.error('Error in handleEvent:', error)
  }
}

async function confirmTrim () {
  return new Promise(async (resolve, reject) => {
    try {
      const inputField = document.getElementById('discard-cards-input')
      const inputValue = inputField.value.trim()

      if (!inputValue || inputValue.split(',').length !== trimLimit) {
        addGameMessage(
          `[WARNING] Please enter exactly ${trimLimit} card names to discard.`
        )
        return
      }

      const selectedCards = inputValue
        .split(',')
        .map(card => card.trim())
        .filter(card => card !== '')

      if (selectedCards.length === 0) {
        addGameMessage('[WARNING] Please enter valid card names.')
        console.log('Please enter valid card names.')
        return
      }

      const validCardPattern = /^[A-Z]\d+$/
      for (let card of selectedCards) {
        if (!validCardPattern.test(card)) {
          addGameMessage(
            `[WARNING] Invalid card ID: ${card}. Please enter valid card IDs.`
          )
          console.log(`Invalid card ID: ${card}. Please enter valid card IDs.`)
          return
        }
      }

      addGameMessage(`[${trimmingPlayer.toUpperCase()}] ${selectedCards}`)
      console.log('selectedCards: ', selectedCards)
      console.log(trimmingPlayer)

      const response = await fetch(`${apiBaseUrl}/trimHand`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          playerName: trimmingPlayer,
          cardsToDiscard: selectedCards
        })
      })

      if (!response.ok) {
        throw new Error('Failed to trim hand. Server error.')
      }

      const result = await response.json()
      console.log('Trim Hand Response:', result)

      if (
        result.playerData.name.toLowerCase() === currentPlayer.toLowerCase()
      ) {
        updateCurrentPlayer(result.playerData)
      }
      updatePlayer(result.playerData)
      updateAdventureDeckData(
        result.adventureDeckCount,
        result.adventureDiscardCount
      )
      hideTrimHandUI()
      trimLimit = 0
      addGameMessage(
        `[Game] ${result.playerData.name} has successfully trimmed their hand to 12 cards.`
      )
      resolve()
    } catch (error) {
      console.error('Error during trimming:', error)
      addGameMessage(
        '[WARNING] An error occurred while trimming your hand. Please try again.'
      )
      reject(error)
    }
  })
}
let trimLimit = 0
async function trimHand (playerData) {
  try {
    showTrimHandUI()

    trimLimit = playerData.cardsCount - 12

    const selectionInfo = document.getElementById('selection-info')
    selectionInfo.textContent = `${playerData.name}, select ${trimLimit} cards to discard (e.g., F5,H10).`
    trimmingPlayer = playerData.name
    return new Promise((resolve, reject) => {
      const confirmButton = document.getElementById('confirm-trim')
      confirmButton.onclick = async () => {
        try {
          await confirmTrim()
          resolve()
        } catch (error) {
          reject(error)
        }
      }
    })
  } catch (error) {
    console.error('Error in trimHand:', error)
  }
}

async function handleQuest (questType) {
  try {
    const response = await fetch(`${apiBaseUrl}/handleQuest`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Handle Quest Response:', result)

    if (!result.players || result.players.length === 0) {
      console.error('No players found in the response.')
      return
    }

    let index = result.currentPlayerIndex
    let sponsorFound = false
    let sponsor
    for (let i = 0; i < 4; i++) {
      const player = result.players[(index + i) % 4]
      addGameMessage(
        `[Game] ${player.name}, do you wish to sponsor the quest? (Click Yes or No)`
      )

      const sponsorshipResult = await promptPlayerAction(player, 'sponsorship')
      if (sponsorshipResult?.message?.includes('is sponsoring')) {
        addGameMessage(
          `[Game] ${player.name} has decided to sponsor the quest!`
        )
        sponsor = player
        sponsorFound = true
        break
      } else {
        addGameMessage(`[Game] ${player.name} declined to sponsor the quest.`)
      }
    }

    if (!sponsorFound) {
      addGameMessage('[Game] No one decided to sponsor the quest.')
      return
    }

    await buildQuest(sponsor)

    await playQuest(sponsor)
  } catch (error) {
    console.error('Error in handleQuest:', error)
  }
}

async function promptPlayerAction (player, actionType) {
  try {
    document.getElementById('button-container').style.display = 'block'

    return new Promise((resolve, reject) => {
      const yesButton = document.getElementById('yes-button')
      const noButton = document.getElementById('no-button')

      const handleResponse = async response => {
        document.getElementById('button-container').style.display = 'none'

        try {
          const endpoint =
            actionType === 'sponsorship' ? '/sponsorship' : '/participation'
          addGameMessage(`[${player.name}] ${response}`)
          const result = await fetch(`${apiBaseUrl}${endpoint}`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              playerName: player.name,
              answer: response
            })
          })
          const data = await result.json()
          console.log('promptPlayerAction Response:', data)
          resolve(data)
        } catch (error) {
          console.error('Error sending promptPlayerAction response:', error)
          reject(error)
        }
      }

      yesButton.onclick = () => handleResponse('y')
      noButton.onclick = () => handleResponse('n')
    })
  } catch (error) {
    console.error('Error in promptPlayerAction: ', error)
  }
}

async function buildQuest (player) {
  try {
    const response = await fetch(`${apiBaseUrl}/questInfo`, { method: 'POST' })
    const result = await response.json()
    console.log('Build Quest Response:', result)

    let prevStageValue = 0

    for (let i = 0; i < result.numStages; i++) {
      let stageValue = await buildStage(player, prevStageValue, i + 1)
      prevStageValue = stageValue
    }

    addGameMessage(
      `[Game] ${player.name} has successfully built Quest of ${result.numStages} stages!`
    )
  } catch (error) {
    console.error('Error in buildQuest: ', error)
    addGameMessage('[WARNING] An error occurred while building the quest.')
  }
}

async function buildStage (player, prevStageValue, stageNumber) {
  return new Promise((resolve, reject) => {
    try {
      document.getElementById('build-stage').style.display = 'block'
      addGameMessage(`[Game] Building stage number ${stageNumber}`)

      const buildStageButton = document.getElementById('confirm-build-stage')
      buildStageButton.onclick = async () => {
        try {
          const inputField = document.getElementById('build-stage-input')
          const inputValue = inputField.value.trim()

          if (!inputValue) {
            addGameMessage(
              `[WARNING] Please enter one foe card and/or unique weapon cards to build a stage.`
            )
            return
          }

          const selectedCards = inputValue
            .split(',')
            .map(card => card.trim())
            .filter(card => card !== '')

          if (selectedCards.length === 0) {
            addGameMessage('[WARNING] Please enter valid card names.')
            console.log('Please enter valid card names.')
            return
          }

          const sponsorHand = player.hand
          let totalStageValue = 0

          for (let card of selectedCards) {
            if (!sponsorHand.includes(card)) {
              addGameMessage(
                `[WARNING] The card "${card}" is not in your hand.`
              )
              console.log(`Invalid card: ${card}`)
              return
            }
            totalStageValue += parseInt(card.slice(1))
          }

          if (totalStageValue <= prevStageValue) {
            addGameMessage(
              `[WARNING] The total stage value must be greater than the previous stage.`
            )
            return
          }

          const validCardPattern = /^[A-Z]\d+$/
          for (let card of selectedCards) {
            if (!validCardPattern.test(card)) {
              addGameMessage(
                `[WARNING] Invalid card ID: ${card}. Please enter valid card IDs.`
              )
              console.log(
                `Invalid card ID: ${card}. Please enter valid card IDs.`
              )
              return
            }
          }

          const foeCards = selectedCards.filter(card => card.startsWith('F'))
          const weaponCards = selectedCards.filter(
            card => !card.startsWith('F')
          )

          if (foeCards.length !== 1) {
            addGameMessage(
              `[WARNING] A stage must include exactly one Foe card.`
            )
            return
          }

          const uniqueWeapons = new Set(weaponCards)
          if (weaponCards.length !== uniqueWeapons.size) {
            addGameMessage(
              `[WARNING] Weapon cards in the stage must be unique.`
            )
            return
          }

          addGameMessage(`[${player.name.toUpperCase()}] ${selectedCards}`)
          console.log('selectedCards: ', selectedCards)

          const response = await fetch(`${apiBaseUrl}/buildStage`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              sponsorName: player.name,
              cards: selectedCards,
              stageNumber: stageNumber
            })
          })
          const result = await response.json()
          console.log('Build Stage Response:', result)
          addGameMessage(
            `[Game] ${result.sponsorData.name} has successfully built stage ${stageNumber}`
          )
          addGameMessage(`[Game] Stage ${stageNumber} Information`)
          addGameMessage(`[Game] Stage Value: ${result.stageValue}`)
          addGameMessage(`[Game] Stage Cards: ${selectedCards}`)

          updatePlayer(result.sponsorData)
          if (
            result.sponsorData.name.toLowerCase() ===
            currentPlayer.toLowerCase()
          ) {
            updateCurrentPlayer(result.sponsorData)
          }

          document.getElementById('build-stage').style.display = 'none'
          resolve(result.stageValue)
        } catch (error) {
          console.error('Error in buildStage: ', error)
          addGameMessage(
            '[WARNING] An error occurred while building the stage.'
          )
          reject(error)
        }
      }
    } catch (error) {
      console.error('Error in buildStage: ', error)
      addGameMessage(
        "[WARNING] An error occurred while waiting for the player's confirmation."
      )
      reject(error)
    }
  })
}

async function drawAdventureDeck (player) {
  try {
    const response = await fetch(`${apiBaseUrl}/drawAdventureDeck`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ playerName: player.name })
    })
    const result = await response.json()
    console.log('Draw adventure deck Response:', result)
    addGameMessage(
      `[Game] ${result.playerData.name} has drawn ${result.drawnCard}.`
    )

    updateAdventureDeckData(
      result.adventureDeckCount,
      result.adventureDiscardCount
    )
    updateCurrentPlayer(result.currentPlayerData)
    result.players.forEach(player => {
      updatePlayer(player)
    })

    if (result.needTrim) {
      console.log('start trimming for ' + result.playerData.name)
      addGameMessage(
        `[Game] ${result.playerData.name}, please trim your hand to 12 cards.`
      )
      await trimHand(result.playerData)
    }
  } catch (error) {
    console.error('Error in drawAdventureDeck: ', error)
  }
}

async function discardAttackCards () {
  try {
    addGameMessage(`[Game] Discarding card all cards used for attacks.`)
    const response = await fetch(`${apiBaseUrl}/discardAttackCards`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('discard attack cards Response:', result)
    result.discardedCards.forEach(card => {
      addGameMessage(`[Game] Discarding card: ${card}`)
    })
    updateAdventureDeckData(
      result.adventureDeckCount,
      result.adventureDiscardCount
    )
  } catch (error) {
    console.error('Error in discardAttackCards: ', error)
  }
}

async function resolveQuest () {
  try {
    const response = await fetch(`${apiBaseUrl}/resolveQuest`, {
      method: 'POST'
    })
    const result = await response.json()
    console.log('Resolve Quest Response:', result)
    updateCurrentPlayer(result.currentPlayerData)
    for (const player of result.questWinners) {
      addGameMessage(
        `[Game] ${player.name} wins the quest and gains ${result.numStages} shields!`
      )
    }
    result.players.forEach(player => {
      updatePlayer(player)
      addGameMessage(`[Game] ${player.name} now has ${player.shields} shields!`)
      if (player.name.toLowerCase() === currentPlayer.toLowerCase()) {
        updateCurrentPlayer(player)
      }
    })
  } catch (error) {
    console.error('Error in resolveQuest: ', error)
  }
}

async function endQuest () {
  try {
    const response = await fetch(`${apiBaseUrl}/endQuest`, { method: 'POST' })
    const result = await response.json()
    console.log('End Quest Response:', result)
    addGameMessage(
      `[Game] ${
        result.playerData.name
      } has discarded cards: ${result.discardedCardsList.join(', ')}`
    )
    addGameMessage(
      `[Game] ${
        result.playerData.name
      } has drawn cards: ${result.drawnCardNames.join(', ')}`
    )

    updateAdventureDeckData(
      result.adventureDeckCount,
      result.adventureDiscardCount
    )
    updateCurrentPlayer(result.currentPlayerData)
    result.players.forEach(player => {
      updatePlayer(player)
    })

    if (result.needTrim) {
      console.log('start trimming for ' + result.playerData.name)
      addGameMessage(
        `[Game] ${result.playerData.name}, please trim your hand to 12 cards.`
      )
      await trimHand(result.playerData)
    }

    addGameMessage(`[Game] Quest has ended!`)
  } catch (error) {
    console.error('Error in endQuest: ', error)
  }
}

async function fetchUpdatedPlayerData (player) {
  try {
    const response = await fetch(`${apiBaseUrl}/getPlayerData`, {
      method: 'POST',
      body: JSON.stringify({ playerName: player.name }),
      headers: { 'Content-Type': 'application/json' }
    })

    const result = await response.json()
    console.log('fetchUpdatedPlayerData Response:', result)

    return result.playerData
  } catch (error) {
    console.error('Error in fetchUpdatedPlayerData:', error)
    return null
  }
}

async function playQuest (sponsorData) {
  try {
    const response = await fetch(`${apiBaseUrl}/startQuest`, { method: 'POST' })
    const result = await response.json()
    console.log('Start Quest Response:', result)

    for (let i = 0; i < result.numStages; i++) {
      const stageNumber = i + 1
      addGameMessage(`[Game] Starting stage ${stageNumber}..`)
      // display eligible players
      const eligibleParticipants = await displayEligibleParticipants(
        stageNumber
      )

      if (!eligibleParticipants || eligibleParticipants.length === 0) {
        console.log('No eligible participants.')
        addGameMessage(
          `[Game] There are no eligible participants for this stage.`
        )
        break
      }

      // Prompt participation

      let participatingPlayers = []
      for (let i = 0; i < 4; i++) {
        const player = result.players[i]
        const updatedPlayer = await fetchUpdatedPlayerData(player)
        updatePlayer(updatedPlayer)
        if (updatedPlayer.name.toLowerCase() === currentPlayer.toLowerCase()) {
          updateCurrentPlayer(updatedPlayer)
        }
        if (
          player.name !== sponsorData.name &&
          eligibleParticipants.some(p => p.name === player.name)
        ) {
          addGameMessage(
            `[Game] ${player.name}, Do you wish to participate in stage ${stageNumber}? (Click yes or no)`
          )
          const participationResult = await promptPlayerAction(
            player,
            'participation'
          )
          if (
            participationResult?.message?.includes(
              'will participate in the stage'
            )
          ) {
            addGameMessage(
              `[Game] ${player.name} will participate in the stage!`
            )
            participatingPlayers.push(player)
          } else if (
            participationResult?.message?.includes(
              'has withdrawn from the quest.'
            )
          ) {
            addGameMessage(
              `[Game] ${player.name} has withdrawn from the quest.`
            )
          }
        }
      }

      if (participatingPlayers.length === 0) {
        addGameMessage(
          '[Game] No players have chosen to participate in this stage.'
        )
        console.log('No participants for the stage.')
        return
      }

      // Each participants draws 1 adventure card and possibly trims hand
      for (let player of participatingPlayers) {
        await drawAdventureDeck(player)
      }

      // Each participants builds attack
      for (let player of participatingPlayers) {
        const updatedPlayer = await fetchUpdatedPlayerData(player)
        player = updatedPlayer
        updatePlayer(updatedPlayer)
        if (updatedPlayer.name.toLowerCase() === currentPlayer.toLowerCase()) {
          updateCurrentPlayer(updatedPlayer)
        }
        let stageValue = await buildAttack(player, stageNumber)
      }

      // resolve attacks
      await resolveAttacks(i)

      await discardAttackCards()
    }
    // ending quest and display winners if there is
    await resolveQuest()

    // resolve quest and sponsor draws cards and possible trims hand
    await endQuest()

    // check if there are winners (end game if yes)
    const winnersExist = await checkForWinners()
    if (winnersExist) {
      console.log('Winners detected at the end of the quest.')
      return
    }
  } catch (error) {
    console.error('Error in playQuest: ', error)
  }
}

async function resolveAttacks (stageIndex) {
  try {
    console.log('resolving attacks.')
    const response = await fetch(`${apiBaseUrl}/resolveAttacks`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        stageIndex: stageIndex
      })
    })
    const result = await response.json()
    console.log('Resolve Attacks Response:', result)

    updateCurrentPlayer(result.currentPlayerData)
    result.players.forEach(player => {
      updatePlayer(player)
    })

    result.stageWinners.forEach(name => {
      addGameMessage(`[Game] ${name} wins stage ${stageIndex + 1}!`)
    })

    result.stageLosers.forEach(name => {
      addGameMessage(
        `[Game] ${name} loses stage ${
          stageIndex + 1
        } and is ineligible to participate further.`
      )
    })
  } catch (error) {
    console.error('Error in resolveAttacks: ', error)
  }
}

async function buildAttack (player, stageNumber) {
  return new Promise((resolve, reject) => {
    try {
      console.log('build attack.')
      document.getElementById('build-attack').style.display = 'block'
      addGameMessage(
        `[Game] ${player.name} is building attack for stage ${stageNumber}`
      )

      const buildAttackButton = document.getElementById('confirm-attack-stage')
      buildAttackButton.onclick = async () => {
        try {
          const inputField = document.getElementById('build-attack-input')
          const inputValue = inputField.value.trim()
          if (inputValue === '') {
            addGameMessage(
              `[${player.name.toUpperCase()}] chose not to play any cards.`
            )
            console.log('No cards selected.')
            document.getElementById('build-attack').style.display = 'none'

            // Send an empty attack to the server
            const response = await fetch(`${apiBaseUrl}/buildAttack`, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                playerName: player.name,
                cards: [],
                stageNumber: stageNumber
              })
            })

            const result = await response.json()
            console.log('Build Attack Response for Empty Input:', result)
            addGameMessage(
              `[Game] ${result.playerData.name} has successfully skipped their attack for stage ${stageNumber}`
            )
            updatePlayer(result.playerData)
            if (
              result.playerData.name.toLowerCase() ===
              currentPlayer.toLowerCase()
            ) {
              updateCurrentPlayer(result.playerData)
            }

            resolve(0)
            return
          }
          const selectedCards = inputValue
            .split(',')
            .map(card => card.trim())
            .filter(card => card !== '')

          const playerHand = player.hand

          for (let card of selectedCards) {
            if (!playerHand.includes(card)) {
              addGameMessage(
                `[WARNING] The card "${card}" is not in your hand.`
              )
              console.log(`Invalid card: ${card}`)
              return
            }
          }

          const validCardPattern = /^[A-Z]\d+$/
          for (let card of selectedCards) {
            if (!validCardPattern.test(card)) {
              addGameMessage(
                `[WARNING] Invalid card ID: ${card}. Please enter valid card IDs.`
              )
              console.log(
                `Invalid card ID: ${card}. Please enter valid card IDs.`
              )
              return
            }
          }

          const foeCards = selectedCards.filter(card => card.startsWith('F'))
          const weaponCards = selectedCards.filter(
            card => !card.startsWith('F')
          )

          if (foeCards.length !== 0) {
            addGameMessage(`[WARNING] An attack cannot have a Foe Card`)
            return
          }

          const uniqueWeapons = new Set(weaponCards)
          if (weaponCards.length !== uniqueWeapons.size) {
            addGameMessage(
              `[WARNING] Weapon cards in the stage must be unique.`
            )
            return
          }

          addGameMessage(`[${player.name.toUpperCase()}] ${selectedCards}`)
          console.log('selectedCards: ', selectedCards)

          const response = await fetch(`${apiBaseUrl}/buildAttack`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              playerName: player.name,
              cards: selectedCards,
              stageNumber: stageNumber
            })
          })
          const result = await response.json()
          console.log('Build Attack Response:', result)
          addGameMessage(
            `[Game] ${result.playerData.name} has successfully built attack for stage ${stageNumber}`
          )
          addGameMessage(`[Game] ${result.playerData.name} Attack Information:`)
          addGameMessage(`[Game] Attack Value: ${result.attackValue}`)
          addGameMessage(`[Game] Attack Cards: ${selectedCards}`)

          updatePlayer(result.playerData)
          if (
            result.playerData.name.toLowerCase() === currentPlayer.toLowerCase()
          ) {
            updateCurrentPlayer(result.playerData)
          }

          document.getElementById('build-attack').style.display = 'none'
          resolve(result.attackValue)
        } catch (error) {
          console.error('Error in buildAttack: ', error)
          addGameMessage(
            '[WARNING] An error occurred while building the attack.'
          )
          reject(error)
        }
      }
    } catch (error) {
      console.error('Error in buildAttack: ', error)
      addGameMessage(
        "[WARNING] An error occurred while waiting for the player's confirmation."
      )
      reject(error)
    }
  })
}

async function displayEligibleParticipants (stageNumber) {
  try {
    console.log('display eligible participants')
    const response = await fetch(`${apiBaseUrl}/eligibleParticipants`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ stageNumber: stageNumber })
    })
    const result = await response.json()
    console.log('Display Eligible Participants Response:', result)
    addGameMessage(
      `[Game] here are the list of eligible participants for stage ${stageNumber}:`
    )
    for (const player of result.eligibleParticipants) {
      addGameMessage(`${player.name}`)
    }
    return result.eligibleParticipants
  } catch (error) {
    console.error('Error in displayEligibleParticipants: ', error)
    return []
  }
}
