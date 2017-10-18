package edu.uco.apatel19.p7aakashp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class WebMapActivity extends Activity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_map);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.webMap);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng defaultCameraLocation = new LatLng(35.654014, -97.472785);
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[0].getCoordinates()).title(MainActivity.departments[0].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.bio)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[1].getCoordinates()).title(MainActivity.departments[1].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.chem)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[2].getCoordinates()).title(MainActivity.departments[2].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.cs)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[3].getCoordinates()).title(MainActivity.departments[3].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.engr)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[4].getCoordinates()).title(MainActivity.departments[4].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.funeral)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[5].getCoordinates()).title(MainActivity.departments[5].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.math)));
        this.googleMap.addMarker(new MarkerOptions().position(MainActivity.departments[6].getCoordinates()).title(MainActivity.departments[6].getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.nurse)));

        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < MainActivity.departments.length; i++) {
                    if (marker.getTitle().equals(MainActivity.departments[i].getName())) {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW);
                        myIntent.setData(Uri.parse(MainActivity.departments[i].getWebsite()));
                        startActivity(myIntent);
                    }
                }
                return true;
            }
        });
        CameraPosition camera = new CameraPosition.Builder().target(defaultCameraLocation).zoom(18).build();
        this.googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
    }
}
