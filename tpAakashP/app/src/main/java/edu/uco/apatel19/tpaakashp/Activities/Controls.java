package edu.uco.apatel19.tpaakashp.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.uco.apatel19.tpaakashp.R;

public class Controls extends Activity {
    String[] controlsNameArray = {"Move Up", "Move Down", "Move Left", "Move Right", "Aim", "Shoot"};
    String[] controlsArray = {"UP Arrow", "DOWN Arrow", "LEFT Arrow", "RIGHT Arrow", "MOUSE", "LEFT MOUSE CLICK"};
    private ListView controlsName, controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        ArrayAdapter controlsNameAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, controlsNameArray);
        ArrayAdapter controlsAdapter = new ArrayAdapter<>(this, R.layout.activity_listview, controlsArray);
        controlsName = (ListView) findViewById(R.id.ControlsName);
        controls = (ListView) findViewById(R.id.ControlsList);
        controlsName.setAdapter(controlsNameAdapter);
        controls.setAdapter(controlsAdapter);
    }

}
