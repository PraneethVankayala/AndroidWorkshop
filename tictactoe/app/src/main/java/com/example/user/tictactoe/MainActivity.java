package com.example.user.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int player=0;
    int[] game={3,3,3,3,3,3,3,3,3};
    int[][] winning={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameover=true;
    public void gameLogic(View view){

        ImageView tappedView=(ImageView) view;
        int location=Integer.parseInt(view.getTag().toString());
        if(game[location]==3&&gameover){
            game[location]=player;

            if(player==0){
                tappedView.setImageResource(R.drawable.index);
                player=1;
            }
            else if(player==1){
                tappedView.setImageResource(R.drawable.redcross);
                player=0;
            }
        }


         for(int[]winningPosition:winning){
            if(game[winningPosition[0]]==game[winningPosition[1]]&&game[winningPosition[1]]==game[winningPosition[2]]&&game[winningPosition[0]]!=3){
                if(player==0){
                    Toast.makeText(getApplicationContext(),"Player 2 is won",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Player 1 is won",Toast.LENGTH_LONG).show();
                }
                LinearLayout winnerLayout=(LinearLayout)findViewById(R.id.winner);
                winnerLayout.setVisibility(View.VISIBLE);
                gameover=false;

            }
         }

    }
    public void playAgain(View view){
        startActivity(new Intent(this , MainActivity.class));
    }

    public void pause(View view){
        onPause();
    }
    public void resume(View view){
        onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout winnerLayout =(LinearLayout)findViewById(R.id.winner);
        winnerLayout.setVisibility(View.INVISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
