package game.logging;

import game.characters.Character;

public class ConsoleLogger implements BattleObserver {
    
    public void onBattleStart(Character fighter1, Character fighter2) {
        System.out.println("[LOG] Battle started between " + fighter1.getName() + 
                           " (" + fighter1.getClass().getSimpleName() + ") and " + 
                           fighter2.getName() + " (" + fighter2.getClass().getSimpleName() + ")");
    }
    
    public void onAttack(Character attacker, Character defender) {
        System.out.println("[LOG] " + attacker.getName() + 
                           " (" + attacker.getClass().getSimpleName() + ") attacked " + 
                           defender.getName() + " (" + defender.getClass().getSimpleName() + ") with " + 
                           attacker.getWeapon().getName() + " for " + attacker.attack() + 
                           " damage. " + defender.getName() + " health: " + defender.getHealth());
    }
    
    public void onCharacterDeath(Character character) {
        System.out.println("[LOG] " + character.getName() + " has died!");
    }
    
    public void onBattleEnd(Character winner, Character loser) {
        System.out.println("[LOG] Battle ended. " + winner.getName() + 
                           " (" + winner.getClass().getSimpleName() + ") defeated " + 
                           loser.getName() + " (" + loser.getClass().getSimpleName() + ")");
    }
}