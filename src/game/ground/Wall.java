package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.interfaces.Jumpable;

public class Wall extends Ground implements Jumpable {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	public String toString(){
		return "Wall";
	}


	/**
	 *
	 * @param actor
	 * @param map
	 * @param location
	 * @return String
	 * Since wall implements Jumpable interface, it will need to implement this jump methods.
	 * Have 80% success rate and 20 fall damage.If there player have super mushroom capability , they
	 * can jump freely.
	 */
	@Override
	public String jump(Actor actor, GameMap map, Location location) {
		if(Math.random()<= 0.8 || actor.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
			map.moveActor(actor,location);
			return actor + " successfully jumped on top of " + this;
		}
		else{
			actor.hurt(20);
			return actor + " jumped unsuccessful and inflicted a damage of 20";
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
