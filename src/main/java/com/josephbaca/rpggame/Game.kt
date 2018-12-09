package com.josephbaca.rpggame

import com.josephbaca.context.ContextManager
import com.josephbaca.context.World
import com.josephbaca.util.Parser

/**
 ** Main game class that holds the input processing and game state management.
 */
class Game {

    val world: World
    val contextManager = ContextManager()
    private val commands = mapOf(
        Pair("new game", this::newGame),
        Pair("help", this::help)
    )

    init {
        world = World(10, 10, contextManager)
        contextManager.insertContextLayer(world, 0) // Adds world as base level of context
        LOG.debug("Added world to beginning of context.")
        LOG.debug("Context is: %s".format(contextManager.contextStack))
    }

    fun input(inputRaw: String): String {
        val input = inputRaw.toLowerCase().trim()

        return when {
            commands.containsKey(input) -> commands[input]!!.invoke() // Run special commands
            contextManager.player.isAlive -> Parser.parseInput(
                input,
                contextManager.currentContext
            ) // Run commands in context
            else -> "Dead men tell no tables...?" // can't run regular commands if dead
        }
    }

    private fun newGame(): String {
        contextManager.gameOver = true
        return "Starting new game..."
    }

    private fun help(): String {
        return contextManager.currentContext.commands.keys.sorted().toString()
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(Game::class.java)
    }
}