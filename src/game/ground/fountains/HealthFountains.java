package game.ground.fountains;

public class HealthFountains extends Fountains {

    /**
     * Constructor.
     */
    public HealthFountains() {
        super('H',new HealthWater());
    }

    @Override
    public String toString() {
        return "Health Fountain (" + drinkCount + "/10)";
    }
}