package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/**
	 * This method will only allow player to enter and not other actor such as koopa,goomba or toad.
	 *
	 * @param actor the Actor to check
	 * @return a boolean value
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
			return true;
		}
		return false;
	}
}
