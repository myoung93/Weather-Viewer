package group17.weatherviewer;

public class CurrentWeather extends SimpleWeather {

    private int windSpeed,
                pressure,
                humidity,
                sunriseTime,
                sunsetTime;

    private String windDirection;

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getSunriseTime() {
        return sunriseTime;
    }

    public int getSunsetTime() {
        return sunsetTime;
    }

    public String getWindDirections() {
        return windDirection;
    }
}
