package leonoronhas.myweatherforecast;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

class WeatherCurrentTemp {

    private String name;

    @SerializedName("main")
    private Map<String, Float> measurements;

    Map<String, Float> getMeasurements() {
        return measurements;
    }

    String getName() {
        return name;
    }

}
