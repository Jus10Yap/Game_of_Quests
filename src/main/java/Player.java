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

    // Getters
    public String getName() {
        return name;
    }

    public int getShields() {
        return shields;
    }

    public List<Card> getHand() {
        return hand;
    }

    // Setters
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
