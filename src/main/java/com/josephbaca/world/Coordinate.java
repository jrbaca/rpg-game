package com.josephbaca.world;

/**
 * Convenience coordinate class. Allows coordinates to be expressed
 * as a single object. Immutable.
 */
public class Coordinate {

  @SuppressWarnings({"checkstyle:memberName"})
  final int x;
  @SuppressWarnings({"checkstyle:memberName"})
  final int y;

  private Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Clean constructor for immutable coordinates.
   */
  public static Coordinate of(int x, int y) {
    return new Coordinate(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Coordinate)) {
      return false;
    } else {
      Coordinate cc = (Coordinate) obj;
      return this.x == cc.x && this.y == cc.y;
    }
  }
}
