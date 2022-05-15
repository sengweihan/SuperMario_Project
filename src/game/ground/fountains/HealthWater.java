package game.ground.fountains;

import edu.monash.fit2099.engine.actors.Actor;
import game.ground.fountains.FountainWater;

public class HealthWater extends FountainWater {

    @Override
    public void consumeWater(Actor actor) {
        actor.heal(50);
    }

    @Override
    public String toString() {
        return "Health Water";
    }
}
