package com.wbrawner.weathermap.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.wbrawner.weathermap.BuildConfig;
import com.wbrawner.weathermap.util.OpenWeatherMapInterface;
import com.wbrawner.weathermap.util.WeatherFetchHandler;
import com.wbrawner.weathermap.util.WeatherForecast;
import com.wbrawner.weathermap.util.WeatherResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FetchWeatherIntentService extends IntentService {
    private static final String ACTION_FETCH = "com.wbrawner.weathermap.service.action.fetch";

    private static final String EXTRA_LAT = "com.wbrawner.weathermap.service.extra.LAT";
    private static final String EXTRA_LON = "com.wbrawner.weathermap.service.extra.LOM";

    public FetchWeatherIntentService() {
        super("FetchWeatherIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFetch(Context context, String lat, String lon) {
        Intent intent = new Intent(context, FetchWeatherIntentService.class);
        intent.setAction(ACTION_FETCH);
        intent.putExtra(EXTRA_LAT, lat);
        intent.putExtra(EXTRA_LON, lon);
        WeatherForecast.getInstance().setContext(context);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH.equals(action)) {
                final String lat = intent.getStringExtra(EXTRA_LAT);
                final String lon = intent.getStringExtra(EXTRA_LON);
                handleActionFetch(lat, lon);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetch(String lat, String lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        OpenWeatherMapInterface weatherMapInterface = retrofit.create(OpenWeatherMapInterface.class);
        Call<WeatherResponse> responseCall =
                weatherMapInterface.getWeatherForCoordinates(lat, lon, BuildConfig.API_KEY);
        responseCall.enqueue(new WeatherFetchHandler());
    }
}
