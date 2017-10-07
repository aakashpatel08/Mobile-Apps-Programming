package edu.uco.apatel19.p5aakashp;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button contactsButton;
    private Button departmentButton;
    private FragmentManager fragmentManager;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsButton = (Button) findViewById(R.id.Contacts_Button);
        departmentButton = (Button) findViewById(R.id.Department_Button);

        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(myIntent);
            }
        });

        departmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                dialogFragment = new DepartmentDetailFragment();
                dialogFragment.show(fragmentManager, "Department Fragment");
            }
        });
    }
}
