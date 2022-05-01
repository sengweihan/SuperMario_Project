package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapon.Wrench;
import java.util.Map;
import java.util.TreeMap;

public class Toad extends Actor {
    /**
     * Hashmap is used to store respective items and its price for selling purposes.
     */
    private Map<Integer,Item> sellerItem = new TreeMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Toad(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        sellerItem.put(200, new Wrench());
        sellerItem.put(400, new SuperMushroom());
        sellerItem.put(600, new SuperMushroom());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionsList = new ActionList();
        for (int item : sellerItem.keySet()){
            if (item == 200){
                actionsList.add(new BuyAction(new Wrench(),item,"Wrench"));
            }
            else if (item == 400){
                actionsList.add(new BuyAction(new SuperMushroom(),item,"Super Mushroom"));
            }
            else if (item == 600){
                actionsList.add(new BuyAction(new PowerStar(),item,"Power Star"));
            }
        }
        return actionsList;
        }
}
