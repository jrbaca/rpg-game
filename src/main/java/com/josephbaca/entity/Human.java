package com.josephbaca.entity;

/**
 * A humanoid living entity. The most simple of enemy/player classes.
 */
public class Human extends LivingEntity {

  private Weapon weapon = new Weapon("fist", 1, "a weapon");
  private int strength = 1;

  public Human(String name, int health) {
    super(name, health, "A human.");
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public int getAttackDamage() {
    return strength * weapon.getPower();
  }
}
