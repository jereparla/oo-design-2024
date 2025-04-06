package game.characters;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import game.simulator.CombatType;
import game.weapons.*;

public class CharacterTest {

    @Test
    public void testKnight() {
        Knight knight = new Knight("Aragorn");
        assertEquals("Aragorn", knight.getName());
        assertEquals(100, knight.getHealth());
        assertTrue(knight.getWeapon() instanceof Sword);
        assertEquals(CombatType.MELEE, knight.getCombatType());
    }
    
    @Test
    public void testWizard() {
        Wizard wizard = new Wizard("Radagast");
        assertEquals("Radagast", wizard.getName());
        assertEquals(100, wizard.getHealth());
        assertTrue(wizard.getWeapon() instanceof MagicStaff);
        assertEquals(CombatType.MAGIC, wizard.getCombatType());
    }
    
    @Test
    public void testOrc() {
        Orc orc = new Orc("Grishnack");
        assertEquals("Grishnack", orc.getName());
        assertEquals(100, orc.getHealth());
        assertTrue(orc.getWeapon() instanceof Axe);
        assertEquals(CombatType.MELEE, orc.getCombatType());
    }
    
    @Test
    public void testArcher() {
        Archer archer = new Archer("Legolas");
        assertEquals("Legolas", archer.getName());
        assertEquals(100, archer.getHealth());
        assertTrue(archer.getWeapon() instanceof LongBow);
        assertEquals(CombatType.RANGE, archer.getCombatType());
    }
    
    @Test
    public void testReceiveAttack() {
        Character knight = new Knight("Aragorn");
        assertEquals(100, knight.getHealth());
        
        knight.receiveAttack(30);
        assertEquals(70, knight.getHealth());
        
        knight.receiveAttack(80);
        assertEquals(0, knight.getHealth());
    }
    
    @Test
    public void testIsAlive() {
        Character knight = new Knight("Isildur");
        assertTrue(knight.isAlive());
        
        knight.receiveAttack(50);
        assertTrue(knight.isAlive());
        
        knight.receiveAttack(50);
        assertFalse(knight.isAlive());
    }
    
    @Test
    public void testWeaponChange() {
        Knight knight = new Knight("Elendil");
        assertTrue(knight.getWeapon() instanceof Sword);
        
        knight.setWeapon(new Axe());
        assertTrue(knight.getWeapon() instanceof Axe);
    }
    
    @Test
    public void testWeaponRestrictionsValidChange() {
        Knight knight = new Knight("Aragorn");
        Wizard wizard = new Wizard("Saruman");
        
        assertDoesNotThrow(() -> knight.setWeapon(new Axe()));
        assertDoesNotThrow(() -> wizard.setWeapon(new MagicWand()));
        
        // Invalid weapon changes
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            knight.setWeapon(new MagicStaff());
        });
        assertTrue(exception1.getMessage().contains("cannot use MAGIC weapons"));
        
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            wizard.setWeapon(new Sword());
        });
        assertTrue(exception2.getMessage().contains("cannot use MELEE weapons"));
    }
    
    @Test
    public void testWeaponRestrictionsInvalidChange() {
        Knight knight = new Knight("Aragorn");
        Wizard wizard = new Wizard("Saruman");
        
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            knight.setWeapon(new MagicStaff());
        });
        assertTrue(exception1.getMessage().contains("cannot use MAGIC weapons"));
        
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            wizard.setWeapon(new Sword());
        });
        assertTrue(exception2.getMessage().contains("cannot use MELEE weapons"));
    }

    @Test
    public void testDisplayMethod() {
        Knight knight = new Knight("Isildur");
        String display = knight.display();
        
        assertTrue(display.contains("Isildur"));
        assertTrue(display.contains("knight"));
        assertTrue(display.contains("Sword"));
    }
}