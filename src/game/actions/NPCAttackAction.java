package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

import java.util.Random;

public class NPCAttackAction extends Action {

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
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public NPCAttackAction(Actor target, String direction) {
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
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

        if (target.hasCapability(Status.IMMUNITY)){
            target.hurt(0);
            result += System.lineSeparator() + target + " is immune to damage!";
        }
        else {
            target.hurt(damage);
            if (target.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
                target.removeCapability(Status.EFFECT_SUPER_MUSHROOM);
                result += System.lineSeparator() + target + "'s Super Mushroom effect has been removed!";
            }
        }

        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}
