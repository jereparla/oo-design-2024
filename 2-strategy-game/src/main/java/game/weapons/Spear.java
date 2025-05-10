package game.weapons;

import game.simulator.CombatType;

public class Spear implements WeaponBehavior {
    public int attack() {
        return 20;
    }
    
    public String getName() {
        return "Spear";
    }

    public CombatType getCombatType() {
        return CombatType.RANGE;
    }
}
