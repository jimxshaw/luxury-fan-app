package me.jimmyshaw.lexusfanapp.edmunds.util;

import java.util.Map;

import me.jimmyshaw.lexusfanapp.edmunds.Models;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface EdmundsService {
    @GET("models")
    Call<Models> getModels(
            @QueryMap Map<String, String> options
    );
}
