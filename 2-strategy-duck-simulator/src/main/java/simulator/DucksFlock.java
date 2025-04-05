package simulator;

import java.util.ArrayList;
import java.util.List;

public class DucksFlock {
    private List<Duck> ducks = new ArrayList<>();
    
    public void addDuck(Duck duck) {
        ducks.add(duck);
    }
    
    public void fly() {
        for (Duck duck : ducks) {
            duck.performFly();
        }
    }
    
    public void quack() {
        for (Duck duck : ducks) {
            duck.performQuack();
        }
    }
    
    public int size() {
        return ducks.size();
    }
}