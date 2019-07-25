package com.android.trial;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAuthenticator implements Authenticator
{

    private Context context;
    private MyServiceHolder myServiceHolder;

    public TokenAuthenticator(Context context,MyServiceHolder myServiceHolder) {
        this.context = context;
        this.myServiceHolder = myServiceHolder;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException
    {
        if (myServiceHolder == null) {
            return null;
        }

        api_service myService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://api.lufthansa.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api_service.class);

        retrofit2.Response retrofitResponse = myService.refreshToken("y24hefr24xhbbbda3zy7dt4d" ,"5Q26e5Ged8","client_credentials").execute();
            RetrofitResponse myResponse  = (RetrofitResponse) retrofitResponse.body();
            String accssToken = myResponse.getAccessToken();

            if(accssToken != null)
            {
                Log.d("TokenAuthenticator", "The retrofit response " + accssToken);
                SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("Token", myResponse.getAccessToken());
                edit.commit();

                Log.d("AccessToken","End of getting the access token");
                return response.request().newBuilder()
                        .header("Authorization", "Bearer" + accssToken)
                        .build();
            }
            return null;


    }
}
