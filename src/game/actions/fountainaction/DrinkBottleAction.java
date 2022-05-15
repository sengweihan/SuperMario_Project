package game.actions.fountainaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.FountainWater;
import game.items.Bottle;

public class DrinkBottleAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        FountainWater water = Bottle.getWater();
        Bottle.drinkWater();
        return actor + " drinks " + water;
    }

    @Override
    public String menuDescription(Actor actor) {
        String output = "";
        for (int i = 0; i < Bottle.getWaterTypeLength(); i++){
            output += Bottle.getWaterType().get(i) + ", ";
        }

        return actor + " consumes Bottle[" + output.substring(0,output.length()-2) + "]";
    }
}
