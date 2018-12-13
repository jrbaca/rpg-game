package com.josephbaca.context

import com.josephbaca.parsing.ContextCommand

/**
 * Interface for any class that can be used in contextManager in the parsing etc.
 */
interface Context {

    /**
     * Commands that can be executed. Is null if given invalid arguments
     */
    val contextCommands: Map<ContextCommand, (List<ContextCommand>) -> String?>

    fun currentContext(): String
}
