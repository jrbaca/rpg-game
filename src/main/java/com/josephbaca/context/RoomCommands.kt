package com.josephbaca.context

import com.josephbaca.parsing.ContextCommand

enum class RoomCommands(
    override val regex: Regex,
    override val numArgs: Int
) : ContextCommand {

    WHERE(Regex("where"), 0),

    GO(Regex("go"), 1),
    UP(Regex("up"), 0),
    DOWN(Regex("down"), 0),
    LEFT(Regex("left"), 0),
    RIGHT(Regex("right"), 0),

    FIGHT(Regex("fight"), 0),
    INVENTORY(Regex("inventory"), 0),
    WHAT(Regex("what"), 0),
    WHO(Regex("who"), 0);
}