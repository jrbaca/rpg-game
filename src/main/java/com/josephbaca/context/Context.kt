package com.josephbaca.context

import com.josephbaca.parser.ContextCommands

/**
 * Interface for any class that can be used in contextManager in the parser etc.
 */
interface Context {

    /**
     * Commands that can be executed
     */
    val contextCommands: Map<ContextCommands, () -> String>

    fun runInput(contextCommand: ContextCommands): String {
        return contextCommands[contextCommand]?.invoke() ?: getUnknownCommandString()
    }

    fun getUnknownCommandString(): String {
        return listOf(
            "i dont think youre old enough to access that content mister",
            "i dont think youre old enough for that mister",
            "No no no I don't think so!",
            "Nah man, not today",
            "Baby shark, doo doo doo doo doo doo"
        ).random()
    }

    fun currentContext(): String
}
