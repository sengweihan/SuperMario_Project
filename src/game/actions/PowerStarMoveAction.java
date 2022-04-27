package game.actions;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.items.Coin;

public class PowerStarMoveAction extends MoveActorAction {
    public PowerStarMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,moveToLocation);
        Location here = map.locationOf(actor);
        here.setGround(new Dirt());
        here.addItem(new Coin(5,"Coin($5)"));
        return actor + " moves to " + super.direction;
    }
}
