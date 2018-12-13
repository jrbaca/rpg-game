package com.josephbaca.context

import com.josephbaca.parsing.ContextCommand

enum class BattleCommands(
    override val regex: Regex,
    override val numArgs: Int
) : ContextCommand {

    WHERE(Regex("where"), 0),
    FIGHT(Regex("fight"), 0);
}