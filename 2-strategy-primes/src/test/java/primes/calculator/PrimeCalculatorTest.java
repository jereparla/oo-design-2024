package primes.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import primes.outputs.ConsoleOutputStrategy;
import primes.outputs.FileOutputStrategy;
import primes.strategies.NaivePrimeStrategy;
import primes.strategies.PrimeCalculationStrategy;
import primes.strategies.SieveOfEratosthenesStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeCalculatorTest {

    static Stream<PrimeCalculationStrategy> strategyProvider() {
        return Stream.of(
            new NaivePrimeStrategy(),
            new SieveOfEratosthenesStrategy()
        );
    }

    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateFirstFivePrimesConsoleOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new ConsoleOutputStrategy());
        List<Integer> primes = calculator.calculatePrimes(5);
        
        assertEquals(5, primes.size());
        assertEquals(2, primes.get(0));
        assertEquals(3, primes.get(1));
        assertEquals(5, primes.get(2));
        assertEquals(7, primes.get(3));
        assertEquals(11, primes.get(4));
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateFirstFivePrimesFileOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new FileOutputStrategy("primes.txt"));
        List<Integer> primes = calculator.calculatePrimes(5);
        
        assertEquals(5, primes.size());
        assertEquals(2, primes.get(0));
        assertEquals(3, primes.get(1));
        assertEquals(5, primes.get(2));
        assertEquals(7, primes.get(3));
        assertEquals(11, primes.get(4));
    }

    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateZeroPrimesConsoleOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new ConsoleOutputStrategy());
        List<Integer> primes = calculator.calculatePrimes(0);
        
        assertTrue(primes.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateZeroPrimesFileOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new FileOutputStrategy("primes.txt"));
        List<Integer> primes = calculator.calculatePrimes(0);
        
        assertTrue(primes.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateFirstTenPrimesConsoleOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new ConsoleOutputStrategy());
        List<Integer> primes = calculator.calculatePrimes(10);
        
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        assertEquals(expected, primes);
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testCalculateFirstTenPrimesFileOutput(PrimeCalculationStrategy strategy) {
        PrimeCalculator calculator = new PrimeCalculator(strategy, new FileOutputStrategy("primes.txt"));
        List<Integer> primes = calculator.calculatePrimes(10);
        
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        assertEquals(expected, primes);
    }
}