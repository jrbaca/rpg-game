package com.josephbaca.entity

/**
 * A weapon to be used in an inventory, in fights, etc.
 */
class Weapon(name: String, val power: Int, description: String) : Item(name, description)
