package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourClickedEvent {

    private final Neighbour neighbour;

    public NeighbourClickedEvent(Neighbour neighbour) {

        this.neighbour = neighbour;
    }

    public Neighbour getNeighbour() {

        return neighbour;
    }

}
