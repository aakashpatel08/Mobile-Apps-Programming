package edu.uco.apatel19.p7aakashp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ImageView weatherIcon;
    private TextView displayDate, displayCity, displayTemperature, displayDetails, displayWindSpeed;
    private String cityName;
    private int MY_PERMISSION_RECEIVE_SMS = 1;
    static Department[] departments = {
            new Department( "Biology","http://www.uco.edu/cms/biology/","4059745017",35.653473,-97.473579),
            new Department( "Chemistry","http://www.uco.edu/cms/chemistry/","4059745018",35.654611,-97.472785),
            new Department( "Computer Science","http://cs.uco.edu/","4059745716",35.653813,-97.472695),
            new Department( "Engineering","http://www.uco.edu/cms/engineering/","4059742000",35.654591,-97.472377),
            new Department( "Funeral Service","http://www.uco.edu/cms/funeral/index.asp","4059745001",35.654918,-97.472666),
            new Department( "Math and Statistics","http://www.math.uco.edu","4059745012",35.654023,-97.472848),
            new Department( "Nursing","http://www.uco.edu/cms/nursing/","4059745000",35.653282,-97.473440)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherIcon = (ImageView) findViewById(R.id.displayWeatherIcon);
        displayCity = (TextView) findViewById(R.id.displayCitName);
        displayTemperature = (TextView) findViewById(R.id.displayTemperature);
        displayDetails = (TextView) findViewById(R.id.displayDetails);
        displayWindSpeed = (TextView) findViewById(R.id.displayWindSpeed);
        displayDate = (TextView) findViewById(R.id.date);

        //Show current date
        Thread thread = new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                                String dateString = simpleDateFormat.format(date);
                                displayDate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cityName = bundle.getString("CITYNAME");
            new HttpGetTask().execute(cityName);
        }

        // API 23 or higher - runtime permission handling
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(
                    Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},
                        MY_PERMISSION_RECEIVE_SMS);
            }
        }
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

            String city = result.remove(0);
            if (city.equalsIgnoreCase(cityName)){
                Picasso.with(MainActivity.this).load(FORECAST_IMAGE_URL).into(weatherIcon); //load weather icon
                displayCity.setText(city); //display city name
                displayTemperature.setText("Temp: " + result.remove(0) + "°F"); //display temperature
                displayDetails.setText("Details: " + result.remove(0)); //display weather details
                displayWindSpeed.setText("Wind: " + result.remove(0) + " mph"); //display wind speed
                return;
            } else {
                Toast.makeText(MainActivity.this, "Invalid City Name Entered: " + city, Toast.LENGTH_SHORT).show();
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
