package com.josephbaca.parser

import com.josephbaca.context.Context

/**
 * Interprets user input and appropriately dispatches it.
 */
object Parser {

    private val LOG = org.slf4j.LoggerFactory.getLogger(Parser::class.java)

    internal fun parseInputWithCurrentContext(input: String, context: Context): String {

        LOG.info(String.format("Parsing \"%s\"", input))
        return context.runInput(input)
    }
}
