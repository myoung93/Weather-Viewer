package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShortTermForecast {
	
	String Url;
	String Key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

	String City, Sky, Country, Time;
	String Temp, Pressure, Humidity, TempMax, TempMin, WindSpeed, Rain, Snow, WindDir, Date;
	double temp, rain, snow;
	double totalRain = 0, totalSnow = 0;

	// arraylist of all weather information
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
			temp = ObjMain.getDouble("temp")-273;// temperature
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
				rain = ObjRain.getDouble("3h");

			} else {
				rain = 0;
			}

			totalRain = totalRain + rain;// total rain

			// snow
			if (ObjList.containsKey("snow")) {
				JSONObject ObjSnow = ObjList.getJSONObject("snow");
				snow = ObjSnow.getDouble("3h");

			} else {
				snow = 0;
				
			}

			totalSnow += snow;// total snow

			JSONArray ArraySky = ObjList.getJSONArray("weather");
			JSONObject ObjSky = ArraySky.getJSONObject(0);
			Sky = ObjSky.getString("description");// sky description

			Date = ObjList.getString("dt_txt");// date
			
			shortTermForecast.add(new ShortTermWeather(Date, Sky, temp, rain, snow));
		}
	}
	
	//getter methods
	
	//returns the total rain
	public String getTotalRain(){
		if (String.valueOf(totalRain).length() > 4)
			return String.valueOf(totalRain).substring(0, 4);
		else
			return String.valueOf(totalRain); 
	}
	
	//returns the total snow
	public String getTotalSnow(){
		return String.valueOf(totalSnow); 
	}
	
	//returns weather information
	public ArrayList<ShortTermWeather> getShortTermForecast(){
		return this.shortTermForecast;
	}
	
	//helper class
	public class ShortTermWeather{
		private String time, skycon; 
		private double temp, rain, snow;
		
		public ShortTermWeather(String time, String skycon, double temp, double rain, double snow){
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
		
		public String getRain(){
			if (String.valueOf(this.rain).length() > 4)
				return String.valueOf(this.rain).substring(0, 4);
			else
				return String.valueOf(this.rain);
		}
		
		public String getSnow(){
			if (String.valueOf(this.snow).length() > 4)
				return String.valueOf(this.snow).substring(0, 4);
			else
				return String.valueOf(this.snow);
		}
	}
}
	


