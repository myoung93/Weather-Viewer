package group17.weatherviewer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Gathers long term forecast data and stores it in an object
 * An arraylist of size 5 of these longTermWeather objects is sent to MainFrame for long term weather display
 */
public class LongTermForecast {

	private String date, skyCon;
	private double temp, high, low, rain, snow;
	private int weatherID;

	// arraylist of weather information for the 5 day period requested
	private ArrayList<LongTermWeather> longTermForecast = new ArrayList<>();

	/**
	 * Retrieves long term weather data and constructs an array of LongTermForecast objects from the JSON returned
	 * @param cityName the city to retrieve weather data for
	 * @param Term number of days to retrieve data for
	 * @throws UnsupportedEncodingException if the api call is not valid
	 */
	public LongTermForecast(String cityName, int Term)
			throws UnsupportedEncodingException {
		final String Key = "&APPID=65da394090951035f3a346d9a356ddd9"; // api key

		String Url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
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

		//Short term shows daily forecast, for at least 5 days forecast
		for (int i = 0; i < Term; i++) {

			JSONObject ObjDay = Arraylist.getJSONObject(i);

			// Rain
			if (ObjDay.containsKey("rain"))
				rain = ObjDay.getDouble("rain");

			else
				rain = 0;
			// snow
			if (ObjDay.containsKey("snow"))
				snow = ObjDay.getDouble("snow");

			else
				snow = 0;

			date = ObjDay.getString("dt");
			JSONArray ArrayWeather = ObjDay.getJSONArray("weather");
			JSONObject ObjSky = ArrayWeather.getJSONObject(0);

			skyCon = ObjSky.getString("description");// sky description
			weatherID = ObjSky.getInt("id"); //weatherID
			
			JSONObject Objtemp = ObjDay.getJSONObject("temp");
			temp = Objtemp.getDouble("day");// temperature
			low = Objtemp.getDouble("min");// min temperature
			high = Objtemp.getDouble("max");// max temperature
			
			longTermForecast.add(new LongTermWeather(date, skyCon, temp, high, low, rain, snow, weatherID));
		}
	}

	/**
	 * @return a reference to the arraylist of long term weathers, ie returns all the retrieved data in java object form
	 */
	public ArrayList<LongTermWeather> getLongTermForecast(){
		return this.longTermForecast;
	}

	/**
	 * Data type that contains longterm weather data
	 */
	public class LongTermWeather{
		private String date, skycon; 
		private double temp, high, low, rain, snow;
		private int weatherID;

		//constructors LongTermWeather from the given parameters
		public LongTermWeather(String date, String skycon, double temp, double high, double low, double rain, double snow, int weatherID){
			this.date = Conversion.unixToDate((date));
			this.skycon = skycon;
			this.temp = temp;
			this.high = high;
			this.low= low;
			this.rain = rain;
			this.snow = snow;
			this.weatherID = weatherID;
		}

		/**
		 * Getter for date
		 * @return the time for this forecast object
		 */
		public String getDate(){
			return this.date;
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
		 * Getter method for max Celsius temperature
		 * @return the max temp in degrees C
		 */
	    public String getHigh(char tempUnit) {
	        if(tempUnit == 'f')
	            return cleanTemp(this.high * 9 / 5 + 32, tempUnit);
	        else
	            return cleanTemp(this.high, tempUnit);
	    }

		/**
		 * Getter method for min Celsius temperature
		 * @return the min temp in degrees C
		 */
	    public String getLow(char tempUnit) {
	        if(tempUnit == 'f')
	            return cleanTemp(this.low * 9 / 5 + 32, tempUnit);
	        else
	            return cleanTemp(this.low, tempUnit);
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
