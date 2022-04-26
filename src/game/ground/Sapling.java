package game.ground;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

public class Sapling extends Tree {
    private int counter ;
    private static  final double CHANCE_SPAWN_COIN = 0.1;

    /**
     * Constructor.
     *
     *
     */
    public Sapling() {
        super('t');
        counter = 0;
    }


    /**
     *
     * @param location The location of the Ground
     * Allow sapling class to keep track of the passage of time
     * with the help of counter attribute.
     *
     * For every 10 turns , it will grow into a tall tree/Mature (t)
     * and at the same time, it has 10 percent chance to spawn Coins($20)
     * on its position in every turn .
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        if (counter % 10 == 0){
            location.setGround(new Mature());
        }

        if (Math.random() <= CHANCE_SPAWN_COIN){
            location.addItem(new Coin(20,"Coin($20)"));
        }


    }
}
