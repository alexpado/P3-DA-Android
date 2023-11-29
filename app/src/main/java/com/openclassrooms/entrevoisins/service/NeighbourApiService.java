package com.openclassrooms.entrevoisins.service;

import androidx.annotation.Nullable;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Find any {@link Neighbour} matching the provided identifier.
     *
     * @param neighbourId
     *         The {@link Neighbour}'s identifier
     *
     * @return An optional {@link Neighbour}. Will be {@code null} if no {@link Neighbour} with the
     *         provided id was found.
     */
    @Nullable
    Neighbour findById(long neighbourId);

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
