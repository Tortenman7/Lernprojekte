package com.hufsm.timetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getCurrentDate(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ,___ hh : mm : ss ");
        String strDate = "Current Date and Time : " + mdformat.format(calendar.getTime());
        display(strDate);
    }

    private void display(String num) {
        TextView textView = (TextView) findViewById(R.id.current_date_view);
        textView.setText(num);
    }
}
