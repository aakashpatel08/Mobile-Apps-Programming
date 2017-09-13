package edu.uco.apatel19.p2aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReadAgeActivity extends Activity {
    private TextView show_age;
    private EditText write_age;
    private Button enter_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_age);
        show_age = (TextView) findViewById(R.id.Display_Get_Age);
        write_age = (EditText) findViewById(R.id.Edit_Age);
        enter_age = (Button) findViewById(R.id.Enter_Age_Button);

        int age = getIntent().getIntExtra("Age", RESULT_OK);
        if (age >= 20){
            write_age.setText("" + age);
        }
        enter_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = 0;
                try{
                    age = Integer.parseInt(write_age.getText().toString());
                } catch (NumberFormatException e){
                }

                if (age >= 20){
                    Intent myIntent = new Intent();
                    myIntent.putExtra("Age", age);
                    setResult(RESULT_OK, myIntent);
                    show_age.setText("Age set successfully!");
                } else {
                    show_age.setText("Invalid age entered");
                }
            }
        });
    }

}
