package com.josephbaca.parsing

enum class GlobalVerbTokens(
    override val regex: Regex,
    override val numArgs: Int
) : VerbToken {

    HELP(Regex("help"), 0),
    NEWGAME(Regex("new game"), 0);
}