package weatherstation;

public class CelsiusDisplay implements Observer, DisplayElement {
    private float temperature;
    private float celsius;
    private WeatherData weatherData;
    
    public CelsiusDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.celsius = convertFahrenheitToCelsius(temperature);
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