package com.linus.myrestaurant.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linus.myrestaurant.Constants;
import com.linus.myrestaurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.findRestaurantsButton) Button mFindRestaurantButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mFindRestaurantButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
       if (v==mFindRestaurantButton)
       {
//           String location=mLocationEditText.getText().toString();
//           addToSharedPreferences(location);
//           Intent intent=new Intent(MainActivity.this, RestaurantListActivity.class);
//          intent.putExtra("location",location);
//           startActivity(intent);

           String location = mLocationEditText.getText().toString();
           if(!(location).equals("")) {
               addToSharedPreferences(location);
           }
           Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
           intent.putExtra("location",location);
           startActivity(intent);
       }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }}
