package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.DestroyShellAction;
import game.actions.DormantAction;

public class Koopa extends Enemies {

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.registerInstance();
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (this.hasCapability(Status.DORMANT) && (otherActor.getInventory().contains(otherActor.getWeapon()))){
            ActionList actions = new ActionList();
            actions.clear();
            actions.add(new DestroyShellAction(this,direction));
            this.behaviours.clear();
            return actions;
        }
        else{
            return super.allowableActions(otherActor,direction,map);
        }
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.DORMANT)) {
            this.setDisplayChar('D');
            return new DormantAction();
        }
        else{
            return super.playTurn(actions,lastAction,map,display);
        }
    }


    @Override
    public void resetInstance(GameMap map) {
        map.removeActor(this);
    }
}
