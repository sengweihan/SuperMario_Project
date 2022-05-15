package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.LavaMoveAction;

public class Lava extends Ground {
    /**
     * Constructor.
     *
     */
    public Lava() {
        super('L');
        this.addCapability(Status.BURN);
    }


    /**
     * Enemies cannot step on this lava ground.
     * @param actor the Actor to check
     * @return a boolean (true or false)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor())
            actionList.add(new LavaMoveAction(location,direction));
        return actionList;
    }

    /**
     * A string "Lava Ground" will be printed when instance of this class is being called.
     * @return a string
     */
    public String toString(){
        return "Lava Ground";
    }

}
