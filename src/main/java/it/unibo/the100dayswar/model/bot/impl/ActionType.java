package it.unibo.the100dayswar.model.bot.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;

import it.unibo.the100dayswar.commons.utilities.impl.Score;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.cell.api.BuildableCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.pathfinder.impl.BfsPathFinder;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.TowerType;
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
                    // if the bot has no soldiers, it should buy one
                    return new Score(HIGH_PRIORITY_SCORE);
                } else if (soldiers.size() >= 3) {
                    // if the bot has more than 3 soldiers, it should not buy another one
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
     * Type that represents the purchase of a tower in a random 
     * position near the spawn point so the tower can defend it.
     */
    PURCHASE_TOWER {
        private static final int DEFAULT_SCORE = 1;
        private static final Random RANDOM = new Random();

        /**
         * {@inheritDoc}
         */
        @Override
        protected boolean canPerform(final BotPlayer botPlayer) {
            return botPlayer.getBankAccount().getBalance() >= TowerType.BASIC.getPrice();
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
                    // if the bot has less towers than half of the soldiers, it should buy one
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
                final Cell towerPosition = findRandomTowerPosition(botPlayer);
                if (towerPosition != null) {
                    final Unit tower = new BasicTowerImpl(botPlayer, towerPosition);
                    botPlayer.buyUnit(tower);
                }
            }
        }

        /**
         * Finds a random cell near the spawn point but not adjacent.
         * 
         * @param botPlayer The bot player placing the tower
         * @return A randomly cell where put the tower on, or null if no valid cell exists
         */
        private Cell findRandomTowerPosition(final BotPlayer botPlayer) {
            final Cell spawnPoint = botPlayer.getSpawnPoint();

            // Filter cells: near the spawn point but not adjacent, and buildable
            final List<Cell> validCells = botPlayer.getAllCells().stream()
                .filter(cell -> !cell.isAdiacent(spawnPoint))
                .filter(cell -> isNearSpawn(cell, spawnPoint))
                .filter(cell -> cell instanceof BuildableCell)
                .collect(Collectors.toList());

            return validCells.isEmpty() ? null : validCells.get(RANDOM.nextInt(validCells.size()));
        }

        /**
         * Checks if a cell is near the spawn point but not adjacent.
         * 
         * @param cell The cell to check.
         * @param spawnPoint The spawn point of the bot.
         * @return True if the cell is near but not adjacent, false otherwise.
         */
        private boolean isNearSpawn(final Cell cell, final Cell spawnPoint) {
            final int distance = Math.abs(cell.getPosition().getX() - spawnPoint.getPosition().getX()) 
                + Math.abs(cell.getPosition().getY() - spawnPoint.getPosition().getY());
            return distance > 1 && distance <= 3;
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
            return evaluateOrNonPerformable(botPlayer, () -> {
                if (botPlayer.getSoldiers().size() >= 2) {
                    return new Score(DEFAULT_SCORE + 2);
                }
                return new Score(DEFAULT_SCORE);
            });
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void execute(final BotPlayer botPlayer) {
            final Random random = new Random();
            final Set<Soldier> soldiers = botPlayer.getSoldiers();
            if (canPerform(botPlayer)) {
                // choose a random soldier
                final Soldier unitToMove = Iterables.get(soldiers, RANDOM.nextInt(soldiers.size()));
                // using the BFS algorithm to find the next cell in the path to the enemy spawn point
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
            final BfsPathFinder pathFinder = new BfsPathFinder(botPlayer.getAllCells());

            final Cell start = unit.getPosition();
            final Cell destination = botPlayer.enemySpawnPoint();

            final List<Cell> path = pathFinder.findPath(start, destination);
            //return the second cell of the path because the first one is the start cell
            return (path.size() > 1) ? path.get(1) : null;
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
