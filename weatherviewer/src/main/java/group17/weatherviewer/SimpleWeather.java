package group17.weatherviewer;

public class SimpleWeather {

    protected double temp, minTemp, maxTemp;
    //private ImageIcon skyIcon;
	//are we going to store this here or elsewhere? 

	//commenting out the method below because it's incomplete
	/*public SimpleWeather(JSONObject jso) {
		//parse JSON and assign fields
	}*/

    //assuming that JSON gives us the temperature in Kelvin
    /**
     * Converts temperature from Kelvin to Fahrenheit
     * @return Fahrenheit temperature as a String
     */
    public String getTempF() {
        return String.valueOf(((temp - 273) * 9 / 5.0) + 32);
    }
 
    /**
     * Converts temperature from Kelvin to Celsius
     * @return Celsius temperature as a String
     */    
    public String getTempC() {
        return String.valueOf(temp - 273); 
    }

    /**
     * Converts minimum temperature from Kelvin to Fahrenheit
     * @return Fahrenheit minimum temperature as a String
     */
    public String getMinTempF() {
        return String.valueOf(((minTemp - 273) * 9 / 5.0) + 32);
    }

    /**
     * Converts minimum temperature from Kelvin to Celsius
     * @return Celsius minimum temperature as a String
     */    
    public String getMinTempC() {
        return String.valueOf(minTemp - 273);
    }

    /**
     * Converts maximum temperature from Kelvin to Fahrenheit
     * @return Fahrenheit maximum temperature as a String
     */
    public String getMaxTempF() {
        return String.valueOf(((maxTemp - 273) * 9 / 5.0) + 32);
    }

    /**
     * Converts maximum temperature from Kelvin to Celsius
     * @return Celsius maximum temperature as a String
     */    
    public String getMaxTempC() {
        return String.valueOf(maxTemp - 273);
    }

    /*public ImageIcon getSkyIcon() {
        return skyIcon;
    }*/
}
