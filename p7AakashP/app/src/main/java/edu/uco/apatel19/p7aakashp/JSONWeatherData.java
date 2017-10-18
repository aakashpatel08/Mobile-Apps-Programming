package edu.uco.apatel19.p7aakashp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONWeatherData {
    private static final String TAG = "JSONWeatherData";
    public static String icon;

    public static ArrayList<String> getData(String forecastJsonStr) throws JSONException {
        try {
            JSONObject forecastJson = new JSONObject(forecastJsonStr);

            JSONArray weatherIcon = forecastJson.getJSONArray("weather"); // JSON array weather
            icon = weatherIcon.getJSONObject(0).getString("icon"); // get icon code

            String cityName = forecastJson.getString("name"); // city name

            JSONObject getTemperature = forecastJson.getJSONObject("main"); // JSON object main
            double temperature = getTemperature.getDouble("temp"); // get temperature

            JSONArray getDescription = forecastJson.getJSONArray("weather"); // JSON array weather
            String description = getDescription.getJSONObject(0).getString("description"); // get description

            JSONObject getWind = forecastJson.getJSONObject("wind"); // JSON object wind
            double windSpeed = getWind.getDouble("speed"); // get wind speed

            ArrayList<String> result = new ArrayList<>();
            result.add(cityName);
            result.add(temperature + "");
            result.add(description);
            result.add(windSpeed + "");
            return result;

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}
