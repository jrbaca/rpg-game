package com.josephbaca.world;

import static org.junit.jupiter.api.Assertions.*;

import com.josephbaca.util.ContextManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinateGridTest {

  private ContextManager contextManager;
  private World world;

  @BeforeEach
  void setUp() {
    contextManager = new ContextManager();
    world = new World("testworld", 10, 10, contextManager);
  }

  @Test
  void setCoordinate() {
    CoordinateGrid<Room> grid = new CoordinateGrid<>(2, 2);

    for (int i = 0; i < grid.sizeX(); i++) {
      for (int j = 0; j < grid.sizeX(); j++) {
        grid.setCoordinate(Coordinate.of(i, j), new Room(contextManager, 5, 5, world));
      }
    }

    // Sanity check
    assertEquals("R R\nR R", grid.toDisplayString());

    // Set top left
    grid.setCoordinate(Coordinate.of(0, 1), new Room(contextManager, 5, 5, world, "T"));
    assertEquals("T R\nR R", grid.toDisplayString());
  }

  @Test
  void toDisplayString() {
    CoordinateGrid<Room> grid = new CoordinateGrid<>(2, 2);

    for (int i = 0; i < grid.sizeX(); i++) {
      for (int j = 0; j < grid.sizeX(); j++) {
        grid.setCoordinate(Coordinate.of(i, j), new Room(contextManager, 5, 5, world));
      }
    }

    assertEquals("R R\nR R", grid.toDisplayString());
  }
}