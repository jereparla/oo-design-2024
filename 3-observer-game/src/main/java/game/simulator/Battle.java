package game.simulator;

import game.characters.Character;
import game.logging.BattleObserver;
import game.logging.BattleSubject;

import java.util.ArrayList;
import java.util.List;

public class Battle implements BattleSubject {
    private List<BattleObserver> observers = new ArrayList<>();

    public void registerObserver(BattleObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BattleObserver observer) {
        observers.remove(observer);
    }

    protected void notifyBattleStart(Character fighter1, Character fighter2) {
        for (BattleObserver observer : observers) {
            observer.onBattleStart(fighter1, fighter2);
        }
    }

    protected void notifyAttack(Character attacker, Character defender) {
        for (BattleObserver observer : observers) {
            observer.onAttack(attacker, defender);
        }
    }

    protected void notifyCharacterDeath(Character character) {
        for (BattleObserver observer : observers) {
            observer.onCharacterDeath(character);
        }
    }

    protected void notifyBattleEnd(Character winner, Character loser) {
        for (BattleObserver observer : observers) {
            observer.onBattleEnd(winner, loser);
        }
    }

    public void fight(Character character1, Character character2) {
        StringBuilder battleLog = new StringBuilder();

        notifyBattleStart(character1, character2);

        Character attacker = character1;
        Character defender = character2;
        int round = 1;
        
        while (character1.isAlive() && character2.isAlive()) {
            battleLog.append("Round ").append(round++).append(": ");
            
            performAttack(attacker, defender);
            
            if (!defender.isAlive()) {
                notifyCharacterDeath(defender);
            }
            
            // Swap roles
            Character temp = attacker;
            attacker = defender;
            defender = temp;
        }
        
        Character winner = character1.isAlive() ? character1 : character2;
        Character loser = character1.isAlive() ? character2 : character1;
        
        notifyBattleEnd(winner, loser);
    }
    
    private void performAttack(Character attacker, Character defender) {
        int damage = attacker.attack();
        defender.receiveAttack(damage);
           
        notifyAttack(attacker, defender);
    }
}