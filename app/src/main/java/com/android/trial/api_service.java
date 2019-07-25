package com.android.trial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface api_service
{

    @GET("v1/oauth/token")
    Call <RetrofitResponse>refreshToken(@Query("client_id") String client_id , @Query("client_secret") String client_secret , @Query("grant_type") String path);

    @GET("mds-references/airports")
    Call<List<AirPort>> getAirportItems();


}
