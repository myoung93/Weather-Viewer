package group17.weatherviewer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.*;
import java.io.UnsupportedEncodingException;

public class MainFrame {

	// this is incredibly messy, we'll have to clean this up later
	// a lot of these can be local fields too, but memory isn't really a big
	// deal
	private static Font font;

	private JFrame frame;

	private ImageIcon backgroundImage;
	private String skyConditionIconLarge;
	private String skyConditionIconSmall;
	private String skyConditionBackground;

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
	private JLabel label12AMSkyConditionIcon;
	private JLabel label12AMTempInfo;
	private JLabel label12AMSkyConditionInfo;
	private JLabel label3AM;
	private JLabel label3AMSkyConditionIcon;
	private JLabel label3AMTempInfo;
	private JLabel label3AMSkyConditionInfo;
	private JLabel label6AM;
	private JLabel label6AMSkyConditionIcon;
	private JLabel label6AMTempInfo;
	private JLabel label6AMSkyConditionInfo;
	private JLabel label9AM;
	private JLabel label9AMSkyConditionIcon;
	private JLabel label9AMTempInfo;
	private JLabel label9AMSkyConditionInfo;
	private JLabel label12PM;
	private JLabel label12PMSkyConditionIcon;
	private JLabel label12PMTempInfo;
	private JLabel label12PMSkyConditionInfo;
	private JLabel label3PM;
	private JLabel label3PMSkyConditionIcon;
	private JLabel label3PMTempInfo;
	private JLabel label3PMSkyConditionInfo;
	private JLabel label6PM;
	private JLabel label6PMSkyConditionIcon;
	private JLabel label6PMTempInfo;
	private JLabel label6PMSkyConditionInfo;
	private JLabel label9PM;
	private JLabel label9PMSkyConditionIcon;
	private JLabel label9PMTempInfo;
	private JLabel label9PMSkyConditionInfo;
	private JLabel labelDay1Info;
	private JLabel labelDay1TempInfo;
	private JLabel labelDay1SkyConditionIcon;
	private JLabel labelDay1SkyConditionInfo;
	private JLabel labelDay1MaxTemp;
	private JLabel labelDay1MinTemp;
	private JLabel labelDay2Info;
	private JLabel labelDay2TempInfo;
	private JLabel labelDay2SkyConditionInfo;
	private JLabel labelDay2SkyConditionIcon;
	private JLabel labelDay2MaxTemp;
	private JLabel labelDay2MinTemp;
	private JLabel labelDay3Info;
	private JLabel labelDay3TempInfo;
	private JLabel labelDay3SkyConditionInfo;
	private JLabel labelDay3SkyConditionIcon;
	private JLabel labelDay3MaxTemp;
	private JLabel labelDay3MinTemp;
	private JLabel labelDay4Info;
	private JLabel labelDay4TempInfo;
	private JLabel labelDay4SkyConditionInfo;
	private JLabel labelDay4SkyConditionIcon;
	private JLabel labelDay4MaxTemp;
	private JLabel labelDay4MinTemp;
	private JLabel labelDay5Info;
	private JLabel labelDay5TempInfo;
	private JLabel labelDay5SkyConditionInfo;
	private JLabel labelDay5SkyConditionIcon;
	private JLabel labelDay5MaxTemp;
	private JLabel labelDay5MinTemp;
	private JLabel labelDay6Info;
	private JLabel labelDay6TempInfo;
	private JLabel labelDay6SkyConditionInfo;
	private JLabel labelDay6SkyConditionIcon;
	private JLabel labelDay6MaxTemp;
	private JLabel labelDay6MinTemp;
	private JLabel labelDay7Info;
	private JLabel labelDay7TempInfo;
	private JLabel labelDay7SkyConditionInfo;
	private JLabel labelDay7SkyConditionIcon;
	private JLabel labelDay7MaxTemp;
	private JLabel labelDay7MinTemp;

	private JList listLocations;
	private DefaultListModel listModel;

	private UserPreferences prefs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					// should we allow resizing?
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
		prefs = UserPreferences.getPrefs();
		createFont();

