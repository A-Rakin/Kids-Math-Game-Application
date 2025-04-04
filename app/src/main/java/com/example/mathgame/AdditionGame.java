package com.example.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import java.util.Random;

public class AdditionGame extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;

    Random random = new Random();
    int num1;
    int num2;
    int userAnswer;
    int correctAnswer;
    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIME_IN_MILLIS = 60000;
    boolean timer_running;
    long time_left_in_milis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addition_game);

        score = findViewById(R.id.textView0);
        life = findViewById(R.id.textView3);
        time = findViewById(R.id.textView60);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);
        gameContinue();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.valueOf(answer.getText().toString());
                pauseTimer();
                if(userAnswer == correctAnswer)
                {
                    userScore=userScore+10;
                    score.setText(""+userScore);
                    question.setText("Correct");
                }
                else {
                    userLife=userLife-1;
                    life.setText(""+userLife);
                    question.setText("Wrong");
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText("");
                resetTimer();
                if(userLife<=0)
                {
                    Toast.makeText(AdditionGame.this, "Game Over", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdditionGame.this, GameResult.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    gameContinue();
                }
            }
        });
    }
    public void gameContinue()
    {
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        correctAnswer = num1 + num2;
        question.setText(num1 + "+" + num2);
        stratTimer();
    }
    public void stratTimer()
    {

        timer = new CountDownTimer( time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife=userLife-1;
                life.setText(""+userLife);
                question.setText("OOPS! Time Out");
            }
        }.start();
        timer_running = true;
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running = false;
    }
    public void resetTimer(){
        time_left_in_milis = START_TIME_IN_MILLIS;
        updateText();
    }
    public void updateText(){
        int second = (int) (time_left_in_milis / 1000) % 60;
        String timeLeft =String.format(Locale.getDefault(),"%02d",second);
        time.setText(timeLeft);
    }
}