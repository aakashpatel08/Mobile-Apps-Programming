package edu.uco.apatel19.p4aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class RegisterActivity extends Activity {
    private TextView displayName, displayPassword, displayDOB, displayGender, displaySkills, displayClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        displayName = (TextView) findViewById(R.id.DisplayName);
        displayPassword = (TextView) findViewById(R.id.DisplayPassword);
        displayDOB = (TextView) findViewById(R.id.DisplayDOB);
        displayGender = (TextView) findViewById(R.id.DisplayGender);
        displaySkills = (TextView) findViewById(R.id.DisplaySkills);
        displayClassification = (TextView) findViewById(R.id.DisplayClassification);


        Intent myIntent = getIntent();
        String name = myIntent.getStringExtra("Name");
        String password = myIntent.getStringExtra("Password");
        String dateOfBirth = myIntent.getStringExtra("DateOfBirth");
        String gender = myIntent.getStringExtra("Gender");
        String skills = myIntent.getStringExtra("Skills");
        String classification = myIntent.getStringExtra("Classification");
        displayName.setText(name);
        displayPassword.setText(password);
        displayDOB.setText(dateOfBirth);
        displayGender.setText(gender);
        displaySkills.setText(skills);
        displayClassification.setText(classification);
    }

}
