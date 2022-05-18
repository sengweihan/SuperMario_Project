package game.actions.bottleaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.FountainWater;
import game.ground.fountains.Fountains;
import game.items.Bottle;

/**
 * An action class that allows the user to fill bottle with fountain water.
 */

public class FillBottleAction extends Action {

    /**
     * the fountain the player is at
     */
    protected FountainWater fountainWater;
    /**
     * the fountain water the player fills the bottle with
     */
    protected Fountains fountains;

    /**
     * Constructor.
     *
     * @param fountain the fountain the player is at
     * @param fountainWater the fountain water the player fills the bottle with
     */
    public FillBottleAction(Fountains fountain,FountainWater fountainWater){
        this.fountainWater = fountainWater;
        this.fountains = fountain;
    }

    /**
     * execute method that simply reduces the fountain water count (because of refilling)
     * then add fountain water into the bottle.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that displays user has refilled bottle with fountain water
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        fountains.reduceCountByFilling();
        Bottle.addWater(fountainWater);
        return actor + " refill " + fountainWater;
    }

    /**
     * display the menu description of what the actor can do, in this case,
     * refill bottle from fountain.
     *
     * @param actor The actor performing the action.
     * @return a string that displays what the actor can do
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can refill bottle from " + fountains;
    }
}
