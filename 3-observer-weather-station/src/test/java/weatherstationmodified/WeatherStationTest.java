package weatherstationmodified;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WeatherStationTest {

    @Test
    public void testMultipleDisplaysAndRuntimeChanges() {
        WeatherData weatherData = new WeatherData();
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        CelsiusDisplay celsiusDisplay = new CelsiusDisplay(weatherData);
        
        weatherData.setMeasurements(80, 65, 30.4f);
        
        assertEquals(80.0f, currentDisplay.getTemperature());
        assertEquals(65.0f, currentDisplay.getHumidity());
        assertEquals(80.0f, statsDisplay.getMaxTemp());
        assertEquals(80.0f, statsDisplay.getMinTemp());
        assertEquals(30.4f, forecastDisplay.getCurrentPressure());
        assertEquals(26.666666f, celsiusDisplay.getCelsius());
        
        weatherData.removeObserver(forecastDisplay);
        weatherData.setMeasurements(82, 70, 29.2f);
        
        assertEquals(82.0f, currentDisplay.getTemperature());
        assertEquals(70.0f, currentDisplay.getHumidity());
        assertEquals(82.0f, statsDisplay.getMaxTemp());
        assertEquals(80.0f, statsDisplay.getMinTemp());
        assertEquals(27.777779f, celsiusDisplay.getCelsius());
        
        // This is the old pressure value, as we've removed this display before we updated
        // the measurements
        assertEquals(30.4f, forecastDisplay.getCurrentPressure());
        FeelsLikeDisplay feelsLikeDisplay = new FeelsLikeDisplay(weatherData);
        
        weatherData.setMeasurements(78, 90, 28.1f);
        
        assertEquals(78.0f, currentDisplay.getTemperature());
        assertEquals(90.0f, currentDisplay.getHumidity());
        assertEquals(82.0f, statsDisplay.getMaxTemp());
        assertEquals(78.0f, statsDisplay.getMinTemp());
        assertEquals(25.555555f, celsiusDisplay.getCelsius());
        assertEquals(83.64967f, feelsLikeDisplay.getHeatIndex());
        
        assertEquals(30.4f, forecastDisplay.getCurrentPressure(), 0.01f);
        
        weatherData.registerObserver(forecastDisplay);
        
        weatherData.setMeasurements(75, 60, 31.2f);
        
        assertEquals(75.0f, currentDisplay.getTemperature(), 0.01f);
        assertEquals(60.0f, currentDisplay.getHumidity(), 0.01f);
        assertEquals(82.0f, statsDisplay.getMaxTemp(), 0.01f);
        assertEquals(75.0f, statsDisplay.getMinTemp(), 0.01f);
        assertEquals(31.2f, forecastDisplay.getCurrentPressure(), 0.01f);
        assertEquals(23.89f, celsiusDisplay.getCelsius(), 0.1f);
        assertEquals(77.702194f, feelsLikeDisplay.getHeatIndex());
    }
}