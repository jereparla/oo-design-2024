package game.weapons;

import game.simulator.CombatType;

public interface WeaponBehavior {
    public int attack();
    public String getName();
    public CombatType getCombatType();
}