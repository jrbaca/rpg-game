package com.josephbaca.parsing

enum class GlobalVerbTokens(
    override val regex: Regex,
    override val numArgs: Int,
    override val helpUsage: String,
    override val helpString: String
) : VerbToken {

    HELP(Regex("help"), 0, "help", "Asks for commands."),
    KILL(Regex("kill"), 0, "kill", "Kill yourself to start a new game!");
}