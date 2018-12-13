package com.josephbaca.parser

/**
 *
 */
internal object Tokenizer {

    private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

    fun tokenizeInputWithContextCommandsRegex(
        input: String,
        contextCommands: Set<ContextCommand>
    ): List<ContextCommand>? {
        LOG.info("Attempting to tokenize \"%s\" with tokens %s".format(input, contextCommands))
        return tokenizeUsingFullMatch(contextCommands, input)
    }

    private fun tokenizeUsingFullMatch(
        contextCommands: Set<ContextCommand>,
        input: String
    ): List<ContextCommand>? {
        return try {
            listOf(contextCommands.single { contextCommand ->
                inputMatchesContextCommandRegex(input, contextCommand)
            })
        } catch (e: NoSuchElementException) {
            LOG.warn("Nothing matches!")
            null
        } catch (e: IllegalArgumentException) {
            LOG.warn("Too many tokens match!")
            null
        }
    }

    private fun inputMatchesContextCommandRegex(input: String, contextCommand: ContextCommand): Boolean {
        return input.matches(contextCommand.regex)
    }
}