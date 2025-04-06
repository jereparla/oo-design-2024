package game.weapons;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import game.simulator.CombatType;

public class WeaponTest {

    @Test
    public void testFistAttributes() {
        Fist fist = new Fist();
        assertEquals("Fists", fist.getName());
        assertEquals(5, fist.attack());
        assertEquals(CombatType.MELEE, fist.getCombatType());
    }

    @Test
    public void testSword() {
        Sword sword = new Sword();
        assertEquals("Sword", sword.getName());
        assertEquals(25, sword.attack());
        assertEquals(CombatType.MELEE, sword.getCombatType());
    }
    
    @Test
    public void testAxe() {
        Axe axe = new Axe();
        assertEquals("Axe", axe.getName());
        assertEquals(30, axe.attack());
        assertEquals(CombatType.MELEE, axe.getCombatType());
    }
    
    @Test
    public void testLongBow() {
        LongBow bow = new LongBow();
        assertEquals("Long Bow", bow.getName());
        assertEquals(15, bow.attack());
        assertEquals(CombatType.RANGE, bow.getCombatType());
    }
    
    @Test
    public void testSpear() {
        Spear spear = new Spear();
        assertEquals("Spear", spear.getName());
        assertEquals(20, spear.attack());
        assertEquals(CombatType.RANGE, spear.getCombatType());
    }
    
    @Test
    public void testMagicStaff() {
        MagicStaff staff = new MagicStaff();
        assertEquals("Magic Staff", staff.getName());
        assertEquals(20, staff.attack());
        assertEquals(CombatType.MAGIC, staff.getCombatType());
    }
    
    @Test
    public void testMagicWand() {
        MagicWand wand = new MagicWand();
        assertEquals("Magic Wand", wand.getName());
        assertTrue(wand.attack() > 0);
        assertEquals(CombatType.MAGIC, wand.getCombatType());
    }
}