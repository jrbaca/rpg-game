package com.josephbaca.world;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateGridTest {

  @Test
  void setCoordinate() {
    CoordinateGrid<Room> grid = new CoordinateGrid<>(2, 2);

    for (int i = 0; i < grid.sizeX(); i++) {
      for (int j = 0; j < grid.sizeX(); j++) {
        grid.setCoordinate(Coordinate.of(i, j), new Room(5, 5));
      }
    }

    // Sanity check
    assertEquals("R R\nR R", grid.toDisplayString());

    // Set top left
    grid.setCoordinate(Coordinate.of(0, 1), new Room(5, 5, "T"));
    assertEquals("T R\nR R", grid.toDisplayString());
  }

  @Test
  void toDisplayString() {
    CoordinateGrid<Room> grid = new CoordinateGrid<>(2, 2);

    for (int i = 0; i < grid.sizeX(); i++) {
      for (int j = 0; j < grid.sizeX(); j++) {
        grid.setCoordinate(Coordinate.of(i, j), new Room(5, 5));
      }
    }

    assertEquals("R R\nR R", grid.toDisplayString());
  }
}