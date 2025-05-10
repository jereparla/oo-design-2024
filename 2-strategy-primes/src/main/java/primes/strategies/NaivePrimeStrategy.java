package primes.strategies;

import java.util.ArrayList;
import java.util.List;

public class NaivePrimeStrategy implements PrimeCalculationStrategy {
    
    public List<Integer> calculatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        
        if (n <= 0) {
            return primes;
        }
        
        int number = 2;
        while (primes.size() < n) {
            if (isPrime(number)) {
                primes.add(number);
            }
            number++;
        }
        
        return primes;
    }
    
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        
        if (number <= 3) {
            return true;
        }
        
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        
        return true;
    }
}