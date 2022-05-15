package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Fire extends Item {
    private int tick;

    /**
     * Constructor.
     */
    public Fire() {
        super("Fire",'v',false);
        tick = 3;
        this.addCapability(Status.BURN);
    }

    @Override
    public void tick(Location location) {
        if (tick!= 0){
            tick--;
        }
        else {
            // fire so strong it burns down everything
            location.removeItem(this);
        }

        Actor actor = location.getActor();
        if (location.containsAnActor()){
            if (!actor.hasCapability(Status.IMMUNITY) && !actor.hasCapability(Status.FLYING)){
                actor.hurt(20);
            }
        }
    }
}
