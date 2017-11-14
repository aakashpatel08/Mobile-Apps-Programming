package edu.uco.apatel19.tpaakashp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import edu.uco.apatel19.tpaakashp.R;

public class LoadingScreen extends Activity {
    private ProgressBar progressBar;
    private int progressValue = 0;
    private Handler handler = new Handler();
    static MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        backgroundMusic = MediaPlayer.create(LoadingScreen.this, R.raw.kd);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressValue <= 100 && (!Thread.currentThread().isInterrupted())) {
                    progressValue += 10;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressValue);
                        }
                    });
                }
                if (progressValue >= progressBar.getMax()){
                    Log.e("PROGRESSVALUE", String.valueOf(progressValue));
                    Intent myIntent = new Intent(LoadingScreen.this, MainMenu.class);
                    startActivity(myIntent);
                }
            }
        });
        thread.start();


    }
}
