package primes.outputs;

import java.util.List;

public class ConsoleOutputStrategy implements OutputStrategy {
    
    public void output(List<Integer> primes) {
        System.out.println("Prime numbers: " + primes);
    }
}