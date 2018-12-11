package com.josephbaca.entity

import com.josephbaca.util.Inventory

enum class Humanoids(
    override val maxHealth: Int,
    override val description: String,
    private val strength: Int
) : Entity {
    HUMAN(10, "The player", 1),
    ZOMBIE(3, "he uglyyyyyyyyyyyyyyyyy", 2),
    JOHNTHESKELETON(7, "he once was a boy :(", 2),
    SKELETON(2, "idk a non john skeleton", 2)
    ;

    override var health: Int = maxHealth
        set(value) {
            field = if (value < 0) 0 else if (value > maxHealth) maxHealth else value
        }

    override val inventory: Inventory = Inventory()

    private var weapon = Weapon.buildAnyRandomWeapon()
    override val attackDamage: Int
        get() = strength * weapon.power

}
