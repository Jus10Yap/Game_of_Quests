package cards;

public class WeaponCard extends Card {
    private int value;

    public WeaponCard(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return "Weapon - " + getName() + " with value " + value;
    }
}
