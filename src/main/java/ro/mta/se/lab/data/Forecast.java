package ro.mta.se.lab.data;

public class Forecast {
    private double temperature;
    private double windSpeed;
    private String weatherDescription;
    private double humidity;
    private double pressure;
    private double precipitations;

    public Forecast(double temperature, double windSpeed, String weatherDescription, double humidity, double pressure, double precipitations) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.weatherDescription = weatherDescription;
        this.humidity = humidity;
        this.pressure = pressure;
        this.precipitations = precipitations;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getPrecipitations() {
        return precipitations;
    }
}
