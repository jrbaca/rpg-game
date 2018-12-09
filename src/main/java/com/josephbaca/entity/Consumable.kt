package com.josephbaca.entity

/**
 * Any item that can be consumed by an entity to induce some effect, usually healing.
 */
class Consumable(name: String, internal var healing: Int, description: String) : Item(name, description)
