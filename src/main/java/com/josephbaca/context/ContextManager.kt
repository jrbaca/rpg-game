package com.josephbaca.context

import com.josephbaca.entity.Entity
import com.josephbaca.world.World
import java.util.*

/**
 * Game state tracking and context management.
 */
class ContextManager {

    var gameOver = false
    val contextStack = ArrayList<Context>()
    val currentContext: Context
        get() = contextStack.last()

    val player: Entity = Entity.buildFromScratch("player", 10, 1, "The player")
    val world: World = World(10, 10, this)

    fun addContextLayer(c: Context) {
        contextStack.add(c)
    }

    fun removeContextLayer() {
        contextStack.removeAt(contextStack.size - 1)
    }

    fun replaceContextLayer(c: Context) {
        removeContextLayer()
        addContextLayer(c)
    }

    fun newGame(): String {
        gameOver = true
        return "New game started!"
    }
}
