package com.linus.myrestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantActivity extends AppCompatActivity {
    public static final String TAG=RestaurantActivity.class.getSimpleName();
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.ListView) ListView mListView;

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
        getRestaurants(location);
    }

    public void getRestaurants(String location){
        final YelpService yelpService=new YelpService();
        yelpService.findRestaurants(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRestaurants = yelpService.processResults(response);
                RestaurantActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] restaurantNames=new String[mRestaurants.size()];
                        for (int i = 0; i < restaurantNames.length; i++) {
                            restaurantNames[i] = mRestaurants.get(i).getmName();
                        }


                        ArrayAdapter adapter = new ArrayAdapter(RestaurantActivity.this,
                                android.R.layout.simple_list_item_1, restaurantNames);
                        mListView.setAdapter(adapter);

                        for (Restaurant restaurant : mRestaurants) {
                            Log.d(TAG, "Name: " + restaurant.getmName());
                            Log.d(TAG, "Phone: " + restaurant.getmPhone());
                            Log.d(TAG, "Website: " + restaurant.getmWebsite());
                            Log.d(TAG, "Image url: " + restaurant.getmImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getmRating()));
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getmAddress()));
                            Log.d(TAG, "Categories: " + restaurant.getmCategories().toString());
                        }
                    }
                });
            }
        });
    }
}
