package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.consumeaction.ConsumeFireFlower;

public class FireFlower extends ConsumableItems {

    protected static final int TICK_COUNT = 21;
    protected  int counter ;
    protected static final int NUMBER_OF_TURNS_EXPIRED = 20;


    public FireFlower() {
        super("Fire Flower",'f',false);
        this.addAction(new ConsumeFireFlower(this));
        counter = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        if (counter != NUMBER_OF_TURNS_EXPIRED){
            counter += 1;

        }
        else{
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void consumeItem(Actor actor) {
        actor.addCapability(Status.FIRE_ATTACK);
    }

    /**
     * displays how many turns left before fire flower disappears
     * from the game
     *
     * @return a string that shows how many turns left before fire flower disappears
     * from the game
     */
    @Override
    public String toString() {
        return "Fire Flower - " + (TICK_COUNT-counter) + " turns remaining";
    }
}
