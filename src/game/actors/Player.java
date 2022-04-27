package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
//import game.WalletSystem;
import game.actions.ResetAction;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	protected int tick = 0;
	protected final int TICK_COUNT = 10;
	protected final static int INITIAL_WALLET_VALUE = 1300;
	private static int walletValue ;
	private boolean effectOngoing = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
//		WalletSystem.setPlayer(this);
		walletValue = INITIAL_WALLET_VALUE;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
//		this.addCapability(Status.BUYING);
		this.registerInstance();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		Location actorCurrentLocation = map.locationOf(this);

		if (tick<TICK_COUNT && this.hasCapability(Status.IMMUNITY)){
			tick++;
			display.println("MARIO IS INVINCIBLE!");
		}
		else if (this.hasCapability(Status.IMMUNITY)){
			tick = 0;
			this.removeCapability(Status.IMMUNITY);
			display.println("IMMUNITY effect has worn off!");
		}


		ResetManager resetManager = ResetManager.getInstance();
		if (!resetManager.getHasReset()){
			actions.add(new ResetAction());
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}
		// return/print the console menu
		display.println(this + this.printHp() + " at (" + actorCurrentLocation.x() + ", " + actorCurrentLocation.y() + ")");
		display.println("wallet: $" + Player.getWalletValue());
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		char out = this.hasCapability(Status.EFFECT_SUPER_MUSHROOM) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
		return out;
	}

	@Override
	public void resetInstance(GameMap map) {
		super.resetMaxHp(100);
		this.removeCapability(Status.IMMUNITY);
		this.removeCapability(Status.EFFECT_SUPER_MUSHROOM);
	}

	public static int getWalletValue() {
		return walletValue;
	}

	public static void setWallet(int value) {
		walletValue += value;
	}

	public static void deductWalletValue(int value){
		walletValue -= value;
	}
}

