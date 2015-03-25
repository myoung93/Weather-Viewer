package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShortTermForecast {

	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	String City, Sky, Country, Time;
	String Temp, Pressure, Humidity, TempMax, TempMin;
	double WindSpeed;
	String WindDir, Date;

	Queue<String> DateList = new LinkedList<String>();
	Queue<String> TempList = new LinkedList<String>();
	Queue<String> PressureList = new LinkedList<String>();
	Queue<String> HumidityList = new LinkedList<String>();
	Queue<String> TempMaxList = new LinkedList<String>();
	Queue<String> TempMinList = new LinkedList<String>();
	Queue<String> WindSpeedList = new LinkedList<String>();
	Queue<String> WindDirList = new LinkedList<String>();

	public ShortTermForecast(String cityName)
			throws UnsupportedEncodingException {

		Url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName
				+ "&type=accurate&mode=json" + Key;

		JsonParaer Data = new JsonParaer(Url);
		String DataStr = Data.getData();
		JSONObject JsonData = JSONObject.fromObject(DataStr);// Save all weather
																// information
		JSONArray Arraylist = JsonData.getJSONArray("list");

		JSONObject ObjSys = JsonData.getJSONObject("city");
		Country = ObjSys.getString("country");// country
		City = ObjSys.getString("name");// city

		// Short term shows every 3 hours forecast, 3X8=24 hours forecast
		for (int i = 0; i < 8; i++) {

			JSONObject ObjList = Arraylist.getJSONObject(i);

			//
			JSONObject ObjMain = ObjList.getJSONObject("main");
			Temp = ObjMain.getString("temp");// temperature
			TempMin = ObjMain.getString("temp_min");// min temperature
			TempMax = ObjMain.getString("temp_max");// max temperature
			Pressure = ObjMain.getString("pressure"); // pressure
			Humidity = ObjMain.getString("humidity");// humidity

			JSONObject ObjWind = ObjList.getJSONObject("wind");
			WindSpeed = ObjWind.getDouble("speed");// wind speed
			WindDir = ObjWind.getString("deg");// wind degree
			String windspeed = Double.toString(WindSpeed);

			JSONArray ArraySky = ObjList.getJSONArray("weather");
			JSONObject ObjSky = ArraySky.getJSONObject(0);
			Sky = ObjSky.getString("description");// sky description

			Date = ObjList.getString("dt_txt");// date

			DateList.add(Date);
			TempList.add(Temp);
			PressureList.add(Pressure);
			HumidityList.add(Humidity);
			TempMaxList.add(TempMax);
			TempMinList.add(TempMin);
			WindSpeedList.add(windspeed);
			WindDirList.add(WindDir);

		}

	}
}
