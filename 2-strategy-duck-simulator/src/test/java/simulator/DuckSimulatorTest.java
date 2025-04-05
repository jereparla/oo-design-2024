package simulator;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuckSimulatorTest {
    
    @Test
    public void testMallardDuckChangeBehavior() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Duck mallard = new MallardDuck();
        
        System.out.println("=== Mallard Duck Test ===");
        mallard.performQuack();
        mallard.performFly();
        
        String initialOutput = outContent.toString();
        assertTrue(initialOutput.contains("Quack"));
        assertTrue(initialOutput.contains("I'm flying!!"));
        
        outContent.reset();
        
        mallard.setQuackBehavior(new MuteQuack());
        mallard.setFlyBehavior(new FlySlowly());
        
        mallard.performQuack();
        mallard.performFly();
        
        String changedOutput = outContent.toString();
        assertTrue(changedOutput.contains("Duck Silence"));
        assertTrue(changedOutput.contains("I'm flying slowly"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testModelDuckChangeBehavior() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Duck model = new ModelDuck();
        
        model.performQuack();
        model.performFly();
        
        String initialOutput = outContent.toString();
        assertTrue(initialOutput.contains("Quack"));
        assertTrue(initialOutput.contains("I can't fly"));
        
        outContent.reset();
        
        model.setFlyBehavior(new FlyRocketPowered());
        model.setQuackBehavior(new Squeak());
        
        model.performFly();
        model.performQuack();
        
        String changedOutput = outContent.toString();
        assertTrue(changedOutput.contains("I'm flying with a rocket"));
        assertTrue(changedOutput.contains("Squeak"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testRubberDuckChangeBehavior() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Duck rubber = new RubberDuck();
        
        rubber.performQuack();
        rubber.performFly();
        
        String initialOutput = outContent.toString();
        assertTrue(initialOutput.contains("Squeak"));
        assertTrue(initialOutput.contains("I can't fly"));
        
        outContent.reset();
        
        rubber.setQuackBehavior(new Quack());
        rubber.setFlyBehavior(new FlyWithWings());
        
        rubber.performQuack();
        rubber.performFly();
        
        String changedOutput = outContent.toString();
        assertTrue(changedOutput.contains("Quack"));
        assertTrue(changedOutput.contains("I'm flying!!"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testMixAndMatchBehaviors() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Duck mallard = new MallardDuck();
        Duck model = new ModelDuck();
        Duck rubber = new RubberDuck();
        
        FlyBehavior rocketPowered = new FlyRocketPowered();
        QuackBehavior mute = new MuteQuack();
        
        mallard.setFlyBehavior(rocketPowered);
        model.setQuackBehavior(mute);
        rubber.setFlyBehavior(rocketPowered);
        
        mallard.performFly();
        String mallardOutput = outContent.toString();
        assertTrue(mallardOutput.contains("I'm flying with a rocket"));
        
        outContent.reset();
        
        model.performQuack();
        String modelOutput = outContent.toString();
        assertTrue(modelOutput.contains("Duck Silence"));
        
        outContent.reset();
        
        rubber.performFly();
        String rubberOutput = outContent.toString();
        assertTrue(rubberOutput.contains("I'm flying with a rocket"));
        
        System.setOut(System.out);
    }
}