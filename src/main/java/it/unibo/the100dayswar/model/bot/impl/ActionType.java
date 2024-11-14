package it.unibo.the100dayswar.model.bot.impl;

/**
 * An enum that represents the possible actions that a bot can take.
 */
public enum ActionType {
    /** 
     * The action of purchasing a soldier unit.
     */
    PURCHASE_SOLDIER,
    /** 
     * The action of purchasing a tower unit.
     */
    PURCHASE_TOWER,
    /** 
     * The action of upgrading a unit.
     * For a simplier logic this action will upgrade the unit
     * with the lowest cost to upgrade.
     */
    UPGRADE_UNIT,
    /** 
     * The action of moving a unit.
     * For a simplier logic this action will move a random unit
     * that can move but with the logic to move int he direction
     * of the enemy's spawn.
     */
    MOVEMENT
}
