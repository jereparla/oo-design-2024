package primes.outputs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class OutputStrategyTest {

    @TempDir
    Path tempDir;

    @Test
    public void testConsoleOutputEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        try {
            OutputStrategy strategy = new ConsoleOutputStrategy();
            List<Integer> emptyList = Arrays.asList();
            
            strategy.output(emptyList);
            
            String output = outContent.toString();
            assertTrue(output.contains("Prime numbers:"));
            assertTrue(output.contains("[]"));
        } finally {
            System.setOut(System.out);
        }
    }
    
    @Test
    public void testConsoleOutputWithPrimes() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        try {
            OutputStrategy strategy = new ConsoleOutputStrategy();
            List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11);
            
            strategy.output(primes);
            
            String output = outContent.toString();
            assertTrue(output.contains("Prime numbers:"));
            assertTrue(output.contains("[2, 3, 5, 7, 11]"));
        } finally {
            System.setOut(System.out);
        }
    }
    
    @Test
    public void testFileOutputEmpty() throws Exception {
        File testFile = tempDir.resolve("empty_primes.txt").toFile();
        OutputStrategy strategy = new FileOutputStrategy(testFile.getAbsolutePath());
        List<Integer> emptyList = Arrays.asList();
        
        strategy.output(emptyList);
        
        assertTrue(testFile.exists());
        String content = new String(Files.readAllBytes(testFile.toPath()));
        
        assertTrue(content.contains("Prime numbers:"));
        assertEquals("Prime numbers:\n", content);
    }
    
    @Test
    public void testFileOutputWithPrimes() throws Exception {
        File testFile = tempDir.resolve("test_primes.txt").toFile();
        OutputStrategy strategy = new FileOutputStrategy(testFile.getAbsolutePath());
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11);
        
        strategy.output(primes);
        
        assertTrue(testFile.exists());
        String content = new String(Files.readAllBytes(testFile.toPath()));
        
        assertTrue(content.contains("Prime numbers:"));
        assertTrue(content.contains("2 3 5 7 11"));
    }
    
    @Test
    public void testFileOutputOverwrite() throws Exception {
        File testFile = tempDir.resolve("overwrite_primes.txt").toFile();
        OutputStrategy strategy = new FileOutputStrategy(testFile.getAbsolutePath());
        
        List<Integer> firstSet = Arrays.asList(2, 3, 5);
        strategy.output(firstSet);
        
        List<Integer> secondSet = Arrays.asList(7, 11, 13);
        strategy.output(secondSet);
        
        String content = new String(Files.readAllBytes(testFile.toPath()));
        
        assertTrue(content.contains("7 11 13"));
        assertFalse(content.contains("2 3 5"));
    }
}