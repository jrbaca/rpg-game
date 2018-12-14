package com.josephbaca.parsing

enum class GlobalVerbTokens(
    override val regex: Regex,
    override val numArgs: Int,
    override val helpUsage: String,
    override val helpString: String
) : VerbToken {

    HELP(Regex("help"), 0, "help", "Asks for commands."),
    NEWGAME(Regex("new game"), 0, "new game", "Starts a new game!");
}