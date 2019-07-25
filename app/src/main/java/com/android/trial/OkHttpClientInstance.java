package com.android.trial;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClientInstance
{
    public static class Builder {
        private HashMap<String, String> headers = new HashMap<>();
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder addHeader(String key, String value) {
            headers.put(key, value);
            return this;
        }

        public OkHttpClient build() {
            TokenAuthenticator authenticator = new TokenAuthenticator(context);

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    // Add default headers
                                    Request.Builder requestBuilder = chain.request().newBuilder();

                                    Iterator it = headers.entrySet().iterator();

                                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                                        if (entry.getKey() != null && entry.getValue() != null) {
                                            requestBuilder.addHeader(entry.getKey(), entry.getValue());
                                        }
                                    }

                                    if (context != null) {
                                        SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
                                        String token = settings.getString("token", null);

                                        if (token != null) {
                                            requestBuilder.addHeader("Authorization", token);
                                        }
                                    }

                                    return chain.proceed(requestBuilder.build());
                                }
                            }
                    );


            okHttpClientBuilder.authenticator(authenticator);

            return okHttpClientBuilder.build();
        }
    }
}
