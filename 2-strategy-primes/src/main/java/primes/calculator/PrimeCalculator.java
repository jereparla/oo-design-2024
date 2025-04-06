package primes.calculator;

import primes.strategies.NaivePrimeStrategy;
import primes.strategies.PrimeCalculationStrategy;
import primes.outputs.ConsoleOutputStrategy;
import primes.outputs.OutputStrategy;
import java.util.List;

public class PrimeCalculator {
    
    private PrimeCalculationStrategy calcStrategy;
    private OutputStrategy outputStrategy;
    
    public PrimeCalculator() {
        calcStrategy = new NaivePrimeStrategy();
        outputStrategy = new ConsoleOutputStrategy();
    }
    
    public PrimeCalculator(PrimeCalculationStrategy calcStrategy, OutputStrategy outputStrategy) {
        this.calcStrategy = calcStrategy;
        this.outputStrategy = outputStrategy;
    }

    public void setCalculationStrategy(PrimeCalculationStrategy strategy) {
        this.calcStrategy = strategy;
    }
    
    public void setOutputStrategy(OutputStrategy strategy) {
        this.outputStrategy = strategy;
    }
    
    public List<Integer> calculatePrimes(int n) {
        return calcStrategy.calculatePrimes(n);
    }
    
    public void printPrimes(int n) {
        List<Integer> primes = calculatePrimes(n);
        outputStrategy.output(primes);
    }
}