package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LongTermForecast {
	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key
	String City, Sky, Country, Time;
	String Temp, Pressure, Humidity, TempMax, TempMin, Rain, Snow;
	String WindSpeed, WindDir;

	// arraylists of all information
	Queue<String> DateList = new LinkedList<String>();
	Queue<String> TempList = new LinkedList<String>();
	Queue<String> PressureList = new LinkedList<String>();
	Queue<String> HumidityList = new LinkedList<String>();
	Queue<String> TempMaxList = new LinkedList<String>();
	Queue<String> TempMinList = new LinkedList<String>();
	Queue<String> WindSpeedList = new LinkedList<String>();
	Queue<String> WindDirList = new LinkedList<String>();
	Queue<String> RainList = new LinkedList<String>();
	Queue<String> SnowList = new LinkedList<String>();

	public LongTermForecast(String cityName, int Term)
			throws UnsupportedEncodingException {
		Url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
				+ cityName + "&mode=json&units=metric&cnt=" + Term + Key;

		// send link to json parser and get weather data back as string
		JsonParser Data = new JsonParser(Url);
		String DataStr = Data.getData();

		// Save all weather information
		JSONObject JsonData = JSONObject.fromObject(DataStr);

		// daily information
		JSONArray Arraylist = JsonData.getJSONArray("list");

		// get information from city object
		JSONObject ObjSys = JsonData.getJSONObject("city");
		Country = ObjSys.getString("country");// country
		City = ObjSys.getString("name");// city

		// Check the city we got from server is our request or not
		String CityMatch = City + "," + Country;
		if (CityMatch.equalsIgnoreCase(cityName) == false)
			throw new RuntimeException("City not found");

		// // Short term shows daily forecast, for at least 5 days forecast
		for (int i = 0; i < Term; i++) {

			JSONObject ObjDay = Arraylist.getJSONObject(i);

			WindSpeed = ObjDay.getString("speed");// wind speed
			WindDir = ObjDay.getString("deg");// wind direction

			// Rain
			if (ObjDay.containsKey("rain")) {
				Rain = ObjDay.getString("rain");
			}

			else {
				Rain = "0";
			}
			// snow
			if (ObjDay.containsKey("snow")) {
				Snow = ObjDay.getString("snow");
			}

			else {
				Snow = "0";
			}

			Pressure = ObjDay.getString("pressure"); // pressure
			Humidity = ObjDay.getString("humidity");// humidity
			Time = ObjDay.getString("dt");
			JSONArray ArrayWeather = ObjDay.getJSONArray("weather");
			JSONObject ObjSky = ArrayWeather.getJSONObject(0);

			Sky = ObjSky.getString("description");// sky description

			JSONObject Objtemp = ObjDay.getJSONObject("temp");
			Temp = Objtemp.getString("day");// temperature
			TempMin = Objtemp.getString("min");// min temperature
			TempMax = Objtemp.getString("max");// max temperature

			DateList.add(Unix2Date(Time));
			TempList.add(Temp);
			PressureList.add(Pressure);
			HumidityList.add(Humidity);
			TempMaxList.add(TempMax);
			TempMinList.add(TempMin);
			WindSpeedList.add(WindSpeed);
			WindDirList.add(WindDir);
			RainList.add(Rain);
			SnowList.add(Snow);
		}

	}

	// convert date from unix type to date
	public String Unix2Date(String date) {
		Long Time = Long.parseLong(date) * 1000;
		String Date = new java.text.SimpleDateFormat("yyyy/MM/dd")
				.format(new java.util.Date(Time));
		return Date;
	}

}
