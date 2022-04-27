package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.items.ConsumableItems;

public abstract class ConsumeItemAction extends Action {
    protected ConsumableItems item;

    public ConsumeItemAction(ConsumableItems item){
        this.item = item;
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can consume " + item;
    }
}
