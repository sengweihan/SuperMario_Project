package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    FERTILE, // use this status to tell the current ground is fertile
    EFFECT_SUPER_MUSHROOM, // use this status when the player consumes super mushroom
    IMMUNITY, // use this status when the player consumes magical star, lasts for 10 turns
    DORMANT,
    BUYING, // use this status to tell which actor can buys item from toad.
    BURN, // LAVA GROUND WILL HAVE THIS STATUS AND WHEN PLAYER STEPS ON IT , IT WILL GET INFLICTED CERTAIN AMOUNT OF DAMAGE.
    FIRE_ATTACK, // when player consume fire flower it will obtain this capability.
}
