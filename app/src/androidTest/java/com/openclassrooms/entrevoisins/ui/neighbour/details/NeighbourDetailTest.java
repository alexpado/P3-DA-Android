package com.openclassrooms.entrevoisins.ui.neighbour.details;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.content.Intent;
import android.os.Bundle;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    static Intent intent;
    static {
        intent = new Intent(ApplicationProvider.getApplicationContext(), NeighbourDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong(NeighbourDetailActivity.EXTRA_NEIGHBOUR_ID, 1);
        intent.putExtras(bundle);
    }

    @Rule
    public ActivityScenarioRule<NeighbourDetailActivity> activityScenarioRule = new ActivityScenarioRule<>(intent);

    @Test
    public void myNeighbourDetail_shouldDisplayName() {

        onView(ViewMatchers.withId(R.id.neighbour_detail_name))
                .check(matches(withText(containsString("Caroline"))));
    }


}
