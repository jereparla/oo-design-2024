package game.logging;

import game.characters.Character;
import java.util.HashMap;
import java.util.Map;

public class StatisticsLogger implements BattleObserver {
    private Map<String, Integer> victoriesByCharacter = new HashMap<>();
    private Map<String, Integer> victoriesByCharacterType = new HashMap<>();
    private Map<String, Integer> totalDamageByCharacter = new HashMap<>();
    private Map<String, Integer> attacksByCharacter = new HashMap<>();
    private Map<String, Integer> victoriesByCharacterWeaponCombo = new HashMap<>();
    
    public void onBattleStart(Character fighter1, Character fighter2) {
        // No statistics to track at battle start
    }
    
    public void onAttack(Character attacker, Character defender) {
        String attackerName = attacker.getName();
        int damage = attacker.attack();
        
        // Track damage dealt
        totalDamageByCharacter.put(
            attackerName, 
            totalDamageByCharacter.getOrDefault(attackerName, 0) + damage
        );
        
        // Track number of attacks
        attacksByCharacter.put(
            attackerName, 
            attacksByCharacter.getOrDefault(attackerName, 0) + 1
        );
    }
    
    public void onCharacterDeath(Character character) {
        // No statistics to track on death
    }
    
    public void onBattleEnd(Character winner, Character loser) {
        String winnerName = winner.getName();
        String winnerType = winner.getClass().getSimpleName();
        String comboKey = winnerType + "+" + winner.getWeapon().getName();
        
        // Track individual character victories
        victoriesByCharacter.put(
            winnerName, 
            victoriesByCharacter.getOrDefault(winnerName, 0) + 1
        );
        
        // Track character type victories
        victoriesByCharacterType.put(
            winnerType,
            victoriesByCharacterType.getOrDefault(winnerType, 0) + 1
        );
        
        // Track character-weapon combination victories
        victoriesByCharacterWeaponCombo.put(
            comboKey,
            victoriesByCharacterWeaponCombo.getOrDefault(comboKey, 0) + 1
        );
    }
    
    public int getVictoriesForCharacter(String characterName) {
        return victoriesByCharacter.getOrDefault(characterName, 0);
    }
    
    public int getVictoriesForCharacterType(String characterType) {
        return victoriesByCharacterType.getOrDefault(characterType, 0);
    }
    
    public int getTotalDamageByCharacter(String characterName) {
        return totalDamageByCharacter.getOrDefault(characterName, 0);
    }
    
    public int getAttacksByCharacter(String characterName) {
        return attacksByCharacter.getOrDefault(characterName, 0);
    }
    
    public int getVictoriesForCombination(String characterType, String weaponName) {
        String key = characterType + "+" + weaponName;
        return victoriesByCharacterWeaponCombo.getOrDefault(key, 0);
    }
    
    public void printStatistics() {
        System.out.println("\n===== BATTLE STATISTICS =====");
        
        System.out.println("\nVictories by Character:");
        for (Map.Entry<String, Integer> entry : victoriesByCharacter.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " wins");
        }
        
        System.out.println("\nVictories by Character Type:");
        for (Map.Entry<String, Integer> entry : victoriesByCharacterType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " wins");
        }
        
        System.out.println("\nTotal Damage by Character:");
        for (Map.Entry<String, Integer> entry : totalDamageByCharacter.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " damage");
        }
        
        System.out.println("\nAttacks by Character:");
        for (Map.Entry<String, Integer> entry : attacksByCharacter.entrySet()) {
            int attacks = entry.getValue();
            int damage = totalDamageByCharacter.getOrDefault(entry.getKey(), 0);
            double avgDamage = attacks > 0 ? (double) damage / attacks : 0;
            System.out.println(entry.getKey() + ": " + attacks + " attacks, " + 
                              String.format("%.2f", avgDamage) + " avg damage per attack");
        }
        
        System.out.println("\nVictories by Character+Weapon Combination:");
        for (Map.Entry<String, Integer> entry : victoriesByCharacterWeaponCombo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " wins");
        }
        
        System.out.println("\n============================");
    }
}