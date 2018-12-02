package com.josephbaca.world;

import static org.junit.jupiter.api.Assertions.*;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import org.junit.jupiter.api.Test;

class CoordinateTest {

  @Test
  void testEquals() {
    assertEquals(Coordinate.of(0, 0),
                 Coordinate.of(0, 0));

    assertEquals(Coordinate.of(3, 5),
                 Coordinate.of(3, 5));

    assertNotEquals(Coordinate.of(3, 5),
                    Coordinate.of(5, 3));

    qt().forAll(
        integers().allPositive(),
        integers().allPositive())
        .check(
            (i, j) -> Coordinate.of(i, j).equals(Coordinate.of(i, j)));

    qt().forAll(
        integers().allPositive(),
        integers().allPositive())
        .assuming((i, j) -> !i.equals(j))
        .check(
            (i, j) -> !Coordinate.of(i, j).equals(Coordinate.of(j, i)));
  }

}