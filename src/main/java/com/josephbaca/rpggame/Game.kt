package com.josephbaca.rpggame

import com.josephbaca.context.ContextManager
import com.josephbaca.parsing.Parser

/**
 ** Main game class that holds the input processing and game state management.
 */
class Game {

    val contextManager = ContextManager()

    private val commands = mapOf(
        Pair("new game", this::newGame),
        Pair("help", this::help)
    )

    fun input(inputRaw: String): String {
        val input = inputRaw.toLowerCase().trim()

        return when {
            commands.containsKey(input) -> commands[input]!!.invoke() // Run special commands
            contextManager.player.isAlive -> Parser.tokenizeAndParseInput(
                input,
                contextManager.currentContext
            )
            else -> "Dead men tell no tables...?" // can't run regular commands if dead
        }
    }

    private fun newGame(): String {
        contextManager.gameOver = true
        return "New game started!"
    }

    private fun help(): String {
        return contextManager.currentContext.verbsToken.keys.toString()
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)
    }
}