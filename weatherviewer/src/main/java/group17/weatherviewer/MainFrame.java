package group17.weatherviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;
	private JButton buttonFavourite;
	private JTextField barSearch;
	private JLabel labelCity;
	private JLabel labelTemp;
	private JLabel labelWind;
	private JLabel iconSkyCondition;
	private JLabel lblAirPressure;
	private JLabel lblMaxTemp;
	private JLabel lblMinTemp;
	private JLabel lblSunrise;
	private JLabel lblSunset;
	private JLabel labelHumidity;
	private JLabel labelAirPressure;
	private JLabel labelMaxTemp;
	private JLabel labelMinTemp;
	private JLabel labelSunrise;
	private JLabel labelSunset;
	private JLabel labelSkyCondition;
	private JButton buttonLongTerm;
	private JButton buttonShortTerm;
	private JButton buttonRefresh;
	private JButton buttonToCelsius;
	private JButton buttonToFahrenheit;
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
	private JScrollPane scrollPane;
	private JLabel lblWind;
	private JLabel lblHumidity;
	

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
		// attributes
		String temp, windSpeed, airPressure, humidity, minTemp, maxTemp, sunRise, sunSet, windDirection, skyCondition, location;
		// hard coded initializations
        //in the future none of these actually have to be stored, we can just do weatherObject.getTemp() etc. -TE
		location = "Toronto, Ont.";
		temp = "999";
		windSpeed = "999";
		windDirection = "East";
		humidity = "999";
		airPressure = "999";
		maxTemp = "999";
		minTemp = "999";
		sunRise = "9999";
		sunSet = "9999";
		skyCondition = "Sunny";

		// set background to sky condition
		Background backgroundImage = new Background(
				Toolkit.getDefaultToolkit().getImage(
		MainFrame.class.getResource("default_background.jpg")));
		if (skyCondition == "Sunny") {

            backgroundImage = new Background(Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("sunny_background.jpg")));
		} else if (skyCondition == "Cloudy") {
            backgroundImage = new Background(Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("cloudy_background.jpg")));
		} else if (skyCondition == "Rainy") {
            backgroundImage = new Background(Toolkit.getDefaultToolkit()
            .getImage(getClass().getResource("rainy_background.jpg")));
		} 
		
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 520);
		frame.getContentPane().add(backgroundImage);
		backgroundImage.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//begin initialize buttons
		
		// refresh button
		buttonRefresh = new JButton("");
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                //update weather here
            }
		});
		buttonRefresh.setIcon(new ImageIcon(getClass().getResource("/refresh_icon.png")));
		buttonRefresh.setBounds(540, 16, 41, 37);
		buttonRefresh.setOpaque(false);
		buttonRefresh.setContentAreaFilled(false);
		buttonRefresh.setBorderPainted(false);
		backgroundImage.add(buttonRefresh);

		// favorite Button
		buttonFavourite = new JButton("");
		buttonFavourite.setIcon(new ImageIcon(getClass().getResource("/star_icon.png")));
        buttonFavourite.setOpaque(false);
		buttonFavourite.setContentAreaFilled(false);
		buttonFavourite.setBorderPainted(false);
		buttonFavourite.setBounds(759, 16, 41, 37);
		backgroundImage.add(buttonFavourite);

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
		backgroundImage.add(buttonShortTerm);

		// Long Term Button
		buttonLongTerm = new JButton("Long Term");
		buttonLongTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				longTermView();
			}
		});

		buttonLongTerm.setOpaque(false);
		buttonLongTerm.setForeground(Color.GRAY);
		buttonLongTerm.setFont(new Font("Helvetica", Font.PLAIN, 15));
		buttonLongTerm.setContentAreaFilled(false);
		buttonLongTerm.setBorderPainted(false);
		buttonLongTerm.setBounds(95, 328, 127, 29);
		backgroundImage.add(buttonLongTerm);

		buttonToCelsius = new JButton("°C");
		buttonToCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonToFahrenheit.setForeground(Color.WHITE);
			}
		});
		buttonToCelsius.setOpaque(false);
		buttonToCelsius.setForeground(Color.WHITE);
		buttonToCelsius.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToCelsius.setContentAreaFilled(false);
		buttonToCelsius.setBorderPainted(false);
		buttonToCelsius.setBounds(475, 283, 96, 29);
		backgroundImage.add(buttonToCelsius);
		
		
		buttonToFahrenheit = new JButton("°F");
		buttonToFahrenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonToCelsius.setForeground(Color.GRAY);
			}
		});
		buttonToFahrenheit.setOpaque(false);
		buttonToFahrenheit.setForeground(Color.GRAY);
		buttonToFahrenheit.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToFahrenheit.setContentAreaFilled(false);
		buttonToFahrenheit.setBorderPainted(false);
		buttonToFahrenheit.setBounds(520, 283, 70, 29);
		backgroundImage.add(buttonToFahrenheit);
	
		//end initialize buttons
		
		//begin initialize MyLocations panel
		
		//Scroll Pane
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(595, 56, 195, 422);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		backgroundImage.add(scrollPane);
		
		// search bar
		barSearch = new JTextField();
		barSearch.setText("Search (City, Country)");
		barSearch.setBounds(590, 16, 171, 37);
		barSearch.setOpaque(false);
		backgroundImage.add(barSearch);
		barSearch.setColumns(10);

		//end initialize MyLocations panel
		
		//begin initialize LocalWeather panel
		
		// city label
		labelCity = new JLabel(location);
		labelCity.setForeground(Color.WHITE);
		labelCity.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelCity.setBounds(50, 25, 200, 37);
		backgroundImage.add(labelCity);

		// temperature label
		labelTemp = new JLabel(temp + "°C");
		labelTemp.setFont(new Font("Helvetica", Font.PLAIN, 93));
		labelTemp.setForeground(Color.WHITE);
		labelTemp.setBounds(50, 64, 260, 94);
		backgroundImage.add(labelTemp);
		
		//wind label
		lblWind = new JLabel("Wind:");
		lblWind.setForeground(Color.LIGHT_GRAY);
		lblWind.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblWind.setBounds(50, 170, 61, 15);
		backgroundImage.add(lblWind);
		
		//humidity label
		lblHumidity = new JLabel("Humidity:");
		lblHumidity.setForeground(Color.LIGHT_GRAY);
		lblHumidity.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblHumidity.setBounds(50, 190, 61, 15);
		backgroundImage.add(lblHumidity);
		
		//air pressure label
		lblAirPressure = new JLabel("Air Pressure:");
		lblAirPressure.setForeground(Color.LIGHT_GRAY);
		lblAirPressure.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblAirPressure.setBounds(50, 210, 96, 15);
		backgroundImage.add(lblAirPressure);
		
		//max temp label
		lblMaxTemp = new JLabel("Max Temp:");
		lblMaxTemp.setForeground(Color.LIGHT_GRAY);
		lblMaxTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblMaxTemp.setBounds(50, 230, 96, 15);
		backgroundImage.add(lblMaxTemp);
		
		//min temp label
		lblMinTemp = new JLabel("Min. Temp:");
		lblMinTemp.setForeground(Color.LIGHT_GRAY);
		lblMinTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblMinTemp.setBounds(50, 250, 96, 15);
		backgroundImage.add(lblMinTemp);
		
		//sunrise label
		lblSunrise = new JLabel("Sunrise:");
		lblSunrise.setForeground(Color.LIGHT_GRAY);
		lblSunrise.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblSunrise.setBounds(50, 270, 61, 15);
		backgroundImage.add(lblSunrise);
		
		//sunset label
		lblSunset = new JLabel("Sunset:");
		lblSunset.setForeground(Color.LIGHT_GRAY);
		lblSunset.setFont(new Font("Helvetica", Font.PLAIN, 15));
		lblSunset.setBounds(50, 290, 58, 15);
		backgroundImage.add(lblSunset);
	
		//end initialize LocalWeather panel
		
		// begin initialize LocalWeather conditions
		
		//humidity info
		labelHumidity = new JLabel(humidity + "%");
		labelHumidity.setForeground(Color.WHITE);
		labelHumidity.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelHumidity.setBounds(120, 190, 200, 15);
		backgroundImage.add(labelHumidity);
		
		//air pressure info
		labelAirPressure = new JLabel(airPressure + "kPa");
		labelAirPressure.setForeground(Color.WHITE);
		labelAirPressure.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelAirPressure.setBounds(145, 210, 200, 15);
		backgroundImage.add(labelAirPressure);
		
		//max temp info
		labelMaxTemp = new JLabel(maxTemp + "°C");
		labelMaxTemp.setForeground(Color.WHITE);
		labelMaxTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMaxTemp.setBounds(130, 230, 200, 15);
		backgroundImage.add(labelMaxTemp);
		
		//min temp info
		labelMinTemp = new JLabel(minTemp + "°C");
		labelMinTemp.setForeground(Color.WHITE);
		labelMinTemp.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMinTemp.setBounds(130, 250, 200, 15);
		backgroundImage.add(labelMinTemp);
		
		
		//sunrise info
		labelSunrise = new JLabel(sunRise + "");
		labelSunrise.setForeground(Color.WHITE);
		labelSunrise.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunrise.setBounds(110, 270, 200, 15);
		backgroundImage.add(labelSunrise);
		
		//sunset info
		labelSunset = new JLabel(sunSet + "");
		labelSunset.setForeground(Color.WHITE);
		labelSunset.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSunset.setBounds(110, 290, 200, 15);
		backgroundImage.add(labelSunset);
		
		//sky condition info
		labelSkyCondition = new JLabel(skyCondition);
		labelSkyCondition.setForeground(Color.WHITE);
		labelSkyCondition.setFont(new Font("Helvetica", Font.PLAIN, 17));
		labelSkyCondition.setBounds(381, 25, 200, 37);
		backgroundImage.add(labelSkyCondition);
		
		//end initialize LocalWeather conditions
		
		//being initialize short-term forecast panel
		
		label12AM = new JLabel("12:00am");
		label12AM.setForeground(Color.LIGHT_GRAY);
		label12AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label12AM.setBounds(50, 360, 58, 15);
		backgroundImage.add(label12AM);
		
		label3AM = new JLabel("  3:00am");
		label3AM.setForeground(Color.LIGHT_GRAY);
		label3AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label3AM.setBounds(115, 360, 58, 15);
		backgroundImage.add(label3AM);
		
		label6AM = new JLabel("  6:00am");
		label6AM.setForeground(Color.LIGHT_GRAY);
		label6AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label6AM.setBounds(180, 360, 58, 15);
		backgroundImage.add(label6AM);
		
		label9AM = new JLabel("  9:00am");
		label9AM.setForeground(Color.LIGHT_GRAY);
		label9AM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label9AM.setBounds(245, 360, 58, 15);
		backgroundImage.add(label9AM);
		
		label12PM = new JLabel("12:00pm");
		label12PM.setForeground(Color.LIGHT_GRAY);
		label12PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label12PM.setBounds(310, 360, 58, 15);
		backgroundImage.add(label12PM);
		
		label3PM = new JLabel("  3:00pm");
		label3PM.setForeground(Color.LIGHT_GRAY);
		label3PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label3PM.setBounds(375, 360, 58, 15);
		backgroundImage.add(label3PM);
		
		label6PM = new JLabel("  6:00pm");
		label6PM.setForeground(Color.LIGHT_GRAY);
		label6PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label6PM.setBounds(440, 360, 58, 15);
		backgroundImage.add(label6PM);
		
		label9PM = new JLabel("  9:00pm");
		label9PM.setForeground(Color.LIGHT_GRAY);
		label9PM.setFont(new Font("Helvetica", Font.PLAIN, 15));
		label9PM.setBounds(505, 360, 58, 15);
		backgroundImage.add(label9PM);
		
		//end initialize Short-Term Forecast panel
		
		//begin initialize Long-Term Forecast panel
		
		labelMon = new JLabel("Mon.");
		labelMon.setForeground(Color.LIGHT_GRAY);
		labelMon.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelMon.setBounds(100, 360, 58, 15);
		backgroundImage.add(labelMon);
		
		labelTues = new JLabel("Tues.");
		labelTues.setForeground(Color.LIGHT_GRAY);
		labelTues.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelTues.setBounds(165, 360, 58, 15);
		backgroundImage.add(labelTues);
		
		labelWed = new JLabel("Wed.");
		labelWed.setForeground(Color.LIGHT_GRAY);
		labelWed.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelWed.setBounds(230, 360, 58, 15);
		backgroundImage.add(labelWed);
		
		labelThurs = new JLabel("Thurs.");
		labelThurs.setForeground(Color.LIGHT_GRAY);
		labelThurs.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelThurs.setBounds(290, 360, 58, 15);
		backgroundImage.add(labelThurs);
		
		labelFri = new JLabel("Fri.");
		labelFri.setForeground(Color.LIGHT_GRAY);
		labelFri.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelFri.setBounds(365, 360, 58, 15);
		backgroundImage.add(labelFri);
		
		labelSat = new JLabel("Sat.");
		labelSat.setForeground(Color.LIGHT_GRAY);
		labelSat.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSat.setBounds(425, 360, 58, 15);
		backgroundImage.add(labelSat);
		
		labelSun = new JLabel("Sun.");
		labelSun.setForeground(Color.LIGHT_GRAY);
		labelSun.setFont(new Font("Helvetica", Font.PLAIN, 15));
		labelSun.setBounds(490, 360, 58, 15);
		backgroundImage.add(labelSun);

		//end initialize Long-Term Conditions

        //testing code to prove that UserPreferences functions at least at a basic level - TE
        try {
            UserPreferences prefs = new UserPreferences();
            //prints c (default)
            System.out.println(prefs.getTempUnits());
            prefs.setCelsius(false);
            //prints f
            System.out.println(prefs.getTempUnits());
            UserPreferences.savePrefs(prefs);

            //create a new preferences and load the old one
            UserPreferences new_prefs = UserPreferences.getPrefs();
            //still f instead of default c because we saved & loaded it
            System.out.println(new_prefs.getTempUnits());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

	}
	
		
	
	//enable short term view
	public void shortTermView (){
		buttonLongTerm.setForeground(Color.GRAY);
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
	//enable long term view
	public void longTermView (){
		buttonLongTerm.setForeground(Color.WHITE);
		buttonShortTerm.setForeground(Color.GRAY);
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
