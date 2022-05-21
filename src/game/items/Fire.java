package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.interfaces.Burning;

public class Fire extends Item implements Burning {
    private int counter;
    private static final int FIRE_DAMAGE = 20;

    public Fire() {
        super("Fire",'v',false);
    }


    @Override
    public void tick(Location location) {
        counter += 1;
        /**
         * This fire item lasts only for 3 rounds.
         */
        if (counter % 3 == 0){
            location.removeItem(this);
        }
        else{
            /**
             * For every subsequent turn after the existence of fire item, it will continuously
             * burn those actors who step into the ground that contain this item and this
             * will last until the item is completely destroy.
             */
            if (location.containsAnActor() && !location.getActor().hasCapability(Status.IMMUNITY) && !location.getActor().hasCapability(Status.FLYING)){
                location.getActor().hurt(FIRE_DAMAGE);
                if (!location.getActor().isConscious()){
                    GameMap map = location.map();
                    map.removeActor(location.getActor());

                }
            }
        }


    }

    /**
     * This method will be called inside the fire attack action class.
     * When the player uses fire attack on enemies, then the enemies location will drop a fire item.
     *
     * @param target
     * @param map
     * @return a string
     */
    @Override
    public void burn(Actor target, GameMap map) {
        map.locationOf(target).addItem(this);

    }




}
