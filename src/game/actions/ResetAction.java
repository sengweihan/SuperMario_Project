package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

public class ResetAction extends Action {

    private ResetManager resetManager;

    public ResetAction(){
        resetManager = ResetManager.getInstance();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.run(map);
        return actor + " has reset the game!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Resets the game";
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