		// / BEGIN INITIALIZING FRAME ///
		// backgroundImage = new ImageIcon(
		// "src/main/resources/backgrounds/default_background.jpg");
		// better to use the classpath method, works on all systems (old method
		// didn't work for me)
		backgroundImage = new ImageIcon(getClass().getResource(
				"/backgrounds/default.jpg"));
		backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setSize(800, 520);
		frame = new JFrame("Group 17 Weather Viewer");
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 520);
		frame.setContentPane(backgroundLabel);
		frame.setResizable(false);

		// this window listener contains a method windowClosing which is called
		// whenever the window is closed
		// therefore, we need to save preferences and anything else we do upon
		// closing here. -TE
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				// save the preferences
				UserPreferences.savePrefs(prefs);
				frame.dispose();
			}
		});
		// not required now that we have window listener
		// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// / END INITIALIZING FRAME ///

		// / BEGIN INITIALIZING LOCAL WEATHER VIEW PANEL ///

		// city label
		labelLocation = new JLabel("");
		labelLocation.setForeground(Color.WHITE);
		labelLocation.setFont(font.deriveFont(17f));
		labelLocation.setBounds(50, 25, 200, 37);
		frame.getContentPane().add(labelLocation);

		// temperature label
		labelTempInfo = new JLabel("");
		labelTempInfo.setFont(font.deriveFont(85f));
		labelTempInfo.setForeground(Color.WHITE);
		labelTempInfo.setBounds(50, 64, 318, 94);
		frame.getContentPane().add(labelTempInfo);

		// wind label
		labelWind = new JLabel("Wind:");
		labelWind.setForeground(Color.LIGHT_GRAY);
		labelWind.setFont(font.deriveFont(15f));
		labelWind.setBounds(50, 170, 61, 15);
		frame.getContentPane().add(labelWind);

		// humidity label
		labelHumidity = new JLabel("Humidity:");
		labelHumidity.setForeground(Color.LIGHT_GRAY);
		labelHumidity.setFont(font.deriveFont(15f));
		labelHumidity.setBounds(50, 190, 70, 15);
		frame.getContentPane().add(labelHumidity);

		// air pressure label
		labelAirPressure = new JLabel("Air Pressure:");
		labelAirPressure.setForeground(Color.LIGHT_GRAY);
		labelAirPressure.setFont(font.deriveFont(15f));
		labelAirPressure.setBounds(50, 210, 96, 15);
		frame.getContentPane().add(labelAirPressure);

		// max temp label
		labelMaxTemp = new JLabel("Max Temp:");
		labelMaxTemp.setForeground(Color.LIGHT_GRAY);
		labelMaxTemp.setFont(font.deriveFont(15f));
		labelMaxTemp.setBounds(50, 230, 96, 15);
		frame.getContentPane().add(labelMaxTemp);

		// min temp label
		labelMinTemp = new JLabel("Min. Temp:");
		labelMinTemp.setForeground(Color.LIGHT_GRAY);
		labelMinTemp.setFont(font.deriveFont(15f));
		labelMinTemp.setBounds(50, 250, 96, 15);
		frame.getContentPane().add(labelMinTemp);

		// sunrise label
		labelSunrise = new JLabel("Sunrise:");
		labelSunrise.setForeground(Color.LIGHT_GRAY);
		labelSunrise.setFont(font.deriveFont(15f));
		labelSunrise.setBounds(50, 270, 61, 15);
		frame.getContentPane().add(labelSunrise);

		// sunset label
		labelSunset = new JLabel("Sunset:");
		labelSunset.setForeground(Color.LIGHT_GRAY);
		labelSunset.setFont(font.deriveFont(15f));
		labelSunset.setBounds(50, 290, 58, 15);
		frame.getContentPane().add(labelSunset);

		// wind info

		labelWindInfo = new JLabel("");
		labelWindInfo.setForeground(Color.WHITE);
		labelWindInfo.setFont(font.deriveFont(15f));
		labelWindInfo.setBounds(103, 170, 200, 15);
		frame.getContentPane().add(labelWindInfo);

		// humidity info
		labelHumidityInfo = new JLabel("");
		labelHumidityInfo.setForeground(Color.WHITE);
		labelHumidityInfo.setFont(font.deriveFont(15f));
		labelHumidityInfo.setBounds(130, 190, 200, 15);
		frame.getContentPane().add(labelHumidityInfo);

		// air pressure info
		labelAirPressureInfo = new JLabel("");
		labelAirPressureInfo.setForeground(Color.WHITE);
		labelAirPressureInfo.setFont(font.deriveFont(15f));
		labelAirPressureInfo.setBounds(158, 210, 200, 15);
		frame.getContentPane().add(labelAirPressureInfo);

		// max temp info
		labelMaxTempInfo = new JLabel("");
		labelMaxTempInfo.setForeground(Color.WHITE);
		labelMaxTempInfo.setFont(font.deriveFont(15f));
		labelMaxTempInfo.setBounds(145, 230, 200, 15);
		frame.getContentPane().add(labelMaxTempInfo);

		// min temp info
		labelMinTempInfo = new JLabel("");
		labelMinTempInfo.setForeground(Color.WHITE);
		labelMinTempInfo.setFont(font.deriveFont(15f));
		labelMinTempInfo.setBounds(145, 250, 200, 15);
		frame.getContentPane().add(labelMinTempInfo);

		// sunrise info
		labelSunriseInfo = new JLabel("");
		labelSunriseInfo.setForeground(Color.WHITE);
		labelSunriseInfo.setFont(font.deriveFont(15f));
		labelSunriseInfo.setBounds(130, 270, 200, 15);
		frame.getContentPane().add(labelSunriseInfo);

		// sunset info
		labelSunsetInfo = new JLabel("");
		labelSunsetInfo.setForeground(Color.WHITE);
		labelSunsetInfo.setFont(font.deriveFont(15f));
		labelSunsetInfo.setBounds(130, 291, 200, 15);
		frame.getContentPane().add(labelSunsetInfo);

		// sky condition info
		labelSkyConditionInfo = new JLabel("");
		labelSkyConditionInfo.setForeground(Color.WHITE);
		labelSkyConditionInfo.setFont(font.deriveFont(17f));
		labelSkyConditionInfo.setBounds(381, 25, 200, 37);
		frame.getContentPane().add(labelSkyConditionInfo);

		// sky condition Icon info
		labelSkyConditionIcon = new JLabel();
		labelSkyConditionIcon.setBounds(375, 55, 204, 181);
		frame.getContentPane().add(labelSkyConditionIcon);
		// Refresh button
		buttonRefresh = new JButton("");
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh(labelLocation.getText());
			}
		});
		// buttonRefresh.setIcon(new ImageIcon(
		// "src/main/resources/icons/refresh_icon.png"));
		buttonRefresh.setIcon(new ImageIcon(getClass().getResource(
				"/icons/refresh_icon.png")));
		buttonRefresh.setBounds(540, 16, 41, 37);
		buttonRefresh.setOpaque(false);
		buttonRefresh.setContentAreaFilled(false);
		buttonRefresh.setBorderPainted(false);
		frame.getContentPane().add(buttonRefresh);

		// Short Term Button
		buttonShortTerm = new JButton("Short Term");
		buttonShortTerm.setForeground(Color.WHITE);
		buttonShortTerm.setFont(font.deriveFont(14f));
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
		buttonLongTerm.setFont(font.deriveFont(14f));
		buttonLongTerm.setContentAreaFilled(false);
		buttonLongTerm.setBorderPainted(false);
		buttonLongTerm.setBounds(95, 328, 127, 29);
		frame.getContentPane().add(buttonLongTerm);

		// toCelsius button
		buttonToCelsius = new JButton("°C");
		buttonToCelsius.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set preferences to celsius
				prefs.setTempUnit('c');
				buttonToCelsius.setForeground(Color.WHITE);
				buttonToFahrenheit.setForeground(Color.DARK_GRAY);
			}
		});
		buttonToCelsius.setOpaque(false);

		// buttonToCelsius.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToCelsius.setFont(font.deriveFont(18f));
		buttonToCelsius.setContentAreaFilled(false);
		buttonToCelsius.setBorderPainted(false);
		buttonToCelsius.setBounds(475, 283, 75, 29);

		// toFarenheit button
		// NEED TO CHECK PREFERENCES to set the color initially!
		buttonToFahrenheit = new JButton("°F");
		buttonToFahrenheit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set preferences to f
				prefs.setTempUnit('f');
				buttonToFahrenheit.setForeground(Color.WHITE);
				buttonToCelsius.setForeground(Color.DARK_GRAY);
			}
		});
		buttonToFahrenheit.setOpaque(false);

		// buttonToFahrenheit.setFont(new Font("Helvetica", Font.PLAIN, 18));
		buttonToFahrenheit.setFont(font.deriveFont(18f));
		buttonToFahrenheit.setContentAreaFilled(false);
		buttonToFahrenheit.setBorderPainted(false);
		buttonToFahrenheit.setBounds(520, 283, 70, 29);
		// update the buttons to the color they should be initially
		if (prefs.getTempUnit() == 'c') {
			buttonToCelsius.setForeground(Color.WHITE);
			buttonToFahrenheit.setForeground(Color.DARK_GRAY);
		} else {
			buttonToCelsius.setForeground(Color.DARK_GRAY);
			buttonToFahrenheit.setForeground(Color.WHITE);
		}

		frame.getContentPane().add(buttonToCelsius);
		frame.getContentPane().add(buttonToFahrenheit);

		// / END INITIALIZATION OF LOCAL WEATHER VIEW PANEL ///

		// / BEGIN INITIALIZATION OF LOCATIONS PANEL ///

		// Scroll Pane
		scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(595, 56, 195, 422);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		frame.getContentPane().add(scrollPane);

		// Locations list
		listModel = new DefaultListModel();
		
		// add saved locations to current list -NK
		for(String loc : prefs.getLocations()) { //loads the MyLocations arraylist into listModel
			listModel.addElement(loc);
		}
		
		listLocations = new JList(listModel);
		ListSelectionModel listSelectionModel = listLocations.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if (e.getValueIsAdjusting() == false)
					refresh(listLocations.getSelectedValue().toString());
			}
		});
		scrollPane.setViewportView(listLocations);

		// favorite Button
		buttonFavourite = new JButton();
		AddLocation addlocation = new AddLocation(buttonFavourite);
		buttonFavourite.addActionListener(addlocation);
		buttonFavourite.setEnabled(false);
		// buttonFavourite.setIcon(new ImageIcon(
		// "src/main/resources/icons/star_icon.png"));
		buttonFavourite.setIcon(new ImageIcon(getClass().getResource(
				"/icons/star_icon.png")));
		buttonFavourite.setOpaque(false);
		buttonFavourite.setContentAreaFilled(false);
		buttonFavourite.setBorderPainted(false);
		buttonFavourite.setBounds(759, 16, 41, 37);
		frame.getContentPane().add(buttonFavourite);

		// search bar
		barSearch = new JTextField();
		barSearch.setText("Search (City, Country)");
		barSearch.setToolTipText("Enter new locations here!");
		barSearch.setForeground(Color.LIGHT_GRAY);
		barSearch.addActionListener(addlocation);
		barSearch.getDocument().addDocumentListener(addlocation);
		barSearch.setBounds(590, 16, 171, 37);
		barSearch.setOpaque(false);
		barSearch.setColumns(10);
		barSearch.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if(barSearch.getText().equals("Search (City, Country)"))
					barSearch.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(barSearch.getText().equals(""))
					barSearch.setText("Search (City, Country)");
			}
		});

		frame.getContentPane().add(barSearch);

		// / END INITIALIZATION OF LOCATIONS PANEL ///

		// BEGIN INITIALIZATION OF SHORT-TERM FORECAST PANEL ///

		// 12AM
		// DAY
		label12AM = new JLabel("12:00am");
		label12AM.setForeground(Color.LIGHT_GRAY);
		label12AM.setFont(font.deriveFont(15f));
		label12AM.setBounds(50, 360, 58, 15);
		frame.getContentPane().add(label12AM);
		// Icon
		label12AMSkyConditionIcon = new JLabel();
		label12AMSkyConditionIcon.setBounds(50, 380, 55, 49);
		frame.getContentPane().add(label12AMSkyConditionIcon);
		
		// Skycondition
		label12AMSkyConditionInfo = new JLabel("");
		label12AMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label12AMSkyConditionInfo.setFont(font.deriveFont(11f));
		label12AMSkyConditionInfo.setBounds(50, 435, 58, 15);
		frame.getContentPane().add(label12AMSkyConditionInfo);
		
		// Temp
		label12AMTempInfo = new JLabel("");
		label12AMTempInfo.setForeground(Color.LIGHT_GRAY);
		label12AMTempInfo.setFont(font.deriveFont(15f));
		label12AMTempInfo.setBounds(50, 455, 58, 15);
		frame.getContentPane().add(label12AMTempInfo);

		// 3AM
		//DAY
		label3AM = new JLabel("  3:00am");
		label3AM.setForeground(Color.LIGHT_GRAY);
		label3AM.setFont(font.deriveFont(15f));
		label3AM.setBounds(115, 360, 58, 15);
		frame.getContentPane().add(label3AM);
		// Icon
		label3AMSkyConditionIcon = new JLabel();
		label3AMSkyConditionIcon.setBounds(115, 380, 55, 49);
		frame.getContentPane().add(label3AMSkyConditionIcon);
		// Skycondition
		label3AMSkyConditionInfo = new JLabel("");
		label3AMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label3AMSkyConditionInfo.setFont(font.deriveFont(11f));
		label3AMSkyConditionInfo.setBounds(115, 435, 58, 15);
		frame.getContentPane().add(label3AMSkyConditionInfo);
		// Temp
		label3AMTempInfo = new JLabel("");
		label3AMTempInfo.setForeground(Color.LIGHT_GRAY);
		label3AMTempInfo.setFont(font.deriveFont(15f));
		label3AMTempInfo.setBounds(115, 455, 58, 15);
		frame.getContentPane().add(label3AMTempInfo);
		
		//6AM
		//DAY
		label6AM = new JLabel("  6:00am");
		label6AM.setForeground(Color.LIGHT_GRAY);
		label6AM.setFont(font.deriveFont(15f));
		label6AM.setBounds(180, 360, 58, 15);
		frame.getContentPane().add(label6AM);
		// Icon
		label6AMSkyConditionIcon = new JLabel();
		label6AMSkyConditionIcon.setBounds(180, 380, 55, 49);
		frame.getContentPane().add(label6AMSkyConditionIcon);
		// Skycondition
		label6AMSkyConditionInfo = new JLabel("");
		label6AMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label6AMSkyConditionInfo.setFont(font.deriveFont(11f));
		label6AMSkyConditionInfo.setBounds(180, 435, 58, 15);
		frame.getContentPane().add(label6AMSkyConditionInfo);
		// Temp
		label6AMTempInfo = new JLabel("");
		label6AMTempInfo.setForeground(Color.LIGHT_GRAY);
		label6AMTempInfo.setFont(font.deriveFont(15f));
		label6AMTempInfo.setBounds(180, 455, 58, 15);
		frame.getContentPane().add(label6AMTempInfo);
		
		//9AM
		//DAY
		label9AM = new JLabel("  9:00am");
		label9AM.setForeground(Color.LIGHT_GRAY);
		label9AM.setFont(font.deriveFont(15f));
		label9AM.setBounds(245, 360, 58, 15);
		frame.getContentPane().add(label9AM);
		// Icon
		label9AMSkyConditionIcon = new JLabel();
		label9AMSkyConditionIcon.setBounds(245, 380, 55, 49);
		frame.getContentPane().add(label9AMSkyConditionIcon);
		// Skycondition
		label9AMSkyConditionInfo = new JLabel("");
		label9AMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label9AMSkyConditionInfo.setFont(font.deriveFont(11f));
		label9AMSkyConditionInfo.setBounds(245, 435, 58, 15);
		frame.getContentPane().add(label9AMSkyConditionInfo);
		// Temp
		label9AMTempInfo = new JLabel("");
		label9AMTempInfo.setForeground(Color.LIGHT_GRAY);
		label9AMTempInfo.setFont(font.deriveFont(15f));
		label9AMTempInfo.setBounds(245, 455, 58, 15);
		frame.getContentPane().add(label9AMTempInfo);

		//12PM
		//DAY
		label12PM = new JLabel("12:00pm");
		label12PM.setForeground(Color.LIGHT_GRAY);
		label12PM.setFont(font.deriveFont(15f));
		label12PM.setBounds(310, 360, 58, 15);
		frame.getContentPane().add(label12PM);
		// Icon
		label12PMSkyConditionIcon = new JLabel();
		label12PMSkyConditionIcon.setBounds(310, 380, 55, 49);
		frame.getContentPane().add(label12PMSkyConditionIcon);
		// Skycondition
		label12PMSkyConditionInfo = new JLabel("");
		label12PMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label12PMSkyConditionInfo.setFont(font.deriveFont(11f));
		label12PMSkyConditionInfo.setBounds(310, 435, 58, 15);
		frame.getContentPane().add(label12PMSkyConditionInfo);
		// Temp
		label12PMTempInfo = new JLabel("");
		label12PMTempInfo.setForeground(Color.LIGHT_GRAY);
		label12PMTempInfo.setFont(font.deriveFont(15f));
		label12PMTempInfo.setBounds(310, 455, 58, 15);
		frame.getContentPane().add(label12PMTempInfo);

		//3AM
		//DAY
		label3PM = new JLabel("  3:00pm");
		label3PM.setForeground(Color.LIGHT_GRAY);
		label3PM.setFont(font.deriveFont(15f));
		label3PM.setBounds(375, 360, 58, 15);
		frame.getContentPane().add(label3PM);
		// Icon
		label3PMSkyConditionIcon = new JLabel();
		label3PMSkyConditionIcon.setBounds(375, 380, 55, 49);
		frame.getContentPane().add(label3PMSkyConditionIcon);
		// Skycondition
		label3PMSkyConditionInfo = new JLabel("");
		label3PMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label3PMSkyConditionInfo.setFont(font.deriveFont(11f));
		label3PMSkyConditionInfo.setBounds(375, 435, 58, 15);
		frame.getContentPane().add(label3PMSkyConditionInfo);
		// Temp
		label3PMTempInfo = new JLabel("");
		label3PMTempInfo.setForeground(Color.LIGHT_GRAY);
		label3PMTempInfo.setFont(font.deriveFont(15f));
		label3PMTempInfo.setBounds(375, 455, 58, 15);
		frame.getContentPane().add(label3PMTempInfo);
		
		//6AM
		//DAY
		label6PM = new JLabel("  6:00pm");
		label6PM.setForeground(Color.LIGHT_GRAY);
		label6PM.setFont(font.deriveFont(15f));
		label6PM.setBounds(440, 360, 58, 15);
		frame.getContentPane().add(label6PM);
		// Icon
		label6PMSkyConditionIcon = new JLabel();
		label6PMSkyConditionIcon.setBounds(440, 380, 55, 49);
		frame.getContentPane().add(label6PMSkyConditionIcon);
		// Skycondition
		label6PMSkyConditionInfo = new JLabel("");
		label6PMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label6PMSkyConditionInfo.setFont(font.deriveFont(11f));
		label6PMSkyConditionInfo.setBounds(440, 435, 58, 15);
		frame.getContentPane().add(label6PMSkyConditionInfo);
		// Temp
		label6PMTempInfo = new JLabel("");
		label6PMTempInfo.setForeground(Color.LIGHT_GRAY);
		label6PMTempInfo.setFont(font.deriveFont(15f));
		label6PMTempInfo.setBounds(440, 455, 58, 15);
		frame.getContentPane().add(label6PMTempInfo);
		
		//9AM
		//DAY
		label9PM = new JLabel("  9:00pm");
		label9PM.setForeground(Color.LIGHT_GRAY);
		label9PM.setFont(font.deriveFont(15f));
		label9PM.setBounds(505, 360, 58, 15);
		frame.getContentPane().add(label9PM);
		// Icon
		label9PMSkyConditionIcon = new JLabel();
		label9PMSkyConditionIcon.setBounds(505, 380, 55, 49);
		frame.getContentPane().add(label9PMSkyConditionIcon);
		// Skycondition
		label9PMSkyConditionInfo = new JLabel("");
		label9PMSkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		label9PMSkyConditionInfo.setFont(font.deriveFont(11f));
		label9PMSkyConditionInfo.setBounds(505, 435, 58, 15);
		frame.getContentPane().add(label9PMSkyConditionInfo);
		// Temp
		label9PMTempInfo = new JLabel("");
		label9PMTempInfo.setForeground(Color.LIGHT_GRAY);
		label9PMTempInfo.setFont(font.deriveFont(15f));
		label9PMTempInfo.setBounds(505, 455, 58, 15);
		frame.getContentPane().add(label9PMTempInfo);

		// / END INITIALIZATION OF SHORT-TERM FORECAST PANEL ///

		// / BEGIN INITIALIZATION OF LONG-TERM FORECAST PANEL ///

		//Day1
		labelDay1Info = new JLabel("Mon.");
		labelDay1Info.setForeground(Color.LIGHT_GRAY);
		labelDay1Info.setFont(font.deriveFont(15f));
		labelDay1Info.setBounds(100, 360, 58, 15);
		frame.getContentPane().add(labelDay1Info);
		// Icon
		labelDay1SkyConditionIcon = new JLabel();
		labelDay1SkyConditionIcon.setBounds(85, 380, 55, 49);
		frame.getContentPane().add(labelDay1SkyConditionIcon);
		// Skycondition
		labelDay1SkyConditionInfo = new JLabel("");
		labelDay1SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay1SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay1SkyConditionInfo.setBounds(85, 435, 58, 15);
		frame.getContentPane().add(labelDay1SkyConditionInfo);
		// Temp
		labelDay1TempInfo = new JLabel("");
		labelDay1TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay1TempInfo.setFont(font.deriveFont(15f));
		labelDay1TempInfo.setBounds(85, 455, 58, 15);
		frame.getContentPane().add(labelDay1TempInfo);

		//Day2
		labelDay2Info = new JLabel("Tues.");
		labelDay2Info.setForeground(Color.LIGHT_GRAY);
		labelDay2Info.setFont(font.deriveFont(15f));
		labelDay2Info.setBounds(165, 360, 58, 15);
		frame.getContentPane().add(labelDay2Info);
		// Icon
		labelDay2SkyConditionIcon = new JLabel();
		labelDay2SkyConditionIcon.setBounds(150, 380, 55, 49);
		frame.getContentPane().add(labelDay2SkyConditionIcon);
		// Skycondition
		labelDay2SkyConditionInfo = new JLabel("");
		labelDay2SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay2SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay2SkyConditionInfo.setBounds(150, 435, 58, 15);
		frame.getContentPane().add(labelDay2SkyConditionInfo);
		// Temp
		labelDay2TempInfo = new JLabel("");
		labelDay2TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay2TempInfo.setFont(font.deriveFont(15f));
		labelDay2TempInfo.setBounds(150, 455, 58, 15);
		frame.getContentPane().add(labelDay2TempInfo);

		//Day3
		labelDay3Info = new JLabel("Wed.");
		labelDay3Info.setForeground(Color.LIGHT_GRAY);
		labelDay3Info.setFont(font.deriveFont(15f));
		labelDay3Info.setBounds(230, 360, 58, 15);
		frame.getContentPane().add(labelDay3Info);
		// Icon
		labelDay3SkyConditionIcon = new JLabel();
		labelDay3SkyConditionIcon.setBounds(220, 380, 55, 49);
		frame.getContentPane().add(labelDay3SkyConditionIcon);
		// Skycondition
		labelDay3SkyConditionInfo = new JLabel("");
		labelDay3SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay3SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay3SkyConditionInfo.setBounds(220, 435, 58, 15);
		frame.getContentPane().add(labelDay3SkyConditionInfo);
		// Temp
		labelDay3TempInfo = new JLabel("");
		labelDay3TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay3TempInfo.setFont(font.deriveFont(15f));
		labelDay3TempInfo.setBounds(220, 455, 58, 15);
		frame.getContentPane().add(labelDay3TempInfo);

		//Day4
		labelDay4Info = new JLabel("Thurs.");
		labelDay4Info.setForeground(Color.LIGHT_GRAY);
		labelDay4Info.setFont(font.deriveFont(15f));
		labelDay4Info.setBounds(290, 360, 58, 15);
		frame.getContentPane().add(labelDay4Info);
		// Icon
		labelDay4SkyConditionIcon = new JLabel();
		labelDay4SkyConditionIcon.setBounds(285, 380, 55, 49);
		frame.getContentPane().add(labelDay4SkyConditionIcon);
		// Skycondition
		labelDay4SkyConditionInfo = new JLabel("");
		labelDay4SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay4SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay4SkyConditionInfo.setBounds(285, 435, 58, 15);
		frame.getContentPane().add(labelDay4SkyConditionInfo);
		// Temp
		labelDay4TempInfo = new JLabel("");
		labelDay4TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay4TempInfo.setFont(font.deriveFont(15f));
		labelDay4TempInfo.setBounds(285, 455, 58, 15);
		frame.getContentPane().add(labelDay4TempInfo);
		
		//Day5
		labelDay5Info = new JLabel("Fri.");
		labelDay5Info.setForeground(Color.LIGHT_GRAY);
		labelDay5Info.setFont(font.deriveFont(15f));
		labelDay5Info.setBounds(365, 360, 58, 15);
		frame.getContentPane().add(labelDay5Info);
		// Icon
		labelDay5SkyConditionIcon = new JLabel();
		labelDay5SkyConditionIcon.setBounds(350, 380, 55, 49);
		frame.getContentPane().add(labelDay5SkyConditionIcon);
		// Skycondition
		labelDay5SkyConditionInfo = new JLabel("");
		labelDay5SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay5SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay5SkyConditionInfo.setBounds(350, 435, 58, 15);
		frame.getContentPane().add(labelDay5SkyConditionInfo);
		// Temp
		labelDay5TempInfo = new JLabel("");
		labelDay5TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay5TempInfo.setFont(font.deriveFont(15f));
		labelDay5TempInfo.setBounds(350, 455, 58, 15);
		frame.getContentPane().add(labelDay5TempInfo);

		//Day6
		labelDay6Info = new JLabel("Sat.");
		labelDay6Info.setForeground(Color.LIGHT_GRAY);
		labelDay6Info.setFont(font.deriveFont(15f));
		labelDay6Info.setBounds(425, 360, 58, 15);
		frame.getContentPane().add(labelDay6Info);
		// Icon
		labelDay6SkyConditionIcon = new JLabel();
		labelDay6SkyConditionIcon.setBounds(410, 380, 55, 49);
		frame.getContentPane().add(labelDay6SkyConditionIcon);
		// Skycondition
		labelDay6SkyConditionInfo = new JLabel("");
		labelDay6SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay6SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay6SkyConditionInfo.setBounds(410, 435, 58, 15);
		frame.getContentPane().add(labelDay6SkyConditionInfo);
		// Temp
		labelDay6TempInfo = new JLabel("");
		labelDay6TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay6TempInfo.setFont(font.deriveFont(15f));
		labelDay6TempInfo.setBounds(410, 455, 58, 15);
		frame.getContentPane().add(labelDay6TempInfo);

		//Day7
		labelDay7Info = new JLabel("Sun.");
		labelDay7Info.setForeground(Color.LIGHT_GRAY);
		labelDay7Info.setFont(font.deriveFont(15f));
		labelDay7Info.setBounds(490, 360, 58, 15);
		frame.getContentPane().add(labelDay7Info);
		// Icon
		labelDay7SkyConditionIcon = new JLabel();
		labelDay7SkyConditionIcon.setBounds(475, 380, 55, 49);
		frame.getContentPane().add(labelDay7SkyConditionIcon);
		// Skycondition
		labelDay7SkyConditionInfo = new JLabel("");
		labelDay7SkyConditionInfo.setForeground(Color.LIGHT_GRAY);
		labelDay7SkyConditionInfo.setFont(font.deriveFont(11f));
		labelDay7SkyConditionInfo.setBounds(475, 435, 58, 15);
		frame.getContentPane().add(labelDay7SkyConditionInfo);
		// Temp
		labelDay7TempInfo = new JLabel("");
		labelDay7TempInfo.setForeground(Color.LIGHT_GRAY);
		labelDay7TempInfo.setFont(font.deriveFont(15f));
		labelDay7TempInfo.setBounds(475, 455, 58, 15);
		frame.getContentPane().add(labelDay7TempInfo);

		// / END INITIALIZATION OF LONG-TERM CONDITIONS ///

		// show short term view by default
		shortTermView();
	}

	///END GUI INITIALAZATION///
	
	
	///MISCELLANIOUS METHODS///
	
	//initializes our custom fonts
	private void createFont() {
		java.io.InputStream fontInputStream = this.getClass()
				.getResourceAsStream("/fonts/HelveticaNeue-Medium.otf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);
		} catch (Exception e) {
			// failsafe, temp workaround -TE
			font = new Font("Helvetica", Font.PLAIN, 20);
			System.out.println("Font loading failed");
			e.printStackTrace();
		}
	}

	// updates the labels current weather view to the new weather conditions
	// in the future, this should probably take a String city parameter to
	// construct the CurrentWeather obj from.
	// should probably show some kind of "updating" message
	public void refresh(String location) {
		
		CurrentWeather new_weather = null;
		try {
			// constructor should take String city parameter in the future.
			System.out.println("Retrieving weather data");	
			new_weather = new CurrentWeather(location);
		} catch (UnsupportedEncodingException e) {
			System.out
					.println("Something went wrong retrieving current weather");
			e.printStackTrace();
		}

		// current location panel
		labelLocation.setText(new_weather.getCity() + ", "
				+ new_weather.getCountry());
		labelSkyConditionInfo.setText(new_weather.getSkyCondition());
		labelTempInfo.setText(new_weather.getTemp().substring(0,4) + "°C");
		labelWindInfo.setText(new_weather.getWindSpeed() + " km/h "
				+ new_weather.getWindDirection());
		labelHumidityInfo.setText(new_weather.getHumidity() + "%");
		labelAirPressureInfo.setText(new_weather.getPressure() + "kPa");
		//need to substring these so we don't have a million decimal places
		labelMaxTempInfo.setText(new_weather.getTempMax().substring(0,4) + "°C");
		labelMinTempInfo.setText(new_weather.getTempMin().substring(0,4) + "°C");
		labelSunriseInfo.setText(new_weather.getSunriseTime());
		labelSunsetInfo.setText(new_weather.getSunsetTime());
		setSkyConditionImages(new_weather.getWeatherID());
		labelSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconLarge));
		backgroundLabel.setIcon(new ImageIcon(skyConditionBackground));
		// short/longterm icons will be the same has current weather, can be easily switched later
		//shortterm
		label12AMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label12AMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label12AMTempInfo.setText(new_weather.getTemp() + "°C");
		label3AMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label3AMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label3AMTempInfo.setText(new_weather.getTemp() + "°C");
		label6AMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label6AMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label6AMTempInfo.setText(new_weather.getTemp() + "°C");
		label9AMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label9AMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label9AMTempInfo.setText(new_weather.getTemp() + "°C");
		label12PMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label12PMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label12PMTempInfo.setText(new_weather.getTemp() + "°C");
		label3PMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label3PMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label3PMTempInfo.setText(new_weather.getTemp() + "°C");
		label6PMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label6PMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label6PMTempInfo.setText(new_weather.getTemp() + "°C");
		label9PMSkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		label9PMSkyConditionInfo.setText(new_weather.getSkyCondition());
		label9PMTempInfo.setText(new_weather.getTemp() + "°C");
		//longterm
		labelDay1SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay1SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay1TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay2SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay2SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay2TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay3SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay3SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay3TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay4SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay4SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay4TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay5SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay5SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay5TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay6SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay6SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay6TempInfo.setText(new_weather.getTemp() + "°C");
		labelDay7SkyConditionIcon.setIcon(new ImageIcon(skyConditionIconSmall));
		labelDay7SkyConditionInfo.setText(new_weather.getSkyCondition());
		labelDay7TempInfo.setText(new_weather.getTemp() + "°C");
	}
		

	// This listener is shared by the barSearch TextField and the AddLocation Button.
	// Adds a new location to the Location List
	class AddLocation implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public AddLocation(JButton button) {
			this.button = button;
		}

		// Required by ActionListener.
		public void actionPerformed(ActionEvent e) {
			String name = barSearch.getText();

			// User didn't type in a unique name...
			if (name.equals("") || alreadyInList(name)) {
				Toolkit.getDefaultToolkit().beep();
				barSearch.requestFocusInWindow();
				barSearch.selectAll();
				return;
			}

			int index = listLocations.getSelectedIndex(); // get selected index
			if (index == -1) { // no selection, so insert at beginning
				index = 0;
			} else { // add after the selected item
				index++;
			}

			listModel.insertElementAt(barSearch.getText(), index);
			// If we just wanted to add to the end, we'd do this:
			// listModel.addElement(barSearch.getText());

			// Add element to user preferences as well - NK
			try {
				prefs.addLocation(index, barSearch.getText());	//adds to "index" position of the MyLocations list
			}
			catch(WeatherException exception) {
				System.out.println(exception.getMessage());
			}
			prefs.printLocations(); // This is just to test addLocation on user preferences - NK


			// Reset the text field.
			barSearch.requestFocusInWindow();
			barSearch.setText("");

			// Select the new item and make it visible.
			listLocations.setSelectedIndex(index);
			listLocations.ensureIndexIsVisible(index);
		}

		// This method tests for string equality. You could certainly
		// get more sophisticated about the algorithm. For example,
		// you might want to ignore white space and capitalization.
		protected boolean alreadyInList(String name) {
			return listModel.contains(name);
		}

		// Required by DocumentListener.
		public void insertUpdate(DocumentEvent e) {
			enableButton();
		}

		// Required by DocumentListener.
		public void removeUpdate(DocumentEvent e) {
			handleEmptyTextField(e);
		}

		// Required by DocumentListener.
		public void changedUpdate(DocumentEvent e) {
			if (!handleEmptyTextField(e)) {
				enableButton();
			}
		}

		private void enableButton() {
			if (!alreadyEnabled) {
				button.setEnabled(true);
			}
		}

		private boolean handleEmptyTextField(DocumentEvent e) {
			if (e.getDocument().getLength() <= 0) {
				button.setEnabled(false);
				alreadyEnabled = false;
				return true;
			}
			return false;
		}
	}

		
	// enable short term view
	public void shortTermView() {
		buttonLongTerm.setForeground(Color.DARK_GRAY);
		buttonShortTerm.setForeground(Color.WHITE);
		labelDay1Info.setVisible(false);
		labelDay1SkyConditionIcon.setVisible(false);
		labelDay1TempInfo.setVisible(false);
		labelDay1SkyConditionInfo.setVisible(false);
		labelDay2Info.setVisible(false);
		labelDay2SkyConditionIcon.setVisible(false);
		labelDay2TempInfo.setVisible(false);
		labelDay2SkyConditionInfo.setVisible(false);
		labelDay3Info.setVisible(false);
		labelDay3SkyConditionIcon.setVisible(false);
		labelDay3TempInfo.setVisible(false);
		labelDay3SkyConditionInfo.setVisible(false);
		labelDay4Info.setVisible(false);
		labelDay4SkyConditionIcon.setVisible(false);
		labelDay4TempInfo.setVisible(false);
		labelDay4SkyConditionInfo.setVisible(false);
		labelDay5Info.setVisible(false);
		labelDay5SkyConditionIcon.setVisible(false);
		labelDay5TempInfo.setVisible(false);
		labelDay5SkyConditionInfo.setVisible(false);
		labelDay6Info.setVisible(false);
		labelDay6SkyConditionIcon.setVisible(false);
		labelDay6TempInfo.setVisible(false);
		labelDay6SkyConditionInfo.setVisible(false);
		labelDay7Info.setVisible(false);
		labelDay7SkyConditionIcon.setVisible(false);
		labelDay7TempInfo.setVisible(false);
		labelDay7SkyConditionInfo.setVisible(false);
		label12AM.setVisible(true);
		label12AMSkyConditionIcon.setVisible(true);
		label12AMTempInfo.setVisible(true);
		label12AMSkyConditionInfo.setVisible(true);
		label3AM.setVisible(true);
		label3AMSkyConditionIcon.setVisible(true);
		label3AMTempInfo.setVisible(true);
		label3AMSkyConditionInfo.setVisible(true);
		label6AM.setVisible(true);
		label6AMSkyConditionIcon.setVisible(true);
		label6AMTempInfo.setVisible(true);
		label6AMSkyConditionInfo.setVisible(true);
		label9AM.setVisible(true);
		label9AMSkyConditionIcon.setVisible(true);
		label9AMTempInfo.setVisible(true);
		label9AMSkyConditionInfo.setVisible(true);
		label12PM.setVisible(true);
		label12PMSkyConditionIcon.setVisible(true);
		label12PMTempInfo.setVisible(true);
		label12PMSkyConditionInfo.setVisible(true);
		label3PM.setVisible(true);
		label3PMSkyConditionIcon.setVisible(true);
		label3PMTempInfo.setVisible(true);
		label3PMSkyConditionInfo.setVisible(true);
		label6PM.setVisible(true);
		label6PMSkyConditionIcon.setVisible(true);
		label6PMTempInfo.setVisible(true);
		label6PMSkyConditionInfo.setVisible(true);
		label9PM.setVisible(true);
		label9PMSkyConditionIcon.setVisible(true);
		label9PMTempInfo.setVisible(true);
		label9PMSkyConditionInfo.setVisible(true);
	}

	// enable long term view
	public void longTermView() {
		buttonLongTerm.setForeground(Color.WHITE);
		buttonShortTerm.setForeground(Color.DARK_GRAY);
		labelDay1Info.setVisible(true);
		labelDay1SkyConditionIcon.setVisible(true);
		labelDay1TempInfo.setVisible(true);
		labelDay1SkyConditionInfo.setVisible(true);
		labelDay2Info.setVisible(true);
		labelDay2SkyConditionIcon.setVisible(true);
		labelDay2TempInfo.setVisible(true);
		labelDay2SkyConditionInfo.setVisible(true);
		labelDay3Info.setVisible(true);
		labelDay3SkyConditionIcon.setVisible(true);
		labelDay3TempInfo.setVisible(true);
		labelDay3SkyConditionInfo.setVisible(true);
		labelDay4Info.setVisible(true);
		labelDay4SkyConditionIcon.setVisible(true);
		labelDay4TempInfo.setVisible(true);
		labelDay4SkyConditionInfo.setVisible(true);
		labelDay5Info.setVisible(true);
		labelDay5SkyConditionIcon.setVisible(true);
		labelDay5TempInfo.setVisible(true);
		labelDay5SkyConditionInfo.setVisible(true);
		labelDay6Info.setVisible(true);
		labelDay6SkyConditionIcon.setVisible(true);
		labelDay6TempInfo.setVisible(true);
		labelDay6SkyConditionInfo.setVisible(true);
		labelDay7Info.setVisible(true);
		labelDay7SkyConditionIcon.setVisible(true);
		labelDay7TempInfo.setVisible(true);
		labelDay7SkyConditionInfo.setVisible(true);
		label12AM.setVisible(false);
		label12AMSkyConditionIcon.setVisible(false);
		label12AMTempInfo.setVisible(false);
		label12AMSkyConditionInfo.setVisible(false);
		label3AM.setVisible(false);
		label3AMSkyConditionIcon.setVisible(false);
		label3AMTempInfo.setVisible(false);
		label3AMSkyConditionInfo.setVisible(false);
		label6AM.setVisible(false);
		label6AMSkyConditionIcon.setVisible(false);
		label6AMTempInfo.setVisible(false);
		label6AMSkyConditionInfo.setVisible(false);
		label9AM.setVisible(false);
		label9AMSkyConditionIcon.setVisible(false);
		label9AMTempInfo.setVisible(false);
		label9AMSkyConditionInfo.setVisible(false);
		label12PM.setVisible(false);
		label12PMSkyConditionIcon.setVisible(false);
		label12PMTempInfo.setVisible(false);
		label12PMSkyConditionInfo.setVisible(false);
		label3PM.setVisible(false);
		label3PMSkyConditionIcon.setVisible(false);
		label3PMTempInfo.setVisible(false);
		label3PMSkyConditionInfo.setVisible(false);
		label6PM.setVisible(false);
		label6PMSkyConditionIcon.setVisible(false);
		label6PMTempInfo.setVisible(false);
		label6PMSkyConditionInfo.setVisible(false);
		label9PM.setVisible(false);
		label9PMSkyConditionIcon.setVisible(false);
		label9PMTempInfo.setVisible(false);
		label9PMSkyConditionInfo.setVisible(false);
	}

	public void setSkyConditionImages(int ID) {
		switch (ID) {
		// THUNDERSTORM
		// Thunderstorm with Light Rain
		case 200:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;
		// Thunderstorm with Rain
		case 201:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;
		// Thunderstorm with Heavy Rain
		case 202:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Light Thunderstorm
		case 210:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Thunderstorm
		case 211:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Heavy Thunderstorm
		case 212:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Ragged Thunderstorm
		case 221:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Thunderstorm with Light Drizzle
		case 230:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Thunderstorm with Drizzle
		case 231:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// Thunderstorm with Heavy Drizzle
		case 232:
			skyConditionIconLarge = "src/main/resources/icons/large/thunder.png";
			skyConditionIconSmall = "src/main/resources/icons/small/thunder.png";
			skyConditionBackground = "src/main/resources/backgrounds/thunder.jpg";
			break;

		// DRIZZLE

		// Light Intensity Drizzle
		case 300:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Drizzle
		case 301:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Heavy Intensity Drizzle
		case 302:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Light Intensity Drizzle Rain
		case 310:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Drizzle Rain
		case 311:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Heavy Intensity Drizzle Rain
		case 312:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Shower Rain and Drizzle
		case 313:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Heavy Shower Rain and Drizzle
		case 314:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Shower Drizzle
		case 321:
			skyConditionIconLarge = "src/main/resources/icons/large/drizzle.png";
			skyConditionIconSmall = "src/main/resources/icons/small/drizzle.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// RAIN

		// Light Rain
		case 500:
			skyConditionIconLarge = "src/main/resources/icons/large/light_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Moderate Rain
		case 501:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Heavy Intensity Rain
		case 502:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Very Heavy Rain
		case 503:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Extreme Rain
		case 504:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Freezing Rain
		case 511:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Light Intensity Shower Rain
		case 520:
			skyConditionIconLarge = "src/main/resources/icons/large/light_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Shower Rain
		case 521:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Heavy Intensity Shower Rain
		case 522:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Ragged Shower Rain
		case 531:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_rain.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_rain.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// SNOW

		// Light Snow
		case 600:
			skyConditionIconLarge = "src/main/resources/icons/large/light_snow.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_snow.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Snow
		case 601:
			skyConditionIconLarge = "src/main/resources/icons/large/light_snow.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_snow.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Heavy Snow
		case 602:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_snow.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_snow.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Sleet
		case 611:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Shower Sleet
		case 612:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Light Rain and Snow
		case 615:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Rain and Snow
		case 616:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Light Shower Snow
		case 620:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Shower Snow
		case 621:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Heavy Shower Snow
		case 622:
			skyConditionIconLarge = "src/main/resources/icons/large/sleet.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sleet.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Atmosphere

		// Mist
		case 701:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/fog.jpg";
			break;

		// Smoke
		case 711:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/volcano.jpg";
			break;

		// Haze
		case 721:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/fog.jpg";
			break;

		// Sand/Dust Whirls
		case 731:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/sand.jpg";
			break;

		// Fog
		case 741:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/fog.jpg";
			break;

		// Sand
		case 751:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/sand.jpg";
			break;

		// Dust
		case 761:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/sand.jpg";
			break;

		// Volcanic Ash
		case 762:
			skyConditionIconLarge = "src/main/resources/icons/large/mist.png";
			skyConditionIconSmall = "src/main/resources/icons/small/mist.png";
			skyConditionBackground = "src/main/resources/backgrounds/volcano.jpg";
			break;

		// Squalls
		case 771:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/torndao.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Tornado
		case 781:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/torndao.png";
			skyConditionBackground = "src/main/resources/backgrounds/tornado.jpg";
			break;

		// CLOUDS

		// Sky is clear
		case 800:
			skyConditionIconLarge = "src/main/resources/icons/large/sun.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sun.png";
			skyConditionBackground = "src/main/resources/backgrounds/sun.jpg";
			break;

		// Calm
		case 951:
			skyConditionIconLarge = "src/main/resources/icons/large/sun.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sun.png";
			skyConditionBackground = "src/main/resources/backgrounds/partial_clouds.jpg";
			break;

		// Few Clouds
		case 801:
			skyConditionIconLarge = "src/main/resources/icons/large/light_clouds.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_clouds.png";
			skyConditionBackground = "src/main/resources/backgrounds/partial_clouds.jpg";
			break;

		// Scattered Clouds
		case 802:
			skyConditionIconLarge = "src/main/resources/icons/large/light_clouds.png";
			skyConditionIconSmall = "src/main/resources/icons/small/light_clouds.png";
			skyConditionBackground = "src/main/resources/backgrounds/partial_clouds.jpg";
			break;

		// Broken Clouds
		case 803:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_clouds.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_clouds.png";
			skyConditionBackground = "src/main/resources/backgrounds/clouds.jpg";
			break;

		// Overcast Clouds
		case 804:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_clouds.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_clouds.png";
			skyConditionBackground = "src/main/resources/backgrounds/clouds.jpg";
			break;

		// EXTREME

		// Tornado
		case 900:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/torndao.png";
			skyConditionBackground = "src/main/resources/backgrounds/tornado.jpg";
			break;

		// Tropical Storm
		case 901:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/torndao.png";
			skyConditionBackground = "src/main/resources/backgrounds/rain.jpg";
			break;

		// Hurricane
		case 902:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/torndao.png";
			skyConditionBackground = "src/main/resources/backgrounds/hurricane.jpg";
			break;

		// Cold
		case 903:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// Hot
		case 904:
			skyConditionIconLarge = "src/main/resources/icons/large/sun.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sun.png";
			skyConditionBackground = "src/main/resources/backgrounds/sun.jpg";
			break;

		// Windy
		case 905:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Hail
		case 906:
			skyConditionIconLarge = "src/main/resources/icons/large/heavy_snow.png";
			skyConditionIconSmall = "src/main/resources/icons/small/heavy_snow.png";
			skyConditionBackground = "src/main/resources/backgrounds/snow.jpg";
			break;

		// ADDITIONAL

		// Setting
		case 950:
			skyConditionIconLarge = "src/main/resources/icons/large/sun.png";
			skyConditionIconSmall = "src/main/resources/icons/small/sun.png";
			skyConditionBackground = "src/main/resources/backgrounds/sun.jpg";
			break;

		// Light Breeze
		case 952:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Gentle Breeze
		case 953:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Moderate breeze
		case 954:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Fresh Breeze
		case 955:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Strong Breeze
		case 956:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// High Wind, near Gale
		case 957:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Gale
		case 958:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Severe Gale
		case 959:
			skyConditionIconLarge = "src/main/resources/icons/large/sun.png";
			skyConditionBackground = "src/main/resources/backgrounds/sun.jpg";
			break;

		// Storm
		case 960:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/wind.jpg";
			break;

		// Violent Storm
		case 961:
			skyConditionIconLarge = "src/main/resources/icons/large/wind.png";
			skyConditionIconSmall = "src/main/resources/icons/small/wind.png";
			skyConditionBackground = "src/main/resources/backgrounds/tornado.jpg";
			break;

		// Hurricane
		case 962:
			skyConditionIconLarge = "src/main/resources/icons/large/tornado.png";
			skyConditionIconSmall = "src/main/resources/icons/small/tornado.png";
			skyConditionBackground = "src/main/resources/backgrounds/hurricane.jpg";
			break;
		}
	}
}
