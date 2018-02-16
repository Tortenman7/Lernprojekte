package com.hufsm.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_MESSAGE = "com.hufsm.myfirstapplication.MESSAGE";

    private CatQuotesApi api;
    private MyCallback myCallback = new MyCallback();

    private class MyCallback implements Callback<String> {

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            String quote = "Not successful";
            if (response.isSuccessful()) {
                quote = response.body();
            }
            Intent intent = new Intent();
            intent.putExtra(EXTRA_MESSAGE, quote);
            startActivity(intent);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.e(TAG, t.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(CatQuotesApi.class);
    }


    public void sendMessage(View view) {
        api.getCatFact().enqueue(myCallback);
    }
}






