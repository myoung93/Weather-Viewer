package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
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
	private double temp, high, low, rain, snow;
	private int weatherID;

	
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
				rain = ObjDay.getDouble("rain");
			}

			else {
				rain = 0;
			}
			// snow
			if (ObjDay.containsKey("snow")) {
				snow = ObjDay.getDouble("snow");
			}

			else {
				snow = 0;
			}

			Pressure = ObjDay.getString("pressure"); // pressure
			Humidity = ObjDay.getString("humidity");// humidity
			Time = ObjDay.getString("dt");
			JSONArray ArrayWeather = ObjDay.getJSONArray("weather");
			JSONObject ObjSky = ArrayWeather.getJSONObject(0);

			Sky = ObjSky.getString("description");// sky description
			weatherID = ObjSky.getInt("id"); //weatherID
			
			JSONObject Objtemp = ObjDay.getJSONObject("temp");
			temp = Objtemp.getDouble("day");// temperature
			low = Objtemp.getDouble("min");// min temperature
			high = Objtemp.getDouble("max");// max temperature
			
			longTermForecast.add(new LongTermWeather(Time, Sky, temp, high, low, rain, snow, weatherID));
		}

	}

	// convert date from unix type to date
	public String Unix2Date(String date) {
		Long Time = Long.parseLong(date) * 1000;
		String Date = new java.text.SimpleDateFormat("EEE")
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
		private String date, skycon; 
		private double temp, high, low, rain, snow;
		private int weatherID;
		
		
		
		public LongTermWeather(String date, String skycon, double temp, double high, double low, double rain, double snow, int weatherID){
			this.date = Unix2Date(date);
			this.skycon = skycon;
			this.temp = temp;
			this.high = high;
			this.low= low;
			this.rain = rain;
			this.snow = snow;
			this.weatherID = weatherID;
		}
			
		//getter methods
		
		public String getDate(){
			return this.date;
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

	    public String getHigh(char tempUnit) {
	        if(tempUnit == 'f')
	            return cleanTemp(this.high * 9 / 5 + 32, tempUnit);
	        else
	            return cleanTemp(this.high, tempUnit);
	    }

	    public String getLow(char tempUnit) {
	        if(tempUnit == 'f')
	            return cleanTemp(this.low * 9 / 5 + 32, tempUnit);
	        else
	            return cleanTemp(this.low, tempUnit);
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
			if (0 < this.rain && this.rain < 1)
				return "< 1";	
			else
				return String.valueOf(Math.round(this.rain));
		}
		
		public String getSnow(){
			if (0 < this.snow && this.snow < 1)
				return "< 1";	
			else
				return String.valueOf(Math.round(this.snow));
		}
		
		public int getWeatherID(){
			return this.weatherID;
		}
	}
}	
