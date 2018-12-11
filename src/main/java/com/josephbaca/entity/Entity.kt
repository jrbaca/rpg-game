package com.josephbaca.entity

import com.josephbaca.util.Inventory

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

    val inventory: Inventory
    val attackDamage: Int

    companion object {
        fun buildRandomDude(): Entity {
            return Humanoids.values().random()
        }
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