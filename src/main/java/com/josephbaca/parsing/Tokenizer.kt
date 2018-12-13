package com.josephbaca.parsing

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
        val processedInput = input.toLowerCase().trim().replace(Regex("\\s+"), " ")
        LOG.debug("Processed \"%s\" into \"%s\"".format(input, processedInput))
        return processedInput
    }

    private fun iterativelyMatchTokens(input: String, contextCommands: Set<ContextCommand>): List<ContextCommand>? {
        return iterativelyMatchTokens(input, contextCommands, 0, 0)
    }

    private fun iterativelyMatchTokens(
        input: String,
        contextCommands: Set<ContextCommand>,
        startIndex: Int,
        endIndex: Int
    ): List<ContextCommand>? {

        if (endIndex == input.length + 1) return listOf()

        val searchString = input.substring(startIndex, endIndex).trim()
        LOG.debug("Searching \"%s\" from \"%s\"".format(searchString, input))

        val matchingCommand =
            getMatchingContextCommand(searchString, contextCommands) ?: return null // Bad if too many matches

        val newStartIndex = if (matchingCommand.size == 1) endIndex - startIndex else startIndex

        val remainingTokens =
            iterativelyMatchTokens(input, contextCommands, newStartIndex, endIndex + 1) ?: return null // Propagate bad matches


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
        return input.matches(contextCommand.regex)
    }
}