package com.josephbaca.entity

import com.josephbaca.util.Inventory

interface Entity {

    val name: String
    val maxHealth: Int
    val description: String

    var health: Int

    val isAlive: Boolean
        get() = health > 0

    val inventory: Inventory
    val attackDamage: Int

    companion object {
        fun buildRandomDude(): Entity {
            return Humanoids.values().random()
        }
    }
}