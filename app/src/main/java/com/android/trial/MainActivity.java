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

        MyServiceHolder myServiceHolder = new MyServiceHolder();
        SharedPreferences settings = getSharedPreferences("PREFS", this.MODE_PRIVATE);
        String token = settings.getString("token", null);

        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(this,myServiceHolder)
                .addHeader("Authorization", "Bearer "+token)
//                .addHeader("X-Originating-IP", "102.167.112.54")
                .build();

        Log.d("MainActivity","MainActivy we are back");

        api_service myService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://api.lufthansa.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(api_service.class);

        Call<AirportResourceModel> call = myService.getAirportItems();
        call.enqueue(
                new Callback<AirportResourceModel>() {
                    @Override
                    public void onResponse(Call<AirportResourceModel> call, Response<AirportResourceModel> response)
                    {
                        Log.d("Airports", "Them airports have been gotten " + response.body());
                        Airports airports = response.body().getAirportResource().getAirports() ;
                        airPortList = airports.getAirPortLists();
                        Log.d("Size","This is the size of the airport list " + airPortList.size());
//                        Log.d("Airports","This is the size of the airport list gotten" + airPort.getCityCode());
//                        Log.d("Airports","This  is another property of the airport " + airPort.getCityCode() + airPort.getCountryCode() + airPort.getAirportCode());

                    }

                    @Override
                    public void onFailure(Call<AirportResourceModel> call, Throwable t)
                    {
                        Log.d("Airports", "Nothing gotten" + t.getMessage());
                    }
                }
        );

    }
}
