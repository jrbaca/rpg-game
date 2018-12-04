package com.josephbaca.entity

class WeaponFactory {

    enum class WeaponType {
        SWORD, AXE, STAFF, KNIFE, THROWINGKNIFE, PISTOL
    }

    companion object {
        fun buildWeapon(weaponType: WeaponType): Weapon {
            return when (weaponType) {
                WeaponFactory.WeaponType.SWORD -> Weapon("sword", 5, "a sword")
                WeaponFactory.WeaponType.AXE -> Weapon("axe", 4, "an axe")
                WeaponFactory.WeaponType.STAFF -> Weapon("staff", 6, "Magic!")
                WeaponFactory.WeaponType.KNIFE -> Weapon("knife", 3, "Edgy!")
                WeaponFactory.WeaponType.THROWINGKNIFE -> Weapon(
                    "Throwing Knife", 4,
                    "still edgy, more deadly."
                )
                WeaponFactory.WeaponType.PISTOL -> Weapon("pistol", 7, "Pew pew pew!")

            }
        }

        fun buildRandomWeapon(): Weapon {
            return buildWeapon(WeaponType.values().random())
        }
    }

}
