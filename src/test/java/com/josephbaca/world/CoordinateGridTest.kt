package com.josephbaca.world

import com.josephbaca.util.ContextManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CoordinateGridTest {

    private var contextManager: ContextManager? = null
    private var world: World? = null

    @BeforeEach
    fun setUp() {
        contextManager = ContextManager()
        world = World("testworld", 10, 10, contextManager!!)
    }

    @Test
    fun setCoordinate() {
        val grid = CoordinateGrid<Room>(2, 2)

        for (i in 0 until grid.sizeX) {
            for (j in 0 until grid.sizeX) {
                grid.setCoordinate(Coordinate(i, j), Room(contextManager!!, 5, 5, world!!))
            }
        }

        // Sanity check
        assertEquals("R R\nR R", grid.toDisplayString())

        // Set top left
        grid.setCoordinate(Coordinate(0, 1), Room(contextManager!!, 5, 5, world!!, "T"))
        assertEquals("T R\nR R", grid.toDisplayString())
    }

    @Test
    fun toDisplayString() {
        val grid = CoordinateGrid<Room>(3, 2)
        grid.sizeX

        for (i in 0 until grid.sizeX) {
            for (j in 0 until grid.sizeY) {
                grid.setCoordinate(Coordinate(i, j), Room(contextManager!!, 5, 5, world!!))
            }
        }

        assertEquals("R R R\nR R R", grid.toDisplayString())
    }
}