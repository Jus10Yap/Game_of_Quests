import cards.*;
import java.util.*;

public class Player {
    // Variables
    private final String name;
    private int shields;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        shields = 0;
        hand = new ArrayList<>();
    }

    // Add shields to the player
    public void addShields(int num) {
        shields += num;
    }

    // Check if the player has 7 or more shields
    public boolean hasWon() {
        return shields >= 7;
    }

    // Sort the hand: Foes first, Weapons after in increasing value, Swords before
    // Horses
    public void sortHand() {
        hand.sort((card1, card2) -> {
            // Foe cards come first
            if (card1 instanceof FoeCard && card2 instanceof WeaponCard) {
                return -1;
            } else if (card1 instanceof WeaponCard && card2 instanceof FoeCard) {
                return 1;
            } else if (card1 instanceof FoeCard && card2 instanceof FoeCard) {
                // Sort Foe cards by value
                return Integer.compare(((FoeCard) card1).getValue(), ((FoeCard) card2).getValue());
            } else if (card1 instanceof WeaponCard && card2 instanceof WeaponCard) {
                // Sort Weapon cards by value, Swords before Horses if same value
                WeaponCard w1 = (WeaponCard) card1;
                WeaponCard w2 = (WeaponCard) card2;
                if (w1.getValue() == w2.getValue()) {
                    // Ensure Swords (S) come before Horses (H) when values are the same
                    if (w1.getName().startsWith("S") && w2.getName().startsWith("H")) {
                        return -1;
                    } else if (w1.getName().startsWith("H") && w2.getName().startsWith("S")) {
                        return 1;
                    }
                }
                return Integer.compare(w1.getValue(), w2.getValue());
            }
            return 0;
        });
    }

    public void displayHand() {
        sortHand(); // Sort the hand before displaying
        System.out.println("\n[Game] " + name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i).getName());
        }
    }

    // Add a card to the player's hand
    public void addCardToHand(Card card) {
        sortHand();
        hand.add(card);
    }

    public boolean hasCard(String name) {
        for (Card card : hand) {
            if (name.equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getShields() {
        return shields;
    }

    public List<Card> getHand() {
        sortHand();
        return hand;
    }

    // Setters
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
