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

    protected Burning item;

    public FireAttackAction(Enemies target, String direction, Burning item) {
        this.target = target;
        this.direction = direction;
        this.item = item;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return item.burn(target,map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}
