package weatherstation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FeelsLikeDisplayTest {

    @Test
    public void testHeatIndexDisplay() {
        WeatherData weatherData = new WeatherData();
        FeelsLikeDisplay feelsLikeDisplay = new FeelsLikeDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        assertEquals(82.95535f,feelsLikeDisplay.getHeatIndex());
        
        weatherData.setMeasurements(82, 20, 29.2f);
        assertEquals(78.5015f, feelsLikeDisplay.getHeatIndex());
    }
}
