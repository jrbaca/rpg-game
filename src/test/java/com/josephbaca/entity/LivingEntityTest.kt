package com.josephbaca.entity

import org.junit.jupiter.api.Test
import org.quicktheories.QuickTheory.qt
import org.quicktheories.generators.SourceDSL.integers
import kotlin.math.max
import kotlin.math.min

internal class LivingEntityTest {

    @Test
    fun settingAppropriateHealthWorks() {
        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, j ->
                val set = min(i, j) // Set lesser value for setting
                val max = max(i, j) // Use greater value for maxHealth

                val h: LivingEntity = Humanoid("name", max, "description", 1)
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

                val h: LivingEntity = Humanoid("name", max, "description", 1)
                h.health = set

                LOG.info("Setting health to %s. Max is %s".format(set, max))
                LOG.info("Health is %s".format(h.health))

                h.health == max
            }
    }

    @Test
    fun settingHealthBelowZeroSetsToMax() {
        qt().forAll(
            integers().allPositive(),
            integers().allPositive()
        )
            .check { i, max ->
                val set = i * -1 // Set value below zero for setting

                val h: LivingEntity = Humanoid("name", max, "description", 1)
                h.health = set

                LOG.info("Setting health to %s. Max is %s".format(set, max))
                LOG.info("Health is %s".format(h.health))

                h.health == 0
            }
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(LivingEntityTest::class.java)
    }
}