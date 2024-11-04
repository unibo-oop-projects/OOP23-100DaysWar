package it.unibo.the100dayswar.model.fight.api;

import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * Rappresent a command that executes a battle between two combattant units.
 * 
 * @param <T>
 * @param <U>
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
