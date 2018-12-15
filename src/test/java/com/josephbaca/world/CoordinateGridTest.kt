package com.josephbaca.world

import com.josephbaca.rpggame.GameStateManager
import com.josephbaca.context.Room
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CoordinateGridTest {

    private var gameStateManager: GameStateManager? = null
    private var world: World? = null

    @BeforeEach
    fun setUp() {
        gameStateManager = GameStateManager()
        world = World(10, 10, gameStateManager!!)
    }

    @Test
    fun setCoordinate() {
        val grid = CoordinateGrid<Room>(2, 2)

        for (i in 0 until grid.sizeX) {
            for (j in 0 until grid.sizeX) {
                grid.setCoordinate(Coordinate(i, j), Room(gameStateManager!!))
            }
        }

        // Sanity check
        assertEquals("R R\nR R", grid.toDisplayString())

        // Set top left
        grid.setCoordinate(Coordinate(0, 1), Room(gameStateManager!!, "T"))
        assertEquals("T R\nR R", grid.toDisplayString())
    }

    @Test
    fun toDisplayString() {
        val grid = CoordinateGrid<Room>(3, 2)
        grid.sizeX

        for (i in 0 until grid.sizeX) {
            for (j in 0 until grid.sizeY) {
                grid.setCoordinate(Coordinate(i, j), Room(gameStateManager!!))
            }
        }

        assertEquals("R R R\nR R R", grid.toDisplayString())
    }
}