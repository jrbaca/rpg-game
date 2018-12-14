package com.josephbaca.context

import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken
import com.josephbaca.parsing.Token
import com.josephbaca.parsing.Parser

/**
 * Interface for any class that can be used in [ContextManager],  [Parser], etc.
 */
interface Context {

    /**
     * Tokens that are mapped to functions. Returns null if given invalid arguments.
     */
    val verbsToken: Map<VerbToken, (List<NounToken>) -> String?>

    /**
     * Tokens that are mapped to concepts or nothing.
     */
    val nounTokens: Set<NounToken>

    /**
     * Set of all tokens.
     */
    val tokens: Set<Token>
        get() = verbsToken.keys.toSet().plus(nounTokens)

    fun currentContext(): String
}
