package com.josephbaca.parser

import com.josephbaca.context.Context

/**
 * Object used for both parsing and tokenizing user inputs given their current [Context].
 * Use [parseInputWithCurrentContext] to do so.
 */
object Parser {

    private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

    /**
     * Uses the [Tokenizer] to tokenize an input using the [ContextCommand] from the [Context]. It then
     * validates the tokens and invokes the appropriate commands.
     */
    internal fun parseInputWithCurrentContext(input: String, context: Context): String {

        val tokenizedInput = Tokenizer.tokenizeInputWithContextCommandsRegex(input, context.contextCommands.keys)

        val parsedInput = if (tokenizedInput != null) {
            parseTokens(context, tokenizedInput)
        } else {
            null
        }

        return invokeParsedInput(parsedInput)
    }

    private fun parseTokens(context: Context, tokens: List<ContextCommand>): (() -> String)? {
        LOG.info("Attempting to parse tokens %s from context %s".format(tokens, context))
        return context.contextCommands[tokens[0]]
    }

    private fun invokeParsedInput(parsedInput: (() -> String)?): String {
        return parsedInput?.invoke() ?: getUnknownCommandString()
    }

    private fun getUnknownCommandString(): String {
        LOG.warn("Cannot parse tokens.")
        return listOf(
            "i dont think youre old enough to access that content mister",
            "i dont think youre old enough for that mister",
            "No no no I don't think so!",
            "Nah man, not today",
            "Baby shark, doo doo doo doo doo doo"
        ).random()
    }
}
