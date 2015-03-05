package group17.weatherviewer;

import java.util.ArrayList;

public class MyLocationsList {
    /**
     * List of favourite locations
     */
    private ArrayList<String> mylocations;

    /**
     * Constructor
     * We can give this a size if we decide there is a maximum number of locations to be stored
     */
    public MyLocationsList() {
        mylocations = new ArrayList<String>();
    }

    /**
     *
     * @return array of favorite locations
     */
    public ArrayList<String> getLocations() {
        return mylocations;
    }

    /**
     * Gives a favorite location identified by index
     * @param index index of the array (from 0 to size - 1)
     * @return the value of the array in given postision, or blank string if index out of bounds
     */
    public String getLocation(int index) throws WeatherException {

        try {
            return mylocations.get(index);
        }
        catch(ArrayIndexOutOfBoundsException ae) {
            throw new WeatherException("Out-of-bounds error in getLocation(int)", ae);
        }
    }

    /**
     * Adds a location to the list of favorite locations
     * @param s name of the location
     */
    public void addLocation(String s) throws WeatherException {
        if(!mylocations.contains(s))
            mylocations.add(s);
        else
            throw new WeatherException("Location already exists");
    }

    /**
     * Removes a location from the list of favorite locations, if the location is in the list
     * @param s name of the location
     */
    public void removeLocation(String s) throws WeatherException {
        if(mylocations.contains(s))
            mylocations.remove(s);
        else
            throw new WeatherException("Location does not exist");
    }
}
