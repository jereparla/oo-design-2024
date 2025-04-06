package game.weapons;

import game.simulator.CombatType;

public class MagicStaff implements WeaponBehavior {
    public int attack() {
        return 20;
    }
    
    public String getName() {
        return "Magic Staff";
    }

    public CombatType getCombatType() {
        return CombatType.MAGIC;
    }
}
