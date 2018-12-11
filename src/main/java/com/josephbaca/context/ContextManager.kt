package com.josephbaca.context


import com.josephbaca.entity.Humanoid
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

    val player: Entity = Humanoid("player", 10, "The player", 1)

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

    fun insertContextLayer(c: Context, index: Int) {
        contextStack.add(index, c)
    }
}
