package game.weapons;

import game.simulator.CombatType;

public class MagicWand implements WeaponBehavior {
    public int attack() {
        return 15;
    }
    
    public String getName() {
        return "Magic Wand";
    }

    public CombatType getCombatType() {
        return CombatType.MAGIC;
    }
}
