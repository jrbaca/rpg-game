package com.josephbaca.context

/**
 * Interface for any class that can be used in contextManager in the parser etc.
 */
interface Context {

    val commands: HashMap<String, () -> String> // Commands that can be executed

    fun runInput(input: String): String {
        return commands[input]?.invoke() ?: listOf(
            "i dont think youre old enough to access that content mister",
            "i dont think youre old enough for that mister",
            "No no no I don't think so!",
            "Nah man, not today",
            "Baby shark, doo doo doo doo doo doo"
        ).random()
    }

    fun currentContext(): String
}
