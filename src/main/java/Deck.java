import cards.*;
import java.util.*;

public class Deck {
    // Variables
    private List<Card> cards; // Unused cards
    private final int MAX_NUM_CARDS;
    private List<Card> discardPile; // Discarded cards

    public Deck(int maxCards) {
        cards = new ArrayList<>();
        discardPile = new ArrayList<>();
        MAX_NUM_CARDS = maxCards;
    }

    // Adds card to the deck
    public void addCard(Card card) {
        if (cards.size() >= MAX_NUM_CARDS) {
            System.out.println("Cannot add card: deck is full.");
            return;
        }
        cards.add(card);
    }

    // Adds multiple cards to the deck
    public void addMultipleCards(Card card, int count) {
        for (int i = 0; i < count; i++) {
            addCard(card); // Use the existing addCard method
        }
    }

    // Discards a card to the discard pile
    public void discardCard(Card card) {
        discardPile.add(card);
    }

    // Draws a card from the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            reshuffleDiscardPile(); // Shuffle discard pile back into deck if empty
        }
        if (!cards.isEmpty()) {
            return cards.removeFirst(); // Draw the top card
        }

        return null; // No cards left to draw
    }

    // Reshuffles discard pile into the main deck
    public void reshuffleDiscardPile() {
        if (!discardPile.isEmpty()) {
            cards.addAll(discardPile);
            discardPile.removeAll(new ArrayList<>(discardPile));
            shuffle();
        }
    }

    // Shuffles deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Draws multiple cards from the deck
    public List<Card> drawMultipleCards(int numCards) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            Card drawnCard = drawCard();
            if (drawnCard != null) {
                drawnCards.add(drawnCard);
            }
        }
        return drawnCards;
    }

    public void removeCard(String name) {
        for (Card card : cards) {
            if (name.equals(card.getName())) {
                cards.remove(card);
                break;
            }
        }
    }

    // Getters
    // Returns the size of the deck
    public int getSize() {
        return cards.size();
    }

    // Return the size of specific card type in the deck
    public int getSize(String name) {
        int count = 0;
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    // Returns the value of a Foe card by name
    public int getFoeCardValue(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                if (card instanceof FoeCard) {
                    return ((FoeCard) card).getValue();
                }
            }
        }
        return -1; // Return -1 if the card is not found or if it's not a FoeCard
    }

    // Returns the value of a Weapon card by name
    public int getWeaponCardValue(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                if (card instanceof WeaponCard) {
                    return ((WeaponCard) card).getValue();
                }
            }
        }
        return -1; // Return -1 if the card is not found or if it's not a WeaponCard
    }

    // Returns the number of stages of a Quest card by name
    public int getQuestCardStage(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                if (card instanceof QuestCard) {
                    return ((QuestCard) card).getStages();
                }
            }
        }
        return -1; // Return -1 if the card is not found or if it's not a QuestCard
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }
}
