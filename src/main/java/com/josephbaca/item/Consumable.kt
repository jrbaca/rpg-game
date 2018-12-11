package com.josephbaca.item

/**
 * Interface for [Consumables]. The static builders directly initialize the enums, so all Consumables are the same
 * in memory, reducing the footprint.
 */
interface Consumable {
    val healing: Int
}
