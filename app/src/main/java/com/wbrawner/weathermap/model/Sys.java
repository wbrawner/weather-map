
package com.wbrawner.weathermap.model;

import com.squareup.moshi.Json;

public class Sys {

    @Json(name = "country")
    private String country;
    @Json(name = "sunrise")
    private Integer sunrise;
    @Json(name = "sunset")
    private Integer sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

}
