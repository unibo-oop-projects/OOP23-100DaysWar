package it.unibo.the100dayswar.commons.utilities.impl;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.model.player.api.Player;

/**
 * An implementation of the Observer pattern that provide the distribution of resources to the players.
 */
public class ResourceDistributor implements Observer<Player> {

    private final ResourceGenerator generator;

    /**
     * Constructor for the ResourceDistributor from the given parameters.
     * 
     * @param generator the generator of the resources
     */
    public ResourceDistributor(final ResourceGenerator generator) {
        this.generator = generator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Player source) {
        source.earnResources(generator.getAmount());
    }
}
