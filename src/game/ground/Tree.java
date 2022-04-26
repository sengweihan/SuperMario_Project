package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public abstract class Tree extends Ground {

    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
