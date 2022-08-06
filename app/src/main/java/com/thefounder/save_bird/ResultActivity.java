package com.thefounder.save_bird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {

    Button playAgain ,  quitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playAgain = findViewById(R.id.button2);
        quitGame = findViewById(R.id.button3);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this , GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}