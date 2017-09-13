package edu.uco.apatel19.p2aakashp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    private Button firstname_button;
    private Button age_button;
    private TextView display_firstname;
    private TextView display_age;
    private int REQUEST_CODE_FIRSTNAME = 1;
    private int REQUEST_CODE_AGE = 2;
    private String name_to_be_displayed = "";
    private int age_to_be_displayed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname_button = (Button) findViewById(R.id.FName_Button);
        age_button = (Button) findViewById(R.id.Age_Button);
        display_firstname = (TextView) findViewById(R.id.Display_Name);
        display_age = (TextView) findViewById(R.id.Display_Age);

        firstname_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ReadFirstnameActivity.class);
                myIntent.putExtra("FirstName", name_to_be_displayed);
                startActivityForResult(myIntent, REQUEST_CODE_FIRSTNAME);
            }
        });
        age_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ReadAgeActivity.class);
                myIntent.putExtra("Age", age_to_be_displayed);
                startActivityForResult(myIntent, REQUEST_CODE_AGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent myIntent){
        if (myIntent == null){

        } else if (requestCode != 1 && requestCode != 2){

        } else if (requestCode == 1){
            name_to_be_displayed = myIntent.getStringExtra("FirstName");
            display_firstname.setText("Hi," + name_to_be_displayed + "!");
        } else {
            age_to_be_displayed = myIntent.getIntExtra("Age",RESULT_OK);
            display_age.setText("You will be " + (age_to_be_displayed + 1) + " years old next year.");
        }
    }
}
