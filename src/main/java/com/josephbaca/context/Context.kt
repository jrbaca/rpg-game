package com.josephbaca.context

import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken
import com.josephbaca.parsing.Token

/**
 * Interface for any class that can be used in contextManager in the parsing etc.
 */
interface Context {

    /**
     * Commands that can be executed. Is null if given invalid arguments
     */
    val verbsToken: Map<VerbToken, (List<NounToken>) -> String?>

    val nounTokens: Set<NounToken>

    val tokens: Set<Token>
        get() = verbsToken.keys.toSet().plus(nounTokens)

    fun currentContext(): String
}
