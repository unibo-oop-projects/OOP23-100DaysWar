package it.unibo.the100dayswar.model.pathfinder.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.pathfinder.api.PathFinder;

/**
 * Implementation of PathFinder using the BFS algorithm.
 */
public class BfsPathFinder implements PathFinder {
    private final Map<Cell, List<Cell>> adjacencyMap = new HashMap<>();
    /**
     * Constructs a BfsPathFinder with all cells in the map.
     * 
     * @param allCells A collection of all cells in the game map.
     */
    public BfsPathFinder(final Collection<Cell> allCells) {
        Objects.requireNonNull(allCells, "The cells of the map cannot be null");
        calculateAdjacency(allCells);
    }
    /**
     * A helper method that calculate the adjacency of the cells of the map
     * to get this algorithm lighter instead of compute the adjacencies
     * for every cell each time.
     * 
     * @param allCells the collection of all cells in the game map
     */
    private void calculateAdjacency(final Collection<Cell> allCells) {
        for (final Cell cell : allCells) {
            final List<Cell> adjacentCells = new ArrayList<>();
            for (final Cell potentialNeighbor : allCells) {
                if (cell.isAdiacent(potentialNeighbor)) {
                    adjacentCells.add(potentialNeighbor);
                }
            }
            adjacencyMap.put(cell, adjacentCells);
        }
    }
    /**
     * Finds all cells adjacent to the given cell.
     * 
     * @param cell the cell to find adjacent cells
     * @return a collection of adjacent cells
     */
    private Collection<Cell> getAdjacents(final Cell cell) {
        return adjacencyMap.getOrDefault(cell, Collections.emptyList());
    }
    /** 
     * {@inheritDoc}
     */
    @Override
    public List<Cell> findPath(final Cell start, final Cell destination) {
        if (start.equals(destination)) {
            return Collections.singletonList(start);
        }
        final Queue<Cell> frontier = new LinkedList<>();
        final Map<Cell, Cell> predecessors = new HashMap<>();
        final Set<Cell> visited = new HashSet<>();
        frontier.add(start);
        visited.add(start);

        while (!frontier.isEmpty()) {
            final Cell current = frontier.poll();

            if (current.equals(destination)) {
                return reconstructPath(predecessors, current);
            }

            getAdjacents(current).forEach(neighbor -> {
                if (!visited.contains(neighbor) && neighbor.isFree()) {
                    frontier.add(neighbor);
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            });
        }
        return Collections.emptyList();
    }
    /**
     * Reconstructs the path from the start cell to the destination cell.
     * 
     * @param predecessors a map of predecessors for each cell.
     * @param destination the destination cell.
     * @return a list of cells representing the path from start to destination.
     */
    private List<Cell> reconstructPath(final Map<Cell, Cell> predecessors, final Cell destination) {
        final List<Cell> path = new ArrayList<>();
        Cell tempCell = destination;
        while (tempCell != null) {
            path.add(0, tempCell);
            tempCell = predecessors.get(tempCell);
        }
        return path;
    }
}
