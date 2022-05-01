package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.systems.WalletSystem;

public class BuyAction extends Action {
    private Item item;
    private int cost;
    private String name;


    public BuyAction(Item item, int cost,String name){
        this.item = item;
        this.cost = cost;
        this.name = name;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "";
        if (actor.hasCapability(Status.BUYING) && WalletSystem.getWalletValue() > cost){
            actor.addItemToInventory(item);
            WalletSystem.subtractWalletValue(cost);
            message += actor + " successfully bought " + name;
            return message;

        }
        else{
            message += actor + " do not have enough coins to buy " + name + "!";
            return message;
        }
    }


    public String menuDescription(Actor actor) {
        return actor + " buys "  + name+ " ($" + this.cost + ")";
    }



}
