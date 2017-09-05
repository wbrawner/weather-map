package com.wbrawner.weathermap.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.wbrawner.weathermap.view.WeatherActivity;

public class WeatherForecast {
    static WeatherForecast instance;
    private WeatherResponse weatherResponse;
    private Context mContext;

    public static WeatherForecast getInstance() {
        if (instance == null) {
            instance = new WeatherForecast();
        }
        return instance;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
        if (mContext != null) {
            Intent updateIntent =
                    new Intent(mContext, WeatherActivity.WeatherBroadcastReceiver.class);
            updateIntent.setAction(WeatherActivity.ACTION_UPDATE);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(updateIntent);
        }
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
}
