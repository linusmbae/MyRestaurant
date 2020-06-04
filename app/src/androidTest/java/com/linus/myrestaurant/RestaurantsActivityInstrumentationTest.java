package com.linus.myrestaurant;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.linus.myrestaurant.ui.RestaurantListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class RestaurantsActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<RestaurantListActivity> activityTestRule=
            new ActivityTestRule<>(RestaurantListActivity.class);

    @Test
    public void listItemClickDisplaysToastWithCorrectRestaurant()throws Exception {
        View activityDecorView=activityTestRule.getActivity().getWindow().getDecorView();
        String restaurantName="Sweet Hereafter";
        onData(anything())
                .inAdapterView(withId(R.id.ListView))
                .atPosition(0)
                .perform(click());
        onView(withText(restaurantName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(restaurantName)));
    }
}
