package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.enemies.Enemies;
import game.items.SuperMushroom;

import java.util.Random;

public class DestroyShellAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Enemies target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public DestroyShellAction(Enemies target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " destroyed " + target + "'s shell!";
        target.hurt(damage);
        Location dropMushroom = map.locationOf(target);
        map.removeActor(target);
        dropMushroom.addItem(new SuperMushroom());
        result += System.lineSeparator() + target + " is killed and it drops a Super Mushroom!";

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}
