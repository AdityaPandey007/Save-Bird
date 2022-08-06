package com.thefounder.save_bird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird , enemy1 , enemy2 ,enemy3 , coin1, coin2 , right1 , right2 , right3;
    private TextView textViewScore , textViewStartInfo;
    private ConstraintLayout constraintLayout;

    Boolean touchControl = false ;
    Boolean beginControl = false;

    Handler handler , handler2;
    Runnable runnable , runnable2;

    //Positions
    int birdX , enemy1X , enemy2X , enemy3X , coin1X , coin2X;
    int birdY , enemy1Y , enemy2Y , enemy3Y , coin1Y , coin2Y;

    //Dimensions
    int screenWidth;
    int screenHeight;

    //remaining life or right
     int right = 3;

     //Points
    int score = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.imageView);
        enemy1 = findViewById(R.id.imageView5);
        enemy2 = findViewById(R.id.imageView6);
        enemy3 = findViewById(R.id.imageView7);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.imageView4);
        right1 = findViewById(R.id.imageView10);
        right2 = findViewById(R.id.imageView11);
        right3 = findViewById(R.id.imageView12);
        textViewScore = findViewById(R.id.textView5);
        textViewStartInfo = findViewById(R.id.textView4);
        constraintLayout = findViewById(R.id.constraintLayout);


        constraintLayout.setOnTouchListener((v, event) -> {

            textViewStartInfo.setVisibility(View.INVISIBLE);

            if (!beginControl ){

                beginControl = true;

                screenWidth = constraintLayout.getWidth();
                screenHeight = constraintLayout.getHeight();

                birdX = (int)bird.getX();
                birdY = (int)bird.getY();


                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {

                        moveToBird();
                        enemyControl();
                        collisionControl();

                    }
                };

                handler.post(runnable);
            }

            else {

                if (event.getAction()==MotionEvent.ACTION_DOWN){

                    touchControl = true;

                }

                if (event.getAction()== MotionEvent.ACTION_UP) {

                    touchControl = false;
                }

            }
            return true;
        });

    }

    public void moveToBird(){

        if (touchControl){

            birdY = birdY- (screenHeight / 50);
        }

        else {

            birdY = birdY + (screenHeight /50);

        }


        if (birdY <=0) {

            birdY = 0;

        }

        if (birdY>=(screenHeight-bird.getHeight())){

            birdY = (screenHeight -bird.getHeight());
        }

        bird.setY(birdY);
    }


    public void enemyControl(){

        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);
        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);


        enemy1X = enemy1X-(screenWidth / 150);

        if (enemy1X <0) {

            enemy1X = screenWidth + 200;
            enemy1Y = (int) Math.floor(Math.random() * screenHeight);


            if (enemy1Y <= 0) {

                enemy1Y = 0;
            }

            if (enemy1Y >= (screenHeight - enemy1.getHeight())) {

                enemy1Y = (screenHeight - enemy1.getHeight());
            }
        }

        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);


        enemy2X = enemy2X-(screenWidth / 150);

        if (enemy2X <0) {

            enemy2X = screenWidth + 200;
            enemy2Y = (int) Math.floor(Math.random() * screenHeight);


            if (enemy2Y <= 0) {

                enemy2Y = 0;
            }

            if (enemy2Y >= (screenHeight - enemy2.getHeight())) {

                enemy2Y = (screenHeight - enemy2.getHeight());
            }
        }

        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);


        enemy3X = enemy3X-(screenWidth / 150);

        if (enemy2X <0) {

            enemy3X = screenWidth + 200;
            enemy3Y = (int) Math.floor(Math.random() * screenHeight);


            if (enemy3Y <= 0) {

                enemy3Y = 0;
            }

            if (enemy3Y >= (screenHeight - enemy3.getHeight())) {

                enemy3Y = (screenHeight - enemy3.getHeight());
            }
        }

        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);


        coin1X = coin1X-(screenWidth / 150);

        if (coin1X <0) {

            coin1X = screenWidth + 200;
            coin1Y = (int) Math.floor(Math.random() * screenHeight);


            if (coin1Y <= 0) {

                coin1Y = 0;
            }

            if (coin1Y >= (screenHeight - coin1.getHeight())) {

                coin1Y = (screenHeight - coin1.getHeight());
            }
        }

        coin1.setX(coin1X);
        coin1.setY(coin1Y);


        coin2X = coin2X-(screenWidth / 150);

        if (coin2X <0) {

            coin2X = screenWidth + 200;
            coin2Y = (int) Math.floor(Math.random() * screenHeight);


            if (coin2Y <= 0) {

                coin2Y = 0;
            }

            if (coin2Y >= (screenHeight - coin2.getHeight())) {

                coin2Y = (screenHeight - coin2.getHeight());

            }
        }

        coin2.setX(coin2X);
        coin2.setY(coin2Y);

    }

    public void collisionControl(){

        int centerEnemy1X = enemy1X + enemy1.getWidth()/2;
        int centerEnemy1Y = enemy1Y + enemy1.getWidth() /2;

        if (centerEnemy1X >= birdX
                &&centerEnemy1X <= (birdX + bird.getWidth())
               && centerEnemy1Y >= birdY
               && centerEnemy1Y <= (birdY + bird.getHeight())
        ) {

            enemy1X = screenWidth + 200;
            right--;
        }


        int centerEnemy2X = enemy2X + enemy2.getWidth()/2;
        int centerEnemy2Y = enemy2Y + enemy2.getWidth() /2;


        if (centerEnemy2X >= birdX
                &&centerEnemy2X <= (birdX + bird.getWidth())
                && centerEnemy2Y >= birdY
                && centerEnemy2Y <= (birdY + bird.getHeight())
        ) {

            enemy2X = screenWidth + 200;
            right--;
        }


        int centerEnemy3X = enemy3X + enemy3.getWidth()/2;
        int centerEnemy3Y = enemy3Y + enemy3.getWidth() /2;


        if (centerEnemy3X >= birdX
                &&centerEnemy3X <= (birdX + bird.getWidth())
                && centerEnemy3Y >= birdY
                && centerEnemy3Y <= (birdY + bird.getHeight())
        ) {

            enemy3X = screenWidth + 200;
            right--;
        }


        int centerCoin1X = coin1X + coin1.getWidth()/2;
        int centerCoin1Y = coin1Y + coin1.getWidth() /2;


        if (centerCoin1X >= birdX
                &&centerCoin1X <= (birdX + bird.getWidth())
                && centerCoin1Y >= birdY
                && centerCoin1Y <= (birdY + bird.getHeight())
        ) {

            coin1X = screenWidth + 200;
            score = score + 10;
            textViewScore.setText(""+score);
        }

        int centerCoin2X = coin2X + coin2.getWidth()/2;
        int centerCoin2Y = coin2Y + coin2.getWidth() /2;


        if (centerCoin2X >= birdX
                &&centerCoin2X <= (birdX + bird.getWidth())
                && centerCoin2Y >= birdY
                && centerCoin2Y <= (birdY + bird.getHeight())
        ) {

            coin2X = screenWidth + 200;
            score = score + 10;
            textViewScore.setText(""+score);
        }

        if (right > 0 && score < 200){

            if (right == 2 ){

                right1.setImageResource(R.drawable.heart2);
            }

            if (right ==1){

                right2.setImageResource(R.drawable.heart2);
            }

            handler.postDelayed(runnable,20);

        }
        else if(score >= 200) {

            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            textViewStartInfo.setVisibility(View.VISIBLE);
            textViewStartInfo.setText("Cogratulation ! You won the game");
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);


            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {

                    birdX = birdX + (screenWidth /300);
                    bird.setX(birdX);
                    bird.setY(screenHeight / 2f);

                    if (birdX <= screenWidth){

                        handler2.postDelayed(runnable2,20);
                    }
                    else {

                        handler2.removeCallbacks(runnable2);

                        Intent intent = new Intent(GameActivity.this , ResultActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);

        }
        else if (right == 0){

            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.heart2);

            Intent intent = new Intent(GameActivity.this ,ResultActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
        }
    }

}