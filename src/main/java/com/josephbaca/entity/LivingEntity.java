package com.josephbaca.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Any entity that is alive, meaning it can take actions.
 */
public abstract class LivingEntity extends Entity {

  private int health;
  private Inventory inventory = new Inventory();

  LivingEntity(String name, int health, String description) {
    super(name, description);
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public abstract int getAttackDamage();

  /**
   * Holds a living entity's {@link Item}.
   */
  class Inventory {

    private List<Item> inventory = new ArrayList<>();

    public String getInventory() {
      return inventory.toString();
    }

    public void add(Item item) {
      inventory.add(item);
    }

  }

}
