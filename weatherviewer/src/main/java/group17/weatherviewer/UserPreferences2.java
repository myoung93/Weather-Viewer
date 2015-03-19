package group17.weatherviewer;

import java.io.*;
import java.util.ArrayList;

public class UserPreferences implements java.io.Serializable {

    /**
     * Filename for serialized object
     */
    private static final transient String FILENAME = "weather.prefs";

    //these will be preference fields, many more in final version
    private char tempUnit;
    private char speedUnit;
    private char pressureUnit;
    private char timeUnit;
    private boolean isCelsius; //temporary?
    private ArrayList<String> locations;

    /**
     * Constructor of user preferences, initializes locations list and sets default values
     */
    public UserPreferences() {
        //we use celsius by default
        isCelsius = true;
        //intialize empty locations (in the future we will have to prompt user so this will change)
        locations = new ArrayList<String>();
    }

    /**
     * Sets temperature type to celsius (c) or fahrenheit (f)
     * @param b boolean to indicate if temperature type is celsius (true) or fahrenheit (false)
     */
    public void setCelsius (boolean b) {
        isCelsius = b;
    } //temporary?

    /**
     * Sets temperature type to celsius (c) or fahrenheit (f)
     * @param unit char to indicate temperature type, can be either celsius (c) or fahrenheit (f)
     */
    public void setTempUnit(char unit) {
        if(unit == 'c' || unit == 'f') { //maybe a try/catch?
            tempUnit = unit;
        }
        else {
            ;//?
        }
    }

    /**
     * Sets speed unit to meters per second (m), kilometers per hour (k), miles per hour (i), knots (n), or feet per second (f)
     * @param unit char to indicate speed unit, either m for m/s, k for km/h, i for mi/h, n for kn, or f for ft/s
     */
    public void setSpeedUnit(char unit) {
        if(unit == 'm' || unit == 'k' || unit == 'i' || unit == 'n' || unit == 'f') {//i know i is not so intuitive, sugggestions?
            speedUnit = unit;
        }
        else {
            ;//?
        }
    }

    /**
     * Sets pressure unit to pascal (p), newton per square meter (n), or bar (b) ???
     * @param unit char to indicate pressure unit, can be either pascal (p), newton per square meter (n), or bar (b)
     */
    public void setPressureUnit(char unit) {
        if(unit == 'p' || unit == 'n' || unit == 'b') { //maybe a try/catch?
            pressureUnit = unit;
        }
        else {
            ;//?
        }
    }

    /**
     * Sets time type to 12-hour (1) or 24-hour (2)
     * @param unit char to indicate time type, can be either 12-hour (1) or 24-hour (2)
     */
    public void setTimeUnit(char unit) {
        if(unit == '1' || unit == '2') { //maybe a try/catch?
            timeUnit = unit;
        }
        else {
            ;//?
        }
    }

    /**
     * Gets temperature unit
     * @return c for celsius, f for fahrenheit
     */
    public char getTempUnit() {
        return tempUnit;
    }

    /**
     * Gets speed unit
     * @return m for meters per second, k for kilometers per hour, i for miles per hour, n for knots, f for feet per second
     */
    public char getSpeedUnit() {
        return speedUnit;
    }

    /**
     * Gets pressure unit
     * @return p for pascal, n for newton per square meter, b for bar
     */
    public char getPressureUnit() {
        return pressureUnit;
    }

    /**
     * Gets time unit
     * @return 1 for 12-hour, 2 for 24-hour
     */
    public char getTimeUnit() {
        return timeUnit;
    }

    /**
     * Gets temperature type
     * @return true if temperature type is celsius
     */
    public boolean isCelsius() { //temporary?
        return isCelsius;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    /**
     * Adds a location to the list of favorite locations
     * @param s name of the location
     */
    public void addLocation(String s) throws WeatherException {
        if(!locations.contains(s))
            locations.add(s);
        else
            throw new WeatherException("Location already exists");
    }

    /**
     * Removes a location from the list of favorite locations, if the location is in the list
     * @param s name of the location
     */
    public void removeLocation(String s) throws WeatherException {
        if(locations.contains(s))
            locations.remove(s);
        else
            throw new WeatherException("Location does not exist");
    }

    /**
     * Gives a favorite location identified by index
     * @param index index of the array (from 0 to size - 1)
     * @return the value of the array in given position if index is not out of bounds
     */
    public String getLocation(int index) throws WeatherException {
        try {
            return locations.get(index);
        }
        catch(ArrayIndexOutOfBoundsException ae) {
            throw new WeatherException("Out-of-bounds error in getLocation(int)", ae);
        }
    }

    /**
     * Saves a copy of the provided preferences object to the working directory in a file called FILENAME.
     * @param up is the preferences object to be serialized to a file for the next run.
     */
    public static void savePrefs(UserPreferences up) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(up);
        oos.close();
    }

    /**
     * Finds the FILENAME file in the working directory and loaded the serialized object, then returns it.
     * Allows loading of preferences at the start of a program run.
     */
    public static UserPreferences getPrefs() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        Object o;
        try {
            ois = new ObjectInputStream(new FileInputStream(FILENAME));
            o = ois.readObject();
        }
        catch(FileNotFoundException | NullPointerException e) {
            //need to create a new set of preferences if one doesn't exist
            return new UserPreferences();
        } finally {
            if(ois != null)
                ois.close();
        }
        return (UserPreferences)o;
    }
}
