package com.example.harshal.trashbinfinderapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng RIT = new LatLng(43.0847, -77.6739);
        mMap.addMarker(new MarkerOptions().position(RIT).title("Marker in RIT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(RIT, 18.2f));

        new FireBaseDatabaseHelper().readTrashCans(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<TrashCan> trashCans, List<String> keys) {
                for (int i=0;i<trashCans.size();i++){
                    LatLng tempPos = new LatLng(trashCans.get(i).getLat(),trashCans.get(i).getLong());
                    mMap.addMarker(new MarkerOptions().position(tempPos).icon(BitmapDescriptorFactory.fromResource(R.drawable.tcan)).title("TrashBin"));
                }
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
