package com.josephbaca.entity;

/**
 * Any item that can be consumed by an entity to induce some effect, usually healing.
 */
public class Consumable extends Item {

  int healing;

  public Consumable(String name, int healing, String description) {
    super(name, description);
    this.healing = healing;
  }
}
