package group17.weatherviewer;

public class SimpleWeather {

    protected double temp, minTemp, maxTemp;
    //private ImageIcon skyIcon;
	//are we going to store this here or elsewhere? 

	//commenting out the method below because it's incomplete
	/*public SimpleWeather(JSONObject jso) {
		//parse JSON and assign fields
	}*/

    //assuming that JSON gives us the temperature in Fahrenheit
    public String getTempF() {
        return String.valueOf(temp);
    }
    
    public String getTempC() {
        return String.valueOf((temp-32) * 5 / 9.0); //9.0 is just to make sure the division is not between two integers
    }

    public String getMinTemp() {
        return String.valueOf(minTemp);
    }

    public String getMaxTemp() {
        return String.valueOf(maxTemp);
    }

    /*public ImageIcon getSkyIcon() {
        return skyIcon;
    }*/
}
