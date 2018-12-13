package com.josephbaca.context

import com.josephbaca.parsing.ContextNoun
import com.josephbaca.parsing.ContextVerb

enum class RoomVerbs(
    override val regex: Regex,
    override val numArgs: Int
) : ContextVerb {

    WHERE(Regex("where"), 0),
    GO(Regex("go"), 1),
    FIGHT(Regex("fight"), 0),
    INVENTORY(Regex("inventory"), 0),
    WHAT(Regex("what"), 0),
    WHO(Regex("who"), 0);
}

enum class RoomNouns(
    override val regex: Regex
) : ContextNoun {
    UP(Regex("up")),
    DOWN(Regex("down")),
    LEFT(Regex("left")),
    RIGHT(Regex("right")),
}