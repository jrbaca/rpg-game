package com.josephbaca.world

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.quicktheories.QuickTheory.qt
import org.quicktheories.generators.SourceDSL.integers

internal class CoordinateTest {

    @Test
    fun testEquals() {
        assertEquals(
            Coordinate(0, 0),
            Coordinate(0, 0)
        )

        assertEquals(
            Coordinate(3, 5),
            Coordinate(3, 5)
        )

        assertNotEquals(
            Coordinate(3, 5),
            Coordinate(5, 3)
        )

        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, j -> Coordinate(i!!, j!!) == Coordinate(i, j) }

        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .assuming { i, j -> i != j }
            .check { i, j -> Coordinate(i!!, j!!) != Coordinate(j, i) }
    }

}