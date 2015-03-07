package com.example.emilio_emilio.clima;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Emilio-Emilio on 2/21/2015.
 */
public class WeatherDataParser {
    /**
     * Given a string of the form returned by the api call:
     * http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
     * retrieve the maximum temperature for the day indicated by dayIndex
     * (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
        throws JSONException {
        // TODO: add parsing code here
        JSONObject weather = new JSONObject(weatherJsonStr);
        JSONArray days = weather.getJSONArray("list");
        JSONObject daysInfo = days.getJSONObject(dayIndex);
        JSONObject temperetureInfo = daysInfo.getJSONObject("temp");
        return temperetureInfo.getDouble("max");
    }
}
