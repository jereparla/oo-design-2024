package weatherstationmodified;

public class CelsiusDisplay implements Observer, DisplayElement {
    private float temperature;
    private float celsius;
    private WeatherData weatherData;
    
    public CelsiusDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    public void update() {
        this.celsius = convertFahrenheitToCelsius(this.weatherData.getTemperature());
        display();
    }
    
    public void display() {
        System.out.println("Current temperature in Celsius: " + celsius + "°C");
    }
    
    private float convertFahrenheitToCelsius(float fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }
    
    // Useful for testing
    public float getCelsius() {
        return celsius;
    }
}