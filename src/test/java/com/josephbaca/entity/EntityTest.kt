package com.josephbaca.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.quicktheories.QuickTheory.qt
import org.quicktheories.generators.SourceDSL.integers
import java.lang.Integer.max
import java.lang.Integer.min

internal class EntityTest {

    @Test
    fun settingAppropriateHealthWorks() {
        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, j ->
                val set = min(i, j) // Set lesser value for setting
                val max = max(i, j) // Use greater value for maxHealth

                val h: Entity = Entity.buildFromScratch("name", max, 1)
                h.health = set

                LOG.info("Setting health to %s. Max is %s".format(set, max))
                LOG.info("Health is %s".format(h.health))

                h.health == set
            }
    }

    @Test
    fun settingHealthAboveMaxSetsToMax() {
        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, j ->
                val set = max(i, j) // Set greater value for setting
                val max = min(i, j) // Use lesser value for maxHealth

                val h: Entity = Entity.buildFromScratch("name", max, 1)
                h.health = set

                LOG.info("Setting health to %s. Max is %s".format(set, max))
                LOG.info("Health is %s".format(h.health))

                h.health == max
            }
    }

    @Test
    fun settingHealthBelowZeroSetsToZero() {
        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, max ->
                val set = i * -1 // Set value below zero for setting

                val h: Entity = Entity.buildFromScratch("name", max, 1)
                h.health = set

                LOG.info("Setting health to %s. Max is %s".format(set, max))
                LOG.info("Health is %s".format(h.health))

                h.health == 0
            }
    }

    @Test
    fun settingHealthOnMultipleOfOneTypeWorks() {
        val human1: Entity = Entity.buildFromExisting(Humanoids.HUMAN)
        val human2: Entity = Entity.buildFromExisting(Humanoids.HUMAN)

        assertEquals(10, human1.health)
        assertEquals(10, human2.health)

        human1.health = 8

        assertEquals(8, human1.health)
        assertEquals(10, human2.health)


    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(EntityTest::class.java)
    }
}