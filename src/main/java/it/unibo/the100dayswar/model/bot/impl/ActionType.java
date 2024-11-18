package it.unibo.the100dayswar.model.bot.impl;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import com.google.common.collect.Iterables;

import it.unibo.the100dayswar.commons.utilities.impl.Score;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.impl.BasicTowerImpl;
import it.unibo.the100dayswar.model.unit.api.Unit;

/**
 * An enum that represents the possible actions that a bot can take.
 */
public enum ActionType {
    /**
     * Type that represents the purchase of a soldier.
     */
    PURCHASE_SOLDIER {
        private static final int DEFAULT_SCORE = 3;

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean canPerform(final BotPlayer botPlayer) {
            return botPlayer.getBankAccount().getBalance() >= Soldier.DEFAULT_COST;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Score evaluate(final BotPlayer botPlayer) {
            return evaluateOrNonPerformable(botPlayer, () -> {
                final Set<Soldier> soldiers = botPlayer.getSoldiers();
                if (soldiers.isEmpty()) {
                    return new Score(HIGH_PRIORITY_SCORE);
                } else if (soldiers.size() >= 3) {
                    return new Score(NON_PERFORMABLE_SCORE);
                }
                return new Score(DEFAULT_SCORE);
            });
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final BotPlayer botPlayer) {
            if (canPerform(botPlayer)) {
                final Soldier soldier = new SoldierImpl(botPlayer);
                botPlayer.buyUnit(soldier);
            }
        }
    },
    /**
     * Type that represents the purchase of a tower.
     */
    PURCHASE_TOWER {
        private static final int DEFAULT_SCORE = 1;

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean canPerform(final BotPlayer botPlayer) {
            // PlaceHolder --> BasicTower.DEFAULT_COST;
            return botPlayer.getBankAccount().getBalance() >= DEFAULT_SCORE;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Score evaluate(final BotPlayer botPlayer) {
            return evaluateOrNonPerformable(botPlayer, () -> {
                final int soldierCount = botPlayer.getSoldiers().size();
                final int towerCount = botPlayer.getTowers().size();
                if (towerCount < soldierCount / 2) {
                    return new Score(HIGH_PRIORITY_SCORE);
                }
                return new Score(DEFAULT_SCORE);
            });
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final BotPlayer botPlayer) {
            if (canPerform(botPlayer)) {
                // botPlayer.getSpawnPoint() --> PlaceHolder to wait for the implementation of
                // the calculation of
                // a random position for the tower near to the bot spawn point.
                final Unit tower = new BasicTowerImpl(botPlayer, botPlayer.getSpawnPoint());
                botPlayer.buyUnit(tower);
            }
        }
    },
    /**
     * Type that rapresents the action of upgrading a unit.
     * For a simplier logic this action will upgrade the unit
     * with the lowest cost to upgrade.
     */
    UPGRADE_UNIT {
        private static final int DEFAULT_SCORE = 2;

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean canPerform(final BotPlayer botPlayer) {
            return botPlayer.getUnits().stream()
                    .anyMatch(unit -> unit.canUpgrade()
                            && botPlayer.getBankAccount().getBalance() >= unit.costToUpgrade());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Score evaluate(final BotPlayer botPlayer) {
            return evaluateOrNonPerformable(botPlayer, () -> new Score(DEFAULT_SCORE));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final BotPlayer botPlayer) {
            if (canPerform(botPlayer)) {
                botPlayer.getUnits().stream()
                        .filter(Unit::canUpgrade)
                        .min(Comparator.comparingInt(Unit::costToUpgrade))
                        .ifPresent(botPlayer::upgradeUnit);
            }
        }
    },
    /**
     * Type that rapresents the action of moving a unit.
     * For a simplier logic this action chooses a random unit
     * that can move and with the use of an algorithm, that
     * finds the shortest path to the enemy's spawn point, it moves
     * the unit in the correct direction.
     */
    MOVE_UNIT {
        private static final int DEFAULT_SCORE = 4;
        private static final Random RANDOM = new Random();

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean canPerform(final BotPlayer botPlayer) {
            return !botPlayer.getSoldiers().isEmpty();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Score evaluate(final BotPlayer botPlayer) {
            return evaluateOrNonPerformable(botPlayer, () -> new Score(DEFAULT_SCORE));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final BotPlayer botPlayer) {
            final Set<Soldier> soldiers = botPlayer.getSoldiers();
            if (canPerform(botPlayer)) {
                final Soldier unitToMove = Iterables.get(soldiers, RANDOM.nextInt(soldiers.size()));
                final Cell destination = determineDestination(botPlayer, unitToMove);

                if (destination != null) {
                    botPlayer.moveUnit(unitToMove, destination);
                }
            }
        }

        /**
         * Determines the destination for the unit using BFS logic.
         * 
         * @param botPlayer the bot player
         * @param unit      the unit to move
         * @return the destination cell
         */
        private Cell determineDestination(final BotPlayer botPlayer, final Unit unit) {
            //Placeholder
            botPlayer.makeMove();
            //PlaceHolder
            unit.takeDamage(10);
            return null;
        }
    };

    private static final int NON_PERFORMABLE_SCORE = -1;
    private static final int HIGH_PRIORITY_SCORE = 10;

    /**
     * Determines if the bot player can perform the move.
     * 
     * @param botPlayer the bot player
     * @return true if the bot player can perform the move, false otherwise
     */
    protected abstract boolean canPerform(BotPlayer botPlayer);

    /**
     * Evaluates the move based on the bot player's state.
     * 
     * @param botPlayer the bot player
     * @return the score of the move
     */
    protected abstract Score evaluate(BotPlayer botPlayer);

    /**
     * Executes the move.
     * 
     * @param botPlayer the bot player
     */
    protected abstract void execute(BotPlayer botPlayer);

    /**
     * Utility method that returns a non-performable score if the action can't be
     * performed,
     * otherwise calculates the score with the provided scorer.
     * 
     * @param botPlayer the bot player
     * @param scorer    a supplier that provides the score if the action can be
     *                  performed
     * @return the score of the move
     */
    protected Score evaluateOrNonPerformable(final BotPlayer botPlayer, final Supplier<Score> scorer) {
        return canPerform(botPlayer) ? scorer.get() : new Score(NON_PERFORMABLE_SCORE);
    }
}
