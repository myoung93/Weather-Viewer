package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShortTermForecast {

	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	String city, sky, country, time;
	String temp, pressure, humidity, tempMax, tempMin;
	double windSpeed;
	String windDir, date;

	Queue<String> dateList = new LinkedList<String>();
	Queue<String> tempList = new LinkedList<String>();
	Queue<String> pressureList = new LinkedList<String>();
	Queue<String> humidityList = new LinkedList<String>();
	Queue<String> tempMaxList = new LinkedList<String>();
	Queue<String> tempMinList = new LinkedList<String>();
	Queue<String> windSpeedList = new LinkedList<String>();
	Queue<String> windDirList = new LinkedList<String>();

	public ShortTermForecast(String cityName)
			throws UnsupportedEncodingException {

		Url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName
				+ "&type=accurate&mode=json" + Key;

		JsonParser data = new JsonParser(Url);
		String dataStr = data.getData();
		JSONObject jsonData = JSONObject.fromObject(dataStr);// Save all weather
																// information
		JSONArray arrayList = jsonData.getJSONArray("list");

		JSONObject objSys = jsonData.getJSONObject("city");
		country = objSys.getString("country");// country
		city = objSys.getString("name");// city

		// Short term shows every 3 hours forecast, 3X8=24 hours forecast
		for (int i = 0; i < 8; i++) {

			JSONObject objList = arrayList.getJSONObject(i);

			//
			JSONObject objMain = objList.getJSONObject("main");
			temp = objMain.getString("temp");// temperature
			tempMin = objMain.getString("temp_min");// min temperature
			tempMax = objMain.getString("temp_max");// max temperature
			pressure = objMain.getString("pressure"); // pressure
			humidity = objMain.getString("humidity");// humidity

			JSONObject objWind = objList.getJSONObject("wind");
			windSpeed = objWind.getDouble("speed");// wind speed
			windDir = objWind.getString("deg");// wind degree
			String windSpeedString = Double.toString(windSpeed);

			JSONArray arraySky = objList.getJSONArray("weather");
			JSONObject objSky = arraySky.getJSONObject(0);
			sky = objSky.getString("description");// sky description

			date = objList.getString("dt_txt");// date

			dateList.add(date);
			tempList.add(temp);
			pressureList.add(pressure);
			humidityList.add(humidity);
			tempMaxList.add(tempMax);
			tempMinList.add(tempMin);
			windSpeedList.add(windSpeedString);
			windDirList.add(windDir);

		}

	}
}
