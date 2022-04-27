package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.NPCAttackAction;

public class AttackBehaviour implements Behaviour {

    private final Actor target;

    public AttackBehaviour (Actor subject){
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                String direction = exit.getName();
                return new NPCAttackAction(target,direction);
            }
        }

        return null;
    }
}
