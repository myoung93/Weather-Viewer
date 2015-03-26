package group17.weatherviewer;


import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LongTermForecast {
	private String url;
	private final String KEY = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	private String 	city, sky, country, time,
					temp, pressure, humidity, tempMax, tempMin;
	private double 	windSpeed, windDir;

	private Queue<String> 	dateList 		= new LinkedList<>(),
							tempList 		= new LinkedList<>(),
							pressureList 	= new LinkedList<>(),
							humidityList 	= new LinkedList<>(),
							tempMaxList  	= new LinkedList<>(),
							tempMinList 	= new LinkedList<>(),
							windSpeedList 	= new LinkedList<>(),
							windDirList 	= new LinkedList<>();

	public LongTermForecast(String cityName, int Term)
			throws UnsupportedEncodingException {
		url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+cityName+"&mode=json&units=metric&cnt="+Term + KEY;

		JsonParser data = new JsonParser(url);
		String dataStr = data.getData();

		JSONObject jsonData = JSONObject.fromObject(dataStr);
		JSONArray arrayList = jsonData.getJSONArray("list");
		// Save all weather information

		// get information from city object
		JSONObject objSys = jsonData.getJSONObject("city");
		country = objSys.getString("country");// country
		city = objSys.getString("name");// city

		// Check the city we got from server is our request or not
		String cityMatch = city + "," + country;
		if (!cityMatch.equalsIgnoreCase(cityName))
			throw new RuntimeException("City not found");

		for (int i = 0; i < Term; i++) {

			JSONObject objWind = arrayList.getJSONObject(i);
			windSpeed = objWind.getDouble("speed");// wind speed
			windDir = objWind.getDouble("deg");// wind direction
			String windSpeedString = Double.toString(windSpeed);
			String windDirString = Double.toString(windDir);

			JSONArray arrayWeather = objWind.getJSONArray("weather");
			JSONObject objSky = arrayWeather.getJSONObject(0);
			sky = objSky.getString("description");// sky description

			JSONObject objTemp = objWind.getJSONObject("temp");
			temp = objTemp.getString("day");// temperature
			tempMin = objTemp.getString("min");// min temperature
			tempMax = objTemp.getString("max");// max temperature
			pressure = objWind.getString("pressure"); // pressure
			humidity = objWind.getString("humidity");// humidity

			time = objWind.getString("dt");

			dateList.add(Conversion.unixToDate(time));
			tempList.add(temp);
			pressureList.add(pressure);
			humidityList.add(humidity);
			tempMaxList.add(tempMax);
			tempMinList.add(tempMin);
			windSpeedList.add(windSpeedString);
			windDirList.add(windDirString);
		}
	}
}
