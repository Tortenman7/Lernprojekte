package com.hufsm.timetest2;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText countdownValue;
    TextView countdown;

    long startTime;
    long duration;
    long remaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startButton = (Button) findViewById(R.id.start_button);
        this.countdownValue = (EditText) findViewById(R.id.countdown_time);
        this.countdown = (TextView) findViewById(R.id.date);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCountdown();
            }
        });
    }


    void startCountdown() {

        try {
            duration = Integer.parseInt(countdownValue.getText().toString());
        } catch (NumberFormatException e) {
            countdown.setText("Enter a number!");
            return;
        }
       final  MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.ring);

        startTime = System.currentTimeMillis();
        remaining = duration;

        Thread t = new Thread() {
            @Override
            public void run() {
                while (remaining > 1) {
                   final long currentTime = System.currentTimeMillis();
                    try {
                      long delay = ((currentTime-startTime)%1000);
                        Thread.sleep(1000 - delay);
                        Log.d("CURRENT", "current = " + currentTime + " delay = " + delay);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                long elapsed = currentTime/1000 - startTime/1000;
                                remaining = duration - elapsed;

                                Log.d("TAG", "current = " + currentTime + " elapsed=" + elapsed + " remaining = " + remaining + " start = " + startTime);
                                if (remaining != 0) {
                                    countdown.setText(Long.toString(remaining) + "s");
                                }
                                else {
                                    ring.start();
                                    Toast.makeText(getApplicationContext(), "time is over", Toast.LENGTH_SHORT).show();
                                    countdown.setText("time is over");
                                    ring.start();

                                }

                            }

                        });
                    } catch (InterruptedException e) {
                    }
                }


            }
        };
        t.start();
    }

}
