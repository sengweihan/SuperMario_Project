package game.ground.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.fountainaction.FillBottleAction;
import game.interfaces.Drinkable;

public abstract class Fountains extends Ground implements Drinkable {
    protected int drinkCount;
    protected int refillCount;
    protected boolean refilling;
    protected FountainWater water;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountains(char displayChar,FountainWater fountainWater) {
        super(displayChar);
        drinkCount = 10;
        refillCount = 0;
        water = fountainWater;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (drinkCount != 0 && !refilling){
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.HAS_BOTTLE)) {
                actions.add(new FillBottleAction(this, water));
            }
        }
        else if (refillCount < 4){
            refilling = true;
            refillCount++;
        }
        else {
            refilling = false;
            refillCount = 0;
            drinkCount = 10;
        }
        return actions;
    }


    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    public void reduceCount(){
        drinkCount = drinkCount - 5;
    }

    public void reduceCountByRefilling() {
        drinkCount--;
    }

    public FountainWater getWater(){
        return water;
    }

    @Override
    public void drinkFromFountain(Actor actor) {
        water.consumeWater(actor);
    }

    public int getDrinkCount(){
        return drinkCount;
    }
}
