package com.josephbaca.context

import com.josephbaca.world.Coordinate
import com.josephbaca.world.CoordinateGrid
import com.josephbaca.world.Room

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every [Tile] and the current location
 * of the player.
 */
class World
    (private val name: String, maxx: Int, maxy: Int, private val contextManager: ContextManager) :
    Context {

    /**
     * Internal representation of the world.
     */
    private val grid: CoordinateGrid<Room> =
        CoordinateGrid(maxx, maxy)

    /**
     * Player coordinates.
     */
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

    private val currentRoom: Room
        get() = grid.getCoordinate(playerCoords)

    override fun runInput(input: String): String {
        LOG.error("Should never run user commands on this context")
        return "Error"
    }

    init {
        generateWorld()
        contextManager.addContextLayer(currentRoom) // Add current room to contextManager
        LOG.debug("Added current room (%s) to context.".format(currentRoom))
        LOG.debug("Context is: %s".format(contextManager.contextStack))
    }

    /**
     * Move the player up one unit.
     */
    fun movePlayerUp() {
        playerCoords = Coordinate(playerCoords.x, playerCoords.y + 1)
    }

    /**
     * Move the player down one unit.
     */
    fun movePlayerDown() {
        playerCoords = Coordinate(playerCoords.x, playerCoords.y - 1)
    }

    /**
     * Move the player right one unit.
     */
    fun movePlayerRight() {
        playerCoords = Coordinate(playerCoords.x + 1, playerCoords.y)
    }

    /**
     * Move the player left one unit.
     */
    fun movePlayerLeft() {
        playerCoords = Coordinate(playerCoords.x - 1, playerCoords.y)
    }

    /**
     * World generator function.
     */
    private fun generateWorld() {
        for (x in 0 until grid.sizeX) {
            for (y in 0 until grid.sizeY) {
                val r = Room(contextManager, 5, 5, this)
                grid.setCoordinate(Coordinate(x, y), r)
            }
        }
    }

    override fun toString(): String {
        return "World (%sx%s)".format(grid.sizeX, grid.sizeY)
    }

    override fun whereAt(): String {
        return "World level"
    }

    /**
     * Returns a print friendly version of the world.
     */
    fun toDisplayString(): String {
        return grid.toDisplayString()
    }

    companion object {

        private val LOG = org.slf4j.LoggerFactory.getLogger(World::class.java)
    }
}
