package com.josephbaca.world

import com.josephbaca.rpggame.ContextManager

/**
 * Contains all the geography about the world the player is in. Specifically,
 * it contains the coordinates of every [Tile] and the current location
 * of the player.
 */
class World
(private val name: String, maxx: Int, maxy: Int, private val contextManager: ContextManager) {

    /**
     * Internal representation of the world.
     */
    private val grid: CoordinateGrid<Room> = CoordinateGrid(maxx, maxy)

    /**
     * Player coordinates.
     */
    private var playerCoords: Coordinate = Coordinate.of(0, 0)
        set(value) {
            if (value.x >= 0 && value.x < grid.sizeX() && value.y >= 0 && value.y < grid.sizeY()) {
                LOG.info("Player moving from (%s, %s) to (%s, %s)"
                        .format(playerCoords.x, playerCoords.y, value.x, value.y))
                field = value
                contextManager.replaceContextLayer(currentRoom)
            } else {
                LOG.info("Player tried to walk off the world of size (%s, %s).".format(grid.sizeX(), grid.sizeY()))
            }
        }

    private val currentRoom: Room
        get() = grid.getCoordinate(playerCoords)

    init {
        generateWorld()
        contextManager.addContextLayer(currentRoom) // Add current room to contextManager
    }

    /**
     * Move the player up one unit.
     */
    fun movePlayerUp() {
        playerCoords = Coordinate.of(playerCoords.x, playerCoords.y + 1)
    }

    /**
     * Move the player down one unit.
     */
    fun movePlayerDown() {
        playerCoords = Coordinate.of(playerCoords.x, playerCoords.y - 1)
    }

    /**
     * Move the player right one unit.
     */
    fun movePlayerRight() {
        playerCoords = Coordinate.of(playerCoords.x + 1, playerCoords.y)
    }

    /**
     * Move the player left one unit.
     */
    fun movePlayerLeft() {
        playerCoords = Coordinate.of(playerCoords.x - 1, playerCoords.y)
    }

    /**
     * World generator function.
     */
    private fun generateWorld() {
        for (x in 0 until grid.sizeX()) {
            for (y in 0 until grid.sizeY()) {
                val r = Room(5, 5, this)
                grid.setCoordinate(Coordinate.of(x, y), r)
            }
        }
    }

    override fun toString(): String {
        return grid.toString()
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
