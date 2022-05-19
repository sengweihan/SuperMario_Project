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
     * When the player uses fire attack on enemies, then the enemies location will drop a fire
     * and will inflict a damage of 20 to the enemies who is standing on the ground that contain this fire item.
     *
     * It will also check if the target actor is conscious or not , if not it will automatically be removed from
     * the current map and finally print a message on the console.
     *
     * @param target
     * @param map
     * @return a string
     */
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
