package com.openclassrooms.entrevoisins.ui.neighbour.list;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.NeighbourClickedEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavoriteNeighbourFragment extends NeighbourFragment {

    /**
     * Create a new instance of this {@link FavoriteNeighbourFragment}.
     *
     * @return An instance with a specific behaviour.
     */
    public static FavoriteNeighbourFragment newInstance() {

        return new FavoriteNeighbourFragment();
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {

        List<Neighbour> neighbours = this.mApiService.getFavoriteNeighbours();
        this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
    }

    @Override
    public int getLayout() {

        return R.layout.fragment_fav_neighbour_list;
    }

    @Override
    public void onResume() {

        super.onResume();
        initList();
    }

    @Override
    public void onStart() {

        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {

        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     *         The event allowing to delete a neighbour
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        initList();
    }

    @Override
    public void onNeighbourClicked(NeighbourClickedEvent event) {

        // Nothing
    }

}
