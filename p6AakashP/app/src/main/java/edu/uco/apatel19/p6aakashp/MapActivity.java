package edu.uco.apatel19.p6aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnMapReadyCallback {
    private GoogleMap map;
    private String cityName;
    private LatLng cityPosition;
    private String temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        cityPosition = new LatLng(Double.parseDouble(intent.getStringExtra("Latitude")),
                Double.parseDouble(intent.getStringExtra("Longitude")));
        cityName = intent.getStringExtra("Name");
        temperature = "Current Temp: " + intent.getStringExtra("Temperature") + "°F";
    }

    public void onMapReady(GoogleMap map){
        this.map = map;

        CameraPosition camera = new CameraPosition.Builder()
                .target(cityPosition).zoom(9).build();
        this.map.clear();
        this.map.addMarker(new MarkerOptions().position(cityPosition).title(cityName).snippet(temperature));
        this.map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

    }

}
