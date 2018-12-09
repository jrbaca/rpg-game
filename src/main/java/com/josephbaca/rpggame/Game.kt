package com.josephbaca.rpggame

import com.josephbaca.util.ContextManager
import com.josephbaca.util.Parser
import com.josephbaca.world.World

/**
 ** Main game class that holds the input processing and game state management.
 */
class Game {

    val world: World
    val contextManager = ContextManager()

    init {
        world = World("the dungeon", 10, 10, contextManager)
        contextManager.insertContextLayer(world, 0) // Adds world as base level of context
        LOG.debug("Added world to beginning of context.")
        LOG.debug("Context is: %s".format(contextManager.contextStack))
    }

    fun input(input: String): String {
        return Parser.parseInput(input, contextManager.currentContext)
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(Game::class.java)
    }
}