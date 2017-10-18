package edu.uco.apatel19.p6aakashp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText enterCity;
    private TextView showTemperature, showDetails, showWind;
    private Button getWeather, showMap;
    private String cityLatitude, cityLongitude, temperatureString;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterCity = (EditText) findViewById(R.id.Enter_CityName);
        showTemperature = (TextView) findViewById(R.id.Temperature_TextView);
        showDetails = (TextView) findViewById(R.id.Details_TextView);
        showWind = (TextView) findViewById(R.id.Wind_TextView);
        getWeather = (Button) findViewById(R.id.GetWeather_Button);
        showMap = (Button) findViewById(R.id.ShowMap_Button);
        imageView = (ImageView) findViewById(R.id.Weather_Icon);

        getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityString = enterCity.getText().toString();
                new HttpGetTask().execute(cityString);
            }
        });
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
                myIntent.putExtra("Latitude", cityLatitude);
                myIntent.putExtra("Longitude", cityLongitude);
                myIntent.putExtra("Name", enterCity.getText().toString());
                myIntent.putExtra("Temperature", temperatureString);
                startActivity(myIntent);
            }
        });
    }

    private class HttpGetTask extends AsyncTask<String, Void, ArrayList<String>> {
        private static final String TAG = "HttpGetTask";
        final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            InputStream in = null;
            HttpURLConnection httpUrlConnection = null;
            ArrayList<String> resultArray = null;
            try {
                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter("q", params[0] + ",us") // city
                        .appendQueryParameter("mode", "json") // json format as result
                        .appendQueryParameter("units", "imperial") // imperial unit"
                        .appendQueryParameter("APPID", "bdfc0b1217d8e693ae3f4a64d929fb76")
                        .build();

                URL url = new URL(builtUri.toString());
                httpUrlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(
                        httpUrlConnection.getInputStream());
                String data = readStream(in);
                resultArray = JSONWeatherData.getData(data);
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            } finally {
                if (null != httpUrlConnection) {
                    httpUrlConnection.disconnect();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }
            return resultArray;
        }


        @Override
        protected void onPostExecute(ArrayList<String> result) {
            final String FORECAST_IMAGE_URL = "http://openweathermap.org/img/w/" + JSONWeatherData.icon + ".png";

            if (result == null || result.size() == 0) {
                Toast.makeText(MainActivity.this, "Invalid weather data. Possibly a wrong query",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String cityName = result.remove(0);

            if (cityName.equalsIgnoreCase(enterCity.getText().toString())){
                cityLatitude = result.remove(0);
                cityLongitude = result.remove(0);
                String temperature = result.remove(0);
                temperatureString = temperature;
                Picasso.with(MainActivity.this).load(FORECAST_IMAGE_URL).into(imageView); //load weather icon
                showTemperature.setText("Temp: " + temperature + "°F"); //display temperature
                showDetails.setText("Details: " + result.remove(0)); //display weather details
                showWind.setText("Wind: " + result.remove(0) + " mph"); //display wind speed
                showMap.setEnabled(true);
                return;
            } else {
                Toast.makeText(MainActivity.this, "Invalid City Name Entered: " + cityName, Toast.LENGTH_SHORT).show();
            }
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuilder data = new StringBuilder("");
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }
    }
}
