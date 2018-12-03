package com.josephbaca.rpggame


import com.josephbaca.world.Context

import java.util.ArrayList

/**
 * Internal representation of the contextManager to manage current game contextManager.
 */
class ContextManager {

    val contextStack = ArrayList<Context>()

    val currentContext: Context
        get() = contextStack.last()

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
