package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumePowerStarAction;

/**
 * Base class for ConsumeableItems. These represent items that can be consumed by the actor
 */
public abstract class ConsumableItems extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItems(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * if the item is picked up, it cannot be dropped
     * @param actor the actor that drops the item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * abstract method that applies the buffs to the actor
     * after the actor consumes the item.
     *
     * @param actor the actor that consumes the item
     */
    public abstract void consumeItem(Actor actor);
}
