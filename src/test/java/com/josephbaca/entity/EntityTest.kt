package com.josephbaca.entity

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

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(EntityTest::class.java)
    }
}