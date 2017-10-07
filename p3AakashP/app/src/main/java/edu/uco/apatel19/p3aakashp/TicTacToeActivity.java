package edu.uco.apatel19.p3aakashp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeActivity extends Activity {
    private TextView display;
    private Button[] boardButtons;
    private Button newGameButton;
    private Button logButton;
    private boolean winner = false;
    private int turnCount = 0;
    private String letter = "";
    private String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        display = (TextView) findViewById(R.id.Display);
        boardButtons = new Button[9];
        boardButtons[0] = (Button) findViewById(R.id.R0C0_Button);
        final Drawable btn0DefaultBackground = boardButtons[0].getBackground();
        boardButtons[1] = (Button) findViewById(R.id.R0C1_Button);
        final Drawable btn1DefaultBackground = boardButtons[1].getBackground();
        boardButtons[2] = (Button) findViewById(R.id.R0C2_Button);
        final Drawable btn2DefaultBackground = boardButtons[2].getBackground();
        boardButtons[3] = (Button) findViewById(R.id.R1C0_Button);
        final Drawable btn3DefaultBackground = boardButtons[3].getBackground();
        boardButtons[4] = (Button) findViewById(R.id.R1C1_Button);
        final Drawable btn4DefaultBackground = boardButtons[4].getBackground();
        boardButtons[5] = (Button) findViewById(R.id.R1C2_Button);
        final Drawable btn5DefaultBackground = boardButtons[5].getBackground();
        boardButtons[6] = (Button) findViewById(R.id.R2C0_Button);
        final Drawable btn6DefaultBackground = boardButtons[6].getBackground();
        boardButtons[7] = (Button) findViewById(R.id.R2C1_Button);
        final Drawable btn7DefaultBackground = boardButtons[7].getBackground();
        boardButtons[8] = (Button) findViewById(R.id.R2C2_Button);
        final Drawable btn8DefaultBackground = boardButtons[8].getBackground();
        newGameButton = (Button) findViewById(R.id.Newgame_Button);
        logButton = (Button) findViewById(R.id.Log_Button);

        for(int i = 0; i < 9; i++){
            final int finalI = i;

            boardButtons[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    turnCount++;
                    Object source = v.getId();

                    //Determine whose turn is it
                    if (turnCount == 1 || turnCount == 3 || turnCount == 5 || turnCount == 7 || turnCount == 9){
                        letter = "O";
                        display.setText("Play Tic-Tac-Toe: X's Turn!");
                    } else if (turnCount == 2 || turnCount == 4 || turnCount == 6 || turnCount == 8){
                        letter = "X";
                        display.setText("Play Tic-Tac-Toe: O's Turn!");
                    }

                    //Place mark(x or o) and disable that button
                   switch (v.getId()){
                       case R.id.R0C0_Button:
                           boardButtons[0].setText(letter);
                           boardButtons[0].setEnabled(false);
                           break;
                       case R.id.R0C1_Button:
                           boardButtons[1].setText(letter);
                           boardButtons[1].setEnabled(false);
                           break;
                       case R.id.R0C2_Button:
                           boardButtons[2].setText(letter);
                           boardButtons[2].setEnabled(false);
                           break;
                       case R.id.R1C0_Button:
                           boardButtons[3].setText(letter);
                           boardButtons[3].setEnabled(false);
                           break;
                       case R.id.R1C1_Button:
                           boardButtons[4].setText(letter);
                           boardButtons[4].setEnabled(false);
                           break;
                       case R.id.R1C2_Button:
                           boardButtons[5].setText(letter);
                           boardButtons[5].setEnabled(false);
                           break;
                       case R.id.R2C0_Button:
                           boardButtons[6].setText(letter);
                           boardButtons[6].setEnabled(false);
                           break;
                       case R.id.R2C1_Button:
                           boardButtons[7].setText(letter);
                           boardButtons[7].setEnabled(false);
                           break;
                       case R.id.R2C2_Button:
                           boardButtons[8].setText(letter);
                           boardButtons[8].setEnabled(false);
                           break;
                   }


//----------------------------------------Check Horizontal Win-------------------------------------------------------
                    if (boardButtons[0].getText() == boardButtons[1].getText() &&
                            boardButtons[1].getText() == boardButtons[2].getText() &&
                            boardButtons[0].getText() != ""){
                        winner = true;
                        log = log + boardButtons[0].getText();
                        boardButtons[0].setBackgroundColor(Color.GREEN);
                        boardButtons[1].setBackgroundColor(Color.GREEN);
                        boardButtons[2].setBackgroundColor(Color.GREEN);
                    }else if (boardButtons[3].getText() == boardButtons[4].getText() &&
                            boardButtons[4].getText() == boardButtons[5].getText() &&
                            boardButtons[3].getText() != ""){
                        winner = true;
                        log = log + boardButtons[3].getText();
                        boardButtons[3].setBackgroundColor(Color.GREEN);
                        boardButtons[4].setBackgroundColor(Color.GREEN);
                        boardButtons[5].setBackgroundColor(Color.GREEN);
                    }else if (boardButtons[6].getText() == boardButtons[7].getText() &&
                            boardButtons[7].getText() == boardButtons[8].getText() &&
                            boardButtons[6].getText() != ""){
                        winner = true;
                        log = log + boardButtons[6].getText();
                        boardButtons[6].setBackgroundColor(Color.GREEN);
                        boardButtons[7].setBackgroundColor(Color.GREEN);
                        boardButtons[8].setBackgroundColor(Color.GREEN);
                    }

//----------------------------------------Check Vertical Win-------------------------------------------------------
                    else if (boardButtons[0].getText() == boardButtons[3].getText() &&
                            boardButtons[3].getText() == boardButtons[6].getText() &&
                            boardButtons[0].getText() != ""){
                        winner = true;
                        log = log + boardButtons[0].getText();
                        boardButtons[0].setBackgroundColor(Color.GREEN);
                        boardButtons[3].setBackgroundColor(Color.GREEN);
                        boardButtons[6].setBackgroundColor(Color.GREEN);
                    }else if (boardButtons[1].getText() == boardButtons[4].getText() &&
                            boardButtons[4].getText() == boardButtons[7].getText() &&
                            boardButtons[1].getText() != ""){
                        winner = true;
                        log = log + boardButtons[1].getText();
                        boardButtons[1].setBackgroundColor(Color.GREEN);
                        boardButtons[4].setBackgroundColor(Color.GREEN);
                        boardButtons[7].setBackgroundColor(Color.GREEN);
                    }else if (boardButtons[2].getText() == boardButtons[5].getText() &&
                            boardButtons[5].getText() == boardButtons[8].getText() &&
                            boardButtons[2].getText() != ""){
                        winner = true;
                        log = log + boardButtons[2].getText();
                        boardButtons[2].setBackgroundColor(Color.GREEN);
                        boardButtons[5].setBackgroundColor(Color.GREEN);
                        boardButtons[8].setBackgroundColor(Color.GREEN);
                    }

//----------------------------------------Check Diagonal Win-------------------------------------------------------
                    else if (boardButtons[0].getText() == boardButtons[4].getText() &&
                            boardButtons[4].getText() == boardButtons[8].getText() &&
                            boardButtons[0].getText() != ""){
                        winner = true;
                        log = log + boardButtons[0].getText();
                        boardButtons[0].setBackgroundColor(Color.GREEN);
                        boardButtons[4].setBackgroundColor(Color.GREEN);
                        boardButtons[8].setBackgroundColor(Color.GREEN);
                    }else if (boardButtons[2].getText() == boardButtons[4].getText() &&
                            boardButtons[4].getText() == boardButtons[6].getText() &&
                            boardButtons[2].getText() != ""){
                        winner = true;
                        log = log + boardButtons[2].getText();
                        boardButtons[2].setBackgroundColor(Color.GREEN);
                        boardButtons[4].setBackgroundColor(Color.GREEN);
                        boardButtons[6].setBackgroundColor(Color.GREEN);
                    }else {
                        winner = false;
                    }

                    //Display result of the game(winner or draw)
                    if(winner == true){
                        display.setText("Game Over: Winner is " + letter + "!!!");
                        for (int i = 0; i < 9; i++){
                            boardButtons[i].setEnabled(false);
                        }
                    }else if (turnCount == 9 && winner == false){
                        display.setText("Game Over: TIE - No Winner!!!");
                        log = log + "D";
                    }
                }
            });
        }

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winner = false;
                turnCount = 0;
                //Enable game board and change color to default
                for(int i = 0; i < 9; i++){
                    boardButtons[i].setEnabled(true);
                    boardButtons[i].setText("");
                    boardButtons[0].setBackgroundDrawable(btn0DefaultBackground);
                    boardButtons[1].setBackgroundDrawable(btn1DefaultBackground);
                    boardButtons[2].setBackgroundDrawable(btn2DefaultBackground);
                    boardButtons[3].setBackgroundDrawable(btn3DefaultBackground);
                    boardButtons[4].setBackgroundDrawable(btn4DefaultBackground);
                    boardButtons[5].setBackgroundDrawable(btn5DefaultBackground);
                    boardButtons[6].setBackgroundDrawable(btn6DefaultBackground);
                    boardButtons[7].setBackgroundDrawable(btn7DefaultBackground);
                    boardButtons[8].setBackgroundDrawable(btn8DefaultBackground);
                }
                display.setText("Play Tic-Tac-Toe: O's Turn!");
            }
        });

        logButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TicTacToeActivity.this, LogActivity.class);
                myIntent.putExtra("Log", log);
                startActivity(myIntent);
            }
        });

    }
}
