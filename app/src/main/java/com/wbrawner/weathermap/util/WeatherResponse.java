
package com.wbrawner.weathermap.util;

import java.util.List;
import com.squareup.moshi.Json;
import com.wbrawner.weathermap.model.Clouds;
import com.wbrawner.weathermap.model.Coord;
import com.wbrawner.weathermap.model.Main;
import com.wbrawner.weathermap.model.Rain;
import com.wbrawner.weathermap.model.Sys;
import com.wbrawner.weathermap.model.Weather;
import com.wbrawner.weathermap.model.Wind;

public class WeatherResponse {

    @Json(name = "coord")
    private Coord coord;
    @Json(name = "sys")
    private Sys sys;
    @Json(name = "weather")
    private List<Weather> weather = null;
    @Json(name = "main")
    private Main main;
    @Json(name = "wind")
    private Wind wind;
    @Json(name = "rain")
    private Rain rain;
    @Json(name = "clouds")
    private Clouds clouds;
    @Json(name = "dt")
    private Integer dt;
    @Json(name = "id")
    private Integer id;
    @Json(name = "name")
    private String name;
    @Json(name = "cod")
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

}
