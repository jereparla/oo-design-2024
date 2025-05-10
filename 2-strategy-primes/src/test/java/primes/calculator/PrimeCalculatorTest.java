package primes.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import primes.outputs.ConsoleOutputStrategy;
import primes.outputs.FileOutputStrategy;
import primes.outputs.OutputStrategy;
import primes.strategies.MillerRabinStrategy;
import primes.strategies.NaivePrimeStrategy;
import java.nio.file.Path;
import java.nio.file.Files;
import primes.strategies.PrimeCalculationStrategy;
import primes.strategies.SieveOfEratosthenesStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeCalculatorTest {

    @TempDir
    Path tempDir;

    static Stream<PrimeCalculationStrategy> strategyProvider() {
        return Stream.of(
            new NaivePrimeStrategy(),
            new SieveOfEratosthenesStrategy(),
            new MillerRabinStrategy()
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

    @Test
    public void testCalculateFirst2PrimesDifferentOutput() throws Exception {
        //first output strategy
        File testFile = tempDir.resolve("test_primes.txt").toFile();
        PrimeCalculator calculator = new PrimeCalculator(new NaivePrimeStrategy(), new FileOutputStrategy(testFile.getAbsolutePath()));
        calculator.printPrimes(2);
        
        assertTrue(testFile.exists());
        String content = new String(Files.readAllBytes(testFile.toPath()));
        
        assertTrue(content.contains("Prime numbers:"));
        assertTrue(content.contains("2 3"));

        //second output strategy
        calculator.setOutputStrategy(new ConsoleOutputStrategy());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.printPrimes(2);
        try {
            String output = outContent.toString();
            assertTrue(output.contains("Prime numbers:"));
            assertTrue(output.contains("[2, 3]"));
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    public void testCalculateFirst2PrimesDifferentStrategy() throws Exception {
        //first calculation strategy
        File testFile = tempDir.resolve("test_primes.txt").toFile();
        PrimeCalculator calculator = new PrimeCalculator(new NaivePrimeStrategy(), new FileOutputStrategy(testFile.getAbsolutePath()));
        calculator.printPrimes(2);
        
        assertTrue(testFile.exists());
        String content = new String(Files.readAllBytes(testFile.toPath()));
        
        assertTrue(content.contains("Prime numbers:"));
        assertTrue(content.contains("2 3"));

        //second calculation strategy
        calculator.setCalculationStrategy(new SieveOfEratosthenesStrategy());
        calculator.printPrimes(2);
        assertTrue(content.contains("Prime numbers:"));
        assertTrue(content.contains("2 3"));
    }
}