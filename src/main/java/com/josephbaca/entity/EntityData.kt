package com.josephbaca.entity

/**
 * Interface that allows for easy enum based customization of [Entity].
 */
interface EntityData {
    val name: String
    val maxHealth: Int
    val attackDamage: Int
    val description: String

}