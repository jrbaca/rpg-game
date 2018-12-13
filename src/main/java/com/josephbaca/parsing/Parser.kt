package com.josephbaca.parsing

import com.josephbaca.context.Context

/**
 * Object used for both parsing and tokenizing user inputs given their current [Context].
 * Use [parseInputWithCurrentContext] to do so.
 */
object Parser {

    private val LOG = org.slf4j.LoggerFactory.getLogger(this::class.java)

    /**
     * Uses the [Tokenizer] to tokenize an input using the [ContextVerb] from the [Context]. It then
     * validates the tokens and invokes the appropriate commands.
     */
    internal fun parseInputWithCurrentContext(input: String, context: Context): String {

        val tokenizedInput = Tokenizer.tokenizeInputWithContextCommandsRegex(input, context.tokens)

        val parsedInput = if (tokenizedInput != null) {
            parseTokens(context, tokenizedInput)
        } else {
            LOG.warn("Tokenizer failed")
            null
        }

        return invokeParsedInput(parsedInput)
    }

    private fun parseTokens(context: Context, tokens: List<Token>): (() -> String?)? {
        LOG.info("Attempting to parse tokens %s from context %s".format(tokens, context))
        return parseTokensHelper(context, tokens)?.getInvocable()
    }

    private fun parseTokensHelper(context: Context, tokens: List<Token>): TokenWithArgs? {
//        val numArgs = tokens[0].numArgs
        val verbToken = tokens[0] as? ContextVerb ?: return null
        val nounTokens = tokens.drop(1).filterIsInstance<ContextNoun>()
            .apply { if (size != tokens.size - 1) return null }

        return TokenWithArgs(context, verbToken, nounTokens)
    }

    private fun invokeParsedInput(parsedInput: (() -> String?)?): String {
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

    private class TokenWithArgs(
        private val context: Context,
        private val contextVerb: ContextVerb,
        private val args: List<ContextNoun>
    ) {
        private val numArgs = contextVerb.numArgs

        fun getInvocable(): () -> String? {
            val command = context.contextVerbs[contextVerb]

            return if (contextVerb.numArgs == numArgs) {
                { command?.invoke(args) }
            } else {
                { null }
            }
        }
    }
}
