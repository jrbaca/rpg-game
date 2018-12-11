package com.josephbaca.entity

import com.josephbaca.util.Inventory

/**
 * An [Entity] that can take action. Can be backed by by an enum implementing [EntityData] if a premade one is desired.
 */
class Entity private constructor(
    val name: String,
    val maxHealth: Int,
    val attackDamage: Int,
    val description: String
) {

    private constructor(entityData: EntityData) : this(
        entityData.name,
        entityData.maxHealth,
        entityData.attackDamage,
        entityData.description
    )

    var health: Int = maxHealth
        set(value) {
            field = if (value < 0) 0 else if (value > maxHealth) maxHealth else value
        }
    val isAlive: Boolean
        get() = health > 0

    val inventory = Inventory()

    override fun toString(): String {
        return "%s(%s/%s)".format(name, health, maxHealth)
    }

    companion object {

        /**
         * Builds an [Entity] from pre-made values implementing [EntityData].
         */
        fun buildFromExisting(entityData: EntityData): Entity {
            return Entity(entityData)
        }

        /**
         * Builds an [Entity] using custom values.
         */
        fun buildFromScratch(name: String, maxHealth: Int, attackDamage: Int, description: String = ""): Entity {
            return Entity(name, maxHealth, attackDamage, description)
        }
    }
}