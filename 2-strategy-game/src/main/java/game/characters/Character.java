package game.characters;

import game.simulator.CombatType;
import game.weapons.WeaponBehavior;

public abstract class Character {
    protected String name;
    protected int health = 100;
    protected WeaponBehavior weapon;
    protected CombatType combatType;
    
    public Character() {
    }

    public abstract String display();
    
    public String getName() {
        return name;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setWeapon(WeaponBehavior weapon) {
        if (weapon.getCombatType() != this.combatType) {
            throw new IllegalArgumentException(
                this.name + " cannot use " + weapon.getCombatType() + 
                " weapons. Only " + this.combatType + " weapons allowed."
            );
        }
        this.weapon = weapon;
    }
    
    public int attack() {
        return weapon.attack();
    }
    
    public void receiveAttack(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    public WeaponBehavior getWeapon() {
        return weapon;
    }

    public CombatType getCombatType() {
        return combatType;
    }
}