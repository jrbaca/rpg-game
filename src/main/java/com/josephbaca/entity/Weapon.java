package com.josephbaca.entity;

public class Weapon extends Item {
  private final int power;

  public Weapon(String name, int power) {
    super(name);
    this.power = power;
  }

  public int getPower() {
    return power;
  }
}
