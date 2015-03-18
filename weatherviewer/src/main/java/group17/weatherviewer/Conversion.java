package group17.weatherviewer;

import java.lang.*;

public class Conversion {

	public static String convert(String value, char unitFrom, char unitTo){
		double number = Double.parseDouble(value);
		double result = 0;
		
		//convert farenheit to celsius
		if (unitFrom == 'F' && unitTo == 'C')
			result = (number - 32) * 5/9;
		
		//convert celsius to farenheit
		if (unitFrom == 'C' && unitTo == 'F')
			result = number *9/5 + 32;
		
		//do the same for other conversions
		
		return String.valueOf(result);
	}
}
