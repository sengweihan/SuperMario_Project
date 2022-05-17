package game.actions.bottleaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.FountainWater;
import game.ground.fountains.Fountains;
import game.items.Bottle;

public class FillBottleAction extends Action {

    protected FountainWater fountainWater;
    protected Fountains fountains;

    public FillBottleAction(Fountains fountain,FountainWater fountainWater){
        this.fountainWater = fountainWater;
        this.fountains = fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        fountains.reduceCountByFilling();
        Bottle.addWater(fountainWater);
        return actor + " refill " + fountainWater;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can refill bottle from " + fountains;
    }
}
