package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    private final Neighbour neighbour;

    /**
     * Constructor.
     *
     * @param neighbour
     *         The neighbour to delete
     */
    public DeleteNeighbourEvent(Neighbour neighbour) {

        this.neighbour = neighbour;
    }

    /**
     * Neighbour to delete
     */
    public Neighbour getNeighbour() {

        return neighbour;
    }

}
