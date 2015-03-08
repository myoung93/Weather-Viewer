package group17.weatherviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

public class MainFrame {

	private JFrame frame;
	private ImageIcon backgroundImage;
	private ImageIcon weatherIcon;
	private JLabel backgroundLabel;
	private JTextField textField;
	private JTextField barSearch;
	private JScrollPane scrollPane;
	private JButton buttonFavourite;
	private JButton buttonLongTerm;
	private JButton buttonShortTerm;
	private JButton buttonRefresh;
	private JButton buttonToCelsius;
	private JButton buttonToFahrenheit;
	private JLabel labelSkyConditionIcon;
	private JLabel labelAirPressure;
	private JLabel labelMaxTemp;
	private JLabel labelMinTemp;
	private JLabel labelSunrise;
	private JLabel labelSunset;
	private JLabel labelWind;
	private JLabel labelHumidity;
	private JLabel labelLocation;
	private JLabel labelTempInfo;
	private JLabel labelWindInfo;
	private JLabel labelHumidityInfo;
	private JLabel labelAirPressureInfo;
	private JLabel labelMaxTempInfo;
	private JLabel labelMinTempInfo;
	private JLabel labelSunriseInfo;
	private JLabel labelSunsetInfo;
	private JLabel labelSkyConditionInfo;
	private JLabel label12AM;
	private JLabel label3AM;
	private JLabel label6AM;
	private JLabel label9AM;
	private JLabel label12PM;
	private JLabel label3PM;
	private JLabel label6PM;
	private JLabel label9PM;
	private JLabel labelMon;
	private JLabel labelTues;
	private JLabel labelWed;
	private JLabel labelThurs;
	private JLabel labelFri;
	private JLabel labelSat;
	private JLabel labelSun;
	private JList listLocations;
	private JScrollBar scrollBarLocations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

        CurrentWeather currentWeather = null;
        try {
            currentWeather = new CurrentWeather("Toronto,CA");
        }
        catch(UnsupportedEncodingException e) {
            System.out.println("Something went wrong retrieving current weather");
            e.printStackTrace();
        }

		// attributes
		String temp, windSpeed, airPressure, humidity, minTemp, maxTemp, sunRise, sunSet, windDirection, skyCondition, location;
		// hard coded initializations
		// in the future none of these actually have to be stored, we can just
		// do weatherObject.getTemp() etc. -TE
		location = currentWeather.getCity();
        //still need to implement converter methods/class so that we don't have temperatures in kelvin.
        temp = currentWeather.getTemp();
		windSpeed = currentWeather.getWindSpeed();
		windDirection = currentWeather.getWindDirection();
		humidity = currentWeather.getHumidity();
		airPressure = currentWeather.getPressure();
		maxTemp =  currentWeather.getTempMax();
		minTemp = currentWeather.getTempMin();
        //Sunrise and sunset need to be converted from Unix time, preferably in the CurrentWeather class itself.
		sunRise =  currentWeather.getSunriseTime();
		sunSet =  currentWeather.getSunsetTime();
		skyCondition =  currentWeather.getSkyCondition();

		// set up background -> this is returning the ugly grey background only it seems
		backgroundImage = new ImageIcon(
				"src/main/resources/backgrounds/default_background.jpg");
		if (skyCondition.equalsIgnoreCase("sky is clear")) {
			backgroundImage = new ImageIcon(
					"src/main/resources/backgrounds/sunny_background.jpg");
			weatherIcon = new ImageIcon("src/main/resources/icons/sun_icon.png");
		} else if (skyCondition.equalsIgnoreCase("Cloudy")) {
			backgroundImage = new ImageIcon(
					"src/main/resources/backgrounds/cloudy_background.jpg");
			weatherIcon = new ImageIcon("src/main/resources/icons/cloud_heavy_icon.png");
		} else if (skyCondition.equalsIgnoreCase("Rainy")) {
			backgroundImage = new ImageIcon(
					"src/main/resources/backgrounds/rainy_background.jpg");
			weatherIcon = new ImageIcon("src/main/resources/icons/rain_heavy_icon.png");
		}

		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setSize(800, 520);

