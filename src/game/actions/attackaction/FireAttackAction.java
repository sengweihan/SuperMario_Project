package game.actions.attackaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enemies.Enemies;
import game.interfaces.Burning;

import java.util.Random;

public class FireAttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     *  Have a strong association with the instance of burning interface type.
     */

    protected Burning item;


    /**
     * A fire attack action constructor.
     * @param target
     * @param direction
     * @param item
     */
    public FireAttackAction(Enemies target, String direction, Burning item) {
        this.target = target;
        this.direction = direction;
        this.item = item;
    }


    /**
     * Will call the burn method that is implemented by those class which implements the burning interface method.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return item.burn(target,map);
    }

    /**
     * A string that will be printed out in the console to allow player to choose which action to perform
     * on that current round.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}
