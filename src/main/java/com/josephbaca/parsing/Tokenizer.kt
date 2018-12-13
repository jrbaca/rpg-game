package com.josephbaca.parsing

import java.lang.Integer.min

/**
 *
 */
internal object Tokenizer {

    private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

    fun tokenizeInputWithContextCommandsRegex(
        input: String,
        contextCommands: Set<ContextCommand>
    ): List<ContextCommand>? {
        val preProcessedInput = preProcessInput(input)

        LOG.info("Attempting to tokenize \"%s\" with tokens %s".format(preProcessedInput, contextCommands))
        return iterativelyMatchTokens(preProcessedInput, contextCommands)
    }

    private fun preProcessInput(input: String): String {
        return input.toLowerCase()
    }

    private fun iterativelyMatchTokens(input: String, contextCommands: Set<ContextCommand>): List<ContextCommand>? {
        return iterativelyMatchTokens(input, contextCommands, 0)
    }

    private fun iterativelyMatchTokens(
        input: String,
        contextCommands: Set<ContextCommand>,
        index: Int
    ): List<ContextCommand>? {

        if (input.isEmpty()) return listOf()

        val remainingTokens =
            iterativelyMatchTokens(input.drop(1), contextCommands) ?: return null // Propagate bad matches

        val matchingCommand =
            getMatchingContextCommand(input, contextCommands) ?: return null // Bad if too many matches

        return matchingCommand.plus(remainingTokens)
        //TODO match on the correct substring
    }

    private fun getMatchingContextCommand(
        input: String,
        contextCommands: Set<ContextCommand>
    ): List<ContextCommand>? {

        val matchingCommands = contextCommands.filter { command -> inputMatchesContextCommandRegex(input, command) }
        return if (matchingCommands.size > 1) {
            LOG.warn("Too many tokens match!")
            null
        } else {
            matchingCommands
        }
    }

    private fun inputMatchesContextCommandRegex(input: String, contextCommand: ContextCommand): Boolean {
        return input.trim().matches(contextCommand.regex)
    }
}