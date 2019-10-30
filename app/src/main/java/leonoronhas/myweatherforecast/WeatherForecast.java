package leonoronhas.myweatherforecast;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class WeatherForecast {

    @SerializedName("dt_txt")
    private String time;

    @SerializedName("main")
    private Map<String, Float> measurements;

    private List<WeatherDescription> weather;
    private WeatherWindItem wind;

    @Override
    public String toString() {
        return  "Date & Time: " + time + '\n' +
                "Temperature: " + measurements.get("temp") + " °F" + '\n' +
                "Description: " + weather.toString() + '\n' +
                "Wind Speed: " + wind.getSpeed() + " mph";
    }

    public String getTime() {
        return time;
    }

    public Map<String, Float> getMeasurements() {
        return measurements;
    }

    public List<WeatherDescription> getWeather() {
        return weather;
    }

    public WeatherWindItem getWind() {
        return wind;
    }
}
