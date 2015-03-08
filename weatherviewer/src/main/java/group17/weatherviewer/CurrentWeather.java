package group17.weatherviewer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class CurrentWeather {

	private String City, Sky, Sunrise, Sunset, Country;
	private double Temp, Pressure, Humidity, TempMax, TempMin, WindSpeed,
			WindDir;

	public CurrentWeather(String CityName) throws UnsupportedEncodingException {

		// URL open weather map

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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String WeatherData = SBR.toString();

		// parse JSON data, JSON_Data:WeatherData

		JSONObject JsonData = JSONObject.fromObject(WeatherData);
        // Save all weather information��

		// get information from sys object
		JSONObject Obj_Sys = JsonData.getJSONObject("sys");// country
		Country = Obj_Sys.getString("country");
		Sunrise = Obj_Sys.getString("sunrise");
		Sunset = Obj_Sys.getString("sunset");

		// get information from string
		City = JsonData.getString("name");// city

		// get information from main object
		JSONObject Obj_Main = JsonData.getJSONObject("main");
		Temp = Obj_Main.getDouble("temp");// temperature
		Pressure = Obj_Main.getDouble("pressure"); // pressure
		Humidity = Obj_Main.getDouble("humidity");// humidity
		TempMin = Obj_Main.getDouble("temp_min");// min temperature
		TempMax = Obj_Main.getDouble("temp_max");// max temperature

		// get information from wind object
		JSONObject Obj_wind = JsonData.getJSONObject("wind");
		WindSpeed = Obj_wind.getDouble("speed");// wind speed
		WindDir = Obj_wind.getDouble("deg");// wind direction

		// get information from weather object
		JSONArray Array_Weather = JsonData.getJSONArray("weather");
		JSONObject Obj_Wea = Array_Weather.getJSONObject(0);
		Sky = Obj_Wea.getString("description");

		
	}

	// Getters -> Modified to return Strings instead of Doubles -TE
	
	//returns wind speed in [km/h]
	public String getWindSpeed() {
		return String.valueOf(WindSpeed);
	}
	//returns air pressure in [?]
	public String getPressure() {
		return String.valueOf(Pressure);
	}
	//returns humidity in [%]
	public String getHumidity() {
		return String.valueOf(Humidity);
	}
	//returns sunrise time in [?]
	public String getSunriseTime() {
		return Sunrise;
	}
	//returns sunset time in [?]
	public String getSunsetTime() {
		return Sunset;
	}
	//returns wind direction in [?]
	public String getWindDirection() {
		return String.valueOf(WindDir);
	}
	//returns city name
	public String getCity() {
        if(City != null)    return City;
        else                return "ERR";
	}
	//returns temperature in [°K]
	public String getTemp() {
		return String.valueOf(Temp);
	}
	//returns max temperature in [°K]
	public String getTempMax() {
		return String.valueOf(TempMax);
	}
	//returns min tmperature in [°K]
	public String getTempMin() {
		return String.valueOf(TempMin);
	}
	//returns country name
	public String getCountry() {
		return Country;
	}

	public String getSkyCondition() {
		return Sky;
	}

	// Setters (probably not needed)
	public void setCity(String City) {
		this.City = City;
	}

	public void setCountry(String Country) {
		this.Country = Country;
	}

	public void setTemp(double Temp) {
		this.Temp = Temp;
	}

	public void setPressure(double Pressure) {
		this.Pressure = Pressure;
	}

	public void setHumidity(double Humidity) {
		this.Humidity = Humidity;
	}

	public void setTempMin(double TempMin) {
		this.TempMin = TempMin;
	}

	public void setTempMax(double TempMax) {
		this.TempMax = TempMax;
	}

	public void setWinddirection(double WindDir) {
		this.WindDir = WindDir;
	}

	public void setWindspeed(double Windspeed) {
		this.WindSpeed = Windspeed;
	}

	public void setSky(String Sky) {
		this.Sky = Sky;
	}

	public void setSunrise(String Sunrise) {
		this.Sunrise = Sunrise;
	}

	public void setSunset(String Sunset) {
		this.Sunset = Sunset;
	}

	public static void main(String[] args) {
		try {
			CurrentWeather test = new CurrentWeather("Shanghai,CN"); // using shanghai CN to test
			System.out.println( test.getCountry()+ "\n" + test.getCity() + "\n" + test.getTemp() + "\n"
					+ test.getPressure() + "\n" + test.getHumidity() + "\n" + test.getTempMin() + "\n" + test.getTempMax()
					+ "\n" + test.getWindSpeed() + "\n" + test.getWindDirection() + "\n" + test.getSkyCondition() + "\n"
					+ test.getSunriseTime() + "\n" + test.getSunsetTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
