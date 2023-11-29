package com.openclassrooms.entrevoisins.enums;

public enum NeighbourListMode {

    ALL,
    FAVORITES;

    /**
     * Retrieve the {@link NeighbourListMode} to used based on the page position provided.
     *
     * @param position
     *         Page position
     *
     * @return A {@link NeighbourListMode}.
     */
    public static NeighbourListMode fromPage(int position) {

        switch (position) {
            case 0:
                return ALL;
            case 1:
                return FAVORITES;
            default:
                throw new IllegalArgumentException("Unsupported page: " + position);
        }
    }
}
