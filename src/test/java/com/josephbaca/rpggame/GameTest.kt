package com.josephbaca.rpggame

import com.josephbaca.context.Room
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
        game!!.contextManager.world.movePlayerUp()
        var newContext = game!!.contextManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Up then down should be the same
        game!!.contextManager.world.movePlayerDown()
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)

        // Right should be different from base
        game!!.contextManager.world.movePlayerRight()
        newContext = game!!.contextManager.currentContext
        assertNotEquals(currentContext, newContext)

        // Right then left should be the same
        game!!.contextManager.world.movePlayerLeft()
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)

        // Can't walk off world
        game!!.contextManager.world.movePlayerDown()
        game!!.contextManager.world.movePlayerLeft()
        newContext = game!!.contextManager.currentContext
        assertEquals(currentContext, newContext)

        // TODO test add/remove from stack
    }

}