package com.josephbaca.rpggame

import com.josephbaca.context.ContextManager
import com.josephbaca.parsing.Parser

/**
 ** Main game class that holds the input processing
 */
class Game {

    val contextManager = ContextManager()

    fun input(inputRaw: String): String {
        val input = inputRaw.toLowerCase().trim()

        return when {
            contextManager.player.isAlive -> Parser.tokenizeAndParseInput(
                input,
                contextManager.currentContext
            )
            else -> "Dead men tell no tables...?" // can't run regular commands if dead
        }
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)
    }
}