package edu.uco.apatel19.tpaakashp.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.uco.apatel19.tpaakashp.R;

public class Options extends Activity {
    private RadioButton musicOn, musicOff;
    int volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        musicOn = (RadioButton) findViewById(R.id.MusicOn);
        musicOff = (RadioButton) findViewById(R.id.MusicOff);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(
                "higher", MODE_PRIVATE);
        volume = pref.getInt("volume", 0);
    }

    public void onClickMusicRadioButtons(View view){
        //Is the button clicked?
        boolean checked = ((RadioButton) view).isChecked();

        //Which radio button was clicked
        switch (view.getId()){
            case R.id.MusicOn:
                if (checked){
                    if (!LoadingScreen.backgroundMusic.isPlaying()){
                        LoadingScreen.backgroundMusic.start();
                    }
                    Toast.makeText(Options.this, "Music On", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.MusicOff:
                if (checked){
                    if (LoadingScreen.backgroundMusic.isPlaying()){
                        LoadingScreen.backgroundMusic.pause();
                    }
                    Toast.makeText(Options.this, "Music Off", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
