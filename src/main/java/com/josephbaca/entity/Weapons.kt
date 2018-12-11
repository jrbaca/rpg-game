package com.josephbaca.entity


enum class Weapons(
    override val power: Int,
    override val description: String
) : Weapon {

    SWORD(5, "a sword"),
    AXE(4, "an axe"),
    STAFF(6, "Magic!"),
    KNIFE(3, "Edgy!"),
    THROWINGKNIFE(4, "still edgy, more deadly."),
    PISTOL(7, "Pew pew pew!"),
    SPECIALBOY(13, "a VERY special boy"),
    DEMONGALAXYMASTERSWORD(69, "bruh idek."),
    TWICESWORD(494, "yes or YES!");

}