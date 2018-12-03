package com.josephbaca.entity;

public class Weapon extends Item {
  private final int power;

  public Weapon(String name, int power, String description) {
    super(name, description);
    this.power = power;
  }

  public int getPower() {
    return power;
  }
}
