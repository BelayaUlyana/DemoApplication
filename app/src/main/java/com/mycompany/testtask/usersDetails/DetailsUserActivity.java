package com.mycompany.testtask.usersDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;


public class DetailsUserActivity extends AppCompatActivity implements OnMapReadyCallback {


    private User user;
    private MapView mapView;
    private GoogleMap mMap;

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_user);

        TextView textViewName, textViewEmail, textViewPhone;
        textViewName = findViewById(R.id.name);
        textViewEmail = findViewById(R.id.email);
        textViewPhone = findViewById(R.id.phone);
        WebView webView = findViewById(R.id.webView);

        user = getIntent().getParcelableExtra("user");

        textViewName.setText(new StringBuilder("Name: ").append(user.getName()));
        textViewEmail.setText(new StringBuilder("Email: ").append(user.getEmail()));
        textViewPhone.setText(new StringBuilder("Phone: ").append(user.getPhone()));

//        webView.loadUrl("https://www." + user.getWebsite() + "/");
        webView.loadUrl("https://www.google.com/");

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        Bundle mapViewBundle = bundle.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            bundle.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat = Double.valueOf(user.getAddress().getGeo().getLat());
        Double lng = Double.valueOf(user.getAddress().getGeo().getLng());
        String address = user.getAddress().getStreet().concat(", ").concat(user.getAddress().getSuite());
        Log.e("LatLng", String.valueOf(lat).concat(String.valueOf(lng)));
        LatLng ny = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(ny).title(address));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ny).zoom(16).bearing(90).tilt(30).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}


