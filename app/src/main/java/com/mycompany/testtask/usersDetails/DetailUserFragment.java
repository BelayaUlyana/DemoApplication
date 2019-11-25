package com.mycompany.testtask.usersDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.POJO.User;
import com.mycompany.testtask.R;

public class DetailUserFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private Double lat, lng;
    private String address;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.mapView);

        mapView.onCreate(null);
        mapView.onResume();
    }

    public void setText(Intent intent) {
        TextView textViewName, textViewEmail, textViewPhone;

        textViewName = getView().findViewById(R.id.name);
        textViewEmail = getView().findViewById(R.id.email);
        textViewPhone = getView().findViewById(R.id.phone);
        WebView webView = getView().findViewById(R.id.webView);

        User user = intent.getParcelableExtra("user");
        setupMap(user);
        textViewName.setText(new StringBuilder("Name: ").append(user.getName()));
        textViewEmail.setText(new StringBuilder("Email: ").append(user.getEmail()));
        textViewPhone.setText(new StringBuilder("Phone: ").append(user.getPhone()));
        webView.loadUrl("https://www." + user.getWebsite() + "/");

    }

    private void setupMap(User user) {
        lat = Double.valueOf(user.getAddress().getGeo().getLat());
        lng = Double.valueOf(user.getAddress().getGeo().getLng());
        address = user.getAddress().getStreet().concat(", ").concat(user.getAddress().getSuite());
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
    public void onMapReady(GoogleMap googleMap) {
        LatLng ny = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(ny).title(address));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ny).zoom(5).tilt(30).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}