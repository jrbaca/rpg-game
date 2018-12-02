package com.josephbaca.world;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.util.Random;
import java.util.function.Supplier;

/**
 * A room that a player can be in. May have any number of enemies, items, curses, doors, etc.
 */
public class Room implements Context, Mappable {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Room.class);

  private final World world;
  private final String icon; // Icon of the room as it appears on maps
  private final String description;
  private CoordinateGrid<Room> grid; // Map of the room
  private BiomeType biome; // Type of room

  private final Map<String, Supplier<String>> commands = HashMap.of(
      "where", this::whereAt,
      "what", this::getRoomDescription,
      "up", this::moveUp,
      "down", this::moveDown,
      "left", this::moveLeft,
      "right", this::moveRight);

  public enum BiomeType {
    FLOWERY,
    SUNNY
  }

  Room(int x, int y, World world) {
    this(x, y, world, "R");
  }

  Room(int x, int y, World world, String icon) {
    Random r = new Random();
    this.world = world;
    this.icon = icon;
    this.grid = new CoordinateGrid<>(x, y);
    this.biome = BiomeType.values()[r.nextInt(BiomeType.values().length)];
    this.description = Biome.getDescription(this.biome);
  }

  @Override
  public String toString() {
    return String.format("Room (%s)", icon);
  }

  @Override
  public String getIcon() {
    return icon;
  }

  /**
   * Returns a string displaying the contents of the room.
   */
  public String toDisplayString() {
    throw new RuntimeException("Not yet implemented.");
  }

  @Override
  public String runInput(String input) {
    return commands.get(input).getOrElse(() -> "Unknown command").get();
  }

  @Override
  public String whereAt() {
    return "You are in a room";
  }

  private String getRoomDescription() {
    return this.description;
  }

  private String moveUp() {
    world.movePlayerUp();
    return world.toDisplayString();
  }

  private String moveDown() {
    world.movePlayerDown();
    return world.toDisplayString();
  }

  private String moveRight() {
    world.movePlayerRight();
    return world.toDisplayString();
  }

  private String moveLeft() {
    world.movePlayerLeft();
    return world.toDisplayString();
  }

  /**
   * Type of room which determines it's contents ?
   */
  private static class Biome {

    static final List<String> floweryDescriptions = List.of("gg bois u got da worst description in the game. Theres a" +
            "buncha dumbass dandelions up in dis club. Also hope you don't have allergies. I guess theres cool grass idk" +
            "use your imagination bub.", "Wow!!!1!1!!! so pretty!!! These roses definitely won't kill you!!");
    static final List<String> sunnyDescriptions = List.of("hot. humid. congratz ur sweating now :/", "You know when the" +
            "weather is like 70 and the rain is barely misting down on you? That's this room its v comfy");

    static String getDescription(BiomeType biome) {
      switch(biome) {
        case FLOWERY: return floweryDescriptions.shuffle().head();
        case SUNNY: return sunnyDescriptions.shuffle().head();
        default: throw new RuntimeException("Unknown biome");
      }
    }
  }
}
