package com.example.simplecountdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button countdown_button;
    private TextView countdown_text;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillisenconds = 30000; // 600000 10 mins
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdown_button = findViewById(R.id.countdown_button);
        countdown_text = findViewById(R.id.countdown_text);

        countdown_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        updateTime();
    }

    private void startStop() {
        if (timerRunning) {
            stopTimer();

        } else {
            startTime();
        }
    }

    private void startTime() {
        countDownTimer = new CountDownTimer(timeLeftInMillisenconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillisenconds = l;
                updateTime();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "TERMINOOOOOOOOO", Toast.LENGTH_SHORT).show();
            }
        }.start();
         countdown_button.setText("PAUSE");
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        countdown_button.setText("START");
        timerRunning = false;
    }

    private void updateTime() {
        int minutes = (int) timeLeftInMillisenconds / 60000;
        int seconds = (int) timeLeftInMillisenconds % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds <10 )timeLeftText += "0";
        timeLeftText += seconds;
        countdown_text.setText(timeLeftText);
    }
}