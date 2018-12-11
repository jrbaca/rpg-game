package com.josephbaca.util

import com.josephbaca.item.Weapon
import com.josephbaca.item.Weapons
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class InventoryTest {

    @Test
    fun testAdd() {
        val newInventory = Inventory()
        val aSword = Weapon.buildWeapon(Weapons.SWORD)

        newInventory.addItem(aSword)
        LOG.info(newInventory.toString())

        assertTrue(newInventory.contains(aSword))
    }

    @Test
    fun testRemoveItem() {
        val newInventory = Inventory()

        val coolersword = Weapon.buildWeapon(Weapons.SWORD)
        val coolstaff = Weapon.buildWeapon(Weapons.STAFF)

        newInventory.addItem(coolersword)
        newInventory.addItem(coolstaff)

        // test both are in inventory
        assertTrue(newInventory.contains(coolstaff))
        assertTrue(newInventory.contains(coolersword))

        newInventory.removeItem(coolersword)

        // test staff is in, sword is not in
        assertTrue(newInventory.contains(coolstaff))
        assertFalse(newInventory.contains(coolersword))

        LOG.info(newInventory.toString())

    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(InventoryTest::class.java)
    }
}