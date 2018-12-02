package com.josephbaca.rpggame

import com.josephbaca.entity.Human
import com.josephbaca.world.World

/**
 * Main game class that holds the input processing and game state management.
 */
class Game {

    val world: World
    private val player = Human("player", 10)
    val context = ContextManager()

    init {
        world = World("the dungeon", 10, 10, context)
    }

    internal fun input(input: String): String {
        return Parser.parseInput(input, context.currentContext)
    }
}