package com.josephbaca.entity

import java.util.ArrayList

/**
 * Any entity that is alive, meaning it can take actions.
 */
abstract class LivingEntity protected constructor
(name: String, var health: Int, description: String) : Entity(name, description) {

    private val inventory = Inventory()
    abstract val attackDamage: Int

    /**
     * Holds a living entity's [Item].
     */
    internal inner class Inventory {

        private val inventory = ArrayList<Item>()

        fun add(item: Item) {
            inventory.add(item)
        }

    }
}
