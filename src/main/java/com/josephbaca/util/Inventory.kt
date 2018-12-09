package com.josephbaca.util

import com.josephbaca.entity.Item

/**
 *Hold a list of what's in the inventory
 */
class Inventory {

    private val inventory = ArrayList<Item>()

    /**
     * additem adds an item to inventory
     */
    fun additem(item: Item) {
        inventory.add(item)
    }

    /**
     * This removes an item from inventory if it's in it and returns new inventory
     */
    fun removeitem(item: Item) {
        if (inventory.contains(item)) inventory.remove(item)
    }

    fun contains(item: Item): Boolean {
        return inventory.contains(item)
    }

    override fun toString(): String {
        return "Inventory (%s)".format(inventory.toString())
    }

}