package group17.weatherviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class JsonParser {

	String OwmUrl;

	// get link from other weather classes and return data as string
	public JsonParser(String url) throws UnsupportedEncodingException {
		this.OwmUrl = url;
	}

	public String getData() {

		StringBuffer SBR;

		SBR = new StringBuffer();

		try {
			URL url = new URL(OwmUrl);
			URLConnection connection = url.openConnection();
			BufferedReader BReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = null;
			while ((line = BReader.readLine()) != null)
				SBR.append(line + " ");
			BReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String WeatherData = SBR.toString();
		// check json data is null or not
		JSONObject JsonData = JSONObject.fromObject(WeatherData);
		if (JsonData.getInt("cod") == 404) {
			throw new RuntimeException(
					"City can not found. Please input your city properly");
		} else {
			return WeatherData;
		}
	}
}
