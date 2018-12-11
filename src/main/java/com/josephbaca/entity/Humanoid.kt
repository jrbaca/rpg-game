package com.josephbaca.entity

/**
 * A humanoid living entity. The most simple of enemy/player classes.
 */
class Humanoid(name: String, maxHealth: Int, description: String, strength: Int) :
    Entity(name, maxHealth, description) {

    private var weapon = Weapon.buildAnyRandomWeapon()

    private val strength = 1

    override val attackDamage: Int
        get() = strength * weapon.power
}
