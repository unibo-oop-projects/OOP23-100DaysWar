package it.unibo.the100dayswar.model.fight.api;

import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * generic interface for a battle between two combatant.
 * 
 * @param <T> type of attacker
 * @param <U> type of defender
 */
public interface Battle<T extends Combatant, U extends Combatant> {
    /**
     * Start the fight between the attacher and defender.
     * 
     * @param attacker
     * @param defender
     */
    void startFiht(T attacker, U defender);
}
