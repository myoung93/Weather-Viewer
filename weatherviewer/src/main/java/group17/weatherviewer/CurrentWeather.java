package group17.weatherviewer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

//A data type class to hold all necessary information for the Current Weather view.
public class CurrentWeather {

    //Data fields required for Current Weather view
	private String City, Sky, Sunrise, Sunset, Country;
	private double temp, pressure, humidity, maxTemp, minTemp, windSpeed, windDir;
	private int weatherID;

    /**
     * Constructor for CurrentWeather
     * Takes a City name as parameter, retrieves and parses the JSON for that city and stores the data
     * @param CityName is the name of the city for which we're storing and parsing information
     * @throws UnsupportedEncodingException if the JSOn retrieval fails
     */
	public CurrentWeather(String CityName) throws UnsupportedEncodingException {

		StringBuffer SBR;

		// get weather information from http://api.openweathermap.org for given
		// city
		// 65da394090951035f3a346d9a356ddd9 api key from OpenWeatherMap
		String OwmUrl = "http://api.openweathermap.org/data/2.5/weather?q="
				+ CityName + "&APPID=65da394090951035f3a346d9a356ddd9";

		// get current weather data from openweathermap.org in JSON format
		SBR = new StringBuffer();

		try {
			URL url = new URL(OwmUrl);
			URLConnection connection = url.openConnection();
			BufferedReader BReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = BReader.readLine()) != null)
				SBR.append(line + " ");
			BReader.close();
		} 
		catch (IOException e) {
            System.out.println("There was an error retrieving the weather information");
        }

		String WeatherData = SBR.toString();

		// parse JSON data, JSON_Data:WeatherData

		JSONObject JsonData = JSONObject.fromObject(WeatherData);
        // Save all weather information��

		// get information from sys object
		JSONObject Obj_Sys = JsonData.getJSONObject("sys");// country
		Country = Obj_Sys.getString("country");
		//grab sunrise/sunset and convert to real time
		Sunrise = Conversion.toTime(Obj_Sys.getString("sunrise"));
		Sunset = Conversion.toTime(Obj_Sys.getString("sunset"));

		// get information from string
		City = JsonData.getString("name");// city

		// get information from main object
		JSONObject Obj_Main = JsonData.getJSONObject("main");
        //we subtract 273 from each temperature value because there is no need to store the kelvin value
		temp = Obj_Main.getDouble("temp") - 273;// temperature
        minTemp = Obj_Main.getDouble("temp_min") - 273;// min temperature
        maxTemp = Obj_Main.getDouble("temp_max") - 273;// max temperature
		//divide by 10 to convert from hPa to kPa
		pressure = Obj_Main.getDouble("pressure")/10; // pressure
		humidity = Obj_Main.getDouble("humidity");// humidity


		// get information from wind object
		JSONObject Obj_wind = JsonData.getJSONObject("wind");
		windSpeed = Obj_wind.getDouble("speed");// wind speed
		windDir = Obj_wind.getDouble("deg");// wind direction

		// get information from weather object
		JSONArray Array_Weather = JsonData.getJSONArray("weather");
		JSONObject Obj_Wea = Array_Weather.getJSONObject(0);
		Sky = Obj_Wea.getString("description");
		
		//get weatherID info
		weatherID = Obj_Wea.getInt("id");
	}

	// Getters -> Modified to return Strings instead of Doubles -TE

    /**
     * Getter method for City
     * @return the city from which this weather data was gathered
     */
    public String getCity() {
        if(City != null)    return City;
        else                return "ERR";
    }

    /**
     * Getter method for windSpeed
     * @return the current wind speed
     */
	public String getWindSpeed() {
		return String.valueOf(windSpeed);
	}
    /**
     * Getter method for pressure
     * @return the current pressure
     */
	public String getPressure() {
		return String.valueOf(pressure);
	}

    /**
     * Getter method for humidity
     * @return the current humidity in %
     */
	public String getHumidity() {
		return String.valueOf(humidity);
	}
    /**
     * Getter method for Sunrise
     * @return the current Sunrise time in Unix time
     */
	public String getSunriseTime() {
		return Sunrise;
	}
    /**
     * Getter method for Sunset
     * @return the current Sunset time in Unix time
     */
	public String getSunsetTime() {
		return Sunset;
	}
    /**
     * Getter method for wind direction
     * @return the current wind direction in degrees
     */
	public String getWindDirection() {
		return String.valueOf(windDir);
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
        int substring_length = 1;
        //correct for negative zero
        if(t < 0 && t >= -1)
            t = 0;
        //correct number of characters to take
        if (t < 0 || t > 10) {
            substring_length++;
            if(t < -10)
                substring_length++;
        }
        return String.valueOf(t).substring(0,substring_length) + '°' + Character.toUpperCase(unit);
    }
    /**
     * Helper method for Celsius temperature
     * @return the current temp in degrees C (will phase this out in later versions)
     */
//	 public String getTempC() {
//        return String.valueOf(temp - 273);
//    }
    /**
     * Getter method for Fahrenheit temperature
     * @return the current temp in degrees F (will phase this out in later versions)
     */
//	public String getTempF() {
//        return String.valueOf(((temp - 273) * 9 / 5.0) + 32);
//    }

    /**
     * Getter method for max Celsius temperature
     * @return the current max temp in degrees C
     */
//	public String getMaxTempC() {
//        return String.valueOf(maxTemp - 273);
//    }
    /**
     * Getter method for max F temperature
     * @return the current max temp in degrees F
     */
//	public String getTempMaxF() {
//        return String.valueOf(((maxTemp - 273) * 9 / 5.0) + 32);
//    }
    /**
     * Getter method for min Celsius temperature
     * Same story as getTemp()s, will be the only getTempMax method later.
     * @return the current min temp in degrees C
     */
//	public String getTempMin() {
//		return getTempMinC();
//	}
    /**
     * Getter method for min Celsius temperature
     * @return the current min temp in degrees C
     */
//	public String getTempMinC() {
//        return String.valueOf(minTemp - 273);
//    }
    /**
     * Getter method for min F temperature
     * @return the current min temp in degrees f
     */
//    public String getTempMinF() {
//        return String.valueOf(((minTemp - 273) * 9 / 5.0) + 32);
//    }
    /**
     * Getter method for country name
     * @return the name of the country the city is in
     */
	public String getCountry() {
		return Country;
	}

    /**
     * Getter method for sky condition
     * @return a string representing sky condition
     */
	public String getSkyCondition() {
		return Sky;
	}

	public int getWeatherID(){
		return weatherID;
	}
    /**
     * Purely for testing purposes, will be taken out in later versions.
     * @param args command line arguments
     */
	/*public static void main(String[] args) {
		try {
			CurrentWeather test = new CurrentWeather("Shanghai,CN"); // using shanghai CN to test
			System.out.println( test.getCountry()+ "\n" + test.getCity() + "\n" + test.getTemp('c') + "\n"
					+ test.getPressure() + "\n" + test.getHumidity() + "\n" + test.getTempMin() + "\n" + test.getTempMax()
					+ "\n" + test.getWindSpeed() + "\n" + test.getWindDirection() + "\n" + test.getSkyCondition() + "\n"
					+ test.getSunriseTime() + "\n" + test.getSunsetTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
