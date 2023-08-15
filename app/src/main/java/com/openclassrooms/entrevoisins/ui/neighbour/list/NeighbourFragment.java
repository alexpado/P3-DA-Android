package com.openclassrooms.entrevoisins.ui.neighbour.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.enums.NeighbourListMode;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private final NeighbourListMode   mode;
    private       NeighbourApiService mApiService;
    private       RecyclerView        mRecyclerView;

    public NeighbourFragment(NeighbourListMode mode) {

        this.mode = mode;
    }

    /**
     * Create a new instance of this {@link NeighbourFragment} associated to a specific list type
     * defined by the provided {@link NeighbourListMode}.
     *
     * @param mode
     *         The list display
     *
     * @return An instance with a specific behaviour.
     */
    public static NeighbourFragment newInstance(NeighbourListMode mode) {

        return new NeighbourFragment(mode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View    view    = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
        ));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {

        switch (this.mode) {
            case ALL: {
                List<Neighbour> neighbours = this.mApiService.getNeighbours();
                this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
                break;
            }
            case FAVORITES: {
                List<Neighbour> neighbours = this.mApiService.getFavoriteNeighbours();
                this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
                break;
            }
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        initList();
    }

    @Override
    public void onStart() {

        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {

        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     *         The event allowing to delete a neighbour
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        this.mApiService.deleteNeighbour(event.getNeighbour());
        initList();
    }

}
