package com.josephbaca.world;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.io.File;
import java.util.Random;
import java.util.function.Supplier;

/**
 * A room that a player can be in. May have any number of enemies, items, curses, doors, etc.
 */
public class Room implements Context, Mappable {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Room.class);

  private final String icon;
  private final String description;
  private CoordinateGrid<Room> grid;
  private BiomeType biome;

  private final Map<String, Supplier<String>> commands = HashMap.of(
          "where", this::whereAt,
          "what", this::getRoomDescription);

  public enum BiomeType {
    FLOWERY,
    SUNNY,
    DESSERT,
    HALLOWEENTOWN,
    STSPATRICKSDAYTOWN,
    EASTERTOWN,
    PUROLAND
  }

  Room(int x, int y) {
    this(x, y, "R");
  }

  Room(int x, int y, String icon) {
    Random r = new Random();
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

  /**
   * Type of room which determines it's contents ?
   */
  private static class Biome {

    static final List<String> floweryDescriptions = List.of("gg bois u got da worst description in the game. Theres a" +
            "buncha dumbass dandelions up in dis club. Also hope you don't have allergies. I guess theres cool grass idk" +
            "use your imagination bub.", "Wow!!!1!1!!! so pretty!!! These roses definitely won't kill you!!");
    static final List<String> sunnyDescriptions = List.of("hot. humid. congratz ur sweating now :/", "You know when the" +
            "weather is like 70 and the rain is barely misting down on you? That's this room its v comfy");
    static final List<String> dessertDescriptions = List.of("Fruit salad, Yummy Yummy", "This room is shaped like a" +
            "milkshake, and you have reason to believe that it brings all the boys to the yard.");
    static final List <String> halloweentownDescriptions = List.of("it looks spooky. Probably has a couple skeletons" +
            "in it's closet");
    static final List <String> stPatricksDayTownDescriptions = List.of("Green. Smells like beer and bad decisions");
    static final List <String> easterTownDescriptions = List.of("Oh cool theres easter eggs!!! It smells like chocolate!");
    static final List <String> puroLandDescriptions = List.of("It's so cute!!!!!!!!!! Literally dead!!! Theres Kuromi" +
            "posters and my little twin star plushies!!!!! Actual best room");
    static String getDescription(BiomeType biome) {
      switch(biome) {
        case FLOWERY: return floweryDescriptions.shuffle().head();
        case SUNNY: return sunnyDescriptions.shuffle().head();
        case DESSERT: return dessertDescriptions.shuffle().head();
        case HALLOWEENTOWN: return halloweentownDescriptions.shuffle().head();
        case STSPATRICKSDAYTOWN: return stPatricksDayTownDescriptions.shuffle().head();
        case EASTERTOWN: return easterTownDescriptions.shuffle().head();
        case PUROLAND: return puroLandDescriptions.shuffle().head();
        default: throw new RuntimeException("Unknown biome");
      }
    }
  }
}
