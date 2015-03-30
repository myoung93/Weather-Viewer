package group17.weatherviewer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.lang.*;

//A data type class to hold all necessary information for the Current Weather view.
public class CurrentWeather {

    //Data fields required for Current Weather view
	private String city, skyCon, sunrise, sunset, country;
	private double temp, pressure, humidity, maxTemp, minTemp, windSpeed, windDir;
	private int weatherID;

    /**
     * Constructor for CurrentWeather
     * Takes a city name as parameter, retrieves and parses the JSON for that city and stores the data
     * @param cityName is the name of the city for which we're storing and parsing information
     * @throws UnsupportedEncodingException if the JSON retrieval fails
     */
	public CurrentWeather(String cityName) throws UnsupportedEncodingException {

		StringBuffer SBR;

		// get weather information from http://api.openweathermap.org for given
		// city
		// 65da394090951035f3a346d9a356ddd9 api key from OpenWeatherMap
		String owmUrl = "http://api.openweathermap.org/data/2.5/weather?q="
				+ cityName + "&APPID=65da394090951035f3a346d9a356ddd9";

		// get current weather data from openweathermap.org in JSON format
		SBR = new StringBuffer();

		try {
			URL url = new URL(owmUrl);
			URLConnection connection = url.openConnection();
			BufferedReader BReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = BReader.readLine()) != null)
				SBR.append(line + " ");
			BReader.close();
		
			String weatherData = SBR.toString();

			// parse JSON data, JSON_Data:weatherData

			JSONObject jsonData = JSONObject.fromObject(weatherData);
			// Save all weather information��
			
			// get information from sys object
			JSONObject objSys = jsonData.getJSONObject("sys");// country
			country = objSys.getString("country");
			//grab sunrise/sunset and convert to real time
			sunrise = Conversion.unixToTime(objSys.getString("sunrise"));
			sunset = Conversion.unixToTime(objSys.getString("sunset"));
			
			// get information from string
			city = jsonData.getString("name");// city
			
			// get information from main object
			JSONObject objMain = jsonData.getJSONObject("main");
			//we subtract 273 from each temperature value because there is no need to store the kelvin value
			temp = objMain.getDouble("temp") - 273;// temperature
			minTemp = objMain.getDouble("temp_min") - 273;// min temperature
			maxTemp = objMain.getDouble("temp_max") - 273;// max temperature
			//divide by 10 to convert from hPa to kPa
			pressure = objMain.getDouble("pressure")/10; // pressure
			humidity = objMain.getDouble("humidity");// humidity
			
			// get information from wind object
			JSONObject objWind = jsonData.getJSONObject("wind");
			windSpeed = objWind.getDouble("speed");// wind speed
			windDir = objWind.getDouble("deg");// wind direction
			
			// get information from weather object
			JSONArray arrayWeather = jsonData.getJSONArray("weather");
			JSONObject objWea = arrayWeather.getJSONObject(0);

			skyCon = objWea.getString("description");
			

			//get weatherID info
			weatherID = objWea.getInt("id");
		}
		catch (IOException e) {
            System.out.println("There was an error retrieving the weather information");
        }
	}

	// Getters -> Modified to return Strings instead of Doubles -TE

    /**
     * Getter method for city
     * @return the city from which this weather data was gathered
     */
    public String getCity() {
        if(city != null)    return city;
        else                return "ERR";
    }

    /**
     * Getter method for windSpeed
     * @return the current wind speed
     */
	public String getWindSpeed() {
		return String.valueOf(Math.round(windSpeed*3.6));
	}
    /**
     * Getter method for pressure
     * @return the current pressure
     */
	public String getPressure() {
		int substringLength;
		if(pressure > 100)
			substringLength = 5;
		else
			substringLength = 4;

		String pressureString = String.valueOf(pressure);
		if(pressureString.length() < 4)
			substringLength = pressureString.length();

		return pressureString.substring(0, substringLength);
	}

    /**
     * Getter method for humidity
     * @return the current humidity in %
     */
	public String getHumidity() {
		return String.valueOf((int)humidity);
	}
    /**
     * Getter method for sunrise
     * @return the current sunrise time in Unix time
     */
	public String getSunriseTime() {
		return sunrise;
	}
    /**
     * Getter method for sunset
     * @return the current sunset time in Unix time
     */
	public String getSunsetTime() {
		return sunset;
	}
    /**
     * Getter method for wind direction
     * @return the current wind direction in degrees
     */
	public String getWindDirection() {
		//this is done using cases, we essentially divide the circle (360 degrees) into sections, and assign each section
		//a corresponding direction representation
		if(windDir > 337.5 && windDir <= 360)
			return "N";
		else if(windDir >= 0 && windDir <= 22.5)
			return "N";
		else if(windDir > 22.5 && windDir <= 67.5)
			return "NE";
		else if(windDir > 67.5 && windDir <= 112.5)
			return "E";
		else if(windDir > 112.5 && windDir <= 157.5)
			return "SE";
		else if(windDir > 157.5 && windDir <= 202.5)
			return "S";
		else if(windDir > 202.5 && windDir <= 247.5)
			return "SW";
		else if(windDir > 247.5 && windDir <= 292.5)
			return "W";
		else if(windDir > 292.5 && windDir <= 337.5)
			return "NW";
		else
			return "?";
	}

    /**
     * Getter method for Celsius temperature
     * @return the current temp in degrees C (will be only temp method in later versions)
     */
	public String getTemp(char tempUnit) {
		if(tempUnit == 'f')
            return cleanTemp(temp * 9 / 5 + 32, tempUnit);
        else
            return cleanTemp(temp, tempUnit);
	}

    public String getMaxTemp(char tempUnit) {
        if(tempUnit == 'f')
            return cleanTemp(maxTemp * 9 / 5 + 32, tempUnit);
        else
            return cleanTemp(maxTemp, tempUnit);
    }

    public String getMinTemp(char tempUnit) {
        if(tempUnit == 'f')
            return cleanTemp(minTemp * 9 / 5 + 32, tempUnit);
        else
            return cleanTemp(minTemp, tempUnit);
    }

    //puts the temperature into a readable form
    private String cleanTemp(double t, char unit) {
        int substringLength = 1;
        //correct for negative zero
        if(t < 0 && t >= -1)
            t = 0;
        //correct number of characters to take
        if (t < 0 || t > 10) {
            substringLength++;
            if(t < -10)
                substringLength++;
        }
        return String.valueOf(t).substring(0,substringLength) + '°' + Character.toUpperCase(unit);
    }
    /**
     * Getter method for country name
     * @return the name of the country the city is in
     */
	public String getCountry() {
		return country;
	}

    /**
     * Getter method for sky condition
     * @return a string representing sky condition
     */
	public String getSkyCondition() {
		return skyCon.substring(0, 1).toUpperCase() + skyCon.substring(1);
	}

	public int getWeatherID(){
		return weatherID;
	}
    
}
