package com.wbrawner.weathermap.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wbrawner.weathermap.R;
import com.wbrawner.weathermap.util.TempUtils;
import com.wbrawner.weathermap.util.WeatherForecast;
import com.wbrawner.weathermap.util.WeatherResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {
    private WeatherResponse forecast;
    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.forecastImg)
    ImageView forecastImg;

    @BindView(R.id.temp)
    TextView temp;

    @BindView(R.id.high)
    TextView high;

    @BindView(R.id.low)
    TextView low;


    public static final String ACTION_UPDATE = "com.wbrawner.weathermap.view.action.UPDATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new WeatherBroadcastReceiver(), filter);
//        WeatherForecast.getInstance().setContext(this);
        checkForecast();
    }

    private void checkForecast() {
        forecast = WeatherForecast.getInstance().getWeatherResponse();
        if (forecast != null)
            updateForecast();
    }

    private void updateForecast() {
        setTitle(forecast.getName());
        Picasso.with(this)
                .load("http://openweathermap.org/img/w/" + forecast.getWeather().get(0).getIcon() + ".png")
                .into(forecastImg);
        description.setText(forecast.getWeather().get(0).getDescription());
        temp.setText(String.format(
                getResources().getConfiguration().locale,
                "%.1f °C",
                TempUtils.kelvinToCelsius(forecast.getMain().getTemp())
        ));
        high.setText(String.format(
                getResources().getConfiguration().locale,
                "%.1f °C",
                TempUtils.kelvinToCelsius(forecast.getMain().getTempMax())
        ));
        low.setText(String.format(
                getResources().getConfiguration().locale,
                "%.1f °C",
                TempUtils.kelvinToCelsius(forecast.getMain().getTempMin())
        ));
    }

    public class WeatherBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkForecast();
        }
    }
}
