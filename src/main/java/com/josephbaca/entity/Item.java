package com.josephbaca.entity;

/**
 * Abstract class to differentiate Items from general entities. These can be stored in a
 * {@link com.josephbaca.entity.LivingEntity.Inventory}, for example.
 */
public abstract class Item extends Entity {

  public Item(String name, String description) {
    super(name, description);
  }

}
