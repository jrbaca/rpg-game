package com.josephbaca.world

import com.josephbaca.rpggame.GameWrapper
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RoomTest {

    @Test
    fun roomMovementChangesRoom() {

        val commands = listOf("forward", "back", "left", "right").map { "go $it" }

        commands.map { command ->
            val game = GameWrapper()
            val contextManager = game.gameStateManager

            // Ensure not at edge of world
            game.getGameReponseFromInput("go forward")
            game.getGameReponseFromInput("go right")

            val room1 = contextManager.currentContext
            game.getGameReponseFromInput(command)
            val room2 = contextManager.currentContext

            room1 != room2
        }.forEach { assertTrue(it) }

    }

    @Test
    fun roomMovementChangesOrientation() {

        val commands = listOf("back", "left", "right").map { "go $it" }

        commands.map { command ->
            val game = GameWrapper()
            val contextManager = game.gameStateManager

            // Ensure not at edge of world
            game.getGameReponseFromInput("go forward")
            game.getGameReponseFromInput("go right")

            val orientation1 = contextManager.world.playerOrientation
            game.getGameReponseFromInput(command)
            val orientation2 = contextManager.world.playerOrientation

            orientation1 != orientation2
        }.forEach { assertTrue(it) }

    }

    @Test
    fun roomMovementDoesntChangeOrientation() {

        val commands = listOf("forward").map { "go $it" }

        commands.map { command ->
            val game = GameWrapper()
            val contextManager = game.gameStateManager

            // Ensure not at edge of world
            game.getGameReponseFromInput("go forward")
            game.getGameReponseFromInput("go right")

            val orientation1 = contextManager.world.playerOrientation
            game.getGameReponseFromInput(command)
            val orientation2 = contextManager.world.playerOrientation

            orientation1 == orientation2
        }.forEach { assertTrue(it) }

    }

}