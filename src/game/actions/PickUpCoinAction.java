package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PickableCoin;

public class PickUpCoinAction extends Action {
    private PickableCoin pickableCoin;

    public PickUpCoinAction(PickableCoin pickableCoin){
        this.pickableCoin = pickableCoin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return pickableCoin.pickableCoin(actor,map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + pickableCoin;
    }
}
