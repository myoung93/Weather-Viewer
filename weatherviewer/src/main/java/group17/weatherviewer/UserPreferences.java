package group17.weatherviewer;

import java.io.*;
import java.util.ArrayList;

public class UserPreferences implements java.io.Serializable {

    /**
     * Filename for serialized object
     */
    private static final transient String FILENAME = "weather.prefs";
    //allows versioning without changing weather.prefs
    private static final long serialVersionUID =  5423608160937997909L;
    //preference fields to be serialized
    private char tempUnit;
    private ArrayList<String> locations;
    private String defaultLocation;

    /**
     * Constructor of user preferences, initializes locations list and sets default values
     */
    public UserPreferences() {
        //intialize empty locations (in the future we will have to prompt user so this will change)
        locations = new ArrayList<>();
        tempUnit = 'c';
        defaultLocation = "";
    }

    /**
     * Sets temperature type to celsius (c) or fahrenheit (f)
     * we can trust that the appropriate units are given because the user has no input for this
     * @param unit char to indicate temperature type, can be either celsius (c) or fahrenheit (f)
     */
    public void setTempUnit(char unit) {
        if(unit == 'c' || unit == 'f') { //maybe a try/catch?
            tempUnit = unit;
        }
    }

    /**
     * Sets speed unit to meters per second (m), kilometers per hour (k), miles per hour (i), knots (n), or feet per second (f)
     * @param unit char to indicate speed unit, either m for m/s, k for km/h, i for mi/h, n for kn, or f for ft/s
     */
    /**
     * Sets default location to the last selected location
     * @param location string representing the new default location
     */
    public void setDefaultLocation(String location){
    	this.defaultLocation = location;
    }
    /**
     * Gets temperature unit
     * @return c for celsius, f for fahrenheit
     */
    public char getTempUnit() {
        return tempUnit;
    }
    /**
     * Gets location list
     * @return an ArrayList<String> of locations
     */
    public ArrayList<String> getLocations() {
        return locations;
    }
    
    /**
     * Gets defaultLocation
     * @return the default location
     */
    public String getDefaultLocation(){
    	return defaultLocation;
    }

    /**
     * Adds a location to the end of the list of favorite locations
     * @param s name of the location
     * Maybe we don't need this one, since we don't add to the end - NK
     */
    public void addLocation(String s) throws WeatherException {
        if(!locations.contains(s)){
            locations.add(s);
            defaultLocation = s;
        }
        else
            throw new WeatherException("Location already exists");
    }

    /**
     * Adds a location to the list of favorite locations in a certain position
     * @param index provides where in the list the element should be added
     * @param s name of the location
     */
     public void addLocation(int index, String s) throws WeatherException {
        if(!locations.contains(s))
            locations.add(index, s);
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
    public static void savePrefs(UserPreferences up) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
            oos.writeObject(up);
            oos.close();
        }
        catch(IOException e) {
            System.out.println("Saving user preferences failed! Due to:");
            e.printStackTrace();
        }
    }

    /**
     * Finds the FILENAME file in the working directory and loaded the serialized object, then returns it.
     * Allows loading of preferences at the start of a program run.
     */
    public static UserPreferences getPrefs() {
        ObjectInputStream ois;
        Object o;
        try {
            ois = new ObjectInputStream(new FileInputStream(FILENAME));
            o = ois.readObject();
        }
        catch(IOException | NullPointerException | ClassNotFoundException e) {
            //need to create a new set of preferences if one doesn't exist
            return new UserPreferences();
        }
        if(o instanceof UserPreferences)
            return (UserPreferences)o;
        else {
            //this block will never be executed
            System.out.println("Something went seriously wrong loading preferences");
            return new UserPreferences();
        }
    }
}
