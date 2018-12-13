package com.josephbaca.context

import com.josephbaca.parsing.VerbToken

enum class BattleCommands(
    override val regex: Regex,
    override val numArgs: Int
) : VerbToken {

    WHERE(Regex("where"), 0),
    FIGHT(Regex("fight"), 0);
}