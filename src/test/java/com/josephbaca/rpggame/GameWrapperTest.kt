package com.josephbaca.rpggame

import com.josephbaca.context.Room
import com.josephbaca.context.RoomNouns
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class GameWrapperTest {

    private var gameWrapper: GameWrapper? = null

    @BeforeEach
    fun setUp() {
        gameWrapper = GameWrapper()
    }

    @Test
    fun testContext() {

        // Test default gameStateManager is in room
        val currentContext = gameWrapper!!.gameStateManager.currentContext
        assertTrue(currentContext is Room)

        // Test we properly enter/exist new contexts (room (replace), battle (stack), etc)
        // Up should be different than base
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.FORWARD)
        var newContext = gameWrapper!!.gameStateManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Up then down should be the same
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.BACK)
        newContext = gameWrapper!!.gameStateManager.currentContext
        assertEquals(currentContext, newContext)

        // Right should be different from base
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.LEFT)
        newContext = gameWrapper!!.gameStateManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Right then left should be the same
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.BACK)
        newContext = gameWrapper!!.gameStateManager.currentContext
        assertEquals(currentContext, newContext)

        // Can't walk off world
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.FORWARD)
        gameWrapper!!.gameStateManager.world.movePlayer(RoomNouns.LEFT)
        newContext = gameWrapper!!.gameStateManager.currentContext
        assertEquals(currentContext, newContext)
    }

    @Test
    fun addContext() {
        // Test default gameStateManager is in room
        val currentContext = gameWrapper!!.gameStateManager.currentContext
        assertTrue(currentContext is Room)

        gameWrapper!!.getGameReponseFromInput("fight")
        val newContext = gameWrapper!!.gameStateManager.currentContext
        assertNotEquals(currentContext, newContext)
    }

    @Test
    fun removeContext() {
        // Test default gameStateManager is in room
        val currentContext = gameWrapper!!.gameStateManager.currentContext
        assertTrue(currentContext is Room)

        gameWrapper!!.getGameReponseFromInput("fight")
        gameWrapper!!.gameStateManager.removeContextLayer()
        val newContext = gameWrapper!!.gameStateManager.currentContext
        assertEquals(currentContext, newContext)
    }

}