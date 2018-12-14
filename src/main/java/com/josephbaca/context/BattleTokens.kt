package com.josephbaca.context

import com.josephbaca.parsing.VerbToken

enum class BattleCommands(
    override val regex: Regex,
    override val numArgs: Int,
    override val helpUsage: String,
    override val helpString: String
) : VerbToken {

    FIGHT(Regex("fight"), 0, "fight", "");
}