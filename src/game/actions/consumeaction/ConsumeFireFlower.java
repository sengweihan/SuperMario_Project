package game.actions.consumeaction;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.ConsumableItems;

public class ConsumeFireFlower extends ConsumeItemAction{


    public ConsumeFireFlower(ConsumableItems item) {
        super(item);
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.FIRE_ATTACK)){
            return actor + " has already consumed a Fire Flower!";
        }
        map.locationOf(actor).removeItem(item);
        item.consumeItem(actor);

        return actor + " consumes Fire Flower";
    }

}
