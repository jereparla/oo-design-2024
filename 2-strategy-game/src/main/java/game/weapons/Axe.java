package game.weapons;

import game.simulator.CombatType;

public class Axe implements WeaponBehavior {
    public int attack() {
        return 30;
    }
    
    public String getName() {
        return "Axe";
    }
    
    public CombatType getCombatType() {
        return CombatType.MELEE;
    }
}
