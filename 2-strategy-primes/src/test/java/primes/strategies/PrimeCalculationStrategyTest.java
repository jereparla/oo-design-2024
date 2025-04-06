package primes.strategies;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PrimeCalculationStrategyTest {

    static Stream<PrimeCalculationStrategy> strategyProvider() {
        return Stream.of(
            new NaivePrimeStrategy(),
            new SieveOfEratosthenesStrategy(),
            new MillerRabinStrategy()
        );
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testStrategyWithZeroPrimes(PrimeCalculationStrategy strategy) {
        List<Integer> primes = strategy.calculatePrimes(0);
        
        assertTrue(primes.isEmpty());
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testStrategyWithNegativePrimes(PrimeCalculationStrategy strategy) {
        List<Integer> primes = strategy.calculatePrimes(-5);
        
        assertTrue(primes.isEmpty());
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testStrategyWithFirstPrime(PrimeCalculationStrategy strategy) {
        List<Integer> primes = strategy.calculatePrimes(1);
        
        assertEquals(1, primes.size());
        assertEquals(2, primes.get(0));
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testStrategyWithSmallSetOfPrimes(PrimeCalculationStrategy strategy) {
        List<Integer> primes = strategy.calculatePrimes(5);
        
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11);
        assertEquals(expected, primes);
    }
    
    @ParameterizedTest
    @MethodSource("strategyProvider")
    public void testStrategyWithLargerSetOfPrimes(PrimeCalculationStrategy strategy) {
        List<Integer> primes = strategy.calculatePrimes(10);
        
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        assertEquals(expected, primes);
    }
}