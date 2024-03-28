package com.example.repositiva;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface AdviceService {
    @GET("advice")
    Call<AdviceResponse> getRandomAdvice();

    @GET("advice/{id}")
    Call<AdviceResponse> getAdviceById(@Path("id") int id);

    @GET("advice/search/{query}")
    Call<AdviceResponse> searchAdvice(@Path("query") String query);

}
