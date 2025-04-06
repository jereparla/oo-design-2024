package primes.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MillerRabinStrategy implements PrimeCalculationStrategy {
    // Random number generator for Miller-Rabin test
    private static final Random random = new Random();
    
    /**
     * Modular exponentiation to calculate (base^exponent) % modulus efficiently
     */
    private static long modularPow(long base, long exponent, long modulus) {
        long result = 1;
        base %= modulus;
        
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent >>= 1;
        }
        
        return result;
    }
    
    /**
     * Miller-Rabin primality test
     * Probabilistic primality test with high accuracy
     * @param n number to test
     * @param k number of iterations (witnesses)
     * @return true if probably prime, false if composite
     */
    private static boolean millerRabinTest(long n, int k) {
        // Handle edge cases
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        
        // Write n as 2^r * d + 1
        long d = n - 1;
        int r = 0;
        while (d % 2 == 0) {
            d /= 2;
            r++;
        }
        
        // Witness loop
        for (int i = 0; i < k; i++) {
            // Choose a random witness between 2 and n-2
            long a = 2 + random.nextInt((int)(n - 4)) + 1;
            
            // Compute x = a^d mod n
            long x = modularPow(a, d, n);
            
            // If x is 1 or n-1, continue to next iteration
            if (x == 1 || x == n - 1) continue;
            
            // Square x r-1 times
            boolean isProbablyPrime = false;
            for (int j = 0; j < r - 1; j++) {
                x = (x * x) % n;
                
                if (x == n - 1) {
                    isProbablyPrime = true;
                    break;
                }
            }
            
            // If we didn't find n-1, n is composite
            if (!isProbablyPrime) return false;
        }
        
        return true;
    }
    
    /**
     * Calculate the first n prime numbers
     * @param n number of primes to generate
     * @return List of first n prime numbers
     */
    public List<Integer> calculatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        
        // Start checking from 2
        int candidate = 2;
        
        while (primes.size() < n) {
            // Use Miller-Rabin test with 5 iterations for high confidence
            if (millerRabinTest(candidate, 5)) {
                primes.add(candidate);
            }
            candidate++;
        }
        
        return primes;
    }
}