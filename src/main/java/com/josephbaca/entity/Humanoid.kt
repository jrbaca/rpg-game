package com.josephbaca.entity

/**
 * A humanoid living entity. The most simple of enemy/player classes.
 */
class Humanoid(name: String, health: Int, description: String, strength: Int) :
    LivingEntity(name, health, description) {

    var weapon = WeaponFactory.buildRandomWeapon()

    private val strength = 1

    override val attackDamage: Int
        get() = strength * weapon.power
}
