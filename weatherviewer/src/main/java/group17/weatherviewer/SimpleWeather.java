package group17.weatherviewer;

public class SimpleWeather {

    protected int temp, minTemp, maxTemp;
    //private ImageIcon skyIcon;
	//are we going to store this here or elsewhere? 

	//commenting out the method below beacause it's incomplete
	/*public SimpleWeather(JSONObject js) {
		//parse JSON and assign fields
	}*/

    //assuming that JSON gives us the temperature in Fahrenheit
    public int getTempF() {
        return temp;
    }
    
    public int getTempC() {
        return (temp-32) * 5 / 9.0; //9.0 is just to make sure the division is not between two integers
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    /*public ImageIcon getSkyIcon() {
        return skyIcon;
    }*/
}
