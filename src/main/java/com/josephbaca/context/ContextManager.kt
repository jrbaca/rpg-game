package com.josephbaca.context

import com.josephbaca.entity.Entity
import java.util.*

/**
 * Internal representation of the contextManager to manage current game contextManager.
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
}
