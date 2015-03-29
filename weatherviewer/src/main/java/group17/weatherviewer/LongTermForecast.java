package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

	// arraylist of all weather information
	private ArrayList<LongTermWeather> longTermForecast = new ArrayList<LongTermWeather>();

	
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
			
			longTermForecast.add(new LongTermWeather(Time, Sky, Temp, TempMax, TempMin, Rain, Snow));
		}

	}

	// convert date from unix type to date
	public String Unix2Date(String date) {
		Long Time = Long.parseLong(date) * 1000;
		String Date = new java.text.SimpleDateFormat("yyyy/MM/dd")
				.format(new java.util.Date(Time));
		return Date;
	}
	
	//getter methods
	
	//returns weather information
	public ArrayList<LongTermWeather> getLongTermForecast(){
		return this.longTermForecast;
	}
	
	//helper class
	public class LongTermWeather{
		private String date, skycon, temp, high, low, rain, snow;
		
		public LongTermWeather(String date, String skycon, String temp, String high, String low, String rain, String snow){
			this.date = date;
			this.skycon = skycon;
			this.temp = temp;
			this.high = high;
			this.low= low;
			this.rain = rain;
			this.snow = snow;
		}
			
		//getter methods
		
		public String getDate(){
			return this.date;
		}
		
		public String getSkyCon(){
			return this.skycon;
		}
		
		public String getTemp(){
			return this.temp;
		}
			
		public String getHigh(){
			return this.high;
		}
		
		public String getLow(){
			return this.low;
		}
		
		public String getRain(){
			if (this.rain.length() > 1)
				return this.rain.substring(0, 4);
			else
				return this.rain;
		}
			
		public String getSnow(){
			return this.snow;
		}
	}
}	
