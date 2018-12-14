package com.josephbaca.world

import com.josephbaca.context.ContextManager
import com.josephbaca.context.Room
import com.josephbaca.context.RoomNouns

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every [Room] and the current location
 * of the player.
 */
class World(maxx: Int, maxy: Int, private val contextManager: ContextManager) {

    /**
     * Internal representation of the world.
     */
    private val grid: CoordinateGrid<Room> =
        CoordinateGrid(maxx, maxy)

    private var playerCoords: Coordinate = Coordinate(0, 0)
        set(value) {
            if (value.x >= 0 && value.x < grid.sizeX && value.y >= 0 && value.y < grid.sizeY) {
                LOG.info(
                    "Player moving from (%s, %s) to (%s, %s)"
                        .format(playerCoords.x, playerCoords.y, value.x, value.y)
                )
                field = value
                contextManager.replaceContextLayer(currentRoom)
            } else {
                LOG.info("Player tried to walk off the world of size (%s, %s).".format(grid.sizeX, grid.sizeY))
            }
        }

    var playerOrientation: Directions = Directions.NORTH

    private val currentRoom: Room
        get() = grid.getCoordinate(playerCoords)

    init {
        generateWorld()
        contextManager.addContextLayer(currentRoom) // Add current room to contextManager
        LOG.debug("Added current room (%s) to context.".format(currentRoom))
        LOG.debug("Context is: %s".format(contextManager.contextStack))
    }

    fun movePlayer(relativeDirection: RoomNouns): String? {

        val oldPlayerOrientation = playerOrientation
        val oldPlayerCoords = playerCoords

        playerOrientation = getNewPlayerOrientation(relativeDirection) ?: return null
        playerCoords = getNewPlayerCoords()

        return if (oldPlayerCoords == playerCoords) {
            playerOrientation = oldPlayerOrientation
            "Oh heck! Sorry there's a wall there..."
        } else {
            "Okay cool you went that way!"
        }
    }

    private fun getNewPlayerOrientation(relativeDirection: RoomNouns): World.Directions? {

        return when (relativeDirection) {
            RoomNouns.FORWARD -> playerOrientation
            RoomNouns.BACK -> playerOrientation.reverse()
            RoomNouns.RIGHT -> playerOrientation.right()
            RoomNouns.LEFT -> playerOrientation.left()
            else -> return null // Didn't pass a direction
        }
    }

    private fun getNewPlayerCoords(): Coordinate {
        return when (playerOrientation) {
            Directions.NORTH -> Coordinate(playerCoords.x, playerCoords.y + 1)
            Directions.SOUTH -> Coordinate(playerCoords.x, playerCoords.y - 1)
            Directions.EAST -> Coordinate(playerCoords.x + 1, playerCoords.y)
            Directions.WEST -> Coordinate(playerCoords.x - 1, playerCoords.y)
        }
    }

    /**
     * World generator function.
     */
    private fun generateWorld() {
        for (x in 0 until grid.sizeX) {
            for (y in 0 until grid.sizeY) {
                val r = Room(contextManager)
                grid.setCoordinate(Coordinate(x, y), r)
            }
        }
    }

    override fun toString(): String {
        return "World (%sx%s)".format(grid.sizeX, grid.sizeY)
    }

    /**
     * Returns a print friendly version of the world.
     */
    fun toDisplayString(): String {
        return grid.toDisplayString()
    }

    enum class Directions {
        EAST,
        WEST,
        NORTH,
        SOUTH;

        fun reverse(): Directions {
            return when (this) {
                EAST -> WEST
                WEST -> EAST
                NORTH -> SOUTH
                SOUTH -> NORTH
            }
        }

        fun right(): Directions {
            return when (this) {
                EAST -> SOUTH
                WEST -> NORTH
                NORTH -> EAST
                SOUTH -> WEST
            }
        }

        fun left(): Directions {
            return when (this) {
                EAST -> NORTH
                WEST -> SOUTH
                NORTH -> WEST
                SOUTH -> EAST
            }
        }
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(World::class.java)
    }
}
