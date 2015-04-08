WeatherViewer
============
WeatherViewer (full documentation [here](https://github.com/UWO-2212-W2015/team17/tree/master/weatherviewer/doc)) is a 
java application for viewing the weather in any city you pick! The application gets the current weather as well as 24-hour
and 7-day forecasts, displaying information such as current and expected high/low temperatures, wind speed and direction, 
air pressure, sunrise/sunset and precipitation. You can also add any location to a favorites list which is saved for 
future uses.

This cross-platform application is being developped in Java using a Swing GUI and Maven build automation. Data persistence
between runs is managed by object serialization and the weather information is gathered from the web service 
[OpenWeatherMap.org](http://openweathermap.org/) in JSON Format. 

Installation and Use
------------
WeatherWiever requires [Maven](http://maven.apache.org/download.cgi) to install and [Java](https://java.com/en/download/manual.jsp)
to run, so make sure you have downloaded and installed the latest stable versions of these programs for your operating
system. To install and run WeatherViewer, download the sources from [GitHub](https://github.com/UWO-2212-W2015/team17), 
unzip everything into a folder, open up a terminal and type:
```
cd my_unzipped_folder/weatherviewer
mvn package
java -jar target/17_weatherviewer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

[Here](https://www.youtube.com/watch?v=wWyd0Rn3mPQ) is an video example of how to use WeatherViewer, enjoy!
