package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumePowerStarAction;

public abstract class ConsumableItems extends Item {
    private boolean droppable = false;
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItems(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (droppable)
            return new DropItemAction(this);
        return null;
    }

    public abstract void consumeItem(Actor actor);
}