		// Initialize Frame
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 520);
		frame.setContentPane(backgroundLabel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set background to sky condition

		// begin initialize buttons

		// refresh button
		buttonRefresh = new JButton("");
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		buttonRefresh.setIcon(new ImageIcon(
				"src/main/resources/icons/refresh_icon.png"));
		buttonRefresh.setBounds(540, 16, 41, 37);
		buttonRefresh.setOpaque(false);
		buttonRefresh.setContentAreaFilled(false);
		buttonRefresh.setBorderPainted(false);
		frame.getContentPane().add(buttonRefresh);

		// favorite Button
		buttonFavourite = new JButton("");
		buttonFavourite.setIcon(new ImageIcon(
				"src/main/resources/icons/star_icon.png"));
		buttonFavourite.setOpaque(false);
		buttonFavourite.setContentAreaFilled(false);
		buttonFavourite.setBorderPainted(false);
		buttonFavourite.setBounds(759, 16, 41, 37);
		frame.getContentPane().add(buttonFavourite);

		// Short Term Button
		buttonShortTerm = new JButton("Short Term");
		buttonShortTerm.setForeground(Color.WHITE);
		buttonShortTerm.setFont(new Font("Helvetica", Font.PLAIN, 15));
		buttonShortTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shortTermView();
			}
		});
		buttonShortTerm.setOpaque(false);
		buttonShortTerm.setContentAreaFilled(false);
		buttonShortTerm.setBorderPainted(false);
		buttonShortTerm.setBounds(19, 328, 120, 29);
		frame.getContentPane().add(buttonShortTerm);

		// Long Term Button
		buttonLongTerm = new JButton("Long Term");
		buttonLongTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				longTermView();
			}
		});

		buttonLongTerm.setOpaque(false);
		buttonLongTerm.setForeground(Color.DARK_GRAY);
		buttonLongTerm.setFont(new Font("Helvetica", Font.PLAIN, 15));
		buttonLongTerm.setContentAreaFilled(false);
		buttonLongTerm.setBorderPainted(false);
		buttonLongTerm.setBounds(95, 328, 127, 29);
		frame.getContentPane().add(buttonLongTerm);

		// toCelsius button
		buttonToCelsius = new JButton("°C");
		buttonToCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonToCelsius.setForeground(Color.WHITE);
				buttonToFahrenheit.setForeground(Color.DARK_GRAY);
			}
		});
		buttonToCelsius.setOpaque(false);
		buttonToCelsius.setForeground(Color.WHITE);
		buttonToCelsius.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToCelsius.setContentAreaFilled(false);
		buttonToCelsius.setBorderPainted(false);
		buttonToCelsius.setBounds(475, 283, 75, 29);
		frame.getContentPane().add(buttonToCelsius);

		// toFarenheit button
		buttonToFahrenheit = new JButton("°F");
		buttonToFahrenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonToFahrenheit.setForeground(Color.WHITE);
				buttonToCelsius.setForeground(Color.DARK_GRAY);
			}
		});
		buttonToFahrenheit.setOpaque(false);
		buttonToFahrenheit.setForeground(Color.DARK_GRAY);
		buttonToFahrenheit.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToFahrenheit.setContentAreaFilled(false);
		buttonToFahrenheit.setBorderPainted(false);
		buttonToFahrenheit.setBounds(520, 283, 70, 29);
		frame.getContentPane().add(buttonToFahrenheit);

		// end initialize buttons

		// begin initialize MyLocations panel

		// Scroll Pane
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(595, 56, 195, 422);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		frame.getContentPane().add(scrollPane);
		
		// Locations list
		listLocations = new JList();
		scrollPane.setViewportView(listLocations);
		
		//scroll bar for Locations list
		scrollBarLocations = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBarLocations);

		// search bar
		barSearch = new JTextField();
		barSearch.setText("Search (City, Country)");
		barSearch.setBounds(590, 16, 171, 37);
		barSearch.setOpaque(false);
		barSearch.setColumns(10);
		frame.getContentPane().add(barSearch);
		// end initialize MyLocations panel

		// begin initialize LocalWeather panel

		// city label
		labelLocation = new JLabel(location);
		labelLocation.setForeground(Color.WHITE);
		labelLocation.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelLocation.setBounds(50, 25, 200, 37);
		frame.getContentPane().add(labelLocation);

		// temperature label
		labelTempInfo = new JLabel(temp + "°C");
		labelTempInfo.setFont(new Font("Helvetica", Font.PLAIN, 93));
		labelTempInfo.setForeground(Color.WHITE);
		labelTempInfo.setBounds(50, 64, 318, 94);
		frame.getContentPane().add(labelTempInfo);

		// wind label
		labelWind = new JLabel("Wind:");
		labelWind.setForeground(Color.LIGHT_GRAY);
		labelWind.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelWind.setBounds(50, 170, 61, 15);
		frame.getContentPane().add(labelWind);

		// humidity label
		labelHumidity = new JLabel("Humidity:");
		labelHumidity.setForeground(Color.LIGHT_GRAY);
		labelHumidity.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelHumidity.setBounds(50, 190, 70, 15);
		frame.getContentPane().add(labelHumidity);

		// air pressure label
		labelAirPressure = new JLabel("Air Pressure:");
		labelAirPressure.setForeground(Color.LIGHT_GRAY);
		labelAirPressure.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelAirPressure.setBounds(50, 210, 96, 15);
		frame.getContentPane().add(labelAirPressure);

		// max temp label
		labelMaxTemp = new JLabel("Max Temp:");
		labelMaxTemp.setForeground(Color.LIGHT_GRAY);
		labelMaxTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMaxTemp.setBounds(50, 230, 96, 15);
		frame.getContentPane().add(labelMaxTemp);

		// min temp label
		labelMinTemp = new JLabel("Min. Temp:");
		labelMinTemp.setForeground(Color.LIGHT_GRAY);
		labelMinTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMinTemp.setBounds(50, 250, 96, 15);
		frame.getContentPane().add(labelMinTemp);

		// sunrise label
		labelSunrise = new JLabel("Sunrise:");
		labelSunrise.setForeground(Color.LIGHT_GRAY);
		labelSunrise.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunrise.setBounds(50, 270, 61, 15);
		frame.getContentPane().add(labelSunrise);

		// sunset label
		labelSunset = new JLabel("Sunset:");
		labelSunset.setForeground(Color.LIGHT_GRAY);
		labelSunset.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunset.setBounds(50, 290, 58, 15);
		frame.getContentPane().add(labelSunset);

		// end initialize LocalWeather panel

		// begin initialize LocalWeather conditions

		// wind info

		labelWindInfo = new JLabel(windSpeed + " km/h " + windDirection);
		labelWindInfo.setForeground(Color.WHITE);
		labelWindInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		labelWindInfo.setBounds(103, 170, 200, 15);
		frame.getContentPane().add(labelWindInfo);

		// humidity info
		labelHumidityInfo = new JLabel(humidity + "%");
		labelHumidityInfo.setForeground(Color.WHITE);
		labelHumidityInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelHumidityInfo.setBounds(130, 190, 200, 15);
		frame.getContentPane().add(labelHumidityInfo);

		// air pressure info
		labelAirPressureInfo = new JLabel(airPressure + "kPa");
		labelAirPressureInfo.setForeground(Color.WHITE);
		labelAirPressureInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelAirPressureInfo.setBounds(158, 210, 200, 15);
		frame.getContentPane().add(labelAirPressureInfo);

		// max temp info
		labelMaxTempInfo = new JLabel(maxTemp + "°C");
		labelMaxTempInfo.setForeground(Color.WHITE);
		labelMaxTempInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMaxTempInfo.setBounds(145, 230, 200, 15);
		frame.getContentPane().add(labelMaxTempInfo);

		// min temp info
		labelMinTempInfo = new JLabel(minTemp + "°C");
		labelMinTempInfo.setForeground(Color.WHITE);
		labelMinTempInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMinTempInfo.setBounds(145, 250, 200, 15);
		frame.getContentPane().add(labelMinTempInfo);

		// sunrise info
		labelSunriseInfo = new JLabel(sunRise + "");
		labelSunriseInfo.setForeground(Color.WHITE);
		labelSunriseInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunriseInfo.setBounds(130, 270, 200, 15);
		frame.getContentPane().add(labelSunriseInfo);

		// sunset info
		labelSunsetInfo = new JLabel(sunSet + "");
		labelSunsetInfo.setForeground(Color.WHITE);
		labelSunsetInfo.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunsetInfo.setBounds(130, 291, 200, 15);
		frame.getContentPane().add(labelSunsetInfo);

		// sky condition info
		labelSkyConditionInfo = new JLabel(skyCondition);
		labelSkyConditionInfo.setForeground(Color.WHITE);
		labelSkyConditionInfo.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelSkyConditionInfo.setBounds(381, 25, 200, 37);
		frame.getContentPane().add(labelSkyConditionInfo);

		// sky condition info
		labelSkyConditionIcon = new JLabel(weatherIcon);
		labelSkyConditionIcon.setBounds(355, 70, 204, 200);
		frame.getContentPane().add(labelSkyConditionIcon);
		
		
		// end initialize LocalWeather conditions

		// being initialize short-term forecast panel

		label12AM = new JLabel("12:00am");
		label12AM.setForeground(Color.LIGHT_GRAY);
		label12AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label12AM.setBounds(50, 360, 58, 15);
		frame.getContentPane().add(label12AM);

		label3AM = new JLabel("  3:00am");
		label3AM.setForeground(Color.LIGHT_GRAY);
		label3AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label3AM.setBounds(115, 360, 58, 15);
		frame.getContentPane().add(label3AM);

		label6AM = new JLabel("  6:00am");
		label6AM.setForeground(Color.LIGHT_GRAY);
		label6AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label6AM.setBounds(180, 360, 58, 15);
		frame.getContentPane().add(label6AM);

		label9AM = new JLabel("  9:00am");
		label9AM.setForeground(Color.LIGHT_GRAY);
		label9AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label9AM.setBounds(245, 360, 58, 15);
		frame.getContentPane().add(label9AM);

		label12PM = new JLabel("12:00pm");
		label12PM.setForeground(Color.LIGHT_GRAY);
		label12PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label12PM.setBounds(310, 360, 58, 15);
		frame.getContentPane().add(label12PM);

		label3PM = new JLabel("  3:00pm");
		label3PM.setForeground(Color.LIGHT_GRAY);
		label3PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label3PM.setBounds(375, 360, 58, 15);
		frame.getContentPane().add(label3PM);

		label6PM = new JLabel("  6:00pm");
		label6PM.setForeground(Color.LIGHT_GRAY);
		label6PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label6PM.setBounds(440, 360, 58, 15);
		frame.getContentPane().add(label6PM);

		label9PM = new JLabel("  9:00pm");
		label9PM.setForeground(Color.LIGHT_GRAY);
		label9PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label9PM.setBounds(505, 360, 58, 15);
		frame.getContentPane().add(label9PM);

		// end initialize Short-Term Forecast panel

		// begin initialize Long-Term Forecast panel

		labelMon = new JLabel("Mon.");
		labelMon.setForeground(Color.LIGHT_GRAY);
		labelMon.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMon.setBounds(100, 360, 58, 15);
		frame.getContentPane().add(labelMon);

		labelTues = new JLabel("Tues.");
		labelTues.setForeground(Color.LIGHT_GRAY);
		labelTues.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelTues.setBounds(165, 360, 58, 15);
		frame.getContentPane().add(labelTues);

		labelWed = new JLabel("Wed.");
		labelWed.setForeground(Color.LIGHT_GRAY);
		labelWed.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelWed.setBounds(230, 360, 58, 15);
		frame.getContentPane().add(labelWed);

		labelThurs = new JLabel("Thurs.");
		labelThurs.setForeground(Color.LIGHT_GRAY);
		labelThurs.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelThurs.setBounds(290, 360, 58, 15);
		frame.getContentPane().add(labelThurs);

		labelFri = new JLabel("Fri.");
		labelFri.setForeground(Color.LIGHT_GRAY);
		labelFri.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelFri.setBounds(365, 360, 58, 15);
		frame.getContentPane().add(labelFri);

		labelSat = new JLabel("Sat.");
		labelSat.setForeground(Color.LIGHT_GRAY);
		labelSat.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSat.setBounds(425, 360, 58, 15);
		frame.getContentPane().add(labelSat);

		labelSun = new JLabel("Sun.");
		labelSun.setForeground(Color.LIGHT_GRAY);
		labelSun.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSun.setBounds(490, 360, 58, 15);
		frame.getContentPane().add(labelSun);

		shortTermView();
		// end initialize Long-Term Conditions

		// testing code to prove that UserPreferences functions at least at a
		// basic level - TE
		/*
		 * try { UserPreferences prefs = new UserPreferences(); //prints c
		 * (default)
		 * System.out.println(prefs.isCelsius()?"Celsius":"Fahrenheit");
		 * prefs.setCelsius(false); //prints f
		 * System.out.println(prefs.isCelsius()?"Celsius":"Fahrenheit");
		 * UserPreferences.savePrefs(prefs);
		 * 
		 * //create a new preferences and load the old one UserPreferences
		 * new_prefs = UserPreferences.getPrefs(); //still f instead of default
		 * c because we saved & loaded it
		 * System.out.println(prefs.isCelsius()?"Celsius":"Fahrenheit"); }
		 * catch(Exception e) { e.printStackTrace(); }
		 */

	}
	//updates the labels current weather view to the new weather conditions
	public void refresh() {
		
		CurrentWeather new_weather = null;
        try {
            new_weather = new CurrentWeather("London,CA");
        }
        catch(UnsupportedEncodingException e) {
            System.out.println("Something went wrong retrieving current weather");
            e.printStackTrace();
        }

		// current location panel
		labelLocation.setText(new_weather.getCity() + ", " + new_weather.getCountry());
		labelSkyConditionInfo.setText(new_weather.getSkyCondition());
		labelTempInfo.setText(new_weather.getTemp() + "°C");
		labelWindInfo.setText(new_weather.getWindSpeed() + " km/h " + new_weather.getWindDirection());
		labelHumidityInfo.setText(new_weather.getHumidity() + "%");
		labelAirPressureInfo.setText(new_weather.getPressure() + "kPa");
		labelMaxTempInfo.setText(new_weather.getTempMax() + "°C");
		labelMinTempInfo.setText(new_weather.getTempMin() + "°C");
		labelSunriseInfo.setText(new_weather.getSunriseTime());
		labelSunsetInfo.setText(new_weather.getSunsetTime());

		// set background and weather icon
		if (new_weather.getSkyCondition().equalsIgnoreCase("sky is clear")) {
			backgroundLabel.setIcon(new ImageIcon(
					"src/main/resources/backgrounds/sunny_background.jpg"));
			labelSkyConditionIcon.setIcon(new ImageIcon("src/main/resources/icons/sun_icon.png"));
		} else if (new_weather.getSkyCondition().equalsIgnoreCase("few clouds")) {
			backgroundLabel.setIcon(new ImageIcon(
					"src/main/resources/backgrounds/cloudy_background.jpg"));
			labelSkyConditionIcon.setIcon(new ImageIcon("src/main/resources/icons/cloud_heavy_icon.png"));
		} else if (new_weather.getSkyCondition().equalsIgnoreCase("Rainy")) {
			backgroundLabel.setIcon(new ImageIcon(
					"src/main/resources/backgournds/rainy_background.jpg"));
			labelSkyConditionIcon.setIcon(new ImageIcon("src/main/resources/icons/rain_heavy_icon.png"));
		}
	}

	// enable short term view
	public void shortTermView() {
		buttonLongTerm.setForeground(Color.DARK_GRAY);
		buttonShortTerm.setForeground(Color.WHITE);
		labelMon.setVisible(false);
		labelTues.setVisible(false);
		labelWed.setVisible(false);
		labelThurs.setVisible(false);
		labelFri.setVisible(false);
		labelSat.setVisible(false);
		labelSun.setVisible(false);
		label12AM.setVisible(true);
		label3AM.setVisible(true);
		label6AM.setVisible(true);
		label9AM.setVisible(true);
		label12PM.setVisible(true);
		label3PM.setVisible(true);
		label6PM.setVisible(true);
		label9PM.setVisible(true);
	}

	// enable long term view
	public void longTermView() {
		buttonLongTerm.setForeground(Color.WHITE);
		buttonShortTerm.setForeground(Color.DARK_GRAY);
		labelMon.setVisible(true);
		labelTues.setVisible(true);
		labelWed.setVisible(true);
		labelThurs.setVisible(true);
		labelFri.setVisible(true);
		labelSat.setVisible(true);
		labelSun.setVisible(true);
		label12AM.setVisible(false);
		label3AM.setVisible(false);
		label6AM.setVisible(false);
		label9AM.setVisible(false);
		label12PM.setVisible(false);
		label3PM.setVisible(false);
		label6PM.setVisible(false);
		label9PM.setVisible(false);
	}
}
