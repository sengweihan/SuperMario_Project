package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Flyable;

public class FlyAction extends Action {
    private Flyable flyable;
    private Location flyLocation;
    private String direction;

    public FlyAction(Flyable flyable, Location moveToLocation, String direction){
        this.flyable = flyable;
        this.flyLocation = moveToLocation;
        this.direction = direction;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return flyable.fly(actor, map, flyLocation);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can fly to " + direction;
    }
}