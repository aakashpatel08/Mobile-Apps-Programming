package edu.uco.apatel19.p4aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class SignupActivity extends Activity {
    private EditText writeName, writePassword;
    private Button pickDOB, register;
    private DatePickerFragment fragment;
    private RadioGroup radioGroup;
    private String gender = "";
    private String skills = "";
    private String classification = "";
    private RadioButton selectMale, selectFemale;
    private CheckBox checkAndroid, checkJava, checkKotlin, checkSwift, checkIos;
    private Spinner spinner;
    ArrayAdapter<CharSequence> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        writeName = (EditText) findViewById(R.id.Edit_Name);
        writePassword = (EditText) findViewById(R.id.Edit_Password);
        pickDOB = (Button) findViewById(R.id.DOB_Button);
        radioGroup = (RadioGroup) findViewById(R.id.Gendergroup_Radio);
        selectMale = (RadioButton) findViewById(R.id.Male_RadioButton);
        selectFemale = (RadioButton) findViewById(R.id.Female_RadioButton);
        CheckBoxListener checkBoxListener = new CheckBoxListener();
        checkAndroid = (CheckBox) findViewById(R.id.Android_Check);
        checkAndroid.setOnClickListener(checkBoxListener);
        checkJava = (CheckBox) findViewById(R.id.Java_Check);
        checkJava.setOnClickListener(checkBoxListener);
        checkKotlin = (CheckBox) findViewById(R.id.Kotlin_Check);
        checkKotlin.setOnClickListener(checkBoxListener);
        checkSwift = (CheckBox) findViewById(R.id.Swift_Check);
        checkSwift.setOnClickListener(checkBoxListener);
        checkIos = (CheckBox) findViewById(R.id.iOS_Check);
        checkIos.setOnClickListener(checkBoxListener);
        spinner = (Spinner) findViewById(R.id.Classification_Spinner);
        register = (Button) findViewById(R.id.Register_Button);
        adapter = ArrayAdapter.createFromResource(this, R.array.classification_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classification = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pickDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "datePicker");
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.Male_RadioButton){
                    gender = selectMale.getText().toString();
                } else {
                    gender = selectFemale.getText().toString();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignupActivity.this, RegisterActivity.class);
                myIntent.putExtra("Name", SignupActivity.this.writeName.getText().toString());
                myIntent.putExtra("Password", SignupActivity.this.writePassword.getText().toString());
                if (SignupActivity.this.fragment != null) {
                    myIntent.putExtra("DateOfBirth", (SignupActivity.this.fragment.month + 1) + "/"
                            + SignupActivity.this.fragment.day + "/" + SignupActivity.this.fragment.year);
                } else {
                    myIntent.putExtra("DateOfBirth", "not set");
                }
                myIntent.putExtra("Gender", gender);
                myIntent.putExtra("Skills", skills);
                myIntent.putExtra("Classification", classification);
                startActivity(myIntent);
            }
        });
    }
    class CheckBoxListener implements View.OnClickListener {
        CheckBoxListener(){
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Android_Check:
                    skills = checkAndroid.getText().toString();
                break;
                case R.id.Java_Check:
                    skills = skills + ", " + checkJava.getText().toString();
                break;
                case R.id.Kotlin_Check:
                    skills = skills + ", " + checkKotlin.getText().toString();
                break;
                case R.id.Swift_Check:
                    skills = skills + ", " + checkSwift.getText().toString();
                break;
                case R.id.iOS_Check:
                    skills = skills + ", " + checkIos.getText().toString();
                break;
            }
        }
    }


}
