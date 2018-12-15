package com.josephbaca.world

import com.josephbaca.rpggame.GameStateManager
import com.josephbaca.context.Room
import com.josephbaca.context.RoomNouns

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every [Room] and the current location
 * of the player.
 */
class World(maxx: Int, maxy: Int, private val gameStateManager: GameStateManager) {

    private val worldGrid: CoordinateGrid<Room> = CoordinateGrid(maxx, maxy)

    private var playerCoordinates: Coordinate = Coordinate(0, 0)
        set(value) {
            if (value.x in 0 until worldGrid.sizeX && value.y in 0 until worldGrid.sizeY) {
                LOG.info(
                    "Player moving from (%s, %s) to (%s, %s)"
                        .format(playerCoordinates.x, playerCoordinates.y, value.x, value.y)
                )
                field = value
                gameStateManager.replaceContextLayer(currentRoom)
            } else {
                LOG.info(
                    "Player tried to walk off the world. Still at (%s, %s)."
                        .format(playerCoordinates.x, playerCoordinates.y)
                )
            }
        }

    var playerOrientation: Directions = Directions.NORTH

    private val currentRoom: Room
        get() = worldGrid.getCoordinate(playerCoordinates)

    init {
        generateWorld()
        gameStateManager.addContextLayer(currentRoom) // Add current room to gameStateManager
        LOG.debug("Added current room (%s) to context.".format(currentRoom))
        LOG.debug("Context is: %s".format(gameStateManager.contextStack))
    }

    /**
     * World generator function.
     */
    private fun generateWorld() {
        for (x in 0 until worldGrid.sizeX) {
            for (y in 0 until worldGrid.sizeY) {
                val r = Room(gameStateManager)
                worldGrid.setCoordinate(Coordinate(x, y), r)
            }
        }
    }

    fun movePlayer(relativeDirection: RoomNouns): String? {

        val oldPlayerOrientation = playerOrientation
        val oldPlayerCoords = playerCoordinates

        playerOrientation = getNewPlayerOrientation(relativeDirection) ?: return null
        playerCoordinates = getNewPlayerCoords()

        return if (oldPlayerCoords == playerCoordinates) {
            playerOrientation = oldPlayerOrientation
            "Oh heck! Sorry there's a wall there..."
        } else {
            "Okay cool you went that way!"
        }
    }

    private fun getNewPlayerOrientation(relativeDirection: RoomNouns): World.Directions? {

        return when (relativeDirection) {
            RoomNouns.FORWARD -> playerOrientation
            RoomNouns.BACK -> playerOrientation.back()
            RoomNouns.RIGHT -> playerOrientation.right()
            RoomNouns.LEFT -> playerOrientation.left()
            else -> return null // Didn't pass a direction
        }
    }

    private fun getNewPlayerCoords(): Coordinate {
        return when (playerOrientation) {
            Directions.NORTH -> Coordinate(playerCoordinates.x, playerCoordinates.y + 1)
            Directions.SOUTH -> Coordinate(playerCoordinates.x, playerCoordinates.y - 1)
            Directions.EAST -> Coordinate(playerCoordinates.x + 1, playerCoordinates.y)
            Directions.WEST -> Coordinate(playerCoordinates.x - 1, playerCoordinates.y)
        }
    }

    override fun toString(): String {
        return "World (%sx%s)".format(worldGrid.sizeX, worldGrid.sizeY)
    }

    enum class Directions {
        EAST,
        WEST,
        NORTH,
        SOUTH;

        fun back(): Directions {
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
