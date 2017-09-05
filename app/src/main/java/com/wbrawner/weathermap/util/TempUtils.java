package com.wbrawner.weathermap.util;

/**
 * A class to provide utility functions for conversion between temperature formats
 */
public class TempUtils {
    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin * (9/5)) - 459.67;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
