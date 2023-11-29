package com.openclassrooms.entrevoisins.ui.neighbour.list;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.openclassrooms.entrevoisins.events.NeighbourClickedEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour.details.NeighbourDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class NeighbourFragment extends Fragment {

    NeighbourApiService mApiService;
    RecyclerView        mRecyclerView;

    /**
     * Create a new instance of this {@link NeighbourFragment}
     *
     * @return An instance with a specific behaviour.
     */
    public static NeighbourFragment newInstance() {

        return new NeighbourFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    public int getLayout() {
        return R.layout.fragment_neighbour_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View    view    = inflater.inflate(this.getLayout(), container, false);
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

        List<Neighbour> neighbours = this.mApiService.getNeighbours();
        this.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
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

        this.mApiService.deleteNeighbour(event.getNeighbour());
        initList();
    }

    @Subscribe
    public void onNeighbourClicked(NeighbourClickedEvent event) {

        NeighbourDetailActivity.navigate(this.getActivity(), event.getNeighbour().getId());
    }

}
