
package com.wbrawner.weathermap.model;

import com.squareup.moshi.Json;

public class Main {

    @Json(name = "temp")
    private Double temp;
    @Json(name = "humidity")
    private Integer humidity;
    @Json(name = "pressure")
    private Integer pressure;
    @Json(name = "temp_min")
    private Double tempMin;
    @Json(name = "temp_max")
    private Double tempMax;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
