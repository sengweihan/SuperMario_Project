package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class EndGameAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " has successfully rescued Princess Peach!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " wins the game!";
    }
}
