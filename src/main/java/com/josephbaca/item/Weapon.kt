package com.josephbaca.item

/**
 * Interface for [Weapons]. The static builders directly initialize the enums, so all weapons are the same in memory,
 * reducing the footprint.
 */
interface Weapon : Item {

    val power: Int


    companion object {

        fun buildWeapon(weaponType: Weapons): Weapon {
            return weaponType
        }

        fun buildAnyRandomWeapon(): Weapon {
            return Weapons.values().random()
        }

        /**
         * Builds a [Weapon] from [Weapons] that is specifically in [allowedWeaponSet].
         */
        fun buildWeaponWithWhitelist(allowedWeaponSet: Set<Weapons>): Weapon {
            return buildWeapon(allowedWeaponSet.random())
        }

        /**
         * Builds a [Weapon] from [Weapons] that is not in [blacklistedWeaponSet].
         */
        fun buildWeaponWithBlacklist(blacklistedWeaponSet: Set<Weapons>): Weapon {
            return buildWeapon(
                Weapons.values().toSet().minus(
                    blacklistedWeaponSet
                ).random()
            )
        }


        fun buildRandomCommonWeapon(): Weapon {
            // TODO make this based on a property instead of a blacklist
            return buildWeaponWithBlacklist(
                setOf(
                    Weapons.SPECIALBOY,
                    Weapons.DEMONGALAXYMASTERSWORD,
                    Weapons.TWICESWORD
                )
            )
        }
    }
}