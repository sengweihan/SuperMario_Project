package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItems;

public class ConsumeSuperMushroomAction extends ConsumeItemAction {

    public ConsumeSuperMushroomAction(ConsumableItems item){
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getInventory().contains(item)) {
            actor.removeItemFromInventory(item);
            item.consumeItem(actor);
        }
        else {
            map.locationOf(actor).removeItem(item);
            item.consumeItem(actor);
        }
        return actor + " has consumed "+ item + "!";
    }


}
