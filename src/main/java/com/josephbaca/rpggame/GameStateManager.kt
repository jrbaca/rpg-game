package com.josephbaca.rpggame

import com.josephbaca.context.Context
import com.josephbaca.entity.Entity
import com.josephbaca.world.World
import java.util.*

/**
 * GameWrapper state tracking and context management.
 */
class GameStateManager {

    val contextStack = ArrayList<Context>()
    val currentContext: Context
        get() = contextStack.last()

    val player: Entity = Entity.buildFromScratch("player", 10, 1, "The player")
    val world: World = World(10, 10, this)

    val gameOver
        get() = !player.isAlive

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

    fun reducePlayerHealthToZero(): String {
        player.health = 0
        return "U ded now."
    }
}
