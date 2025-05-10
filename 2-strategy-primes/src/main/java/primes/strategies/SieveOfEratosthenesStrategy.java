package primes.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenesStrategy implements PrimeCalculationStrategy {
    
    public List<Integer> calculatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        
        if (n <= 0) {
            return primes;
        }
        
        int upperBound = Math.max(n * 20, 100);
        
        boolean[] isPrime = new boolean[upperBound];
        Arrays.fill(isPrime, true);
        
        for (int p = 2; p * p < upperBound; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i < upperBound; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        for (int i = 2; i < upperBound && primes.size() < n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
}