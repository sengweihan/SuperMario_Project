package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.reset.Resettable;

import java.util.Random;

public abstract class Tree extends Ground implements Resettable {
    protected Random rand = new Random();
    protected final int removed = 5;
    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public void resetInstance(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        for (int i : x){
            for (int j : y){
                Location here = map.at(i,j);
                if (here.getGround() == this){
                    if (removed >= rand.nextInt(10)){
                        here.setGround(new Dirt());
                    }
                }
            }
        }
    }
}
