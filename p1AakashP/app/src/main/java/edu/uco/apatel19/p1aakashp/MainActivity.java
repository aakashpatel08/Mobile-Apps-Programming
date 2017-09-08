package edu.uco.apatel19.p1aakashp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button name_button;
    private Button degree_button;
    private Button clear_button;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        name_button = (Button) findViewById(R.id.Name_Button);
        degree_button = (Button) findViewById(R.id.Degree_Button);
        clear_button = (Button) findViewById(R.id.Clear_Button);
        view = (TextView) findViewById(R.id.Display);

        name_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                view.setText("Aakash Patel");
            }
        });

        degree_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                view.setText("Computer Science");
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                view.setText("");
            }
        });
    }
}
