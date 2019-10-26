package leonoronhas.myweatherforecast;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class BackgroundRun extends Thread {

    private String city = "";


    BackgroundRun(String city) {
        this.city = city;
    }

    @Override
    public void run() {
        super.run();

        loadTemp(city);
    }


    private WeatherCurrentTemp loadTemp(String city){
        Gson gson = new Gson();
        String data = HTTPHelper(city);

        return gson.fromJson(data, WeatherCurrentTemp.class);
    }


    // Connect and receive Json data.
        private String HTTPHelper(String city){

            String baseURL = "http://api.openweathermap.org/data/2.5/weather?";
            String apiKey = "4f961575f8f90a33e18628595c421e8d";
            String query = baseURL + "q=" + city + "&apiKey=" + apiKey;

            //combine parts of url and create URL Object
            String urlC = baseURL+ query;

            StringBuilder jsonData = new StringBuilder();
            try {
                URL url = new URL(urlC);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Retrieve info into string
                String line;
                do{
                    line = reader.readLine();
                    if(line != null){
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

