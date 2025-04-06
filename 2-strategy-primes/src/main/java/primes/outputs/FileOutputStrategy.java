package primes.outputs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileOutputStrategy implements OutputStrategy {
    
    private String filename;
    
    public FileOutputStrategy(String filename) {
        this.filename = filename;
    }
    
    public void output(List<Integer> primes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Prime numbers:");
            for (Integer prime : primes) {
                writer.print(prime + " ");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}