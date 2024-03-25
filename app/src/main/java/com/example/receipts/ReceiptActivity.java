package com.example.receipts;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {
    public TextView textSteps, textName, textIngridients, timerTextView, startTimerButton;
    public  ImageView imageReceipt;

    private CountDownTimer countDownTimer;
    private boolean timerRunning = false, audioRunning = false;
    private MediaPlayer mediaPlayer, audioReceipt;

    private Button button_audio_receipt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_page);

        Intent intent = getIntent();
        String steps = intent.getStringExtra("steps");
        String name = intent.getStringExtra("name");
        String ingridients = intent.getStringExtra("ingridients");
        String image = intent.getStringExtra("image");

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        audioReceipt = MediaPlayer.create(getApplicationContext(), R.raw.full_receipt);







        imageReceipt = findViewById(R.id.receipt_image);
        textSteps = findViewById(R.id.steps);
        textName = findViewById(R.id.receipt_name);
        textIngridients = findViewById(R.id.receipt_ingidients);

        timerTextView = findViewById(R.id.timerTextView);
        startTimerButton = findViewById(R.id.startTimerButton);

        button_audio_receipt = findViewById(R.id.startReceipt);

        imageReceipt.setImageResource(Integer.parseInt(image));
        textSteps.setText(steps);
        textName.setText(name);
        textIngridients.setText(ingridients);

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        button_audio_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audioRunning) {
                    stopAudio();
                } else {
                    startAudio();
                }
            }
        });
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) { // 30 секунд
            @Override
            public void onTick(long millisUntilFinished) {
                String remain = getString(R.string.remain);
                String sec = getString(R.string.sec);
                timerTextView.setText(remain + ": " + millisUntilFinished / 1000 + " " + sec);
            }

            @Override
            public void onFinish() {
                timerTextView.setText(R.string.end_timer);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (vibrator != null) {
                    vibrator.vibrate(5000);
                }
                mediaPlayer.start();

            }
        }.start();

        startTimerButton.setText(R.string.end_timer);
        timerRunning = true;
    }


    private void stopTimer() {
        countDownTimer.cancel();
        startTimerButton.setText(R.string.start_timer);
        timerTextView.setText("00:00");
        mediaPlayer.stop();
        timerRunning = false;
    }

    private void startAudio() {
        button_audio_receipt.setText("Остановить");
        audioRunning = true;
        audioReceipt.start();
    }


    private void stopAudio() {
        button_audio_receipt.setText("Стартовать");
        audioRunning = false;
        audioReceipt.stop();

    }

}
