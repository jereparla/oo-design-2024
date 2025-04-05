package simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DucksFlockTest {

    private DucksFlock flock;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    
    @BeforeEach
    public void setUp() {
        flock = new DucksFlock();
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    public void testEmptyFlock() {
        assertEquals(0, flock.size());
        flock.quack();
        flock.fly();
        assertEquals("", outContent.toString());
    }
    
    @Test
    public void testFlockWithOneDuck() {
        Duck mallard = new MallardDuck();
        flock.addDuck(mallard);
        
        assertEquals(1, flock.size());
        
        flock.quack();
        assertTrue(outContent.toString().contains("Quack"));
        
        outContent.reset();
        
        flock.fly();
        assertTrue(outContent.toString().contains("I'm flying!!"));
    }
    
    @Test
    public void testFlockWithMultipleDuckTypes() {
        flock.addDuck(new MallardDuck());
        flock.addDuck(new ModelDuck());
        flock.addDuck(new RubberDuck());
        
        assertEquals(3, flock.size());
        
        flock.quack();
        String quackOutput = outContent.toString();
        assertTrue(quackOutput.contains("Quack"));
        assertTrue(quackOutput.contains("Squeak"));
        
        outContent.reset();
        
        flock.fly();
        String flyOutput = outContent.toString();
        assertTrue(flyOutput.contains("I'm flying!!"));
        assertTrue(flyOutput.contains("I can't fly"));
    }
    
    @Test
    public void testChangingBehaviorsInFlock() {
        Duck mallard = new MallardDuck();
        Duck model = new ModelDuck();
        
        flock.addDuck(mallard);
        flock.addDuck(model);
        
        mallard.setQuackBehavior(new MuteQuack());
        model.setFlyBehavior(new FlyRocketPowered());
        
        flock.quack();
        String quackOutput = outContent.toString();
        assertTrue(quackOutput.contains("Duck Silence"));
        assertTrue(quackOutput.contains("Quack"));
        
        outContent.reset();
        
        flock.fly();
        String flyOutput = outContent.toString();
        assertTrue(flyOutput.contains("I'm flying!!"));
        assertTrue(flyOutput.contains("I'm flying with a rocket"));
    }
    
    @Test
    public void testAddingDucksAfterFlockCreation() {
        flock.addDuck(new MallardDuck());
        
        flock.quack();
        String firstQuack = outContent.toString();
        assertTrue(firstQuack.contains("Quack"));
        assertEquals(1, flock.size());
        
        outContent.reset();
        
        flock.addDuck(new RubberDuck());
        flock.quack();
        String secondQuack = outContent.toString();
        assertTrue(secondQuack.contains("Squeak"));
        assertEquals(2, flock.size());
    }
}