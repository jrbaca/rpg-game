package com.josephbaca.context

import com.josephbaca.parsing.ContextNoun
import com.josephbaca.parsing.ContextVerb
import com.josephbaca.parsing.Token

/**
 * Interface for any class that can be used in contextManager in the parsing etc.
 */
interface Context {

    /**
     * Commands that can be executed. Is null if given invalid arguments
     */
    val contextVerbs: Map<ContextVerb, (List<ContextNoun>) -> String?>

    val contextNouns: Set<ContextNoun>

    val tokens: Set<Token>
        get() = contextVerbs.keys.toSet().plus(contextNouns)

    fun currentContext(): String
}
