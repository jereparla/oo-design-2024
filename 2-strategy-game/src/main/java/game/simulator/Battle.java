package game.simulator;

import game.characters.Character;

public class Battle {

    public static String fight(Character character1, Character character2) {
        StringBuilder battleLog = new StringBuilder();

        battleIntro(battleLog, character1, character2);

        Character attacker = character1;
        Character defender = character2;
        int round = 1;
        
        while (character1.isAlive() && character2.isAlive()) {
            battleLog.append("Round ").append(round++).append(": ");
            
            performAttack(battleLog, attacker, defender);
            
            // Swap roles
            Character temp = attacker;
            attacker = defender;
            defender = temp;
        }
        
        battleConclusion(battleLog, character1, character2);
        
        return battleLog.toString();
    }
    
    private static void battleIntro(StringBuilder log, Character fighter1, Character fighter2) {
        log.append("BATTLE BEGINS!\n");
        log.append("Fighter 1: ").append(fighter1.display()).append("\n");
        log.append("Fighter 2: ").append(fighter2.display()).append("\n\n");
    }
    
    private static void performAttack(StringBuilder log, Character attacker, Character defender) {
        int damage = attacker.attack();
        defender.receiveAttack(damage);
        
        log.append(attacker.getName())
           .append(" attacks for ")
           .append(damage)
           .append(" damage. ")
           .append(defender.getName())
           .append(" health: ")
           .append(defender.getHealth())
           .append("\n");
    }
    
    private static void battleConclusion(StringBuilder log, Character character1, Character character2) {
        Character winner = character1.isAlive() ? character1 : character2;
        Character loser = character1.isAlive() ? character2 : character1;
        
        log.append("\nBATTLE ENDS! ")
           .append(winner.getName())
           .append(" defeats ")
           .append(loser.getName())
           .append("!");
    }
}