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
        return tokenizeUsingFullMatch(preProcessedInput, contextCommands)
    }

    private fun preProcessInput(input: String): String {
        return input.toLowerCase()
    }

    private fun tokenizeUsingFullMatch(
        input: String,
        contextCommands: Set<ContextCommand>
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

//    private fun iterativelyMatchTokens(input: String, contextCommands: Set<ContextCommand>): List<ContextCommand>? {
//
//    }

    private fun inputMatchesContextCommandRegex(input: String, contextCommand: ContextCommand): Boolean {
        return input.matches(contextCommand.regex)
    }
}