package com.josephbaca.rpggame


import com.josephbaca.world.Context

import java.util.ArrayList

/**
 * Internal representation of the context to manage current game context.
 */
class ContextManager {

    private val contextStack = ArrayList<Context>()

    val currentContext: Context
        get() = contextStack[contextStack.size - 1]

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
