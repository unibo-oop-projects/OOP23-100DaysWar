package it.unibo.the100dayswar.model.fight.api;

import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * Represents a command that executes a battle between two combatant units.
 * 
 * @param <T>
 * @param <U>
 */
public interface BattleCommand<T extends Combatant, U extends Combatant> {
    /**
     * execute the action between combatants.
     * 
     * @param attacker
     * @param defender
     */
    void execute(T attacker, U defender);
}
