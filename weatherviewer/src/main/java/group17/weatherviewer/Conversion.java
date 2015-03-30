package group17.weatherviewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {
	/**
	 * Accepts a unix time string and converts it to a time of day on a 24-hour clock
     * @param unixTime string representation of unix time
     * @return the time of day corresponding to the unix time
     */
	public static String unixToTime(String unixTime) {
        //we only care about hours and minutes
        DateFormat df = new SimpleDateFormat("HH:mm");
        //multiply by 1000 for milliseconds
        return df.format(new Date(1000*Long.parseLong(unixTime)));
    }

	public static String unixToDate(String date) {
		Long time = Long.parseLong(date) * 1000;
		String Date = new java.text.SimpleDateFormat("yyyy/MM/dd")
				.format(new java.util.Date(time));
		return Date;
	}
}
