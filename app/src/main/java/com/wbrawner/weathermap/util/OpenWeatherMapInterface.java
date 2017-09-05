package com.wbrawner.weathermap.util;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapInterface {

    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeatherForCoordinates(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("APPID") String appId
    );

}
