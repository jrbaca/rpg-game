package com.josephbaca.item

/**
 * All of the specific Consumables that can be created. Implements [Consumable].
 */
enum class Consumables(
    override val healing: Int
) : Consumable {

    HEALINGPOTION(2);
}