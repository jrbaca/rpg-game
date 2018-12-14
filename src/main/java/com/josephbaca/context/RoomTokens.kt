package com.josephbaca.context

import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken

enum class RoomVerbs(
    override val regex: Regex,
    override val numArgs: Int,
    override val helpUsage: String,
    override val helpString: String
) : VerbToken {

    GO(Regex("go"), 1, "go <direction>", "Takes you in a direction. direction=[up, down, left, right]"),
    FIGHT(Regex("fight"), 0, "fight", "Starts a fight with the enemy!"),
    INVENTORY(Regex("inventory"), 0, "inventory", ""),
    WHAT(Regex("what"), 0, "what", ""),
    ENEMIES(Regex("enemies"), 0, "enemies", ""),
    WHO(Regex("who"), 0, "who", "");
}

enum class RoomNouns(
    override val regex: Regex
) : NounToken {
    UP(Regex("up")),
    DOWN(Regex("down")),
    LEFT(Regex("left")),
    RIGHT(Regex("right")),
}