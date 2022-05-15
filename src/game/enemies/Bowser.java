package game.enemies;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

public class Bowser extends Enemies {
    private GameMap secondMap;
    /**
     * Constructor.
     */
    public Bowser (GameMap secondGameMap) {
        super("Bowser", 'B', 500);
        this.behaviours.clear();
        this.addCapability(Status.FINAL_BOSS);
        secondMap = secondGameMap;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80,"punches");
    }

    @Override
    public void resetInstance(GameMap map) {
        if (this.isConscious()){
            this.heal(500);
            this.behaviours.clear();
            MoveActorAction defaultLocation = new MoveActorAction(secondMap.at(41,8),"","");
            defaultLocation.execute(this,secondMap);
        }
    }
}
