package me.jimmyshaw.luxuryfanapp.edmunds.util;

import java.util.Map;

import me.jimmyshaw.luxuryfanapp.edmunds.Models;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface EdmundsService {
    @GET("activity_model")
    Call<Models> getModels(
            @QueryMap Map<String, String> options
    );
}
