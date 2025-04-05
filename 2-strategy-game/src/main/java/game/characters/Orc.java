package game.characters;

import game.simulator.CombatType;
import game.weapons.Axe;

public class Orc extends Character {

    public Orc(String name) {
        this.name = name;
        this.weapon = new Axe();
        this.combatType = CombatType.MELEE;
    }

    public String display() {
        return "I'm " + this.name + ", a nasty orc brandishing a " + this.weapon.getName();
    }
}
