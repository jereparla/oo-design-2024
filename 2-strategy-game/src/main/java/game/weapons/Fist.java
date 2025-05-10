package game.weapons;

import game.simulator.CombatType;

public class Fist implements WeaponBehavior{

    public int attack() {
        return 5;
    }
    
    public String getName() {
        return "Fists";
    }
    
    public CombatType getCombatType() {
        return CombatType.MELEE;
    }
}
