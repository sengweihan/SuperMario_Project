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

import java.util.HashMap;

public class Toad extends Actor {
    /**
     * Hashmap is used to store respective items and its price for selling purposes.
     */
    private HashMap<Item,Integer> sellerItem = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Toad(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        sellerItem.put(new Wrench(),200);
        sellerItem.put(new PowerStar(),600);
        sellerItem.put(new SuperMushroom(),400);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionsList = new ActionList();
        for (Item item : sellerItem.keySet()){
            actionsList.add(new BuyAction(item,sellerItem.get(item),item.toString()));
        }

        return actionsList;
        }


}
