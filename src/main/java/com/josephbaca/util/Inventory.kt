package com.josephbaca.util

import com.josephbaca.entity.Item

class Inventory {

    private val inventory = ArrayList<Item>()

    fun additem(item: Item) {
        inventory.add(item)
    }

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