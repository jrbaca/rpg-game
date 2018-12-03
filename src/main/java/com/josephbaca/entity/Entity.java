package com.josephbaca.entity;

/**
 * Abstract superclass for all entities.
 */
public abstract class Entity {

  private final String name;
  private final String description;

  Entity(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }
  public String getDescription(){
    return description;
  }
}
