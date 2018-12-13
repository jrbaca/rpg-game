package com.josephbaca.context

import com.josephbaca.parsing.ContextCommand

/**
 * Interface for any class that can be used in contextManager in the parsing etc.
 */
interface Context {

    /**
     * Commands that can be executed
     */
    val contextCommands: Map<ContextCommand, () -> String>

    fun currentContext(): String
}
