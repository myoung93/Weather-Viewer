package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gathers short term forecast data and stores it in an object
 * An arraylist of size 8 of these shortTermWeather objects is sent to MainFrame for short term weather display
 */
public class ShortTermForecast {
	
	private String time, skyCon;
	private double temp, rain, snow;
	private double totalRain = 0, totalSnow = 0;
	private int weatherID;

	// arraylist of weather information for the day in question
	private ArrayList<ShortTermWeather> shortTermForecast = new ArrayList<>();

	/**
	 * Retrieves short term weather data and constructs an array of ShortTermForecast objects from the JSON returned
	 * @param cityName the city to retrieve weather data for
	 * @throws UnsupportedEncodingException if the api call is not valid
	 */
	public ShortTermForecast(String cityName)
			throws UnsupportedEncodingException {

		final String key = "&APPID=65da394090951035f3a346d9a356ddd9";// api key

		String Url = "http://api.openweathermap.org/data/2.5/forecast?q="
				+ cityName + "&type=accurate&mode=json" + key;

		// send link to json parser and get weather data back as string
		JsonParser Data = new JsonParser(Url);
		String DataStr = Data.getData();

		// Save all weather information
		JSONObject JsonData = JSONObject.fromObject(DataStr);
		JSONArray Arraylist = JsonData.getJSONArray("list");

		// Short term shows every 3 hours forecast, 3X8=24 hours forecast
		for (int i = 0; i < 8; i++) {
			// every 3 hours forecast
			JSONObject ObjList = Arraylist.getJSONObject(i);

			JSONObject ObjMain = ObjList.getJSONObject("main");
			temp = ObjMain.getDouble("temp")-273;// temperature

			// rain
			if (ObjList.containsKey("rain")) {
				JSONObject ObjRain = ObjList.getJSONObject("rain");
				rain = ObjRain.getDouble("3h");
			}
			else
				rain = 0;

			totalRain += rain;// total rain

			// snow
			if (ObjList.containsKey("snow")) {
				JSONObject ObjSnow = ObjList.getJSONObject("snow");
				snow = ObjSnow.getDouble("3h");
			}
			else
				snow = 0;

			totalSnow += snow;// total snow

			JSONArray ArraySky = ObjList.getJSONArray("weather");
			JSONObject ObjSky = ArraySky.getJSONObject(0);
			skyCon = ObjSky.getString("description");// sky description
			weatherID = ObjSky.getInt("id"); //weatherID
			time = ObjList.getString("dt_txt");// date
			
			shortTermForecast.add(new ShortTermWeather(time, skyCon, temp, rain, snow, weatherID));
		}
	}
	
	//getter methods

	/**
	 * Calculates and returns the total precipitation for this time period
	 * @return the sum of precipitation over this time period
	 */
	public String getTotalPrecipitation(){
		double totalPrecip = totalRain + totalSnow;
		if (0 < totalPrecip && totalPrecip < 1)
			return "< 1";
		else
			return String.valueOf(Math.round(totalPrecip));
	}

	/**
	 * getter for the list of short term forecasts representing a day's worth of weather
	 * @return the list of short term forecasts
	 */
	public ArrayList<ShortTermWeather> getShortTermForecast(){
		return this.shortTermForecast;
	}

	/**
	 * Represents a 3-hour short term forecast period
	 */
	public class ShortTermWeather{
		private String time, skycon; 
		private double temp, rain, snow;
		private int weatherID;

		//constructors ShortTermWeather from the given parameters
		public ShortTermWeather(String time, String skycon, double temp, double rain, double snow, int weatherID){
			this.time = time;
			this.skycon = skycon;
			this.temp = temp;
			this.rain = rain;
			this.snow = snow;
			this.weatherID = weatherID;
		}

		/**
		 * Getter for time
		 * @return the time for this forecast object
		 */
		public String getTime(){
			return this.time;
		}

		/**
	     * Getter method for Celsius temperature
	     * @return the current temp in degrees C
	     */
		public String getTemp(char tempUnit) {
			if(tempUnit == 'f')
	            return cleanTemp(temp * 9 / 5 + 32, tempUnit);
	        else
	            return cleanTemp(temp, tempUnit);
		}

	    /**
		 * Helper method for getTemp, getMaxTemp, getMinTemp since they all return the temperature in the same form
		 * puts it into a nice form with no decimal places
		 * @param t the temperature to convert to a nice string in double form
		 * @param unit c or f, the unit the user requests
		 * @return a string of the correct length representing the temperature plus the degrees symbol and the unit
		 */
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

		/**
		 * Getter for the amount of rain
		 * @return the amount of rain for this ShortTermForecast period
		 */
	    public String getRain(){
			if (0 < this.rain && this.rain < 1)
				return "< 1";	
			else
				return String.valueOf(Math.round(this.rain));
		}

		/**
		 * Getter for the amount of snow
		 * @return the amount of snow for this ShortTermForecast period
		 */
		public String getSnow(){
			if (0 < this.snow && this.snow < 1)
				return "< 1";	
			else
				return String.valueOf(Math.round(this.snow));
		}

		/**
		 * Getter for the sky condition
		 * @return the sky condition for this ShortTermForecast period
		 */
		public String getSkyCondition() {
			return skyCon.substring(0, 1).toUpperCase() + skyCon.substring(1);
		}

		/**
		 * Getter for the weather id
		 * @return the weather ID for this ShortTermForecast period
		 */
		public int getWeatherID(){
			return this.weatherID;	
		}
	}
}
	


