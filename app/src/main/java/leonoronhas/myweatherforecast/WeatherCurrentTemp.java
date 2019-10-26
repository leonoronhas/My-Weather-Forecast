package leonoronhas.myweatherforecast;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

class WeatherCurrentTemp {

    private String name;

    @SerializedName("main")
    private Map<String, Float> measurements;

    private Map<String, Float> getMeasurements() {
        return measurements;
    }

    public String getName() {
        return name;
    }

    public String toStrings(){
        return "Temperature in " + name + " is " + measurements;
    }
}
