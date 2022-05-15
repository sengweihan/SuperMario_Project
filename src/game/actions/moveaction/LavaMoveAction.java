package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class LavaMoveAction extends MoveActorAction {

    public LavaMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (moveToLocation.getGround().hasCapability(Status.BURN)){
            map.moveActor(actor,moveToLocation);
            actor.hurt(15);

        }
        return "ARGHHH " + actor + " is currently burning!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + moveToLocation.getGround() + " in " + direction;
    }
}
