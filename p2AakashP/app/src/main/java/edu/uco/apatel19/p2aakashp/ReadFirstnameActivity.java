package edu.uco.apatel19.p2aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReadFirstnameActivity extends Activity {
    private TextView show_name;
    private EditText write_fname;
    private Button enter_fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_first_name);
        show_name = (TextView) findViewById(R.id.Display_Get_Name);
        write_fname = (EditText) findViewById(R.id.Write_FName);
        enter_fname = (Button) findViewById(R.id.Enter_FName_Button);

        String name = getIntent().getStringExtra("FirstName");
        if (name.length() >= 3){
            write_fname.setText(name);
        }
        enter_fname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = write_fname.getText().toString();
                if (fname.length() < 3){
                    show_name.setText("Invalid name. Min 3 chars");
                } else {
                    ReadFirstnameActivity.this.show_name.setText("Name set successfully!");
                    Intent myIntent = new Intent();
                    myIntent.putExtra("FirstName", fname);
                    setResult(RESULT_OK, myIntent);
                }
            }
        });
    }
}
