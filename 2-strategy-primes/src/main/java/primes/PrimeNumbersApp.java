package primes;

import primes.strategies.NaivePrimeStrategy;
import primes.strategies.SieveOfEratosthenesStrategy;
import primes.calculator.PrimeCalculator;
import primes.outputs.FileOutputStrategy;

public class PrimeNumbersApp {
    
    public static void main(String[] args) {
        // Default calculator with basic algorithm and console output
        PrimeCalculator calculator = new PrimeCalculator();
        
        System.out.println("Calculating first 10 primes with basic algorithm:");
        calculator.printPrimes(10);
        
        // Change to Sieve of Eratosthenes algorithm
        calculator.setCalculationStrategy(new SieveOfEratosthenesStrategy());
        
        System.out.println("\nCalculating first 10 primes with Sieve of Eratosthenes:");
        calculator.printPrimes(10);
        
        // Change output to file
        calculator.setOutputStrategy(new FileOutputStrategy("primes.txt"));
        System.out.println("\nSaving first 20 primes to file 'primes.txt'...");
        calculator.printPrimes(20);
        
        PrimeCalculator customCalculator = new PrimeCalculator(
            new NaivePrimeStrategy(),
            new FileOutputStrategy("more_primes.txt")
        );
        
        System.out.println("Saving first 50 primes to file 'more_primes.txt'...");
        customCalculator.printPrimes(50);
    }
}