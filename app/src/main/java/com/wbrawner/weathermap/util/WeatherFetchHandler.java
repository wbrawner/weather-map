package com.wbrawner.weathermap.util;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFetchHandler implements Callback<WeatherResponse> {
    private static final String TAG = WeatherFetchHandler.class.getSimpleName();

    @Override
    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            WeatherForecast.getInstance().setWeatherResponse(response.body());
            Log.d(TAG, "Weather retrieved. Conditions: " + response.body().getName());
        } else if (response.code() == 401) {
            Log.e(TAG, "Authentication error. Check API Key");
        }
    }

    @Override
    public void onFailure(Call<WeatherResponse> call, Throwable t) {
        Log.e(TAG, "Error retrieving weather: ", t);
    }
}
