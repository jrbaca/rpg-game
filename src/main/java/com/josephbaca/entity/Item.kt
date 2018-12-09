package com.josephbaca.entity

/**
 * Abstract class to differentiate Items from general entities. These can be stored in a
 * [com.josephbaca.util.Inventory], for example.
 */
abstract class Item protected constructor(name: String, description: String) : Entity(name, description)
