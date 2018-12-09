package com.josephbaca.context

/**
 * Interface for any class that can be used in contextManager in the parser etc.
 */
interface Context {

    val commands: HashMap<String, () -> String> // Commands that can be executed

    fun runInput(input: String): String {
        return commands[input]?.invoke() ?: "Unknown command"
    }

    fun whereAt(): String
}
