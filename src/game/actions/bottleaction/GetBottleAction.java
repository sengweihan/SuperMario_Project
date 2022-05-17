package game.actions.bottleaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;

public class GetBottleAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle());
        Bottle.setPlayer(actor);
        actor.addCapability(Status.HAS_BOTTLE);
        return actor + " obtains bottle!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle";
    }
}
