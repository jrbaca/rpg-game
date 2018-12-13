package com.josephbaca.context

import com.josephbaca.parsing.ContextVerb

enum class BattleCommands(
    override val regex: Regex,
    override val numArgs: Int
) : ContextVerb {

    WHERE(Regex("where"), 0),
    FIGHT(Regex("fight"), 0);
}