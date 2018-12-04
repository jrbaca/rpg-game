package com.josephbaca.entity

import com.josephbaca.util.Inventory

/**
 * Any entity that is alive, meaning it can take actions.
 */
abstract class LivingEntity protected constructor
    (name: String, var health: Int, description: String) : Entity(name, description) {

    private val inventory = Inventory()
    abstract val attackDamage: Int

}
