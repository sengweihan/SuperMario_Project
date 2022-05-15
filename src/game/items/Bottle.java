package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.ground.fountains.FountainWater;

import java.util.ArrayList;

public class Bottle extends Item {
    private static ArrayList<FountainWater> waterType;
    private static Actor player;
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        waterType = new ArrayList<>();
    }

    public static void addWater(FountainWater fountainWater){
        waterType.add(fountainWater);
    }

    public static void setPlayer(Actor player){
        Bottle.player=player;
    }

    public static void drinkWater(){
        FountainWater water = waterType.get(waterType.size()-1);
        waterType.remove(waterType.size()-1);
        water.consumeWater(player);
    }

    public static int getWaterTypeLength(){
        if (waterType != null){
            return waterType.size();
        }

        return 0;
    }

    public static FountainWater getWater(){
        return waterType.get(waterType.size()-1);
    }

    public static ArrayList<FountainWater> getWaterType(){
        return waterType;
    }
}
