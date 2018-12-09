package com.josephbaca.entity

import com.josephbaca.util.Inventory

/**
 * Any entity that is alive, meaning it can take actions.
 */
abstract class LivingEntity protected constructor
    (name: String, health: Int, description: String) : Entity(name, description) {

    var health: Int = health
        set(value) {
            field = if (value < 0) 0 else field
        }
    private val inventory = Inventory()
    abstract val attackDamage: Int

}
