package com.hufsm.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private static final String TAG = DisplayMessageActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        /*String name = intent.getStringExtra(MainActivity.EXTRA_AUTHOR);
        String addresse = intent.getStringExtra(MainActivity.EXTRA_ADDRESSEE);*/

        Log.d(TAG, "Received message : '" + message /*+ "' from author " + name + " written for " + addresse*/);

        TextView messageView = findViewById(R.id.message_view);

        messageView.setText(message);
    }
}