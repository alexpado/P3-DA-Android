package com.openclassrooms.entrevoisins.service;

import androidx.annotation.Nullable;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

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
    @Override
    public Neighbour findById(long neighbourId) {

        for (Neighbour neighbour : this.getNeighbours()) {
            if (neighbour.getId() == neighbourId) {
                return neighbour;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {

        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {

        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {

        neighbours.add(neighbour);
    }

    /**
     * Get all my favorites {@link Neighbour}
     *
     * @return A {@link List} where all {@link Neighbour#isFavorite()} returns true.
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        List<Neighbour> favorites = new ArrayList<>();
        for (Neighbour neighbour : this.neighbours) {
            if (neighbour.isFavorite()) {
                favorites.add(neighbour);
            }
        }

        return favorites;
    }

    /**
     * Toggle the favorite state for the provided neighbour.
     *
     * @param neighbour
     *         The neighbour for which the state should be toggled.
     */
    @Override
    public void toggleFavoriteNeighbour(Neighbour neighbour) {

        neighbour.setFavorite(!neighbour.isFavorite());
    }

}
