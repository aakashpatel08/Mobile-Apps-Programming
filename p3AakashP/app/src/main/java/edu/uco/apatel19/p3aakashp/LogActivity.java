package edu.uco.apatel19.p3aakashp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class LogActivity extends Activity {
    private TextView game_number;
    private TextView game_winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        game_number = (TextView) findViewById(R.id.Number_View);
        game_winner = (TextView) findViewById(R.id.Winner_View);

        String resultLog = getIntent().getStringExtra("Log");
        int entryNumber = resultLog.length();
        String resultNumber = "#\t\n";
        for (int i = 0; i < entryNumber; i++){
            resultNumber = resultNumber + (i + 1) + "\n";
        }
        game_number.setText(resultNumber);

        String displayWinner = "Winner\n";
        for (int i = 0; i < entryNumber; i++){
            if (resultLog.charAt(i) == 'D'){
                displayWinner = displayWinner + "draw\n";
            }else {
                displayWinner = displayWinner + resultLog.charAt(i) + "\n";
            }
        }
        game_winner.setText(displayWinner);
    }

}
