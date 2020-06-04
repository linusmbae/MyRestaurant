package com.linus.myrestaurant.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linus.myrestaurant.Business;
import com.linus.myrestaurant.Constants;
import com.linus.myrestaurant.R;
import com.linus.myrestaurant.YelpApi;
import com.linus.myrestaurant.YelpBusinessesSearchResponse;
import com.linus.myrestaurant.adapters.RestaurantListAdapter;
import com.linus.myrestaurant.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantListActivity extends AppCompatActivity {
    public static final String TAG= RestaurantListActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar)ProgressBar mProgressBar;

    private RestaurantListAdapter mAdapter;

    public List<Business> restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        Log.d("Shared Pref Location", mRecentAddress);

        YelpApi client= YelpClient.getClient();
        retrofit2.Call<YelpBusinessesSearchResponse> call=client.getRestaurants(location,"restaurants");
        call.enqueue(new retrofit2.Callback<YelpBusinessesSearchResponse>() {

            @Override
            public void onResponse(retrofit2.Call<YelpBusinessesSearchResponse> call, retrofit2.Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    restaurants = response.body().getBusinesses();
                    mAdapter = new RestaurantListAdapter(RestaurantListActivity.this, restaurants);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(RestaurantListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
                }


            @Override
            public void onFailure(retrofit2.Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}
