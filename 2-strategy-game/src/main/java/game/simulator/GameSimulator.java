package game.simulator;

import game.characters.Archer;
import game.characters.Character;
import game.characters.Knight;
import game.characters.Orc;
import game.characters.Wizard;
import game.weapons.Axe;
import game.weapons.MagicStaff;
import game.weapons.MagicWand;
import game.weapons.Spear;

public class GameSimulator {
    public static void main(String[] args) {
        System.out.println("=== Action Adventure Game Simulator ===\n");
        
        Character knight = new Knight("Aragorn");
        Character wizard = new Wizard("Radagast");
        Character orc = new Orc("Count Grishnack");
        Character archer = new Archer("Legolas");
        
        System.out.println("Initial characters:");
        System.out.println(knight.display());
        System.out.println(wizard.display());
        System.out.println(orc.display());
        System.out.println();
        
        // Change of weapons
        System.out.println("Knight changes weapon to Axe:");
        knight.setWeapon(new Axe());
        System.out.println(knight.display());
        System.out.println();
        
        System.out.println("===== BATTLE 1: Knight vs Wizard =====");
        knight = new Knight("Isildur");
        String battleResult1 = Battle.fight(knight, wizard);
        System.out.println(battleResult1);
        System.out.println();
        
        System.out.println("===== BATTLE 2: Orc vs Knight =====");
        knight = new Knight("Anarion");
        orc = new Orc("Boldog");
        String battleResult2 = Battle.fight(orc, knight);
        System.out.println(battleResult2);
        System.out.println();
        
        System.out.println("===== BATTLE 3: Orc vs Archer =====");
        orc = new Orc("Baldur");
        String battleResult3 = Battle.fight(orc, archer);
        System.out.println(battleResult3);
        System.out.println();

        // Show combat type restrictions
        System.out.println("===== COMBAT TYPE RESTRICTIONS DEMO =====");
        knight = new Knight("Anardil");
        try {
            System.out.println("Trying to give knight a magic staff...");
            knight.setWeapon(new MagicStaff());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            System.out.println("\nTrying to give wizard an axe...");
            wizard.setWeapon(new Axe());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Show compatible weapon change
        System.out.println("\nChanging wizard weapon to wand (compatible):");
        wizard.setWeapon(new MagicWand());
        System.out.println(wizard.display());

        System.out.println("\nChanging archer weapon to spear (compatible):");
        archer.setWeapon(new Spear());
        System.out.println(archer.display());
    }
}