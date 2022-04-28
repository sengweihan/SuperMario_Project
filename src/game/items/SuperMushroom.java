package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actions.ConsumeSuperMushroomAction;

public class SuperMushroom extends ConsumableItems {
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addAction(new ConsumeSuperMushroomAction(this));
    }

    @Override
    public void consumeItem(Actor actor) {
        actor.addCapability(Status.EFFECT_SUPER_MUSHROOM);
        actor.increaseMaxHp(50);
    }
}
