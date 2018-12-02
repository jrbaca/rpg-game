package com.josephbaca.world;

import com.josephbaca.rpggame.ContextManager;

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every {@link Tile} and the current location
 * of the player.
 */
public class World {

  private final String name;

  /**
   * Player coordinates.
   */
  private Coordinate playerCoords;

  private final ContextManager context;
  /**
   * Internal representation of the world.
   */
  private CoordinateGrid<Room> grid;

  /**
   * Creates a world.
   *
   * @param name of the world
   * @param maxx coordinate of the world
   * @param maxy coordinate of the world
   */
  public World(String name, int maxx, int maxy, ContextManager context) {
    this.name = name;

    this.grid = new CoordinateGrid<>(maxx, maxy);
    this.playerCoords = Coordinate.of(0, 0);

    generateWorld();

    this.context = context;
    context.addContextLayer(getCurrentRoom()); // Add current room to context
  }

  private void setPlayerCoords(Coordinate c) {
    playerCoords = c;
    context.replaceContextLayer(getCurrentRoom());

  }

  /**
   * Move the player up one unit.
   */
  public void movePlayerUp() {
    setPlayerCoords(Coordinate.of(playerCoords.x, playerCoords.y + 1));
  }

  /**
   * Move the player down one unit.
   */
  public void movePlayerDown() {
    setPlayerCoords(Coordinate.of(playerCoords.x, playerCoords.y - 1));
  }

  /**
   * Move the player right one unit.
   */
  public void movePlayerRight() {
    setPlayerCoords(Coordinate.of(playerCoords.x + 1, playerCoords.y));
  }

  /**
   * Move the player left one unit.
   */
  public void movePlayerLeft() {
    setPlayerCoords(Coordinate.of(playerCoords.x - 1, playerCoords.y));
  }

  private Room getCurrentRoom() {
    return grid.getCoordinate(playerCoords);
  }

  /**
   * World generator function.
   */
  private void generateWorld() {
    for (int x = 0; x < grid.sizeX(); x++) {
      for (int y = 0; y < grid.sizeY(); y++) {
        Room r = new Room(5, 5);
        grid.setCoordinate(Coordinate.of(x, y), r);
      }
    }
  }

  @Override
  public String toString() {
    return grid.toString();
  }

  /**
   * Returns a print friendly version of the world.
   */
  public String toDisplayString() {
    return grid.toDisplayString();
  }
}
