package game.logging;

import game.characters.Character;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements BattleObserver {
    private String filePath;
    private PrintWriter writer;
    
    public FileLogger(String filePath) {
        this.filePath = filePath;
        try {
            this.writer = new PrintWriter(new FileWriter(filePath, true));
        } catch (IOException e) {
            System.err.println("Error opening log file: " + e.getMessage());
        }
    }
    
    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public void onBattleStart(Character fighter1, Character fighter2) {
        writer.println(timestamp() + " - Battle started between " + fighter1.getName() + 
                      " (" + fighter1.getClass().getSimpleName() + ") and " + 
                      fighter2.getName() + " (" + fighter2.getClass().getSimpleName() + ")");
        writer.flush();
    }
    
    public void onAttack(Character attacker, Character defender) {
        writer.println(timestamp() + " - " + attacker.getName() + 
                      " (" + attacker.getClass().getSimpleName() + ") attacked " + 
                      defender.getName() + " (" + defender.getClass().getSimpleName() + ") with " + 
                      attacker.getWeapon().getName() + " for " + attacker.attack() + 
                      " damage. " + defender.getName() + " health: " + defender.getHealth());
        writer.flush();
    }
    
    public void onCharacterDeath(Character character) {
        writer.println(timestamp() + " - " + character.getName() + " has died!");
        writer.flush();
    }
    
    public void onBattleEnd(Character winner, Character loser) {
        writer.println(timestamp() + " - Battle ended. " + winner.getName() + 
                      " (" + winner.getClass().getSimpleName() + ") defeated " + 
                      loser.getName() + " (" + loser.getClass().getSimpleName() + ")");
        writer.println("-------------------------------------------");
        writer.flush();
    }
    
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}