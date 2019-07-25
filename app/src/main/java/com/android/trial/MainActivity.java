package com.android.trial;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    List<AirPort> airPortList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("PREFS", this.MODE_PRIVATE);
        String token = settings.getString("token", null);
        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(this)
                .addHeader("Authorization", token)
                .build();

        api_service myService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://api.lufthansa.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(api_service.class);

        Call<List<AirPort>> call = myService.getAirportItems();
        call.enqueue(
                new Callback<List<AirPort>>() {
                    @Override
                    public void onResponse(Call<List<AirPort>> call, Response<List<AirPort>> response)
                    {
                        Log.d("Airports", "Them airports have been gotten ");
                    }

                    @Override
                    public void onFailure(Call<List<AirPort>> call, Throwable t)
                    {
                        Log.d("Airports", "Nothing gotten");
                    }
                }
        );

    }
}
