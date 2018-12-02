package com.josephbaca.world;

import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import java.util.function.Supplier;

/**
 * A room that a player can be in. May have any number of enemies, items, curses, doors, etc.
 */
public class Room implements Context, Mappable {

  private final String icon;
  private CoordinateGrid<Room> grid;

  private final Map<String, Supplier<String>> commands = HashMap.of(Tuple.of("where", this::whereAt));

  Room(int x, int y) {

    this.icon = "R";
  }

  Room(int x, int y, String icon) {
    this.icon = icon;
    this.grid = new CoordinateGrid<>(x, y);
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
}
