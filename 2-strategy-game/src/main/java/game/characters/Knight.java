package game.characters;

import game.simulator.CombatType;
import game.weapons.Sword;

public class Knight extends Character {

    public Knight(String name) {
        this.name = name;
        this.weapon = new Sword();
        this.combatType = CombatType.MELEE;
    }

    public String display() {
        return "I'm " + this.name + ", a brave knight wielding a " + this.weapon.getName();
    }
}
