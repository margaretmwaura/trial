package com.android.trial;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface api_service
{

    @FormUrlEncoded
    @POST("v1/oauth/token")
    Call <RetrofitResponse>refreshToken(@Field("client_id") String client_id , @Field("client_secret") String client_secret , @Field("grant_type") String path);

    @Headers("Accept: application/json")
    @GET("v1/references/airports/TXL?limit=20&offset=0&LHoperated=0")
    Call<AirportResourceModel> getAirportItems();


}
