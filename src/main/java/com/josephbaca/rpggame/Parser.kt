package com.josephbaca.rpggame

import com.josephbaca.world.Context

/**
 * Interprets user input and appropriately dispatches it.
 */
object Parser {

    private val LOG = org.slf4j.LoggerFactory.getLogger(Parser::class.java)

    internal fun parseInput(input: String, context: Context): String {

        LOG.info(String.format("Parsing \"%s\"", input))
        return context.runInput(input)
    }
}
