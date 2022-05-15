package game.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

public class FlyingKoopa extends Koopa{

    public FlyingKoopa(){
        super("Flying Koopa", 'F',150);
        this.addCapability(Status.FLYING);
    }
}
