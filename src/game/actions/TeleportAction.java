package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class TeleportAction extends Action {

    private int x;
    private int y;
    private GameMap targetMap;


    public TeleportAction(int x, int y ,GameMap targetMap) {
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (targetMap != map ){

            if (targetMap.at(x,y).containsAnActor()){
                targetMap.removeActor(targetMap.at(x,y).getActor());
            }

            map.removeActor(actor);
            targetMap.addActor(actor, targetMap.at(x,y));

            return actor + " " + menuDescription(actor);
        }


        return "Please try teleporting again!";

    }

    @Override
    public String menuDescription(Actor actor) {
        if (targetMap.at(44,11).containsAnActor()){
            return "Teleporting to original map!";
        }
        return "Teleporting to Lava Zone!";
    }
}
