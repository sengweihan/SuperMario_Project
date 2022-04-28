package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumePowerStarAction;

public class PowerStar extends ConsumableItems{
    protected int tick = 0;
    protected final int TICK_COUNT = 10;

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.addAction(new ConsumePowerStarAction(this));
    }

    @Override
    public void tick(Location location, Actor actor){
        if (tick < TICK_COUNT){
            tick++;
        }
        else {
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public void tick(Location currentLocation) {
        if (tick < TICK_COUNT){
            currentLocation.removeItem(this);
        }
        else {
            tick++;
        }
    }

    @Override
    public void consumeItem(Actor actor) {
        actor.heal(200);
        actor.addCapability(Status.IMMUNITY);
    }



}
