package group17.weatherviewer;


import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LongTermForecast {
	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	String City, Sky, Country, Time;
	String Temp, Pressure, Humidity, TempMax, TempMin;
	double WindSpeed, WindDir;

	Queue<String> DateList = new LinkedList<String>();
	Queue<String> TempList = new LinkedList<String>();
	Queue<String> PressureList = new LinkedList<String>();
	Queue<String> HumidityList = new LinkedList<String>();
	Queue<String> TempMaxList = new LinkedList<String>();
	Queue<String> TempMinList = new LinkedList<String>();
	Queue<String> WindSpeedList = new LinkedList<String>();
	Queue<String> WindDirList = new LinkedList<String>();

	public LongTermForecast(String cityName, int Term)
			throws UnsupportedEncodingException {
		Url= "http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityName+"&mode=json&units=metric&cnt="+Term + Key;

		JsonParaer Data = new JsonParaer(Url);
		String DataStr = Data.getData();

		JSONObject JsonData = JSONObject.fromObject(DataStr);
		JSONArray Arraylist = JsonData.getJSONArray("list");
		// Save all weather information

		// get information from city object
		JSONObject ObjSys = JsonData.getJSONObject("city");
		Country = ObjSys.getString("country");// country
		City = ObjSys.getString("name");// city

		// Check the city we got from server is our request or not
		String CityMatch = City + "," + Country;
		if (CityMatch.equalsIgnoreCase(cityName) == false)
			throw new RuntimeException("City not found");

		for (int i = 0; i < Term; i++) {

			JSONObject ObjWind = Arraylist.getJSONObject(i);
			WindSpeed = ObjWind.getDouble("speed");// wind speed
			WindDir = ObjWind.getDouble("deg");// wind direction
			String windspeed = Double.toString(WindSpeed);
			String winddir = Double.toString(WindDir);

			JSONArray ArrayWeather = ObjWind.getJSONArray("weather");
			JSONObject ObjSky = ArrayWeather.getJSONObject(0);
			Sky = ObjSky.getString("description");// sky description

			JSONObject Objtemp = ObjWind.getJSONObject("temp");
			Temp = Objtemp.getString("day");// temperature
			TempMin = Objtemp.getString("min");// min temperature
			TempMax = Objtemp.getString("max");// max temperature
			Pressure = ObjWind.getString("pressure"); // pressure
			Humidity = ObjWind.getString("humidity");// humidity

			Time = ObjWind.getString("dt");

			DateList.add(Unix2Date(Time));
			TempList.add(Temp);
			PressureList.add(Pressure);
			HumidityList.add(Humidity);
			TempMaxList.add(TempMax);
			TempMinList.add(TempMin);
			WindSpeedList.add(windspeed);
			WindDirList.add(winddir);

		}

	}

	public String Unix2Date(String date) {
		Long Time = Long.parseLong(date) * 1000;
		String Date = new java.text.SimpleDateFormat("yyyy/MM/dd")
				.format(new java.util.Date(Time));
		return Date;
	}
}
