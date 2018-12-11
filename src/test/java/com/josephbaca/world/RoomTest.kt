package com.josephbaca.world

import com.josephbaca.rpggame.Game
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RoomTest {

    @Test
    fun testRoomMovement() {

        val commands = listOf("up", "right", "left", "down")

        val success = commands.map { command ->
            List(10) { false }.map {
                val g = Game()

                // Ensure not at edge of world
                g.input("up")
                g.input("right")

                val description1 = g.input("what")
                val description2 = g.input(command)

                description1 != description2
            }
        }

        for (llb in success) {
            LOG.info(llb.toString())
            assertTrue(llb.fold(false) { l, r -> l || r })
        }
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(RoomTest::class.java)
    }

}