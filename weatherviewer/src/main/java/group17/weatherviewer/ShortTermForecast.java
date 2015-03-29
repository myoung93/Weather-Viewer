package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShortTermForecast {
	
	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	String City, Sky, Country, Time;
	String Temp, Pressure, Humidity, TempMax, TempMin, WindSpeed;
	double Rain, Snow, totalRain = 0, TotalSnow = 0;
	String WindDir, Date, snow;

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
	
	private ArrayList<ShortTermWeather> shortTermForecast = new ArrayList<ShortTermWeather>();

	public ShortTermForecast(String cityName)
			throws UnsupportedEncodingException {

		this.Url = "http://api.openweathermap.org/data/2.5/forecast?q="
				+ cityName + "&type=accurate&mode=json" + Key;

		// send link to json parser and get weather data back as string
		JsonParser Data = new JsonParser(Url);
		String DataStr = Data.getData();

		// Save all weather information
		JSONObject JsonData = JSONObject.fromObject(DataStr);
		JSONArray Arraylist = JsonData.getJSONArray("list");

		JSONObject ObjSys = JsonData.getJSONObject("city");
		Country = ObjSys.getString("country");// country
		City = ObjSys.getString("name");// city


		// Short term shows every 3 hours forecast, 3X8=24 hours forecast
		for (int i = 0; i < 8; i++) {

			// every 3 hours forecast
			JSONObject ObjList = Arraylist.getJSONObject(i);

			JSONObject ObjMain = ObjList.getJSONObject("main");
			Temp = ObjMain.getString("temp");// temperature
			TempMin = ObjMain.getString("temp_min");// min temperature
			TempMax = ObjMain.getString("temp_max");// max temperature
			Pressure = ObjMain.getString("pressure"); // pressure
			Humidity = ObjMain.getString("humidity");// humidity

			JSONObject ObjWind = ObjList.getJSONObject("wind");
			WindSpeed = ObjWind.getString("speed");// wind speed
			WindDir = ObjWind.getString("deg");// wind degree

			// rain
			if (ObjList.containsKey("rain")) {

				JSONObject ObjRain = ObjList.getJSONObject("rain");
				Rain = ObjRain.getDouble("3h");

			} else {
				Rain = 0;
			}

			String rain = Double.toString(Rain);
			totalRain = +Rain;// total rain

			// snow
			if (ObjList.containsKey("snow")) {
				JSONObject ObjSnow = ObjList.getJSONObject("snow");
				snow = ObjSnow.getString("3h");

			} else {
				snow = "0";
				
			}

			Snow = Double.valueOf(snow);
			TotalSnow = +Snow;// total snow

			JSONArray ArraySky = ObjList.getJSONArray("weather");
			JSONObject ObjSky = ArraySky.getJSONObject(0);
			Sky = ObjSky.getString("description");// sky description

			Date = ObjList.getString("dt_txt");// date
			
			shortTermForecast.add(new ShortTermWeather(Date, Sky, Temp, Snow, Rain));
			
			DateList.add(Date);
			TempList.add(Temp);
			PressureList.add(Pressure);
			HumidityList.add(Humidity);
			TempMaxList.add(TempMax);
			TempMinList.add(TempMin);
			WindSpeedList.add(WindSpeed);
			WindDirList.add(WindDir);
			RainList.add(rain);
			SnowList.add(snow);
		}
		
	}
	
	//getter methods
	
	//returns the total precipitation
	public String getTotalRain(){
		return String.valueOf(totalRain); 
	}
	
	//returns weather information
	public ArrayList<ShortTermWeather> getShortTermForecast(){
		return this.shortTermForecast;
	}
	
	//helper class
	public class ShortTermWeather{
		private String time, skycon, temp;
		private double rain, snow;
		
		public ShortTermWeather(String time, String skycon, String temp, double rain, double snow){
			this.time = time;
			this.skycon = skycon;
			this.temp = temp;
			this.rain = rain;
			this.snow = snow;
		}
		
		//getter methods
		
		public String getTime(){
			return this.time;
		}
		
		public String getSkyCon(){
			return this.skycon;
		}
		
		public String getTemp(){
			return this.temp;
		}
		
		public String getRain(){
			return String.valueOf(this.rain);
		}
		
		public String getSnow(){
			return String.valueOf(this.snow);
		}
	}
}
	


