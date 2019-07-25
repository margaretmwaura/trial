package com.android.trial;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientInstance
{
    public static class Builder {
        private HashMap<String, String> headers = new HashMap<>();
        private Context context;
        private MyServiceHolder myServiceHolder;

        public Builder(Context context,MyServiceHolder myServiceHolder)
        {
            this.context = context;
            this.myServiceHolder = myServiceHolder;
            Log.d("OkhhtpClient","The Okhttpclient has been started");
        }

        public Builder addHeader(String key, String value)
        {
            headers.put(key, value);
            return this;
        }

        public OkHttpClient build() {
            TokenAuthenticator authenticator = new TokenAuthenticator(context,myServiceHolder);

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    // Add default headers
                                    Request.Builder requestBuilder = chain.request().newBuilder();

                                        SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
                                        String token = settings.getString("token", null);

                                        if (token != null)
                                        {
                                            requestBuilder.addHeader("Authorization","Bearer"+token);
                                            Log.d("TokenSaving","Token had been saved");
                                        }
                                        else
                                        {
                                            Log.d("TokenSaving","Token was never saved ");
                                        }


                                    return chain.proceed(requestBuilder.build());
                                }
                            }
                    ).connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)

                    .readTimeout(30, TimeUnit.SECONDS);


            okHttpClientBuilder.authenticator(authenticator);

            return okHttpClientBuilder.build();
        }
    }
}
