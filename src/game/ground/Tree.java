package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;
import game.actions.JumpAction;
import game.actions.PowerStarMoveAction;
import game.interfaces.Jumpable;
import game.reset.Resettable;

import java.util.Random;

public abstract class Tree extends Ground implements Resettable, Jumpable {
    protected Random rand = new Random();
    protected final int removed = 5;
    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (actor.hasCapability(Status.IMMUNITY) && !location.containsAnActor()){
            actionList.add(new PowerStarMoveAction(location,direction));
        }
        else if (!location.containsAnActor()){
            actionList.add(new JumpAction(this,location,direction));
        }

        return actionList;

    }

    @Override
    public void resetInstance(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        for (int i : x){
            for (int j : y){
                Location here = map.at(i,j);
                if (here.getGround() == this){
                    if (removed >= rand.nextInt(10)){
                        here.setGround(new Dirt());
                    }
                }
            }
        }
    }
}
