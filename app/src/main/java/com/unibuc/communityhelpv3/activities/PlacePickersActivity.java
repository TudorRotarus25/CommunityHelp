package com.unibuc.communityhelpv3.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.unibuc.communityhelpv3.MyApplication;
import com.unibuc.communityhelpv3.R;
import com.unibuc.communityhelpv3.managers.NetworkManager;
import com.unibuc.communityhelpv3.pojos.interfaces.AddLocationListener;

public class PlacePickersActivity extends AppCompatActivity implements AddLocationListener {

    private final String TAG = getClass().getSimpleName();

    private final int PLACE_PICKER_1_REQUEST = 1;
    private final int PLACE_PICKER_2_REQUEST = 2;

    private TextView location1NameTextView;
    private TextView location2NameTextView;
    private TextView location1PickerTextView;
    private TextView location2PickerTextView;
    private Button continueButton;

    private String location1Name;
    private String location1Address;
    private Double location1Lat;
    private Double location1Lon;

    private String location2Name;
    private String location2Address;
    private Double location2Lat;
    private Double location2Lon;

    NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_pickers);

        checkIfLocationExists();

        initLayout();
    }

    private void checkIfLocationExists() {
        if(((MyApplication) getApplication()).getPrefManager().isLocationInserted()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void initLayout() {

        networkManager = NetworkManager.getInstance();

        location1NameTextView = (TextView) findViewById(R.id.activity_place_pickers_location1_name_textView);
        location2NameTextView = (TextView) findViewById(R.id.activity_place_pickers_location2_name_textView);
        location1PickerTextView = (TextView) findViewById(R.id.activity_place_pickers_location1_picker_textView);
        location2PickerTextView = (TextView) findViewById(R.id.activity_place_pickers_location2_picker_textView);
        continueButton = (Button) findViewById(R.id.activity_place_pickers_continue_button);

        location1PickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(PlacePickersActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_1_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        location2PickerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(PlacePickersActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_2_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (!isHomeLocationEmpty()) {
                    networkManager.addLocation(accessToken.getToken(), location1Name, location1Address, location1Lat, location2Lon, AddLocationListener.TYPE_HOME, PlacePickersActivity.this);
                }
                if (!isWorkLocationEmpty()) {
                    networkManager.addLocation(accessToken.getToken(), location2Name, location2Address, location2Lat, location2Lon, AddLocationListener.TYPE_WORK, PlacePickersActivity.this);
                }
            }
        });
    }

    private boolean isHomeLocationEmpty() {

        if (location1Lat != null && location1Lon != null) {
            return false;
        }
        Toast.makeText(this, "Home location is mandatory", Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean isWorkLocationEmpty() {
        if (location2Lat != null && location2Lon != null) {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_1_REQUEST && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(data, this);
            location1Name = place.getName().toString();
            location1Address = place.getAddress().toString();
            location1Lat = place.getLatLng().latitude;
            location1Lon = place.getLatLng().longitude;

            Log.i(TAG, location1Name + " - " + location1Address + ", (" + location1Lat + ", " + location1Lon + ")");

            if (!location1Name.contains("(")) {
                location1PickerTextView.setText(location1Name);
            } else {
                location1PickerTextView.setText(location1Address);
            }

        } else if (requestCode == PLACE_PICKER_2_REQUEST && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(data, this);
            location2Name = place.getName().toString();
            location2Address = place.getAddress().toString();
            location2Lat = place.getLatLng().latitude;
            location2Lon = place.getLatLng().longitude;

            Log.i(TAG, location2Name + " - " + location2Address + ", (" + location2Lat + ", " + location2Lon + ")");

            if (!location2Name.contains("(")) {
                location2PickerTextView.setText(location2Name);
            } else {
                location2PickerTextView.setText(location2Address);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onAddLocationSuccess(int type) {
        if (type == AddLocationListener.TYPE_HOME) {

            ((MyApplication) getApplication()).getPrefManager().addLocationInserted();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onAddLocationFailed() {
        Toast.makeText(this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
    }
}
