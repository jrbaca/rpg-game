package com.josephbaca.util

import com.josephbaca.item.Item

class Inventory {

    private val inventory = ArrayList<Item>()

    fun addItem(item: Item) {
        inventory.add(item)
    }

    fun removeItem(item: Item) {
        if (inventory.contains(item)) inventory.remove(item)
    }

    fun contains(item: Item): Boolean {
        return inventory.contains(item)
    }

    override fun toString(): String {
        return "Inventory (%s)".format(inventory.toString())
    }

}