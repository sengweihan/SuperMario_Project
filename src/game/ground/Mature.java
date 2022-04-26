package game.ground;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Status;
import game.enemies.Koopa;

import java.util.List;
import java.util.Random;

public class Mature extends Tree {
    private int counter;
    private Random rand = new Random();
    private static final double CHANCE_SPAWN_KOOPA = 0.15;


    /**
     * Constructor.
     *
     * Everytime a new instance of Mature is created, the couter will be initialized to 0
     *
     */
    public Mature() {
        super('T');
        counter = 0;
    }



    /**
     *
     * @param location The location of the Ground
     * Allow sapling class to keep track of the passage of time
     * with the help of counter attribute.
     *
     * For every 5 turns , it will grow a new Sprout in one of the surroundings
     * and at the same time, it has 15% chance to spawn Koopa and 20% chance to wither and die
     * and eventually turns the ground into dirt.
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        List<Exit> e =  location.getExits();
        int randomExit = rand.nextInt(e.size());
        Exit exit = e.get(randomExit);

        if (counter % 5 == 0 && exit.getDestination().getGround().hasCapability(Status.FERTILE)){
            location.setGround(new Sprout());
        }

        if (Math.random() <= CHANCE_SPAWN_KOOPA && !location.containsAnActor()){
            location.addActor(new Koopa());
        }

        if (Math.random() <= 0.2){
            location.setGround(new Dirt());
        }

    }
}
