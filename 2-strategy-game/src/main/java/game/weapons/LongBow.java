package game.weapons;

import game.simulator.CombatType;

public class LongBow implements WeaponBehavior {

    public int attack() {
        return 15;
    }
    
    public String getName() {
        return "Long Bow";
    }

    public CombatType getCombatType() {
        return CombatType.RANGE;
    }
}
