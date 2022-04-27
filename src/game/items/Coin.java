package game.items;

import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {
    private int coinValue;


    public Coin(int coinValue,String name){
        super(name,'$',true);
        this.coinValue = coinValue;
    }
}
