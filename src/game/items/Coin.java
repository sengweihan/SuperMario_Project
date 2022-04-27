package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.reset.Resettable;

public class Coin extends Item implements Resettable {
    private int coinValue;

    public Coin(int coinValue,String name) {
        super(name, '$', true);
        this.coinValue = coinValue;
        this.registerInstance();
    }

    @Override
    public void resetInstance(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        for (int i : x){
            for (int j : y){
                Location here = map.at(i,j);
                if (here.getItems().contains(this)){
                    here.removeItem(this);
                }
            }
        }
    }
}
