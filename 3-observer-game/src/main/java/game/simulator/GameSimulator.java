package game.simulator;

import game.characters.*;
import game.characters.Character;
import game.logging.*;
import game.weapons.*;

public class GameSimulator {

    public static void main(String[] args) {
        System.out.println("=== Action Adventure Game Simulator with Observer Pattern ===\n");
        
        ConsoleLogger consoleLogger = new ConsoleLogger();
        FileLogger fileLogger = new FileLogger("battle_log.txt");
        StatisticsLogger statsLogger = new StatisticsLogger();
        
        // First battle with console logger only
        System.out.println("===== BATTLE 1: Knight vs Wizard (Console Logger) =====");
        Battle battle1 = new Battle();
        battle1.registerObserver(consoleLogger);
        
        Character knight = new Knight("Isildur");
        Character wizard = new Wizard("Radagast");
        battle1.fight(knight, wizard);
        
        // Second battle with both console and file loggers
        System.out.println("===== BATTLE 2: Orc vs Knight (Console + File Loggers) =====");
        Battle battle2 = new Battle();
        battle2.registerObserver(consoleLogger);
        battle2.registerObserver(fileLogger);
        
        Character orc = new Orc("Boldog");
        knight = new Knight("Anarion");
        battle2.fight(orc, knight);
        
        // Third battle with all loggers
        System.out.println("===== BATTLE 3: Knight vs Archer (All Loggers) =====");
        Battle battle3 = new Battle();
        battle3.registerObserver(consoleLogger);
        battle3.registerObserver(fileLogger);
        battle3.registerObserver(statsLogger);
        
        knight = new Knight("Elendil");
        Character archer = new Archer("Legolas");
        battle3.fight(knight, archer);
        
        // Fourth battle with dynamically changing loggers
        System.out.println("\n===== BATTLE 4: Wizard vs Orc (Dynamic Loggers) =====");
        Battle battle4 = new Battle();
        battle4.registerObserver(consoleLogger);
        battle4.registerObserver(statsLogger);
        
        wizard = new Wizard("Gandalf");
        orc = new Orc("Azog");
        wizard.setWeapon(new MagicWand()); // Change weapon
        
        // Run the battle
        battle4.fight(wizard, orc);
        
        // Print overall statistics
        statsLogger.printStatistics();
        
        // Close file logger
        fileLogger.close();
    }
}