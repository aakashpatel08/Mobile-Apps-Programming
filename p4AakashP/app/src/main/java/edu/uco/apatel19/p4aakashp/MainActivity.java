package edu.uco.apatel19.p4aakashp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button signup_button;
    private Button department_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        signup_button = (Button) findViewById(R.id.Signup_Button);
        department_button = (Button) findViewById(R.id.Department_Button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(myIntent);
            }
        });

        department_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
