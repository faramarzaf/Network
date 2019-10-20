package com.faramarz.tictacdev.network.network;

import com.faramarz.tictacdev.network.Model.IPModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("/")
    Call<IPModel> getFlag(@Query("api-key") String apiKey);


}
