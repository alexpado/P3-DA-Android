package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my favorites {@link Neighbour}
     *
     * @return
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     *
     * @param neighbour
     *         The neighbour to delete
     */
    void deleteNeighbour(Neighbour neighbour);

    void toggleFavoriteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     *
     * @param neighbour
     *         The neighbour to register
     */
    void createNeighbour(Neighbour neighbour);

}
