package com.josephbaca.entity

class WeaponFactory {

    enum class WeaponType {
        SWORD, AXE, STAFF, KNIFE, THROWINGKNIFE, PISTOL, SPECIALBOY, DEMONGALAXYMASTERSWORD, TWICESWORD
    }

    companion object {

        /**
         * Builds a specified weapon from [WeaponType].
         */
        fun buildWeapon(weaponType: WeaponType): Weapon {
            return when (weaponType) {
                WeaponType.SWORD -> Weapon("sword", 5, "a sword")
                WeaponType.AXE -> Weapon("axe", 4, "an axe")
                WeaponType.STAFF -> Weapon("staff", 6, "Magic!")
                WeaponType.KNIFE -> Weapon("knife", 3, "Edgy!")
                WeaponType.THROWINGKNIFE -> Weapon(
                    "Throwing Knife",
                    4,
                    "still edgy, more deadly."
                )
                WeaponType.PISTOL -> Weapon("pistol", 7, "Pew pew pew!")
                WeaponType.SPECIALBOY -> Weapon("Special Boy", 13, "a VERY special boy")
                WeaponType.DEMONGALAXYMASTERSWORD -> Weapon(
                    "Demon Galaxy Master Galaxy Demon Master Sword",
                    69,
                    "bruh idek."
                )
                WeaponType.TWICESWORD -> Weapon("Twice Sword", 494, "yes or YES!")
            }
        }

        /**
         * Builds any random [WeaponType].
         */
        fun buildAnyRandomWeapon(): Weapon {
            return buildWeapon(WeaponType.values().random())
        }

        /**
         * Builds any random [WeaponType] that is specifically in [allowedWeaponSet].
         */
        fun buildWeaponWithWhitelist(allowedWeaponSet: Set<WeaponType>): Weapon {
            return buildWeapon(allowedWeaponSet.random())
        }

        /**
         * Builds any random [WeaponType] that is not in [blacklistedWeaponSet].
         */
        fun buildWeaponWithBlacklist(blacklistedWeaponSet: Set<WeaponType>): Weapon {
            return buildWeapon(WeaponType.values().toSet().minus(blacklistedWeaponSet).random())
        }

        fun buildRandomCommonWeapon(): Weapon {
            return buildWeaponWithBlacklist(
                setOf(
                    WeaponType.SPECIALBOY,
                    WeaponType.DEMONGALAXYMASTERSWORD,
                    WeaponType.TWICESWORD
                )
            )
        }
    }

}
