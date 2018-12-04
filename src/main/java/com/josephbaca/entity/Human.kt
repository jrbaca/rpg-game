package com.josephbaca.entity

/**
 * A humanoid living entity. The most simple of enemy/player classes.
 */
class Human(name: String, health: Int) : LivingEntity(name, health, "A human.") {

    var weapon = WeaponFactory.buildRandomWeapon()
    private val strength = 1

    override val attackDamage: Int
        get() = strength * weapon.power
}
