package com.josephbaca.context

import com.josephbaca.parsing.NounToken
import com.josephbaca.parsing.VerbToken

enum class RoomVerbs(
    override val regex: Regex,
    override val numArgs: Int,
    override val helpUsage: String,
    override val helpString: String
) : VerbToken {

    GO(Regex("go"), 1, "Go <direction>", "Takes you in a direction. direction=[up, down, left, right]"),
    FIGHT(Regex("fight"), 0, "Fight", "Starts a fight with the enemy!"),
    INVENTORY(Regex("inventory"), 0, "Inventory", "Tells you what's in your inventory!"),
    WHAT(Regex("what"), 0, "What", "Lets you know what the room you're in looks like!"),
    ENEMIES(Regex("enemies"), 0, "Enemies", "Tells you if (and sometimes how many) bad guys there are."),
    WHO(Regex("who"), 0, "Who", "Tells you which bad guy is there!");
}

enum class RoomNouns(
    override val regex: Regex
) : NounToken {
    UP(Regex("up")),
    DOWN(Regex("down")),
    LEFT(Regex("left")),
    RIGHT(Regex("right")),
}