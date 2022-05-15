package game.items;

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
            if (location.containsAnActor() && !location.getActor().hasCapability(Status.IMMUNITY)){
                location.getActor().hurt(FIRE_DAMAGE);
            }
        }


    }


    @Override
    public String burn(Actor target, GameMap map) {
        map.locationOf(target).addItem(this);
        target.hurt(FIRE_DAMAGE);

        /**
         *  if the target (enemies) hit point < 0 then it shall be remove from the current map.
         */
        if (!target.isConscious()){
            map.removeActor(target);
            return target + " is removed from the map due to extreme fire burning! ";


        }
        return  target + " burnt with a damage of " + FIRE_DAMAGE + "!";


    }




}
