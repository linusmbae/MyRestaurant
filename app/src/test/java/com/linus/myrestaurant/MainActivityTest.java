package com.linus.myrestaurant;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertTrue;
@Config(sdk = Build.VERSION_CODES.O_MR1)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity= Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent()throws Exception {
        TextView appNameTextView=activity.findViewById(R.id.appNameTextView);
        assertTrue("MyRestaurants".equals(appNameTextView.getText().toString()));
    }

    @Test
    public void secondActivityStarted()throws Exception {
        activity.findViewById(R.id.findRestaurantsButton).performClick();
        Intent expectedIntent=new Intent(activity,RestaurantActivity.class);
        ShadowActivity shadowActivity=org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent=shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
