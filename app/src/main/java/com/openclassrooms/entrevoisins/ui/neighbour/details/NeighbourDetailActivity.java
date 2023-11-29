package com.openclassrooms.entrevoisins.ui.neighbour.details;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NEIGHBOUR_ID = "neighbourId";

    /**
     * Navigate to a new {@link NeighbourDetailActivity} using the provided neighbour id.
     *
     * @param activity
     *         The {@link FragmentActivity} from which the navigation occurs, used as context for
     *         the new {@link Intent}.
     * @param neighbourId
     *         The {@link Neighbour}'s identifier to display on the
     *         {@link NeighbourDetailActivity}.
     */
    public static void navigate(FragmentActivity activity, long neighbourId) {

        Intent intent = new Intent(activity, NeighbourDetailActivity.class);
        intent.putExtra(EXTRA_NEIGHBOUR_ID, neighbourId);
        ActivityCompat.startActivity(activity, intent, null);
    }

    // Specific UI Components
    @BindView(R.id.neighbour_detail_name)
    public TextView  uiNeighbourName;
    @BindView(R.id.neighbour_avatar)
    public ImageView uiNeighbourAvatar;
    @BindView(R.id.neighbour_detail_address)
    public TextView  uiNeighbourAddress;
    @BindView(R.id.neighbour_detail_phone_number)
    public TextView  uiNeighbourPhoneNumber;
    @BindView(R.id.neighbour_detail_about_me)
    public TextView  uiNeighbourAboutMe;
    @BindView(R.id.neighbour_detail_social)
    public TextView  uiNeighbourSocial;

    // Global UI Component
    @BindView(R.id.toolbar)
    public Toolbar     toolbar;
    @BindView(R.id.toggle_favorite)
    public ImageButton uiFavoriteButton;

    private NeighbourApiService service;
    private Neighbour           neighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_view_neighbour);

        long neighbourId = this.getIntent().getLongExtra(EXTRA_NEIGHBOUR_ID, -1);

        this.service   = DI.getNeighbourApiService();
        this.neighbour = this.service.findById(neighbourId);

        if (this.neighbour == null) {
            this.finish();
            return;
        }

        this.registerUi();

        this.setSupportActionBar(this.toolbar);
        ActionBar bar = this.getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * Link the UI to this {@link NeighbourDetailActivity}.
     */
    private void registerUi() {

        ButterKnife.bind(this);
        this.linkNeighbour();
        this.uiFavoriteButton.setOnClickListener(this::onFavoriteButtonPressed);
    }

    /**
     * Set UI components values based on this {@link NeighbourDetailActivity}'s {@link Neighbour}.
     */
    private void linkNeighbour() {

        this.uiNeighbourName.setText(this.neighbour.getName());
        this.uiNeighbourAddress.setText(this.neighbour.getAddress());
        this.uiNeighbourPhoneNumber.setText(this.neighbour.getPhoneNumber());
        this.uiNeighbourAboutMe.setText(this.neighbour.getAboutMe());
        this.uiNeighbourSocial.setText(String.format(
                "www.facebook.com/%s",
                this.neighbour.getName().toLowerCase()
        ));

        Glide.with(this.uiNeighbourAvatar.getContext())
             .load(this.neighbour.getAvatarUrl())
             .into(this.uiNeighbourAvatar);

        if (this.neighbour.isFavorite()) {
            this.uiFavoriteButton.setImageResource(R.drawable.baseline_star_24);
            this.uiFavoriteButton.setColorFilter(getResources().getColor(R.color.colorYellow));
        } else {
            this.uiFavoriteButton.setImageResource(R.drawable.baseline_star_border_24);
            this.uiFavoriteButton.clearColorFilter();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return false;
    }

    public void onFavoriteButtonPressed(View view) {

        this.service.toggleFavoriteNeighbour(this.neighbour);

        if (this.neighbour.isFavorite()) {
            Snackbar.make(this.findViewById(R.id.base), R.string.message_favorite_added, BaseTransientBottomBar.LENGTH_LONG).show();
        } else {
            Snackbar.make(this.findViewById(R.id.base), R.string.message_favorite_remove, BaseTransientBottomBar.LENGTH_LONG).show();
        }

        this.linkNeighbour();
    }

}
