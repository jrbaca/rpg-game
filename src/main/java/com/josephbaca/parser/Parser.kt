package com.josephbaca.parser

import com.josephbaca.context.Context

/**
 * Interprets user input and appropriately dispatches it.
 */
object Parser {

    private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

    internal fun parseInputWithCurrentContext(input: String, context: Context): String {

        LOG.info("Parsing \"%s\" with context %s".format(input, context))
        val contextCommand = parseInputWithContextCommands(input, context.contextCommands.keys)
        return if (contextCommand != null) context.runInput(contextCommand) else "Unable to match command"
    }

    private fun parseInputWithContextCommands(input: String, contextCommands: Set<ContextCommands>): ContextCommands? {
        LOG.info("Parsing %s given commands %s".format(input, contextCommands))

        return try {
            contextCommands.single { contextCommand ->
                inputMatchesContextCommandRegex(input, contextCommand)
            }
        } catch (e: NoSuchElementException) {
            LOG.error("Nothing matches!")
            null
        } catch (e: IllegalArgumentException) {
            LOG.error("too many matching!")
            null
        }
    }

    private fun inputMatchesContextCommandRegex(input: String, contextCommand: ContextCommands): Boolean {
        return input.matches(contextCommand.regex)
    }

// Need to add commands to parser to recognize as enums. The parser should match the strings to the enums and dispatch
// appropriately. The commands need to be aware of how many arguments they take, and some interact with the
// player at runtime. For example:
// UP might be aliased by "up", "go up", "move up", etc. And takes no arguments
// INSPECT takes an argument of an item in the players possession
// EQUIP is aliased by "put on" and takes an argument of an item in the players possession
}
