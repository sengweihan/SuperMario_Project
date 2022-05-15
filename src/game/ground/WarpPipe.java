package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actions.PowerStarMoveAction;
import game.actions.TeleportAction;
import game.enemies.PiranhaPlant;
import game.interfaces.Jumpable;

public class WarpPipe extends Ground implements Jumpable {

    private int x;
    private int y;
    private GameMap targetMap;
    private int counter ;

    /**
     * Constructor.
     *
     */
    public WarpPipe(int x, int y ,GameMap targetMap) {
        super('C');
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
        counter = 0;

    }

    /**
     * Piranha Plant will spawn at the second turn of the game.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        counter +=1;
        if (counter == 2 && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
        }
    }

    @Override
    public String jump(Actor actor, GameMap map, Location location) {
        map.moveActor(actor, location);
        return actor + " is currently standing on top of the portal for teleportation ";
    }


    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor() && !actor.hasCapability(Status.IMMUNITY)){
            actionList.add(new JumpAction(this,location,direction));

        }

        else if (actor.hasCapability(Status.IMMUNITY) && !location.containsAnActor()){
            actionList.add(new PowerStarMoveAction(location,direction));
        }

        else if (location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
            actionList.add(new TeleportAction(x,y,targetMap));
        }

        else{
            actionList.add(new DoNothingAction());
        }
        return actionList;
    }



    /**
     * No actor can enter this ground
     * @param actor the Actor to check
     * @return a boolean (true/false)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    public String toString(){
        return "Warp pipe";
    }
}
