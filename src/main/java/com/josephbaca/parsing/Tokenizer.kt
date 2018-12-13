package com.josephbaca.parsing

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

    private fun iterativelyMatchTokens(
        input: String,
        contextCommands: Set<ContextCommand>
    ): List<ContextCommand>? {

        val foundTokens: MutableList<ContextCommand> = mutableListOf()
        val matchedStrings: MutableList<String> = mutableListOf()

        var startIndex = 0
        for (endIndex in 0..input.length) {
            val searchString = input.substring(startIndex, endIndex).trim()
            LOG.debug("Searching \"%s\" from \"%s\"".format(searchString, input))

            val matchingCommand =
                getMatchingContextCommand(searchString, contextCommands) ?: return null // Bad if too many matches

            if (!matchingCommand.isEmpty()) {
                startIndex = endIndex
                foundTokens.add(matchingCommand.single())
                matchedStrings.add(searchString)
            }
        }
        return if (matchedStrings.joinToString(" ") == input) foundTokens else null // Didn't fully tokenize
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