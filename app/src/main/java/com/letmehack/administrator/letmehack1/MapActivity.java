package com.letmehack.administrator.letmehack1;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    private android.support.v7.widget.Toolbar mToolBar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mToolBar = findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("University Map");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng mana1 = new LatLng(6.713272, 80.789965);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mana1, 15);
        map.animateCamera(cameraUpdate);



        LatLng sinharaja = new LatLng(6.717384,80.787229);
        map.addMarker(new MarkerOptions().position(sinharaja).title("Sinharaja Boys Hostel").snippet("Sabaragamuwa University").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        LatLng walawa = new LatLng(6.713272, 80.789965);
        map.addMarker(new MarkerOptions().position(walawa).title("Walawa Girls Hostel").snippet("Sabaragamuwa University").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));



        LatLng sola = new LatLng(6.714452, 80.786717);
        map.addMarker(new MarkerOptions().position(sola).title("Registration Area").snippet("Poolside").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        LatLng metta = new LatLng(6.708352, 80.790118);
        map.addMarker(new MarkerOptions().position(metta).title("Roof-Top Cafe").snippet("Sabaragamuwa University").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));



        LatLng mana = new LatLng(6.709085, 80.790062);
        map.addMarker(new MarkerOptions().position(mana).title("Management Auditorium").snippet("Sabaragamuwa Univer    sity").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


    }
}

