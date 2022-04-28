package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.enemies.Goomba;
import game.interfaces.Jumpable;

/**
 * @author SENG WEI HAN
 * @version 1.0
 */
public class Sprout extends Tree implements Jumpable {
    private int counter;
    private static final double CHANCE_SPAWN_GOOMBA = 0.1;

    public Sprout(){
        super('+');
        counter = 0;
    }


    /**
     *
     * @param location The location of the Ground
     * Allow sprout class to keep track of the passage of time
     * with the help of counter attribute.
     *
     * For every 10 turns , it will grow into a small tree/Sapling (t)
     * and at the same time, it has 10 percent chance to spawn Goomba(enemies)
     * on its position in every turn . If there's actor standing on the ground
     * it will not be able to spawn Goomba.
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        if (counter % 10 == 0){
            location.setGround(new Sapling());
        }

        if (Math.random() <= CHANCE_SPAWN_GOOMBA && !location.containsAnActor()){
            location.addActor(new Goomba());

        }
    }


    public String toString(){
        return "Sprout";
    }


    /**
     *
     * @param actor
     * @param map
     * @param location
     * @return String
     * Since sprout implements Jumpable interface, it will need to implement this jump methods.
     * Have 90% success rate and 10 fall damage.If there player have super mushroom capability , they
     * can jump freely.
     */
    @Override
    public String jump(Actor actor, GameMap map, Location location) {
        if(Math.random()<= 0.9 || actor.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
            map.moveActor(actor,location);
            return actor + " successfully jumped on top of " + this;
        }
        else{
            actor.hurt(10);
            return actor + " jumped unsuccessful and inflicted a damage of 10";
        }
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor()){
            actionList.add(new JumpAction(this,location,direction));
        }

        return actionList;

    }
}