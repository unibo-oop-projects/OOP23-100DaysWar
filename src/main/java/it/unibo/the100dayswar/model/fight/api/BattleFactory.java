package it.unibo.the100dayswar.model.fight.api;

import it.unibo.the100dayswar.model.unit.api.Combatant;

/**
 * inteface for creating istance of battle using the factory pattern.
 */
public interface BattleFactory {
    /**
     * create an istance of battle.
     * 
     * @param <T> type of the unit attacker
     * @param <U> type of the unit defender
     * @param attacker the unit defender
     * @param defender the unit attacker
     * @return return an istance of battle
     */
    <T extends Combatant, U extends Combatant> Battle<T, U> createBattle(T attacker, U defender);
}
