package com.josephbaca.entity

import com.josephbaca.util.Inventory

/**
 * Any entity that is alive, meaning it can take actions.
 */
abstract class Entity protected constructor
    (val name: String, val maxHealth: Int, val description: String) {

    val isAlive: Boolean
        get() = health > 0

    var health: Int = maxHealth
        set(value) {
            field = if (value < 0) 0 else if (value > maxHealth) maxHealth else value
        }
    val inventory = Inventory()
    abstract val attackDamage: Int

    override fun toString(): String {
        return "%s(%s/%s)".format(name, health, maxHealth)
    }
}
