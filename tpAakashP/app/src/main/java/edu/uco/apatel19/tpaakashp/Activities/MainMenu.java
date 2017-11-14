package edu.uco.apatel19.tpaakashp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.os.Process;
import android.view.View;
import android.widget.Button;

import edu.uco.apatel19.tpaakashp.R;
import edu.uco.apatel19.tpaakashp.SurfaceViews.GamePanel;

public class MainMenu extends Activity {
    public Button newGame;
    private Button controls;
    private Button highScore;
    private Button options;
    private Button quit;
    private GamePanel gamePanel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        newGame = (Button) findViewById(R.id.NewGame);
        controls = (Button) findViewById(R.id.Controls);
        highScore = (Button) findViewById(R.id.HighScore);
        options = (Button) findViewById(R.id.Options);
        quit = (Button) findViewById(R.id.Quit);

        controls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent controlsIntent = new Intent(MainMenu.this, Controls.class);
                startActivity(controlsIntent);
            }
        });

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highScoreIntent = new Intent(MainMenu.this, HighScore.class);
                startActivity(highScoreIntent);
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent optionsIntent = new Intent(MainMenu.this, Options.class);
                startActivity(optionsIntent);
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClickNewGame(View view){
        gamePanel = new GamePanel(this);
        setContentView(gamePanel);
    }

    @Override
    protected void onDestroy() {
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }
}
