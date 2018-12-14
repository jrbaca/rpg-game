package com.josephbaca.world

import com.josephbaca.rpggame.Game
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RoomTest {

    @Test
    fun roomMovementChangesRoom() {

        val commands = listOf("forward", "back", "left", "right").map { "go $it" }

        commands.map { command ->
            val game = Game()
            val contextManager = game.contextManager

            // Ensure not at edge of world
            game.input("go forward")
            game.input("go right")

            val room1 = contextManager.currentContext
            game.input(command)
            val room2 = contextManager.currentContext

            room1 != room2
        }.forEach { assertTrue(it) }

    }

    @Test
    fun roomMovementChangesOrientation() {

        val commands = listOf("back", "left", "right").map { "go $it" }

        commands.map { command ->
            val game = Game()
            val contextManager = game.contextManager

            // Ensure not at edge of world
            game.input("go forward")
            game.input("go right")

            val orientation1 = contextManager.world.playerOrientation
            game.input(command)
            val orientation2 = contextManager.world.playerOrientation

            orientation1 != orientation2
        }.forEach { assertTrue(it) }

    }

    fun roomMovementDoesntChangeOrientation() {

        val commands = listOf("forward").map { "go $it" }

        commands.map { command ->
            val game = Game()
            val contextManager = game.contextManager

            // Ensure not at edge of world
            game.input("go forward")
            game.input("go right")

            val orientation1 = contextManager.world.playerOrientation
            game.input(command)
            val orientation2 = contextManager.world.playerOrientation

            orientation1 == orientation2
        }.forEach { assertTrue(it) }

    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(RoomTest::class.java)
    }

}