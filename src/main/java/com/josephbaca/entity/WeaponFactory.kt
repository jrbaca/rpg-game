package com.josephbaca.entity

class WeaponFactory {

    enum class WeaponType {
        SWORD, AXE
    }

    companion object {
        fun buildWeapon(weaponType: WeaponType): Weapon {
            return when (weaponType) {
                WeaponFactory.WeaponType.SWORD -> Weapon("sword", 5, "a sword")
                WeaponFactory.WeaponType.AXE -> Weapon("axe", 3, "an axe")
            }
        }

        fun buildRandomWeapon(): Weapon {
            return buildWeapon(WeaponType.values().random())
        }
    }

}
