package com.josephbaca.entity

import com.josephbaca.item.Weapon

/**
 * A data implementation of [Entity]. This class is used to provide backing data for premade [Entity] of this type.
 */
enum class Humanoids(
    override val maxHealth: Int,
    override val description: String,
    private val strength: Int
) : EntityData {
    HUMAN(10, "he walk", 1),
    ZOMBIE(3, "he uglyyyyyyyyyyyyyyyyy", 2),
    JOHNTHESKELETON(7, "he once was a boy :(", 2),
    SKELETON(2, "idk a non john skeleton", 2),
    GIANTBEETLE(2, "The actual spookiest boi alive", 1),
    DRAGON(13, "He's just trying to protec his room.", 3),

    
    ;

    private var weapon = Weapon.buildAnyRandomWeapon()
    override val attackDamage: Int
        get() = strength * weapon.power

}
