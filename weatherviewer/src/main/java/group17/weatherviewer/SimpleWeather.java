package group17.weatherviewer;

public class SimpleWeather {

    protected int temp, minTemp, maxTemp;
    //private ImageIcon skyIcon;
	//are we going to store this here or elsewhere? 

	//commenting out the method below beacause it's incomplete
	/*public SimpleWeather(JSONObject js) {
		//parse JSON and assign fields
	}*/

    public int getTemp() {
        return temp;
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
