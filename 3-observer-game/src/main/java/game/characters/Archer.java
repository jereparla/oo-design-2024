package game.characters;

import game.simulator.CombatType;
import game.weapons.LongBow;

public class Archer extends Character {

    public Archer(String name) {
        this.name = name;
        this.weapon = new LongBow();
        this.combatType = CombatType.RANGE;
    }

    public String display() {
        return "I'm " + this.name + ", a precise archer attacking with my " + this.weapon.getName();
    }
}
