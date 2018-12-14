package com.josephbaca.rpggame

import com.josephbaca.context.Room
import com.josephbaca.context.RoomNouns
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class GameTest {

    private var game: Game? = null

    @BeforeEach
    fun setUp() {
        game = Game()
    }

    @Test
    fun testContext() {

        // Test default contextManager is in room
        val currentContext = game!!.contextManager.currentContext
        assertTrue(currentContext is Room)

        // Test we properly enter/exist new contexts (room (replace), battle (stack), etc)
        // Up should be different than base
        game!!.contextManager.world.movePlayer(RoomNouns.FORWARD)
        var newContext = game!!.contextManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Up then down should be the same
        game!!.contextManager.world.movePlayer(RoomNouns.BACK)
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)

        // Right should be different from base
        game!!.contextManager.world.movePlayer(RoomNouns.LEFT)
        newContext = game!!.contextManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Right then left should be the same
        game!!.contextManager.world.movePlayer(RoomNouns.BACK)
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)

        // Can't walk off world
        game!!.contextManager.world.movePlayer(RoomNouns.FORWARD)
        game!!.contextManager.world.movePlayer(RoomNouns.LEFT)
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)
    }

    @Test
    fun addContext() {
        // Test default contextManager is in room
        val currentContext = game!!.contextManager.currentContext
        assertTrue(currentContext is Room)

        game!!.input("fight")
        val newContext = game!!.contextManager.currentContext
        assertNotEquals(currentContext, newContext)
    }

    @Test
    fun removeContext() {
        // Test default contextManager is in room
        val currentContext = game!!.contextManager.currentContext
        assertTrue(currentContext is Room)

        game!!.input("fight")
        game!!.contextManager.removeContextLayer()
        val newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)
    }

}