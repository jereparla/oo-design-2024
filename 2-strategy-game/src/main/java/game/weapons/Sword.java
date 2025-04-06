package game.weapons;

import game.simulator.CombatType;

public class Sword implements WeaponBehavior {

    public int attack() {
        return 25;
    }
    
    public String getName() {
        return "Sword";
    }

    public CombatType getCombatType() {
        return CombatType.MELEE;
    }
}
