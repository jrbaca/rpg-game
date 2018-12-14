package com.josephbaca.context

import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken

enum class RoomVerbs(
    override val regex: Regex,
    override val numArgs: Int
) : VerbToken {

    GO(Regex("go"), 1),
    FIGHT(Regex("fight"), 0),
    INVENTORY(Regex("inventory"), 0),
    WHAT(Regex("what"), 0),
    WHO(Regex("who"), 0);
}

enum class RoomNouns(
    override val regex: Regex
) : NounToken {
    UP(Regex("up")),
    DOWN(Regex("down")),
    LEFT(Regex("left")),
    RIGHT(Regex("right")),
}