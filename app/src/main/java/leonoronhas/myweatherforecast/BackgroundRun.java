package leonoronhas.myweatherforecast;


import android.view.Gravity;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class BackgroundRun extends Thread {

    private MainActivity activity;
    private String city = "";

    BackgroundRun(String city, MainActivity activity) {
        this.city = city;
        this.activity = activity;
    }

    @Override
    public void run() {
        super.run();

        final WeatherCurrentTemp weatherCurrentTemp;
        weatherCurrentTemp = loadTemp(city);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(activity, "Temp in " + weatherCurrentTemp.getName() + " is " + weatherCurrentTemp.getMeasurements().get("temp"),
//                        Toast.LENGTH_LONG).show(); THIS IS THE DEFAULT
                Toast toast = Toast.makeText(activity, "Temp. in " + weatherCurrentTemp.getName() +
                                " is " + weatherCurrentTemp.getMeasurements().get("temp") + " °F",
                    Toast.LENGTH_LONG);
                // Set Toast location
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 35, -470);
                toast.show();
            }
        });
    }

    private WeatherCurrentTemp loadTemp(String city) {
        Gson gson = new Gson();
        String data = HTTPHelper(city);

        return gson.fromJson(data, WeatherCurrentTemp.class);
    }

    // Connect and receive Json data.
    private String HTTPHelper(String city) {

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

}

