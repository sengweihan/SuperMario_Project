package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.SuperMushroom;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

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
	public AttackAction(Actor target, String direction) {
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
		if (actor.hasCapability(Status.IMMUNITY)){
			damage = 500;
		}
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			if (actor.hasCapability(Status.IMMUNITY)){
				if (target.getDisplayChar()=='K') {
					Location dropMushroom = map.locationOf(target);
					dropMushroom.addItem(new SuperMushroom());
				}
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
			else {
				if (target.getDisplayChar()=='K') {
					if (!target.hasCapability(Status.DORMANT)){
						target.addCapability(Status.DORMANT);
					}
				}
				else {
					// remove actor
					map.removeActor(target);
					result += System.lineSeparator() + target + " is killed.";
				}
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
