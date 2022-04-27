package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.Map;
import java.util.TreeMap;

public abstract class Enemies extends Actor implements Resettable {
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    protected boolean attacked = false;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
    }

    @Override
    protected abstract IntrinsicWeapon getIntrinsicWeapon();

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious()) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Actor otherActor;
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                otherActor = map.getActorAt(destination);
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && destination.containsAnActor()){
                    if (this.behaviours.get(0) == null){
                        this.behaviours.put(0, new AttackBehaviour(otherActor));
                        attacked = true;
                    }
                }
            }
            else if (attacked){
                this.behaviours.remove(0);
                otherActor = map.getActorAt(destination);
                this.behaviours.put(5,new FollowBehaviour(otherActor));
            }
        }
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
