package weatherstationmodified;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CelsiusDisplayTest {

    @Test
    public void testCelsiusDisplay() {
        WeatherData weatherData = new WeatherData();
        CelsiusDisplay celsiusDisplay = new CelsiusDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        assertEquals(26.7f, celsiusDisplay.getCelsius(), 0.1f);

        weatherData.setMeasurements(32, 70, 29.2f);
        assertEquals(0.0f, celsiusDisplay.getCelsius(), 0.1f);
    }
    
}
