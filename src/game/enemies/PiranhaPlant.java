package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class PiranhaPlant extends Enemies {

    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);

    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90,"Chomps");
    }


}
