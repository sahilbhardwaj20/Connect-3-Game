package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // 0:yellow, 1:red
    int activePlayer=0,track=0;
    TextView turnTextView;
    int[] filledPos={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    Button playAgainButton;

    public void dropIn(View view){

        ImageView counter= (ImageView) view;

        int clickedTag=Integer.parseInt(counter.getTag().toString());

        if(filledPos[clickedTag-1]==-1 && gameActive){

            counter.setTranslationY(-1000);
            track++;
            filledPos[clickedTag-1]=activePlayer;

            if(activePlayer==0){
                counter.setImageResource(R.drawable.yellow);
                turnTextView.setText("Red's Turn");
                activePlayer=1;
            }else{
                counter.setImageResource(R.drawable.red);
                turnTextView.setText("Yellow's Turn");
                activePlayer=0;
            }
            counter.animate().translationYBy(1000).rotationBy(360).setDuration(500);
            checkForWin();

            if(track==9){
                track=0;
                turnTextView.setText("Draw !");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void checkForWin(){
        for(int[] check:winningPos){
            if(filledPos[check[0]] == filledPos[check[1]] && filledPos[check[1]] == filledPos[check[2]] && filledPos[check[0]] != -1){
               gameActive = false;
                if(activePlayer==1){turnTextView.setText("Yellow has won!");}
               else{turnTextView.setText("Red has won!");}

               playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view){

        playAgainButton.setVisibility(View.INVISIBLE);
        turnTextView.setText("Yellow's Turn");
        GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0; i<filledPos.length; i++){
            filledPos[i]=-1;
        }
        gameActive = true;
        activePlayer=0;
        track=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        turnTextView=(TextView) findViewById(R.id.turnTextView);
        turnTextView.setText("Yellow's Turn");
    }
}