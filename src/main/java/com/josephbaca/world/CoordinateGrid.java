package com.josephbaca.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Easy to use class for representing things that follow a cartesian coordinate grid.
 */
public class CoordinateGrid<T extends Mappable> {

  private final List<List<T>> grid;

  CoordinateGrid(int x, int y) {
    grid = new ArrayList<>();
    for (int i = 0; i < x; i++) {
      grid.add(new ArrayList<>());
      for (int j = 0; j < y; j++) {
        grid.get(i).add(null);
      }
    }
  }

  /**
   * Gets the T at the provided coordinate.
   */
  public T getCoordinate(Coordinate c) {
    return grid.get(c.x).get(c.y);
  }

  /**
   * Sets a coordinate to a specified T.
   */
  public void setCoordinate(Coordinate c, T t) {
    grid.get(c.y).set(c.x, t);
  }

  @Override
  public String toString() {
    return grid.toString();
  }

  /**
   * Returns a print friendly version of the grid formatted as a standard cartesian coordinate grid.
   */
  String toDisplayString() {
    int sizeY = grid.size();
    int sizeX = sizeY != 0
              ? grid.get(0).size()
              : 0;

    StringBuilder sb = new StringBuilder(sizeX * sizeY);

    for (int i = sizeY - 1; i >= 0; i--) {
      for (int j = 0; j < sizeX; j++) {
        sb.append(getCoordinate(Coordinate.of(i, j)).getIcon());
        sb.append(" ");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append("\n");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  public int sizeX() {
    return grid.size();
  }

  public int sizeY() {
    return grid.get(0).size();
  }
}
