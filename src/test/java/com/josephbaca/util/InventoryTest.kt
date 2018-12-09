package com.josephbaca.util

import com.josephbaca.entity.WeaponFactory
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class InventoryTest {

    @Test
    fun testAdd() {
        val newInventory = Inventory()
        val aSword = WeaponFactory.buildWeapon(WeaponFactory.WeaponType.SWORD)

        newInventory.additem(aSword)
        LOG.info(newInventory.toString())

        assertTrue(newInventory.contains(aSword))
    }

    @Test
    fun testRemoveItem() {
        val newInventory = Inventory()

        val coolersword = WeaponFactory.buildWeapon(WeaponFactory.WeaponType.SWORD)
        val coolstaff = WeaponFactory.buildWeapon(WeaponFactory.WeaponType.STAFF)

        newInventory.additem(coolersword)
        newInventory.additem(coolstaff)

        // test both are in inventory
        assertTrue(newInventory.contains(coolstaff))
        assertTrue(newInventory.contains(coolersword))

        newInventory.removeitem(coolersword)

        // test staff is in, sword is not in
        assertTrue(newInventory.contains(coolstaff))
        assertFalse(newInventory.contains(coolersword))

        LOG.info(newInventory.toString())

    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(InventoryTest::class.java)
    }
}