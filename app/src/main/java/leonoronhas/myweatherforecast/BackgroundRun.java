package leonoronhas.myweatherforecast;

import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class BackgroundRun extends Thread {

    WeatherCurrentTemp weatherCurrentTemp;
    WeatherForecastResults weatherForecastResults;
    private MainActivity activity;
    private String city = "";
    private int choice = 0;

    BackgroundRun(String city, MainActivity activity, int choice) {
        this.city = city;
        this.activity = activity;
        this.choice = choice;
    }

    @Override
    public void run() {
        super.run();

        if (choice == 1)
            weatherCurrentTemp = loadTemp(city);
        else
            weatherForecastResults = loadForecast(city);

        if (choice == 1) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DisplayTemp();
                }
            });
        }
        else{
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DisplayForecast();
                }
            });
        }
    }

    private void DisplayTemp() {
        //                Toast.makeText(activity, "Temp in " + weatherCurrentTemp.getName() + " is " + weatherCurrentTemp.getMeasurements().get("temp"),
//                        Toast.LENGTH_LONG).show(); THIS IS THE DEFAULT
        Toast toast = Toast.makeText(activity, "Temp. in " + weatherCurrentTemp.getName() +
                        " is " + weatherCurrentTemp.getMeasurements().get("temp") + " °F",
                Toast.LENGTH_LONG);
        // Set Toast location
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 105, -470);
        toast.show();
    }

    private void DisplayForecast(){
        ArrayAdapter adapter = new ArrayAdapter<WeatherForecast>(activity, android.R.layout.simple_list_item_1,
                weatherForecastResults.getWeatherForecastList());
        activity.listView.setAdapter(adapter);
    }

    private WeatherCurrentTemp loadTemp(String city) {
        Gson gson = new Gson();
        String data = HTTPHelperTemp(city);

        return gson.fromJson(data, WeatherCurrentTemp.class);
    }

    private WeatherForecastResults loadForecast(String city) {
        Gson gson = new Gson();
        String data = HTTPHelperForecast(city);

        return gson.fromJson(data, WeatherForecastResults.class);
    }


    // Connect and receive Json data.
    private String HTTPHelperTemp(String city) {

        String baseURL = "http://api.openweathermap.org/data/2.5/weather?";
        String apiKey = "4f961575f8f90a33e18628595c421e8d";
        String urlC = baseURL + "q=" + city + "&units=imperial&apiKey=" + apiKey;

        StringBuilder jsonData = new StringBuilder();
        try {
            URL url = new URL(urlC);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Retrieve info into string
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    jsonData.append(line);
                }
            }
            while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData.toString();
    }

    // Connect and receive Json data.
    private String HTTPHelperForecast(String city) {

        String baseURL = "http://api.openweathermap.org/data/2.5/forecast?";
        String apiKey = "4f961575f8f90a33e18628595c421e8d";
        String urlC = baseURL + "q=" + city + "&units=imperial&apiKey=" + apiKey;

        StringBuilder jsonData = new StringBuilder();
        try {
            URL url = new URL(urlC);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Retrieve info into string
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    jsonData.append(line);
                }
            }
            while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData.toString();
    }

}

