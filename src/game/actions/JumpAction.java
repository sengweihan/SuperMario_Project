package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Jumpable;

public class JumpAction extends Action {
    private Jumpable jumpable;
    private Location jumpLocation;
    private String direction;

    public JumpAction(Jumpable jumpable, Location moveToLocation, String direction){
        this.jumpable = jumpable;
        this.jumpLocation = moveToLocation;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return jumpable.jump(actor,map,jumpLocation);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + direction + " " + jumpable;
    }
}
