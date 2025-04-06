package game.characters;

import game.simulator.CombatType;
import game.weapons.MagicStaff;

public class Wizard extends Character {

    public Wizard(String name) {
        this.name = name;
        this.weapon = new MagicStaff();
        this.combatType = CombatType.MAGIC;
    }

    public String display() {
        return "I'm " + this.name + ", a mighty wizard wielding a " + this.weapon.getName();
    }
}
