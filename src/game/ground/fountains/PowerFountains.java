package game.ground.fountains;

public class PowerFountains extends Fountains {
    /**
     * Constructor.
     */
    public PowerFountains() {
        super('A', new PowerWater());
    }

    @Override
    public String toString() {
        return "Power Fountain (" + drinkCount + "/10)";
    }
}
