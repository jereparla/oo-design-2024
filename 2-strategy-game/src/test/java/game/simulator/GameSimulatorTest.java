package game.simulator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.characters.*;
import game.weapons.*;

public class GameSimulatorTest {

    @Test
    public void testGameComponents() {
        Knight knight = new Knight("TestKnight");
        Wizard wizard = new Wizard("TestWizard");
        
        assertEquals(CombatType.MELEE, knight.getCombatType());
        assertEquals(CombatType.MAGIC, wizard.getCombatType());
        
        assertThrows(IllegalArgumentException.class, () -> {
            knight.setWeapon(new Spear());
        });
        
        String battleResult = Battle.fight(knight, wizard);
        assertTrue(battleResult.contains("BATTLE BEGINS"));
        assertTrue(battleResult.contains("BATTLE ENDS"));
        
        assertTrue(knight.isAlive() || wizard.isAlive());
        assertFalse(knight.isAlive() && wizard.isAlive());
    }
    
    @Test
    public void testCompleteGameFlow() {
        Knight knight = new Knight("Aragorn");
        Orc orc = new Orc("Baldug");
        
        assertEquals(100, knight.getHealth());
        assertEquals(100, orc.getHealth());
        
        knight.setWeapon(new Axe());
        assertTrue(knight.getWeapon() instanceof Axe);
        
        String result = Battle.fight(knight, orc);
        
        assertFalse(knight.isAlive() && orc.isAlive());
        
        if (knight.isAlive()) {
            assertTrue(result.contains("Aragorn defeats Baldug"));
        } else {
            assertTrue(result.contains("Baldug defeats Aragorn"));
        }
    }
}