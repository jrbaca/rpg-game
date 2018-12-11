package com.josephbaca.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.quicktheories.QuickTheory.qt
import org.quicktheories.generators.SourceDSL.integers

internal class EntityTest {

    @Test
    fun settingAppropriateHealthWorks() {
        val h: Entity = Humanoids.HUMAN

        qt().forAll(
            integers().from(0).upToAndIncluding(h.maxHealth)
        )
            .check { set ->
                h.health = set
                h.health == set
            }
    }

    @Test
    fun settingHealthAboveMaxSetsToMax() {
        val h: Entity = Humanoids.HUMAN

        qt().forAll(
            integers().between(h.maxHealth, 10000)
        )
            .check { set ->

                h.health = set
                h.health == h.maxHealth
            }
    }

    @Test
    fun settingHealthBelowZeroSetsToZero() {
        val h: Entity = Humanoids.HUMAN
        qt().forAll(
            integers().between(-10000, 0)
        )
            .check { set ->
                h.health = set
                h.health == 0
            }
    }

    @Test
    fun settingHealthOnMultipleOfOneTypeWorks() {
        val human1: Entity = Humanoids.HUMAN
        val human2: Entity = Humanoids.HUMAN

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