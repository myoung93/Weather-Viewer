package group17.weatherviewer;

public class CurrentWeather extends SimpleWeather {

    private String  windSpeed,
                    pressure,
                    humidity,
                    sunriseTime,
                    sunsetTime,
                    windDirection;

    /*public CurrentWeather(JSONObject jso) {
        //initialize fields INCLUDING superclass temperature fields from JSON
        //initialize temperature fields in super
        super(jso);
        //initialize fields specific to CurrentWeather
    }*/

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getWindDirection() {
        return windDirection;
    }
}
