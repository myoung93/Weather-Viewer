package group17.weatherviewer;

import java.io.*;

public class UserPreferences implements java.io.Serializable {

    private static final transient String FILENAME = "weather_prefs.prefs";

    private boolean isCelsius = true;               //celsius is the default unit

    //set Temperature type to c or f
    public void setCelsius (boolean b) {
        isCelsius = b;
    }

    public char getTempUnits() {
        if(isCelsius)   return 'c';
        else            return 'f';
    }

    public static void savePrefs(UserPreferences up) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(up);
        oos.close();
    }

    public static UserPreferences getPrefs() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));
        Object o = ois.readObject();
        ois.close();
        return (UserPreferences)o;
    }
}
