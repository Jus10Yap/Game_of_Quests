import cards.*;
import java.util.*;

public class Player {
    // Variables
    private final String name;
    private int shields;

    public Player(String name) {
        this.name = name;
        shields = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getShields() {
        return shields;
    }


}
